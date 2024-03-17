package com.itau.operacaobancaria.adapter.infra.seguranca;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StsTokenImpl {

    public String getToken() {
        return "token";
    }
}
