package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

public class SaldoInsuficienteException extends RuntimeException{
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
