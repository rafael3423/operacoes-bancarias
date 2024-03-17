package com.itau.operacaobancaria.adapter.httpclient.cliente;

import com.itau.operacaobancaria.adapter.httpclient.converter.ObjectMapperConverter;
import com.itau.operacaobancaria.adapter.infra.seguranca.StsTokenImpl;
import com.itau.operacaobancaria.core.domain.mapper.EQ3Mapper;
import com.itau.operacaobancaria.core.domain.model.Cliente;
import com.itau.operacaobancaria.core.domain.port.out.BuscaClientePortOut;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.FallBackException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteAdapter implements BuscaClientePortOut {

    private final EQ3Client client;
    private final StsTokenImpl stsTokenClient;
    private final EQ3Mapper eq3Mapper;
    private final ObjectMapperConverter objectMapperConverter;
    private final String BEARER = "Bearer ";
    @Value("${sts.token-generation.client-id}")
    private String xItauApiKey;
    @Value("${api.client.eq3.gtw-id:null}")
    private String xApigwId;
    @Value("${api.client.eq3.inquilino}")
    private String idInquilino;

    @Override
    @SneakyThrows
    public Cliente buscaCliente(String idCliente) {
        final var authorizationToken = BEARER + stsTokenClient.getToken();

        log.info("Token de autenticacao gerado para busca do cliente: {}", authorizationToken);

        String correlationId = UUID.randomUUID().toString();
        log.info("Busca de cliente pelo ID: {}, correlation da chamada: {}", idCliente, correlationId);

        try {
            var response = client.buscarCliente(
                    authorizationToken,
                    xItauApiKey,
                    correlationId,
                    correlationId,
                    idCliente,
                    idInquilino);

            return eq3Mapper.toModel(response.getBody());
        } catch (Exception e) {
            log.error("Erro na busca do cliente no eq3: {}", e.getMessage());
            throw e;
        }
    }

    void retornaMensagemFallBack() {
        log.error("Muitas falhas para buscar cliente na API eq3, desativando transação para API por 5 segundos.");
        throw new FallBackException("API temporariamente indisponível..");
    }
}
