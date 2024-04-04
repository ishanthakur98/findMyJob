package com.crazzyy.coding.job.service.impl;

import com.crazzyy.coding.job.dao.ReviewDao;
import com.crazzyy.coding.job.model.Job;
import com.crazzyy.coding.job.model.Review;
import com.crazzyy.coding.job.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    ReviewDao reviewDao;
    @Override
    public List<Review> fetchReview() {
        return reviewDao.findAll();
    }

    @Override
    public Review addReview(Review review) {
        return reviewDao.save(review);
    }

    @Override
    public void editReview(Review review) {

    }

    @Override
    public Review fetchById(String id) throws Exception {
        return null;
    }

    @Override
    public void deleteReview(String id) {

    }
}
