package com.crazzyy.coding.job.service;

import com.crazzyy.coding.job.model.Job;

import java.util.List;

public interface JobService {

    List<Job> fetchJobs();

    Job addJob(Job job);

    List<Job> editJob(Job job);

    Job fetchById(long id) throws Exception;

    List<Job> deleteJob(long id);
}
