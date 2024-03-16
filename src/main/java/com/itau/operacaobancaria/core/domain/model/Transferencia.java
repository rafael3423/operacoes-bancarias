package com.itau.operacaobancaria.core.domain.model;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
public class Transferencia {
    private String idCliente;
    private String idConta;
    private String codigoChavePixDestino;
    private String dataHoraTransferencia;
    private BigDecimal valorTransferencia;

}
