package com.itau.operacaobancaria.adapter.dynamo;
import com.itau.operacaobancaria.core.domain.model.ContaCorrente;
import com.itau.operacaobancaria.core.domain.port.out.ContaCorrentePortOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;

@Component
@Slf4j
@RequiredArgsConstructor
public class BuscaContaCorrenteAdapter implements ContaCorrentePortOut {

    @Override
    public ContaCorrente buscarConta(String idConta) {
        log.info("buscando dados da conta corrente: {}", idConta);
        return ContaCorrente
                .builder()
                .dataUltimaTransferencia(LocalDate.of(2024, Month.MARCH, 14))
                .idCliente("6ebcf820-f499-4a5a-82e5-2422270f56d7")
                .saldoContaCorrente(new BigDecimal("1500.00"))
                .valorLimiteDiario(new BigDecimal("200.00"))
                .valorLimiteDiarioAjustado(new BigDecimal("1000.00"))
                .contas(Collections.singletonList(ContaCorrente.Conta.builder()
                        .dac("9")
                        .codigoAgencia("3392")
                        .idConta("6ebcf820-f499-4a5a-82e5-2422270f56d7")
                        .codigoBanco("341")
                        .codigoConta("19482")
                        .codigoContaInativa("")
                        .codigoFaseEncerramento("")
                        .codigoBloqueioMovimento("")
                        .codigoBloqueioJudicial("")
                        .tipoConta("C")
                .build())).build();
    }

    @Override
    public void salvarConta(ContaCorrente contaCorrente) {
        log.info("salvando dados debitados para conta corrente do cliente: {}", contaCorrente.getIdCliente());
//        repository.save();
    }
}
