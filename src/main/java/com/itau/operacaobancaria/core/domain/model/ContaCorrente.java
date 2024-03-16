package com.itau.operacaobancaria.core.domain.model;

import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.ContaInativaException;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.ContaNaoEncontradaException;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.LimiteDiarioInsuficienteException;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.SaldoInsuficienteException;
import io.micrometer.common.util.StringUtils;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Slf4j
@Builder
public class ContaCorrente {

    private String idCliente;
    private BigDecimal saldoContaCorrente;
    private BigDecimal valorLimiteDiario;
    private BigDecimal valorLimiteDiarioAjustado;
    private LocalDate dataUltimaTransferencia;
    private List<Conta> contas;

    @Data
    @Builder
    public static class Conta {
        private String idConta;
        private String tipoConta;
        private String codigoEmpresa;
        private String codigoBanco;
        private String codigoAgencia;
        private String codigoConta;
        private String dac;
        private String codigoFaseEncerramento;
        private String codigoContaInativa;
        private String codigoBloqueioMovimento;
        private String codigoBloqueioJudicial;

    }

    public void validarContaInativaOuInexistente(String idConta) {
        this.getContas().stream()
                .filter(contas -> contas.getIdConta().equals(idConta))
                .findFirst().ifPresentOrElse(conta -> {
                    if (StringUtils.isNotBlank(conta.getCodigoFaseEncerramento())
                            || StringUtils.isNotBlank(conta.getCodigoContaInativa())
                            || StringUtils.isNotBlank(conta.getCodigoBloqueioMovimento())
                            || StringUtils.isNotBlank(conta.getCodigoBloqueioJudicial())) {
                        log.error("Conta informada está inativa, bloqueada, ou em processo de encerramento - id_conta: {}", conta.getIdConta());
                        throw new ContaInativaException("Conta informada está inativa, bloqueada, ou em processo de encerramento.");
                    }
                }, () -> {
                    throw new ContaNaoEncontradaException("Conta informada não encontrada ao cliente.");
                });
    }


    public void redefinirLimiteDiario() {
        if (this.getDataUltimaTransferencia().
                isBefore(LocalDate.now())) {
            log.info("Redefinindo limite diário para o valor ajustado.");
            this.setValorLimiteDiario(this.getValorLimiteDiarioAjustado());
        }
    }
    public void validarLimites(BigDecimal valorTransferencia, Transferencia transferencia) {
        if (this.getSaldoContaCorrente().compareTo(valorTransferencia) < 0) {
            log.error("Conta sem saldo para realizar transferência. idCliente: {}", this.getIdCliente());
            throw new SaldoInsuficienteException("Conta sem saldo para realizar transferência.");
        }
        if (this.getValorLimiteDiario().compareTo(
                valorTransferencia) < 0) {
            log.error("Limite diário atingido para realizar transferência. idCliente: {}", this.getIdCliente());
            throw new LimiteDiarioInsuficienteException("Limite diário atingido para realizar transferência.");
        }
        debitarValorTransferencia(valorTransferencia, transferencia);
    }

    private void debitarValorTransferencia(BigDecimal valorTransferencia, Transferencia transferencia) {
        this.setSaldoContaCorrente(this.getSaldoContaCorrente()
                .subtract(valorTransferencia));

        this.setValorLimiteDiario(this.getValorLimiteDiario()
                .subtract(valorTransferencia));

        this.setDataUltimaTransferencia(LocalDate.now());
        transferencia.setDataHoraTransferencia(LocalDateTime.now().toString());
    }
}
