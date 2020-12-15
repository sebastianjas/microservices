package com.poc.springbatch.batch.reader;

import com.poc.springbatch.batch.mapper.EmployeeRowMapper;
import com.poc.springbatch.model.Employee;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class EmpReader {

    @Autowired
    DataSource dataSource;

    public JdbcCursorItemReader<Employee> read() {
        // System.out.println("!!!Date received "  + cuttingDate);
        var cursorItemReader = new JdbcCursorItemReader<Employee>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql("SELECT id, name, department, salary from EMPLOYEE");
        cursorItemReader.setRowMapper(new EmployeeRowMapper());
        return cursorItemReader;
    }
}
