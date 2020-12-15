package com.poc.springbatch.batch.mapper;

import com.poc.springbatch.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Employee.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .department(resultSet.getString("department"))
                .salary(resultSet.getDouble("salary"))
                .build();
    }
}
