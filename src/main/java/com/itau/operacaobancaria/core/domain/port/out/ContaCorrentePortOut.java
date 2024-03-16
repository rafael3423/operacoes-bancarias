package com.itau.operacaobancaria.core.domain.port.out;
import com.itau.operacaobancaria.core.domain.model.ContaCorrente;

public interface ContaCorrentePortOut {
    ContaCorrente buscarConta(String idConta);
    void salvarConta(ContaCorrente contaCorrente);

}
