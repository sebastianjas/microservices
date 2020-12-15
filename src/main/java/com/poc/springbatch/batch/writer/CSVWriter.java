package com.poc.springbatch.batch.writer;

import com.poc.springbatch.model.Employee;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CSVWriter {

    private final Resource outputResource = new FileSystemResource("employees.csv");

    @Bean
    public FlatFileItemWriter<Employee> writer() {
        //Create writer instance
        var writer = new FlatFileItemWriter<Employee>();
        //Set output file location
        writer.setResource(outputResource);
        //All job repetitions should "append" to same output file
        writer.setAppendAllowed(true);
        //Name field values sequence based on object properties
        writer.setLineAggregator(getAggregator());
        return writer;
    }

    private DelimitedLineAggregator<Employee> getAggregator() {
        return new DelimitedLineAggregator<>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    {
                        setNames(new String[]{"id", "name", "department", "salary"});
                    }
                });
            }
        };
    }
}
