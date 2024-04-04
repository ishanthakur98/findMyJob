package com.crazzyy.coding.job.dao;

import com.crazzyy.coding.job.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao extends MongoRepository<Review,String> {
}
