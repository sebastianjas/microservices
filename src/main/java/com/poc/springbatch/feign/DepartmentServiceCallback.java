package com.poc.springbatch.feign;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class DepartmentServiceCallback implements DepartmentServiceClient {

    private final Throwable cause;

    DepartmentServiceCallback(Throwable throwable) {
        this.cause = throwable;
    }

    @Override
    public String getDepartmentName(String code) {
        logExceptions();
        return "Department service not available";
    }

    private void logExceptions() {
        if (cause instanceof FeignException
                && ((FeignException) cause).status() == HttpStatus.NOT_FOUND.value()) {
            log.error("Department Service: 404 error took place when getToken called. {}",
                    cause.getLocalizedMessage());
        } else {
            log.error("Department Service: unexpected error: {}", cause.getLocalizedMessage());
        }
    }
}