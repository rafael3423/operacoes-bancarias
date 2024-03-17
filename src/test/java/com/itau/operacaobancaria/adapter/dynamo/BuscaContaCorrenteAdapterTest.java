package com.itau.operacaobancaria.adapter.dynamo;

import com.itau.operacaobancaria.core.domain.model.ContaCorrente;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class BuscaContaCorrenteAdapterTest {

    @InjectMocks
    private BuscaContaCorrenteAdapter buscaContaCorrenteAdapter;


    @Test
    @DisplayName("Testar busca de conta corrente no  banco de dados.")
    public void testeBuscarcontaCorrente() {
        buscaContaCorrenteAdapter.buscarConta(UUID.randomUUID().toString());
    }


    @Test
    @DisplayName("Testar salvar conta corrente no  banco de dados.")
    public void testeSalvarContaCorrente() {
        buscaContaCorrenteAdapter.salvarConta(ContaCorrente.builder().build());
    }

}
