package com.itau.operacaobancaria.adapter.httpclient.feign;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.FormHttpMessageConverter;

public class CoreFeignConfiguration {

    @Bean
    @Primary
    public SpringEncoder feignFormEncoder() {
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(
                new FormHttpMessageConverter());
            return new SpringEncoder(objectFactory);

        }
    }
