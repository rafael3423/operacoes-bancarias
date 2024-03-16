package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

public class ContaInativaException extends RuntimeException{
    public ContaInativaException(String message) {
        super(message);
    }
}
