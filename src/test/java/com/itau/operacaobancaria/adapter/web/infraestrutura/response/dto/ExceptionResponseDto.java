package com.itau.operacaobancaria.adapter.web.infraestrutura.response.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ExceptionResponseDto implements Serializable {
    private static final long serialVersion = 1L;

    private Integer codigo;
    private String mensagem;
    private String codigoErroInterno;
}
