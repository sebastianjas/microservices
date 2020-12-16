package com.poc.springbatch.config;

import com.poc.springbatch.batch.mapper.EmployeeRowMapper;
import com.poc.springbatch.batch.processor.Processor;
import com.poc.springbatch.batch.reader.EmployeeReader;
import com.poc.springbatch.batch.reader.QueryInput;
import com.poc.springbatch.batch.writer.CSVWriter;
import com.poc.springbatch.batch.listener.WriterListener;
import com.poc.springbatch.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private EmployeeReader employeeReader;
    @Autowired
    private CSVWriter csvWriter;
    @Autowired
    DataSource dataSource;
    @Autowired
    QueryInput queryInput;


    @Bean
    public Step stepOne(EmployeeReader employeeReader) {
        return stepBuilderFactory.get("stepOne")
                .<Employee, Employee>chunk(100)
                .reader(reader())
                .processor(new Processor())
                .writer(csvWriter.writer())
                .listener(new WriterListener())
                .build();
    }

    @Bean
    public Job exportEmployeesJob(EmployeeReader employeeReader) {
        return jobBuilderFactory.get("Export employees to CSV job")
                .incrementer(new RunIdIncrementer())
                .flow(stepOne(employeeReader))
                .end()
                .build();
    }

    @StepScope
    @Bean
    public ItemStreamReader<Employee> reader() {
        var cursorItemReader = new JdbcCursorItemReader<Employee>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader
                .setSql("SELECT id, name, department, salary from EMPLOYEE " );
        /** "where create_date_time < ?");
        cursorItemReader.setPreparedStatementSetter(queryInput); **/
        cursorItemReader.setRowMapper(new EmployeeRowMapper());
        log.info("Query executed " + cursorItemReader.getSql());
        return cursorItemReader;
    }

}
