package com.crazzy.coding.review.service.impl;


import com.crazzy.coding.review.constants.ReviewConstants;
import com.crazzy.coding.review.dao.ReviewDao;
import com.crazzy.coding.review.dto.ApiRequest;
import com.crazzy.coding.review.dto.ApiResponse;
import com.crazzy.coding.review.exception.ReviewException;
import com.crazzy.coding.review.modal.Company;
import com.crazzy.coding.review.modal.Review;
import com.crazzy.coding.review.service.ApiService;
import com.crazzy.coding.review.service.ReviewService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@Service
@AllArgsConstructor
public class ReviewServiceImpl extends BaseService implements ReviewService , ReviewConstants {

    ReviewDao reviewDao;

    ApiService apiService;

    Environment environment;

    @Override
    public List<Review> fetchReview(String companyId) {
        return reviewDao.findByCompanyId(companyId);
    }

    @Override
    public Review addReview(Review review) throws JsonProcessingException {
        boolean isReviewAdded = false;
        boolean isCompanyAdded = false;
        try {
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setAccept(MediaType.APPLICATION_JSON);
            apiRequest.setEndpoint(environment.getProperty("company.get.by.id.endpoint") + "/" + review.getCompany().getId());
            apiRequest.setHttpMethod(HttpMethod.GET);
            ApiResponse apiResponse = apiService.callApi(apiRequest);
            if (HttpStatus.OK.value() != apiResponse.getApiResponseCode()) {
                log.error("Api exception {}", apiResponse);
                throw new ReviewException(String.valueOf(apiResponse.getApiResponseCode()), ERROR_MESSAGE);
            }
            JsonNode jsonNode = generateJsonNode(apiResponse.getResponseBody());
            if (jsonNode.has("errorCode")) {
                throw new ReviewException(jsonNode.get("errorCode").textValue(), jsonNode.get("errorDescription").textValue());
            }

            Company company = generateObjectFromNode(jsonNode, Company.class);
            List<Review> reviewList = company.getReview();
            if (reviewList == null) {
                reviewList = new ArrayList<>();
            }
            reviewList.add(review);
            isReviewAdded = true;
            company.setReview(reviewList);
            review = reviewDao.save(review);
            ApiRequest apiRequest2 = new ApiRequest();
            apiRequest2.setAccept(MediaType.APPLICATION_JSON);
            apiRequest2.setContentType(MediaType.APPLICATION_JSON);
            apiRequest2.setBody(company);
            apiRequest2.setEndpoint(environment.getProperty("company.add.endpoint"));
            apiRequest2.setHttpMethod(HttpMethod.POST);
            ApiResponse apiResponse2 = apiService.callApi(apiRequest2);
            if (HttpStatus.OK.value() != apiResponse2.getApiResponseCode()) {
                log.error("Api exception {}", apiResponse2);
                throw new ReviewException(String.valueOf(apiResponse.getApiResponseCode()), ERROR_MESSAGE);
            }
            JsonNode jsonNode2 = generateJsonNode(apiResponse2.getResponseBody());
            if (jsonNode2.has("errorCode")) {
                throw new ReviewException(jsonNode2.get("errorCode").textValue(), jsonNode2.get("errorDescription").textValue());
            }else{
                isCompanyAdded = true;
            }
        }finally {
            if(isReviewAdded && !isCompanyAdded){
                reviewDao.deleteById(review.getId());
            }
        }
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
    public Review fetchById( String id) throws Exception {
        return reviewDao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteReview(String companyId,String id) {
        try {
            Review review = reviewDao.findById(id).orElseThrow(NoSuchElementException::new);
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setAccept(MediaType.APPLICATION_JSON);
            apiRequest.setEndpoint(environment.getProperty("company.get.by.id.endpoint") + "/" + review.getCompany().getId());
            apiRequest.setHttpMethod(HttpMethod.GET);
            ApiResponse apiResponse = apiService.callApi(apiRequest);
            if (HttpStatus.OK.value() != apiResponse.getApiResponseCode()) {
                log.error("Api exception {}", apiResponse);
                throw new ReviewException(String.valueOf(apiResponse.getApiResponseCode()), ERROR_MESSAGE);
            }
            JsonNode jsonNode = generateJsonNode(apiResponse.getResponseBody());
            if (jsonNode.has("errorCode")) {
                throw new ReviewException(jsonNode.get("errorCode").textValue(), jsonNode.get("errorDescription").textValue());
            }

            Company company = generateObjectFromNode(jsonNode, Company.class);
            if (review.getCompany().getId().equals(company.getId())) {


                log.info("Company for this job is {} ",company);
                company.getReview().remove(review);
                ApiRequest apiRequest2 = new ApiRequest();
                apiRequest2.setAccept(MediaType.APPLICATION_JSON);
                apiRequest2.setContentType(MediaType.APPLICATION_JSON);
                apiRequest2.setEndpoint(environment.getProperty("company.add.endpoint"));
                apiRequest2.setBody(company);
                apiRequest2.setHttpMethod(HttpMethod.POST);
                ApiResponse apiResponse2 = apiService.callApi(apiRequest2);
                if (HttpStatus.OK.value() != apiResponse2.getApiResponseCode()) {
                    log.error("Api exception {}", apiResponse2);
                    throw new ReviewException(String.valueOf(apiResponse.getApiResponseCode()), ERROR_MESSAGE);
                }
                JsonNode jsonNode2 = generateJsonNode(apiResponse2.getResponseBody());
                if (jsonNode2.has("errorCode")) {
                    throw new ReviewException(jsonNode2.get("errorCode").textValue(), jsonNode2.get("errorDescription").textValue());
                }
//                companyService.addCompany(company);
            } else {
                throw new ReviewException("400", "Company doesn't match");
            }
            reviewDao.deleteById(id);
        } catch (Exception e) {
            log.error("{} ",e);
            throw new ReviewException("400", "No review with given id found");
        }
    }
}
