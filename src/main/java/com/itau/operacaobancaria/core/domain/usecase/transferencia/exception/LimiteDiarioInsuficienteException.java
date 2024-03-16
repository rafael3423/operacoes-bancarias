package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

public class LimiteDiarioInsuficienteException extends RuntimeException{
    public LimiteDiarioInsuficienteException(String message) {
        super(message);
    }
}
