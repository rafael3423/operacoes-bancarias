package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

public class ContaNaoEncontradaException extends RuntimeException{
    public ContaNaoEncontradaException(String message) {
        super(message);
    }
}
