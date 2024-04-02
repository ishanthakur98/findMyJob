package com.crazzyy.coding.job.service.impl;

import com.crazzyy.coding.job.model.Job;
import com.crazzyy.coding.job.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();

    private long counter = 0L;

    @Override
    public List<Job> fetchJobs() {
        log.info("list of jobs {}",jobs);
        return jobs;
    }

    @Override
    public Job addJob(Job job) {
        job.setId(counter++);
        jobs.add(job);
        log.info("job added is {}",job);
        return job;
    }

    @Override
    public List<Job> editJob(Job job) {
        jobs = jobs.stream()
                .map(e -> {
                    if(e.getId() == job.getId()){
                        e = job;
                    }
                    return e;
                }).collect(Collectors.toList());
        log.info("list of jobs after edit is {}",jobs);
        return jobs;
    }

    @Override
    public Job fetchById(long id) throws Exception{
        Job jobById = jobs.stream()
                .filter(job -> job.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("no such element present"));
        log.info("job by id is {}",jobById);
        return jobById;

    }

    @Override
    public List<Job> deleteJob(long id) {
        jobs = jobs.stream()
                .filter(job -> job.getId() != id)
                .collect(Collectors.toList());
        log.info("list of jobs after deleting is {}",jobs);
        return jobs;
    }
}
