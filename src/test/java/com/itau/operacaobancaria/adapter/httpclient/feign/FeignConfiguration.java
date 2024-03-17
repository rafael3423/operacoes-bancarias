package com.itau.operacaobancaria.adapter.httpclient.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    System.Logger.Level feignLoggerLevel(){
        return System.Logger.Level.ALL;
    }
}
