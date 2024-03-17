package com.itau.operacaobancaria.core.domain.usecase.transferencia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.operacaobancaria.core.domain.mapper.BacenMapper;
import com.itau.operacaobancaria.core.domain.mapper.TransferenciaMapper;
import com.itau.operacaobancaria.core.domain.model.Cliente;
import com.itau.operacaobancaria.core.domain.model.ContaCorrente;
import com.itau.operacaobancaria.core.domain.model.Transferencia;
import com.itau.operacaobancaria.core.domain.port.out.BuscaClientePortOut;
import com.itau.operacaobancaria.core.domain.port.out.ContaCorrentePortOut;
import com.itau.operacaobancaria.core.domain.port.out.NotificaBacenPortOut;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.ContaInativaException;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.LimiteDiarioInsuficienteException;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.SaldoInsuficienteException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TransferirUseCaseTest {

    @Mock
    private ContaCorrentePortOut contaCorrentePortOut;
    @Mock
    private BuscaClientePortOut buscaClientePortOut;

    @Mock
    private NotificaBacenPortOut notificaBacenPortOut;

    @Spy
    private TransferenciaMapper transferenciaMapper;

    @Spy
    private BacenMapper bacenMapper;
    @InjectMocks
    private TransferirUsecase transferirUsecase;

    @Test
    public void testeTrransferencia() throws InterruptedException, JsonProcessingException {

       var contaCorrente = buildContaCorrente();

        doReturn(contaCorrente)
                .when(contaCorrentePortOut).buscarConta(anyString());

        doReturn(Cliente.builder().idCliente("1234-1234").build())
                .when(buscaClientePortOut).buscaCliente(anyString());

        doNothing().when(notificaBacenPortOut).notificaBacen(any());

        var transferencia = Transferencia.builder()
                .idCliente("1234-1234")
                .idConta("1234-1234")
                .valorTransferencia(new BigDecimal("200"))
                .codigoChavePixDestino("1234-1234")
                .build();

        doNothing().when(contaCorrentePortOut).salvarConta(any(ContaCorrente.class));
        transferirUsecase.execute(transferencia);

        assertEquals(new BigDecimal("1000"), contaCorrente.getSaldoContaCorrente());
    }

    @Test
    public void testeLancarContaInantivaException() throws InterruptedException, JsonProcessingException {

        var contaCorrente = buildContaCorrente();
        contaCorrente.getContas().get(0).setCodigoContaInativa(UUID.randomUUID()
                .toString());

        doReturn(contaCorrente)
                .when(contaCorrentePortOut).buscarConta(anyString());

        doReturn(Cliente.builder().idCliente("1234-1234").build())
                .when(buscaClientePortOut).buscaCliente(anyString());

        Assertions.assertThrows(ContaInativaException.class, () -> transferirUsecase.execute(Transferencia.builder()
                .idCliente("1234-1234")
                .idConta("1234-1234")
                .valorTransferencia(new BigDecimal("200"))
                .codigoChavePixDestino("1234-1234")
                .build()));

    }

    @Test
    public void testeLancarSaldoInsuficienteException() throws InterruptedException, JsonProcessingException {

        var contaCorrente = buildContaCorrente();
        contaCorrente.setSaldoContaCorrente(new BigDecimal("180"));

        doReturn(contaCorrente)
                .when(contaCorrentePortOut).buscarConta(anyString());

        doReturn(Cliente.builder().idCliente("1234-1234").build())
                .when(buscaClientePortOut).buscaCliente(anyString());

        Assertions.assertThrows(SaldoInsuficienteException.class, () -> transferirUsecase.execute(Transferencia.builder()
                .idCliente("1234-1234")
                .idConta("1234-1234")
                .valorTransferencia(new BigDecimal("200"))
                .codigoChavePixDestino("1234-1234")
                .build()));

    }

    @Test
    public void testeLancarLimiteDiarioInsuficienteException() throws InterruptedException, JsonProcessingException {

        var contaCorrente = buildContaCorrente();
        contaCorrente.setDataUltimaTransferencia(LocalDate.now());
        contaCorrente.setValorLimiteDiario(new BigDecimal("80"));

        doReturn(contaCorrente)
                .when(contaCorrentePortOut).buscarConta(anyString());

        doReturn(Cliente.builder().idCliente("1234-1234").build())
                .when(buscaClientePortOut).buscaCliente(anyString());

        Assertions.assertThrows(LimiteDiarioInsuficienteException.class, () -> transferirUsecase.execute(Transferencia.builder()
                .idCliente("1234-1234")
                .idConta("1234-1234")
                .valorTransferencia(new BigDecimal("200"))
                .codigoChavePixDestino("1234-1234")
                .build()));

    }


    ContaCorrente buildContaCorrente(){
        return ContaCorrente.builder()
                .saldoContaCorrente(new BigDecimal("1200"))
                .valorLimiteDiario(new BigDecimal("150"))
                .valorLimiteDiarioAjustado(new BigDecimal("1000"))
                .contas(Collections.singletonList(ContaCorrente.Conta.builder().idConta("1234-1234")
                        .build()))
                .dataUltimaTransferencia(LocalDate.of(2024, Month.MARCH, 15))
                .build();
    }

}
