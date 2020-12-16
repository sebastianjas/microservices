package com.poc.springbatch.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DepartmentServiceFallbackFactory implements FallbackFactory<DepartmentServiceClient> {

    @Override
    public DepartmentServiceClient create(Throwable cause) {
        return new DepartmentServiceCallback(cause);
    }
}


