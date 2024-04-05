package com.crazzyy.coding.job.service;

import com.crazzyy.coding.job.model.Job;
import com.crazzyy.coding.job.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> fetchReview(String companyId);

    Review addReview(Review review);

    void editReview(Review review);

    Review fetchById(String companyId,String id) throws Exception;

    void deleteReview(String companyId,String id);
}
