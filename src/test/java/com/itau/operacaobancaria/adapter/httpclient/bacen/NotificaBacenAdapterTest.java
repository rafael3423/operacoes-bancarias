package com.itau.operacaobancaria.adapter.httpclient.bacen;

import com.itau.operacaobancaria.adapter.httpclient.bacen.dto.BacenRequestDTO;
import com.itau.operacaobancaria.adapter.httpclient.bacen.dto.BacenResponseDTOTest;
import com.itau.operacaobancaria.adapter.infra.seguranca.StsTokenImpl;
import com.itau.operacaobancaria.core.domain.mapper.BacenMapper;
import com.itau.operacaobancaria.core.domain.port.out.NotificaBacenPortOut;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.FallBackException;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.NotificacaoBacenException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotificaBacenAdapterTest {

    @Mock
    private BacenClient client;

    @Mock
    private StsTokenImpl stsToken;
    @Spy
    private BacenMapper bacenMapper;
    @InjectMocks
    private NotificaBacenAdapter notificaBacenAdapter;

    @Test
    @DisplayName("Testar integracao notificacao bacen.")
    public void testeIntegracaNotificacaoBacen() throws InterruptedException {
        doReturn("token").when(stsToken).getToken();
        ResponseEntity<BacenResponseDTOTest> response =
                new ResponseEntity<>(BacenResponseDTOTest.builder().cpfCliente("34879027880").build()
                        , HttpStatusCode.valueOf(200));

        doReturn(response)
                .when(client).notificarBacen(any(), any(), any(), any(), any());

        notificaBacenAdapter.notificaBacen(NotificaBacenPortOut.Entrada.builder()
                .codigoChavePixDestino("1234")
                .build());

        verify(stsToken, atLeastOnce()).getToken();
    }

    @Test
    @DisplayName("Testar integracao notificacao bacen com rate limit retornado.")
    public void testeNotificacaoBacenComRateLimitRetornado() throws InterruptedException {
        doReturn("token").when(stsToken).getToken();
        ResponseEntity<BacenResponseDTOTest> response =
                new ResponseEntity<>(BacenResponseDTOTest.builder().cpfCliente("34879027880").build()
                        , HttpStatusCode.valueOf(429));

        doReturn(response)
                .when(client).notificarBacen(any(), any(), any(), any(), any());

        assertEquals("Bacen com muitos acessos recorrentes, enviando evento para callback",
                Assertions.assertThrows(NotificacaoBacenException.class, () -> {
                    notificaBacenAdapter.notificaBacen(NotificaBacenPortOut.Entrada.builder()
                            .codigoChavePixDestino("1234")
                            .build());
                }).getMessage());
    }
    @Test
    @DisplayName("Teste mensagemFallBack ao notificar bacen")
    public void testeNotificacaoBacenMensagemFallback() throws InterruptedException {
        assertEquals("API temporariamente indisponível..",
                Assertions.assertThrows(FallBackException.class, () -> {
                    notificaBacenAdapter.retornaMensagemFallBack();
                }).getMessage());
    }


}
