package com.itau.operacaobancaria.core.domain.mapper;

import com.itau.operacaobancaria.adapter.httpclient.bacen.dto.BacenRequestDTO;
import com.itau.operacaobancaria.adapter.httpclient.bacen.dto.BacenResponseDTO;
import com.itau.operacaobancaria.adapter.httpclient.cliente.dto.EQ3ResponseDTO;
import com.itau.operacaobancaria.core.domain.model.Bacen;
import com.itau.operacaobancaria.core.domain.model.Cliente;
import com.itau.operacaobancaria.core.domain.model.Transferencia;
import com.itau.operacaobancaria.core.domain.port.out.NotificaBacenPortOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface BacenMapper {
    Bacen toModel(BacenResponseDTO bacenResponseDTO);


    @Mappings({
            @Mapping(target = "idCliente", source = "transferencia.idCliente"),
            @Mapping(target = "cpfOrigem", source = "cliente.cpfCnpj"),
            @Mapping(target = "valorTransferencia", source = "transferencia.valorTransferencia", qualifiedByName = "getValorTrasnferencia"),
            @Mapping(target = "dataHoraTransferencia", source = "transferencia.dataHoraTransferencia"),
            @Mapping(target = "codigoChavePixDestino", source = "transferencia.codigoChavePixDestino")})
    NotificaBacenPortOut.Entrada toAdapter(Transferencia transferencia, Cliente cliente);
    BacenRequestDTO toRequestDTO(NotificaBacenPortOut.Entrada entrada);

    @Named("getValorTrasnferencia")
    default String getValorTrasnferencia(BigDecimal valor){
        return valor.toString();
    }
    @Named("getDataHoraTransferencia")
    default String getDataHoraTransferencia(){
        return LocalDateTime.now().toString();
    }
}