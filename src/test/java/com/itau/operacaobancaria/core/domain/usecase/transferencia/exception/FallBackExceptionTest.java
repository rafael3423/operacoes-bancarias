package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.FallBackException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FallBackExceptionTest {

    @Test
    @DisplayName("Testar lancamento de exception FallBackException.")
    public void testeFallBackException() {
        String messageError = "Erro de integracao";
        String message = Assertions.assertThrows(FallBackException.class, () -> {
            throw new FallBackException("Erro de integracao");
        }).getMessage();
        assertEquals(messageError, message);
    }


}
