package com.poc.springbatch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "department-service", fallbackFactory = DepartmentServiceFallbackFactory.class)
public interface DepartmentServiceClient {

    @GetMapping("/department")
    public String getDepartmentName(@RequestParam("code") String code);
}
