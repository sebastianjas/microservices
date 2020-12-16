package com.poc.springbatch.batch.listener;

import com.poc.springbatch.model.Employee;
import io.micrometer.core.lang.NonNullApi;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class WriterListener implements ItemWriteListener<Employee> {


    @Override
    public void beforeWrite(List<? extends Employee> list) {

    }

    @Override
    public void afterWrite(List<? extends Employee> list) {
        log.info("##Employees appended to the file {}", list);
    }

    @Override
    public void onWriteError(Exception e, List<? extends Employee> list) {
        log.error("##Employees were not appended to the file {}", list);
    }
}
