package com.itau.operacaobancaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OperacaoBancariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperacaoBancariaApplication.class, args);
    }

}
