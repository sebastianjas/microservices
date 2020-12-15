package com.poc.springbatch.batch.reader;

import com.poc.springbatch.batch.mapper.EmployeeRowMapper;
import com.poc.springbatch.model.Employee;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class EmployeeReader  {

    @Autowired
    DataSource dataSource;
    @Autowired
    QueryInput queryInput;

    @StepScope
    @Bean
    public JdbcCursorItemReader<Employee> reader() {
        var cursorItemReader = new JdbcCursorItemReader<Employee>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader
                .setSql("SELECT id, name, department, salary from EMPLOYEE " +
                        "where create_date_time < ?");
        cursorItemReader.setRowMapper(new EmployeeRowMapper());
        cursorItemReader.setPreparedStatementSetter(queryInput);
        return cursorItemReader;
    }

}
