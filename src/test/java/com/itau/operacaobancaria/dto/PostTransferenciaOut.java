package com.itau.operacaobancaria.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PostTransferenciaOut {

	private String idCliente;
	private String nome;
	private BigDecimal saldoContaCorrente;
	private String codigoChavePixDestino;
	private BigDecimal valorTransferencia;
	private String dataHoraTransferencia;

}
