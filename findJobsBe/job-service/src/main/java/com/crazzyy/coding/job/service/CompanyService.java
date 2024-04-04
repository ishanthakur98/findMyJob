package com.crazzyy.coding.job.service;

import com.crazzyy.coding.job.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> fetchCompany();

    Company addCompany(Company company);

    Company fetchById(String id);

    void deleteCompany(String id);

    void editCompany(Company company);
}
