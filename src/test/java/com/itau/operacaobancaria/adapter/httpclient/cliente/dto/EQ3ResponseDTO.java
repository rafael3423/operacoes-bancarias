package com.itau.operacaobancaria.adapter.httpclient.cliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class EQ3ResponseDTO {

    @JsonProperty("id_cliente")
    private String idCliente;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("nome_social")
    private String nomeSocial;
    @JsonProperty("contas")
    private List<Conta> contas;

    @Data
    @Builder
    public static class Conta {
        @JsonProperty("id_conta")
        private String idConta;
        @JsonProperty("tipo_conta")
        private String tipoConta;
        @JsonProperty("codigo_empresa")
        private String codigoEmpresa;
        @JsonProperty("codigo_banco")
        private String codigoBanco;
        @JsonProperty("codigo_agencia")
        private String codigoAgencia;
        @JsonProperty("codigo_conta")
        private String codigoConta;
        @JsonProperty("dac")
        private String dac;
        @JsonProperty("codigo_fase_encerramento")
        private String codigoFaseEncerramento;
        @JsonProperty("codigo_conta_inativa")
        private String codigoContaInativa;

    }
}


