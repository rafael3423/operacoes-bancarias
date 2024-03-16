package com.itau.operacaobancaria.adapter.web;

import com.itau.operacaobancaria.adapter.web.request.TransferenciaRequestDTO;
import com.itau.operacaobancaria.adapter.web.response.TransferenciaResponseDTO;
import com.itau.operacaobancaria.core.domain.mapper.TransferenciaMapper;
import com.itau.operacaobancaria.core.domain.port.in.TransferenciaPortIn;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/transferencia")
public class TransferenciaController {
    private final TransferenciaPortIn transferenciaPortIn;
    private final TransferenciaMapper transferenciaMapper = Mappers.getMapper(TransferenciaMapper.class);

    public TransferenciaController(TransferenciaPortIn transferenciaPortIn) {
        this.transferenciaPortIn = transferenciaPortIn;
    }

    @PostMapping("/transferir")
    ResponseEntity<TransferenciaResponseDTO> transferir(@RequestBody @Valid TransferenciaRequestDTO transferenciaRequestDTO) throws Exception {

        log.info("Iniciando transferencia para cliente: {}", transferenciaRequestDTO.getIdCliente());
        var transferencia = transferenciaPortIn.execute(transferenciaMapper.toUseCase(transferenciaRequestDTO));

        log.info("Transferência realizada para o cliente: {}", transferenciaRequestDTO.getIdCliente());
        return new ResponseEntity<>(transferencia, HttpStatus.ACCEPTED);
    }


}
