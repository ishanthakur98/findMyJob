package com.crazzy.coding.review.service;



import com.crazzy.coding.review.modal.Review;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ReviewService {

    List<Review> fetchReview(String companyId);

    Review addReview(Review review) throws JsonProcessingException;

    void editReview(Review review);

    Review fetchById(String id) throws Exception;

    void deleteReview(String companyId,String id);
}
