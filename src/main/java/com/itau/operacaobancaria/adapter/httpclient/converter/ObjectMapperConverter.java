package com.itau.operacaobancaria.adapter.httpclient.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ObjectMapperConverter {
    private final ObjectMapper objectMapper;

    public <T> T readValue(String json, Class<T> valueType) {
        try {
            return this.objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            log.error("Erro na conversao da resposta da API mensagem: {}, classe: {}", json, valueType);
            throw new ObjectMapperConverterException(valueType.getName());
        }
    }


}
