package com.itau.operacaobancaria.adapter.exception;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class InternalIntegrationExceptionTest {

    @Test
    @DisplayName("Testar lancamento de exception.")
    public void testeInternalIntegrationException() {
        String messageError = "Erro de integracao";
        String message = Assertions.assertThrows(InternalIntegrationException.class, () -> {
            throw new InternalIntegrationException("Erro de integracao");
        }).getMessage();
        assertEquals(messageError, message);
    }


}
