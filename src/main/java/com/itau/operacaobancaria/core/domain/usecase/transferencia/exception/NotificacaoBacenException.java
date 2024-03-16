package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

public class NotificacaoBacenException extends RuntimeException{
    public NotificacaoBacenException(String message) {
        super(message);
    }
}
