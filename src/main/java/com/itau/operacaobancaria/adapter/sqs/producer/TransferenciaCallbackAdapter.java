package com.itau.operacaobancaria.adapter.sqs.producer;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.itau.operacaobancaria.core.domain.port.out.TransferenciaCallBackPotOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
@Slf4j
public class TransferenciaCallbackAdapter implements TransferenciaCallBackPotOut {

//    private final SqsClient sqsClient;

//    public TransferenciaCallbackAdapter(SqsClient sqsClient) {
//        this.sqsClient = sqsClient;
//    }


    public void sendMessageCallBack(String message) {
        log.info("Enviando evento para retentativa de transferência. {}", message);
//        sqsClient.sendMessage(SendMessageRequest.builder().messageBody(message).queueUrl("sqs-callback-transsferencia").build());
    }

}
