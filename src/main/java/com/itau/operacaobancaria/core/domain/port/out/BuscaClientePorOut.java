package com.itau.operacaobancaria.core.domain.port.out;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.operacaobancaria.core.domain.model.Cliente;

public interface BuscaClientePorOut {
    Cliente buscaCliente(String idCliente);

}
