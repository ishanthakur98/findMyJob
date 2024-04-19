package com.crazzy.coding.review.dao;

import com.crazzy.coding.review.modal.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDao extends MongoRepository<Review,String> {
    List<Review> findByCompanyId(String companyId);
}
