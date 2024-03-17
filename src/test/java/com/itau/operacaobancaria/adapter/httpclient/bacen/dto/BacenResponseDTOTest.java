package com.itau.operacaobancaria.adapter.httpclient.bacen.dto;

import lombok.Builder;

@Builder
public class BacenResponseDTOTest {

    private String mensagemNotificacao;
    private String cpfCliente;
    private String chavePixDestino;
    private String dataHoraNotificacao;

}
