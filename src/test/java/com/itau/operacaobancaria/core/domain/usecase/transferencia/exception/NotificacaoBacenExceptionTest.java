package com.itau.operacaobancaria.core.domain.usecase.transferencia.exception;

import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.NotificacaoBacenException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class NotificacaoBacenExceptionTest {

    @Test
    @DisplayName("Testar lancamento de exception NotificacaoBacenException.")
    public void testeFallBackException() {
        String messageError = "Erro de integracao";
        String message = Assertions.assertThrows(NotificacaoBacenException.class, () -> {
            throw new NotificacaoBacenException("Erro de integracao");
        }).getMessage();
        assertEquals(messageError, message);
    }


}
