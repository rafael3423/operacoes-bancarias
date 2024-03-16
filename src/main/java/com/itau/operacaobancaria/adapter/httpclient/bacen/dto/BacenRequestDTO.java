package com.itau.operacaobancaria.adapter.httpclient.bacen.dto;

import lombok.Data;

@Data
public class BacenRequestDTO {

    String idCliente;
    String cpfOrigem;
    String valorTransferencia;
    String codigoChavePixDestino;
    String dataHoraTransferencia;
}
