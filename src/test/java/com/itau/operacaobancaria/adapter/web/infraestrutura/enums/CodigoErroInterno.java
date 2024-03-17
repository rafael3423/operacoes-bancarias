package com.itau.operacaobancaria.adapter.web.infraestrutura.enums;

import lombok.Getter;

@Getter
public enum CodigoErroInterno {

//    0000 a 0999, erros de negócio
    ERRO_CAMPO_INVALIDO("0000", "Campo preenchido incorretamente"),
    ERRO_CONTA_INATIVA("0010", "Conta informada está inativa ou em processo de encerramento"),
    ERRO_CONTA_NAO_ENCONTRADA("0020", "Conta informada não encontrada ao cliente."),
    ERRO_SALDO_INSUFICIENTE("0030", "Conta sem saldo para realizar transferência."),
    ERRO_LIMITE_DIARIO_INSUFICIENTE("0040", "Limite diário atingido para realizar transferência."),

// 1000 a 1999, erros de integracao
    ERRO_RATE_LIMIT_BACEN("1000", "Numeros excessivos para notificacao ao bacen, reprocessando novas tentativas."),
    ERRO_DEPENCIA_EXTERNA("1010", "Erro em alguma dependência externa para a transferência, temporariamente indisponível.");


    private final String code;

    private final String message;

    CodigoErroInterno(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
