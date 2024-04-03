package com.crazzyy.coding.job.service.impl;

import com.crazzyy.coding.dao.JobDao;
import com.crazzyy.coding.exception.JobException;
import com.crazzyy.coding.job.model.Job;
import com.crazzyy.coding.job.service.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

    JobDao jobDao;

    public JobServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public List<Job> fetchJobs() {
        return jobDao.findAll();
    }

    @Override
    public Job addJob(Job job) {
        return jobDao.save(job);
    }

    @Override
    public List<Job> editJob(Job job) {
        jobDao.save(job);
        return jobDao.findAll();
    }

    @Override
    public Job fetchById(String id) throws Exception{
        return jobDao.findById(id).orElseThrow(NoSuchElementException::new);

    }

    @Override
    public List<Job> deleteJob(String id) {
        try {
            jobDao.deleteById(id);
        }catch (Exception e){
            throw new JobException("400","No job with given id found");
        }

        return jobDao.findAll();
    }
}
