package com.poc.springbatch.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @GetMapping
    public BatchStatus load(@RequestParam("date") String date) throws Exception {
        var parameters = new JobParametersBuilder()
                .addDate("time", new Date())
                .addString("cuttingDate", date)
                .toJobParameters();
        var jobExecution = jobLauncher.run(job, parameters);
        while(jobExecution.isRunning()){
            System.out.println(jobExecution.getStatus());
        }
        return jobExecution.getStatus();
    }

}
