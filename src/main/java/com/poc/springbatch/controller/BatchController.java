package com.poc.springbatch.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/load")
public class BatchController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @GetMapping
    public BatchStatus load(@RequestParam("date") String date) throws Exception {
        var parameters = new JobParametersBuilder()
                .addDate("time", new Date())
                .addString("limitDate", date)
                .toJobParameters();
        var jobExecution = jobLauncher.run(job, parameters);
        while(jobExecution.isRunning()){
            System.out.println(jobExecution.getStatus());
        }
        return jobExecution.getStatus();
    }

}
