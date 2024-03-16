package com.itau.operacaobancaria.core.domain.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.operacaobancaria.adapter.web.request.TransferenciaRequestDTO;
import com.itau.operacaobancaria.adapter.web.response.TransferenciaResponseDTO;
import com.itau.operacaobancaria.core.domain.model.Cliente;
import com.itau.operacaobancaria.core.domain.model.ContaCorrente;
import com.itau.operacaobancaria.core.domain.model.Transferencia;
import com.itau.operacaobancaria.core.domain.port.out.NotificaBacenPortOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {
    Transferencia toUseCase(TransferenciaRequestDTO transferenciaRequestDTO);


    @Mappings({
            @Mapping(target = "idCliente", source = "transferencia.idCliente"),
            @Mapping(target = "saldoContaCorrente", source = "contaCorrente.saldoContaCorrente"),
            @Mapping(target = "codigoChavePixDestino", source = "transferencia.codigoChavePixDestino"),
            @Mapping(target = "nome", source = "cliente.nomeSocial"),
            @Mapping(target = "valorTransferencia", source = "transferencia.valorTransferencia"),
    })
    TransferenciaResponseDTO toSaida(Transferencia transferencia, ContaCorrente contaCorrente, Cliente cliente);

}
