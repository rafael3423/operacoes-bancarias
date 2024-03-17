package com.itau.operacaobancaria.core.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder
public class Cliente {

    private String idCliente;
    private String cpfCnpj;
    private String nomeSocial;
    private String nomeCompleto;

}
