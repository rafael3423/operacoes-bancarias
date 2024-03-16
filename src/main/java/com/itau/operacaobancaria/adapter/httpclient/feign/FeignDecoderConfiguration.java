package com.itau.operacaobancaria.adapter.httpclient.feign;

import com.itau.operacaobancaria.adapter.exception.InternalIntegrationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static feign.FeignException.errorStatus;

public class FeignDecoderConfiguration implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        HttpStatus futureStatus = HttpStatus.resolve(response.status());
        String body = null;
        try{
            body = IOUtils.toString(response.body().asReader());
        } catch (IOException e){
            errorStatus(s, response);
        }
        return new InternalIntegrationException(body);
    }
}
