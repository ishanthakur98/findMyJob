package com.crazzyy.coding.job.service.impl;

import com.crazzyy.coding.job.dao.CompanyDao;
import com.crazzyy.coding.job.dao.JobDao;
import com.crazzyy.coding.job.exception.JobException;
import com.crazzyy.coding.job.model.Company;
import com.crazzyy.coding.job.model.Job;
import com.crazzyy.coding.job.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    CompanyDao companyDao;

    JobDao jobDao;
    @Override
    public List<Company> fetchCompany() {
        return companyDao.findAll();
    }

    @Override
    public Company addCompany(Company company) {
        return companyDao.save(company);
    }

    @Override
    public Company fetchById(String id) {
        return companyDao.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteCompany(String id) {
        try {
            Company company = companyDao.findById(id).orElseThrow(NoSuchElementException::new);
            List<Job> jobList = company.getJobs();
            if(jobList != null && !jobList.isEmpty()){
                log.info("List of jobs for company {} is {}",company.getName(),jobList);
                List<String> idList = new ArrayList<>();
                jobList.forEach(job -> idList.add(job.getId()));
                jobDao.deleteByIdIn(idList);

            }else {
                log.info("No jobs exist for company {} ",company.getName());
            }
            companyDao.deleteById(id);
        }catch (Exception e){
            throw new JobException("400","No job with given id found");
        }
    }

    @Override
    public void editCompany(Company company) {
        Company company1 = companyDao.findById(company.getId())
                .orElseThrow(NoSuchElementException::new);
        company1.setId(company.getId());
        company1.setName(company.getName());
        company1.setDescription(company.getDescription());
        company1.setJobs(company1.getJobs());
        companyDao.save(company1);
    }
}
