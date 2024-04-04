package com.crazzyy.coding.job.dao;

import com.crazzyy.coding.job.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends MongoRepository<Company , String> {
}
