package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

public class CallBackException extends RuntimeException{
    public CallBackException(String message) {
        super(message);
    }
}
