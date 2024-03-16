package com.itau.operacaobancaria.adapter.dynamo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.http.apache.ProxyConfiguration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
@RequiredArgsConstructor
public class DynamoDBConfig {

    @Autowired
//    private AwsProperties awsProperties;

    @Bean(value = "dynamoDBenhancedClient")
    public static DynamoDbEnhancedClient dynamoDBEnhancedClient(DynamoDbClient dynamoDbCllient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbCllient)
                .build();
    }

    @Bean(value = "dynamoDbClient")
    @Profile("!local")
    public DynamoDbClient dynamoDbClient() {
        ProxyConfiguration.Builder proxyConfig = ProxyConfiguration.builder();

        ApacheHttpClient.Builder httpClientBuilder = ApacheHttpClient.builder()
                .proxyConfiguration(proxyConfig.build());

        ClientOverrideConfiguration.Builder overrideConfig = ClientOverrideConfiguration.builder();

        return DynamoDbClient.builder()
                .region(Region.SA_EAST_1)
                .httpClientBuilder(httpClientBuilder)
                .overrideConfiguration(overrideConfig.build())
                .build();
    }

    @Bean(value = "dynamoDbClientLocal")
    @Profile("local")
    public DynamoDbClient dynamoDbClientLocal() {
//        URI uri = URI.create(awsProperties.getDynamoDb().getEndpoint);
//        AwsCredentials awsCredentials = AwsBasicCredentials.create(awsProperties);
        return DynamoDbClient.builder()
                .region(Region.SA_EAST_1)
//                .endpointOverride(uri)
//                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }


}
