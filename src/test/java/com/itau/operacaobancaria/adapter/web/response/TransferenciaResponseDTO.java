package com.itau.operacaobancaria.adapter.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferenciaResponseDTO {

    @JsonProperty("id_cliente")
    private String idCliente;

    private String nome;

    @JsonProperty("saldo_conta_corrente")
    private BigDecimal saldoContaCorrente;

    @JsonProperty("codigo_chave_pix_destino")
    private String codigoChavePixDestino;

    @JsonProperty("valor_transferencia")
    private BigDecimal valorTransferencia;

    @JsonProperty("data_hora_transferencia")
    private String dataHoraTransferencia;

}
