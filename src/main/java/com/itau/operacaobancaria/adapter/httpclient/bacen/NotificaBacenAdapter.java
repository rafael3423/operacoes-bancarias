package com.itau.operacaobancaria.adapter.httpclient.bacen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.operacaobancaria.adapter.httpclient.converter.ObjectMapperConverter;
import com.itau.operacaobancaria.adapter.infra.seguranca.StsTokenImpl;
import com.itau.operacaobancaria.core.domain.mapper.BacenMapper;
import com.itau.operacaobancaria.core.domain.model.Bacen;
import com.itau.operacaobancaria.core.domain.model.Transferencia;
import com.itau.operacaobancaria.core.domain.port.out.NotificaBacenPortOut;
import com.itau.operacaobancaria.core.domain.port.out.TransferenciaCallBackPotOut;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.CallBackException;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.FallBackException;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.NotificacaoBacenException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificaBacenAdapter implements NotificaBacenPortOut {

    private final BacenClient client;
    private final StsTokenImpl stsTokenClient;
    private final BacenMapper bacenMapper;
    private final String BEARER = "Bearer ";
    @Value("${sts.token-generation.client-id}")
    private String xItauApiKey;
    @Value("${api.client.cliente.gtw-id:null}")
    private String xApigwId;
    private int attemptNotificarBacen = 1;

    @CircuitBreaker(name = "integrationFallBack", fallbackMethod = "retornaMensagemFallBack")
    public void notificaBacen(Entrada entrada) throws InterruptedException, NotificacaoBacenException {
        final var authorizationToken = BEARER + stsTokenClient.getToken();

        log.info("Token de autenticacao gerado para notificar bacen sobre transferência: {}", authorizationToken);

        String correlationId = UUID.randomUUID().toString();
        log.info("Notificando bacen para o cliente: {}", entrada);

        try {
            var response = client.notificarBacen(
                    authorizationToken,
                    xItauApiKey,
                    correlationId,
                    correlationId,
                    bacenMapper.toRequestDTO(entrada));

            if (response.getStatusCode().value() == 429) {
                attemptNotificarBacen++;
                if (attemptNotificarBacen == 4) {
                    throw new NotificacaoBacenException("Bacen com muitos acessos recorrentes, enviando evento para callback");
                }
                log.info("Bacen com rate limite atingido, tentando notificação pela {} vez.", attemptNotificarBacen);
                Thread.sleep(500);
                notificaBacen(entrada);
            }

        } catch (Exception e) {
            log.error("Erro ao notificar bacen de transferência efetuada: {}", e.getMessage());
            throw e;
        }
    }
    void retornaMensagemFallBack() {
        log.error("Muitas falhas para notificar bacen sobre transferência, desativando transação para API por 5 segundos.");
        throw new FallBackException("API temporariamente indisponível..");
    }
}
