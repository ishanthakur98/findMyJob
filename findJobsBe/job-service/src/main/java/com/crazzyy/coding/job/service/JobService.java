package com.crazzyy.coding.job.service;

import com.crazzyy.coding.job.model.Job;

import java.util.List;

public interface JobService {

    List<Job> fetchJobs();

    Job addJob(Job job);

    void editJob(Job job);

    Job fetchById(String id) throws Exception;

    void deleteJob(String id);

    void deleteIdIn(List<String> id);
}
