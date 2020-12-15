package com.poc.springbatch.batch.reader;

import com.poc.springbatch.batch.mapper.EmployeeRowMapper;
import com.poc.springbatch.model.Employee;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class EmployeeReader {

    @Autowired
    DataSource dataSource;

    @Bean
    @StepScope
    public JdbcCursorItemReader<Employee> reader(@Value("#{jobParameters['cuttingDate']}") String cuttingDate) {
        System.out.println("!!!Date received "  + cuttingDate);
        var cursorItemReader = new JdbcCursorItemReader<Employee>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql("SELECT id, name, department, salary from EMPLOYEE");
        cursorItemReader.setRowMapper(new EmployeeRowMapper());
        return cursorItemReader;
    }
}
