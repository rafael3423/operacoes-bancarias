package com.itau.operacaobancaria.core.domain.mapper;

import com.itau.operacaobancaria.adapter.httpclient.cliente.dto.EQ3ResponseDTO;
import com.itau.operacaobancaria.core.domain.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EQ3Mapper {
    Cliente toModel(EQ3ResponseDTO eq3ResponseDTO);
}
