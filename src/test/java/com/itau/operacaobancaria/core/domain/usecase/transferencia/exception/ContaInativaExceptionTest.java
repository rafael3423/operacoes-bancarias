package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ContaInativaExceptionTest {

    @Test
    @DisplayName("Testar lancamento de exception ContaInativaException.")
    public void testeContaInativaException() {
        String messageError = "Erro de integracao";
        String message = Assertions.assertThrows(ContaInativaException.class, () -> {
            throw new ContaInativaException("Erro de integracao");
        }).getMessage();
        assertEquals(messageError, message);
    }


}
