package com.itau.operacaobancaria.adapter.sqs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AmazonSQSAsyncConfig {

//    @Bean
//    public SqsAsyncClient sqsClient() {
//        return SqsAsyncClient.builder().region(Region.SA_EAST_1).build();
//    }
}
