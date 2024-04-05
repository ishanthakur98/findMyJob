package com.crazzyy.coding.job.controller;

import com.crazzyy.coding.job.model.Company;
import com.crazzyy.coding.job.model.Review;
import com.crazzyy.coding.job.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/company/{companyId}")
public class ReviewController {

    ReviewService reviewService;

    /*
     * /reviews
     * - Method Get
     * - endpoint to fetch all the company
     */
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> fetchCompany(@PathVariable("companyId") String companyId){
        log.info("inside fetch Review controller !!!!!!!!!!!!!!!");
        return ResponseEntity.ok(reviewService.fetchReview(companyId));
    }

    /*
     * /review
     * - Method Post
     * - endpoint to add company
     */
    @PostMapping("/review")
    public ResponseEntity<Review> addCompany(@PathVariable("companyId") String companyId,@RequestBody Review review){
        log.info("inside add Review controller !!!!!!!!!!!!!!!!!");
        Company company = new Company();
        company.setId(companyId);
        review.setCompany(company);
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    /*
     * /review/{id}
     * - Method Get
     * - endpoint to fetch company by id
     */
    @GetMapping("/review/{id}")
    public ResponseEntity<Review> fetchById(@PathVariable("companyId") String companyId,@PathVariable("id") String id) throws Exception {
        log.info("inside fetch Review by id controller !!!!!!!!!!!!!!");
        return ResponseEntity.ok(reviewService.fetchById(companyId,id));
    }

    /*
     * /review/{id}
     * - Method Delete
     * - endpoint to delete company by id
     */
    @DeleteMapping("/review/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("companyId") String companyId,@PathVariable("id") String id){
        log.info("inside delete Review by id controller !!!!!!!!!!!!!!");
        reviewService.deleteReview(companyId,id);
        return ResponseEntity.ok("Done");
    }
    /*
     * /review/{id}
     * - Method Delete
     * - endpoint to delete company by id
     */
    @PutMapping("/review")
    public ResponseEntity<String> editById(@PathVariable("companyId") String companyId,@RequestBody Review review){
        log.info("inside edit Review by id controller !!!!!!!!!!!!!!");
        Company company = new Company();
        company.setId(companyId);
        review.setCompany(company);
        reviewService.editReview(review);
        return ResponseEntity.ok("Done");
    }

}
