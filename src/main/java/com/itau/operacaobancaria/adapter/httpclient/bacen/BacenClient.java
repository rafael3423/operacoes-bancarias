package com.itau.operacaobancaria.adapter.httpclient.bacen;

import com.itau.operacaobancaria.adapter.httpclient.bacen.dto.BacenRequestDTO;
import com.itau.operacaobancaria.adapter.httpclient.bacen.dto.BacenResponseDTO;
import com.itau.operacaobancaria.adapter.httpclient.feign.FeignConverterConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@FeignClient(value = "bacenClient",
        url = "${api.client.bacen.url}",
        configuration = FeignConverterConfiguration.class,
        primary = false)
public interface BacenClient {

    @GetMapping
    ResponseEntity<BacenResponseDTO> notificarBacen(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "X-itau-apiKey") String xItauApiKey,
            @RequestHeader(value = "X-itau-correlationId") String correlationId,
            @RequestHeader(value = "X-itau-flowID") String flowID,
            @RequestBody BacenRequestDTO bacenRequestDTO);

}

@Slf4j
@Primary
@Component
@ConditionalOnProperty(value = "feature-toggle.bacen.mock", havingValue = "true")
class BacenClientMock implements BacenClient {

    @Override
    public ResponseEntity<BacenResponseDTO> notificarBacen(String authorization, String xItauApiKey, String correlationId, String flowID, BacenRequestDTO bacenRequestDTO) {
        var bacenResponse = BacenResponseDTO.builder()
                .chavePixDestino(UUID.randomUUID().toString())
                .cpfCliente("34879027880")
                .dataHoraNotificacao(LocalDateTime.now().toString())
                .mensagemNotificacao("Notificação sobre transferêñcia registrada!")
                .build();
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}

