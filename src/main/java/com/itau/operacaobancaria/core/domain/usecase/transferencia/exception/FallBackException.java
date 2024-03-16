package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

public class FallBackException extends RuntimeException{
    public FallBackException(String message) {
        super(message);
    }
}
