package com.poc.springbatch.config;

import com.poc.springbatch.batch.processor.Processor;
import com.poc.springbatch.batch.reader.EmployeeReader;
import com.poc.springbatch.batch.writer.CSVWriter;
import com.poc.springbatch.batch.listener.WriterListener;
import com.poc.springbatch.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private EmployeeReader employeeReader;
    @Autowired
    private CSVWriter csvWriter;

    @Bean
    public Step stepOne() {
        return stepBuilderFactory.get("stepOne")
                .<Employee, Employee>chunk(100)
                .reader(reader())
                .processor(new Processor())
                .writer(writer())
                .listener(new WriterListener())
                .build();
    }

    @Bean
    public Job exportEmployeesJob() {
        return jobBuilderFactory.get("Export employees to CSV job")
                .incrementer(new RunIdIncrementer())
                .flow(stepOne())
                .end()
                .build();
    }

    private ItemWriter<Employee> writer() {
        return csvWriter.writer();
    }

    private ItemReader<Employee> reader() {
        return employeeReader.reader(null);
    }

}
