package com.itau.operacaobancaria.core.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
@Builder
public class Transferencia {
    private String idCliente;
    private String idConta;
    private String codigoChavePixDestino;
    private String dataHoraTransferencia;
    private BigDecimal valorTransferencia;

}
