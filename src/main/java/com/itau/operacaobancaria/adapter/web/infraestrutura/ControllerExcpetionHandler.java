package com.itau.operacaobancaria.adapter.web.infraestrutura;

import com.itau.operacaobancaria.adapter.web.infraestrutura.enums.CodigoErroInterno;
import com.itau.operacaobancaria.adapter.web.infraestrutura.response.dto.ExceptionResponseDto;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExcpetionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContaInativaException.class)
    public ResponseEntity<ExceptionResponseDto> handleContainativaExcpetion(ContaInativaException e) {
        var body = new ExceptionResponseDto(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(),
                CodigoErroInterno.ERRO_CONTA_INATIVA.getCode());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(body);
    }

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<ExceptionResponseDto> handleContaNaoEncontrada(ContaNaoEncontradaException e) {
        var body = new ExceptionResponseDto(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(),
                CodigoErroInterno.ERRO_CONTA_NAO_ENCONTRADA.getCode());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(body);
    }
    @ExceptionHandler(LimiteDiarioInsuficienteException.class)
    public ResponseEntity<ExceptionResponseDto> handleContaNaoEncontrada(LimiteDiarioInsuficienteException e) {
        var body = new ExceptionResponseDto(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(),
                CodigoErroInterno.ERRO_LIMITE_DIARIO_INSUFICIENTE.getCode());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(body);
    }
    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ExceptionResponseDto> handleContaNaoEncontrada(SaldoInsuficienteException e) {
        var body = new ExceptionResponseDto(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(),
                CodigoErroInterno.ERRO_SALDO_INSUFICIENTE.getCode());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(body);
    }
    @ExceptionHandler(CallBackException.class)
    public ResponseEntity<ExceptionResponseDto> handleCallBack(SaldoInsuficienteException e) {
        var body = new ExceptionResponseDto(HttpStatus.PROCESSING.value(),
                e.getMessage(),
                CodigoErroInterno.ERRO_DEPENCIA_EXTERNA.getCode());

        return ResponseEntity.status(HttpStatus.PROCESSING)
                .body(body);
    }
    @ExceptionHandler(CallBackException.class)
    public ResponseEntity<ExceptionResponseDto> handleCallBack(CallBackException e) {
        var body = new ExceptionResponseDto(HttpStatus.PROCESSING.value(),
                e.getMessage(),
                CodigoErroInterno.ERRO_DEPENCIA_EXTERNA.getCode());

        return ResponseEntity.status(HttpStatus.PROCESSING)
                .body(body);
    }
    @ExceptionHandler(FallBackException.class)
    public ResponseEntity<ExceptionResponseDto> handleFallBack(FallBackException e) {
        var body = new ExceptionResponseDto(HttpStatus.SERVICE_UNAVAILABLE.value(),
                e.getMessage(),
                CodigoErroInterno.ERRO_DEPENCIA_EXTERNA.getCode());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(body);
    }

    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        var body = new ExceptionResponseDto(status.value(),
                ex.getMessage(),
                CodigoErroInterno.ERRO_CAMPO_INVALIDO.getCode());

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
