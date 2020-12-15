package com.poc.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchPocApplication.class, args);
	}

}
