package com.itau.operacaobancaria.core.domain.port.out;

import com.itau.operacaobancaria.core.domain.model.Bacen;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.NotificacaoBacenException;
import lombok.Builder;
import lombok.Data;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;


public interface NotificaBacenPortOut {

    void notificaBacen(Entrada entrada) throws InterruptedException, NotificacaoBacenException;

    @Data
    @Builder
    class Entrada {
        String idCliente;
        String cpfOrigem;
        String valorTransferencia;
        String codigoChavePixDestino;
        String dataHoraTransferencia;
    }
}
