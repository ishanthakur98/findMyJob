package com.crazzy.coding.company.service;


import com.crazzy.coding.company.modal.Company;

import java.util.List;

public interface CompanyService {

    List<Company> fetchCompany();

    Company addCompany(Company company);

    Company fetchById(String id);

    void deleteCompany(String id);

    void editCompany(Company company);
}
