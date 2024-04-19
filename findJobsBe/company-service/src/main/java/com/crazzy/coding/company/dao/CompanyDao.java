package com.crazzy.coding.company.dao;

import com.crazzy.coding.company.modal.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends MongoRepository<Company,String> {
}
