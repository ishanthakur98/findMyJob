package com.crazzy.coding.company.service.impl;

import com.crazzy.coding.company.dao.CompanyDao;
import com.crazzy.coding.company.dto.ApiRequest;
import com.crazzy.coding.company.dto.ApiResponse;
import com.crazzy.coding.company.exception.CompanyException;
import com.crazzy.coding.company.modal.Company;
import com.crazzy.coding.company.modal.Job;
import com.crazzy.coding.company.service.ApiService;
import com.crazzy.coding.company.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDao companyDao;

    @Autowired
    ApiService apiService;

    @Autowired
    Environment environment;

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
            log.info(jobList.isEmpty() + " "+jobList.size() + " "+jobList.contains(null));
            if(jobList != null && !jobList.isEmpty() && !jobList.contains(null)){
                log.info("List of jobs for company {} is {}",company.getName(),jobList);
                List<String> idList = new ArrayList<>();
                jobList.forEach(job -> idList.add(job.getId()));
                ApiRequest apiRequest = new ApiRequest();
                apiRequest.setHttpMethod(HttpMethod.POST);
                apiRequest.setBody(idList);
                apiRequest.setContentType(MediaType.APPLICATION_JSON);
                apiRequest.setAccept(MediaType.TEXT_PLAIN);
                apiRequest.setService(com.crazzy.coding.company.enums.Service.JOB_SERVICE.value());
                apiRequest.setEndpoint(environment.getProperty("job-service-delete-job-in-endpoint"));
                ApiResponse apiResponse = apiService.callApi(apiRequest);
                if(200!=apiResponse.getApiResponseCode()) {
                    log.info("api response is "+apiResponse);
                    throw new CompanyException(String.valueOf(apiResponse.getApiResponseCode()), "Something went wrong");
                }else{
                    log.info("api response is "+apiResponse);
                }

            }else {
                log.info("No jobs exist for company {} ",company.getName());
            }
            companyDao.deleteById(id);
        }catch (Exception e){
            log.error("Exception occurred while deleting jobs "+e);
            throw new CompanyException("400","No job with given id found");
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
