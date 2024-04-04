package com.crazzyy.coding.job.dao;

import com.crazzyy.coding.job.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobDao extends MongoRepository<Job , String> {

    void deleteByIdIn(List<String> ids);
}
