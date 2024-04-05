package com.crazzyy.coding.job.service.impl;

import com.crazzyy.coding.job.dao.CompanyDao;
import com.crazzyy.coding.job.dao.JobDao;
import com.crazzyy.coding.job.exception.JobException;
import com.crazzyy.coding.job.model.Company;
import com.crazzyy.coding.job.model.Job;
import com.crazzyy.coding.job.service.CompanyService;
import com.crazzyy.coding.job.service.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobDao jobDao;

    @Lazy
    @Autowired
    CompanyService companyService;

    @Override
    public List<Job> fetchJobs() {
        return jobDao.findAll();
    }

    @Override
    public Job addJob(Job job) {
        if (job.getCompany() == null) {
            throw new JobException("400", "Company cannot be null");
        }

        Company company = companyService.fetchById(job.getCompany().getId());
        log.info("Company for this job is {} ",company);
        if (company.getJobs() == null) {
            company.setJobs(new ArrayList<>());
        }
        List<Job> jobList = company.getJobs();
        jobList.add(job);
        company.setJobs(jobList);
        jobDao.save(job);

        companyService.addCompany(company);
        return job;
    }

    @Override
    public void editJob(Job job) {
        try {
            jobDao.findById(job.getId()).orElseThrow(NoSuchElementException::new);
            jobDao.save(job);
        } catch (Exception e) {
            throw new JobException("400", "No job with given id found");
        }

    }

    @Override
    public Job fetchById(String id) {
        return jobDao.findById(id).orElseThrow(NoSuchElementException::new);

    }

    @Override
    public void deleteJob(String id) {
        try {
            Job job = jobDao.findById(id).orElseThrow(NoSuchElementException::new);
            Company company = companyService.fetchById(job.getCompany().getId());
            log.info("Company for this job is {} ",company);
            company.getJobs().remove(job);
            companyService.addCompany(company);

            jobDao.deleteById(id);
        } catch (Exception e) {
            log.error("{} ",e);
            throw new JobException("400", "No job with given id found");
        }
    }

    public void deleteIdIn(List<String> id){
        jobDao.deleteByIdIn(id);
    }
}
