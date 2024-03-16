package com.itau.operacaobancaria.adapter.sqs.consumer;

import com.itau.operacaobancaria.adapter.httpclient.converter.ObjectMapperConverter;
import com.itau.operacaobancaria.core.domain.model.Transferencia;
import com.itau.operacaobancaria.core.domain.port.in.TransferenciaPortIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

@Slf4j
public class TransferenciaCallBackListener {
    private ObjectMapperConverter objectMapperConverter;
    private final TransferenciaPortIn transferenciaPortIn;

    public TransferenciaCallBackListener(TransferenciaPortIn transferenciaPortIn) {
        this.transferenciaPortIn = transferenciaPortIn;
    }

    @SqsListener("sqs-callback-transferencia")
    public void receiveMessage(String message) throws Exception {
        log.info("Evento recebido para retentativa de transferência {}", message);
        transferenciaPortIn.execute(objectMapperConverter.readValue(message, Transferencia.class));
    }
}
