package com.itau.operacaobancaria.adapter.sqs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SQSConfig {

    @Bean
    SqsClient getSQSClient(){
        AwsCredentials awsCredentials = new AwsCredentials() {
            @Override
            public String accessKeyId() {
                return null;
            }

            @Override
            public String secretAccessKey() {
                return null;
            }
        };
        return SqsClient.builder().region(Region.SA_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials)).build();

    }
}
