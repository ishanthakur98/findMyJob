package com.crazzyy.coding.job.service.impl;

import com.crazzyy.coding.job.dao.CompanyDao;
import com.crazzyy.coding.job.dao.JobDao;
import com.crazzyy.coding.job.exception.JobException;
import com.crazzyy.coding.job.model.Company;
import com.crazzyy.coding.job.model.Job;
import com.crazzyy.coding.job.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

    JobDao jobDao;

    CompanyDao companyDao;

    public JobServiceImpl(JobDao jobDao, CompanyDao companyDao) {
        this.jobDao = jobDao;
        this.companyDao = companyDao;
    }

    @Override
    public List<Job> fetchJobs() {
        return jobDao.findAll();
    }

    @Override
    public Job addJob(Job job) {
        if (job.getCompany() == null) {
            throw new JobException("400", "Company cannot be null");
        }

        Company company = companyDao.findById(job.getCompany().getId()).orElse(companyDao.save(job.getCompany()));
        log.info("Company for this job is {} ",company);
        if (company.getJobs() == null) {
            company.setJobs(new ArrayList<>());
        }
        List<Job> jobList = company.getJobs();
        jobList.add(job);
        company.setJobs(jobList);
        jobDao.save(job);

        companyDao.save(company);
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
            Optional<Company> company = companyDao.findById(job.getCompany().getId());
            if (company.isPresent()) {

                Company company1 = company.get();
                log.info("Company for this job is {} ",company1);
                company1.getJobs().remove(job);
                companyDao.save(company1);
            } else {
                throw new JobException("400", "No company exist for this job");
            }
            jobDao.deleteById(id);
        } catch (Exception e) {
            log.error("{} ",e);
            throw new JobException("400", "No job with given id found");
        }
    }
}
