package com.itau.operacaobancaria.dto;

import lombok.Data;

@Data
public class PostTransferenciaIn {

	private String idCliente;
	private String codigoChavePixDestino;
	private String idConta;
	private String valorTransferencia;

}
