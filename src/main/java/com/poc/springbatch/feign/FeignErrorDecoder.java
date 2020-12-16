package com.poc.springbatch.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case HttpServletResponse.SC_BAD_REQUEST:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        response.body().toString());
            case HttpServletResponse.SC_NOT_FOUND:
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Department Service not found");
            default:
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        response.body().toString());
        }
    }
}
