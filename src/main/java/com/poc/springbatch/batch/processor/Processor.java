package com.poc.springbatch.batch.processor;

import com.poc.springbatch.feign.DepartmentServiceClient;
import com.poc.springbatch.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<Employee, Employee> {

    @Autowired
    private DepartmentServiceClient departmentClient;

    @Override
    public Employee process(Employee employee) throws Exception {
        var deptName = departmentClient.getDepartmentName(employee.getDepartment());
        employee.setDepartment(deptName);
        return employee;
    }
}
