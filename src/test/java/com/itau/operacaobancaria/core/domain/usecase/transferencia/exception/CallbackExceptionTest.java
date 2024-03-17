package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.CallBackException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CallbackExceptionTest {

    @Test
    @DisplayName("Testar lancamento de exception.")
    public void testeCallBackException() {
        String messageError = "Erro de integracao";
        String message = Assertions.assertThrows(CallBackException.class, () -> {
            throw new CallBackException("Erro de integracao");
        }).getMessage();
        assertEquals(messageError, message);
    }


}
