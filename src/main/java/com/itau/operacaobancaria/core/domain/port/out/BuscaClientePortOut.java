package com.itau.operacaobancaria.core.domain.port.out;


import com.itau.operacaobancaria.core.domain.model.Cliente;

public interface BuscaClientePortOut {
    Cliente buscaCliente(String idCliente);

}
