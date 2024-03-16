package com.itau.operacaobancaria.core.domain.port.out;

import com.itau.operacaobancaria.core.domain.model.Bacen;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.NotificacaoBacenException;
import lombok.Data;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;


public interface NotificaBacenPortOut {

    void notificaBacen(Entrada entrada) throws InterruptedException, NotificacaoBacenException;

    @Data
    class Entrada {
        String idCliente;
        String cpfOrigem;
        String valorTransferencia;
        String codigoChavePixDestino;
        String dataHoraTransferencia;
    }
}
