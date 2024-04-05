package com.crazzyy.coding.job.service.impl;


import com.crazzyy.coding.job.dao.ReviewDao;
import com.crazzyy.coding.job.exception.JobException;
import com.crazzyy.coding.job.model.Company;
import com.crazzyy.coding.job.model.Review;
import com.crazzyy.coding.job.service.CompanyService;
import com.crazzyy.coding.job.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    ReviewDao reviewDao;

    CompanyService companyService;

    @Override
    public List<Review> fetchReview(String companyId) {
        return reviewDao.findByCompanyId(companyId);
    }

    @Override
    public Review addReview(Review review) {
        Company company = companyService.fetchById(review.getCompany().getId());
        List<Review> reviewList =  company.getReview();
        if(reviewList == null){
            reviewList = new ArrayList<>();
        }
        reviewList.add(review);
        company.setReview(reviewList);
        review = reviewDao.save(review);
        companyService.addCompany(company);
        return review;
    }

    @Override
    public void editReview(Review review) {
        Review review1 = reviewDao.findById(review.getId()).orElseThrow(NoSuchElementException::new);
        review1.setRating(review.getRating());
        review1.setTitle(review.getTitle());
        review1.setDescription(review.getDescription());
        reviewDao.save(review1);
    }

    @Override
    public Review fetchById(String companyId,String id) throws Exception {
        companyService.fetchById(companyId);
        return reviewDao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteReview(String companyId,String id) {
        try {
            Review review = reviewDao.findById(id).orElseThrow(NoSuchElementException::new);
            Company company = companyService.fetchById(review.getCompany().getId());
            if (review.getCompany().getId().equals(company.getId())) {


                log.info("Company for this job is {} ",company);
                company.getReview().remove(review);
                companyService.addCompany(company);
            } else {
                throw new JobException("400", "Company doesn't match");
            }
            reviewDao.deleteById(id);
        } catch (Exception e) {
            log.error("{} ",e);
            throw new JobException("400", "No review with given id found");
        }
    }
}
