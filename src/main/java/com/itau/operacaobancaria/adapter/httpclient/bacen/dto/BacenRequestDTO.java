package com.itau.operacaobancaria.adapter.httpclient.bacen.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BacenRequestDTO {

    public String idCliente;
    public String cpfOrigem;
    public String valorTransferencia;
    public String codigoChavePixDestino;
    public String dataHoraTransferencia;
}
