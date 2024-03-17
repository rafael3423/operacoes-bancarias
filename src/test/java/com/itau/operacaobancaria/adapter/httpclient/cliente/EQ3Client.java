package com.itau.operacaobancaria.adapter.httpclient.cliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.operacaobancaria.adapter.httpclient.cliente.dto.EQ3ResponseDTO;
import com.itau.operacaobancaria.adapter.httpclient.feign.FeignConverterConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Collections;
import java.util.Optional;

@FeignClient(value = "eq3Client",
        url = "${api.client.eq3.url}",
        configuration = FeignConverterConfiguration.class,
        primary = false)
public interface EQ3Client {

    @GetMapping("/dados_cadastrais/v1/inquilinos/{id_inquilino}/clientes/{id_cliente}")
    ResponseEntity<EQ3ResponseDTO> buscarCliente(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "X-itau-apiKey") String xItauApiKey,
            @RequestHeader(value = "X-itau-correlationId") String correlationId,
            @RequestHeader(value = "X-itau-flowID") String flowID,
            @PathVariable("id_cliente") String idCliente,
            @PathVariable("id_inquilino") String idInquilino
    ) throws JsonProcessingException;

}

@Slf4j
@Primary
@Component
@ConditionalOnProperty(value = "feature-toggle.eq3.mock", havingValue = "true")
class EQ3ClientMock implements EQ3Client {

    @Override
    public ResponseEntity<EQ3ResponseDTO> buscarCliente(String authorization, String xItauApiKey, String correlationId, String flowID, String idCliente,  String idInquilino) {
       var eq3Response =  EQ3ResponseDTO.builder()
                .idCliente("9831b57d-6460-4d28-ac70-3e8e9b0779a1")
                .cpfCnpj("34879027880")
                .nomeSocial("Rafael Marcos Batista dos Santos")
                .contas(Collections.singletonList(EQ3ResponseDTO.Conta.builder()
                        .dac("9")
                        .codigoAgencia("3392")
                        .idConta("6ebcf820-f499-4a5a-82e5-2422270f56d7")
                        .codigoBanco("341")
                        .codigoConta("19482")
                        .codigoContaInativa("")
                        .codigoFaseEncerramento("")
                        .tipoConta("C")
                        .build())).build();
        return ResponseEntity.of(Optional.of(eq3Response));
    }
}

