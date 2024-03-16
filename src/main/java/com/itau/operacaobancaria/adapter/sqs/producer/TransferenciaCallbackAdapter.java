package com.itau.operacaobancaria.adapter.sqs.producer;

import com.itau.operacaobancaria.core.domain.port.out.TransferenciaCallBackPotOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
@Slf4j
public class TransferenciaCallbackAdapter implements TransferenciaCallBackPotOut {

    public final SqsClient sqsClient;;

    public TransferenciaCallbackAdapter(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendMessageCallBack(String message){
        log.info("Enviando evento para retentativa de transferência. {}", message);

        SendMessageRequest messageRequest = SendMessageRequest.builder()
                .messageBody(message)
                .queueUrl("sqs-callback-transsferencia")
                .build();

        SqsClient.create().sendMessage(messageRequest);
    }

}
