package com.poc.springbatch.batch.processor;

import com.poc.springbatch.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<Employee, Employee> {

    private static Map<String, String> DEPT_NAMES = new HashMap<>();

    public Processor(){
        DEPT_NAMES= Map.of("001", "Technology",
                "002", "Operations",
                "003", "IT");
    }

    @Override
    public Employee process(Employee employee) throws Exception {
        var deptName = DEPT_NAMES.get(employee.getDepartment());
        employee.setDepartment(deptName);
        return employee;
    }
}
