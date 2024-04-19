package com.crazzy.coding.review.controller;

import com.crazzy.coding.review.modal.Company;
import com.crazzy.coding.review.modal.Review;
import com.crazzy.coding.review.service.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    ReviewService reviewService;

    /*
     * ?companyId={companyId}
     * - Method Get
     * - endpoint to fetch all the company
     */
    @GetMapping
    public ResponseEntity<List<Review>> fetchCompany(@RequestParam("companyId") String companyId){
        log.info("inside fetch Review controller !!!!!!!!!!!!!!!");
        return ResponseEntity.ok(reviewService.fetchReview(companyId));
    }

    /*
     * ?companyId={companyId}
     * - Method Post
     * - endpoint to add company
     */
    @PostMapping
    public ResponseEntity<Review> addCompany(@RequestParam("companyId") String companyId,@RequestBody Review review) throws JsonProcessingException {
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
    @GetMapping("{id}")
    public ResponseEntity<Review> fetchById(@PathVariable("id") String id) throws Exception {
        log.info("inside fetch Review by id controller !!!!!!!!!!!!!!");
        return ResponseEntity.ok(reviewService.fetchById(id));
    }

    /*
     * /review/{id}
     * - Method Delete
     * - endpoint to delete company by id
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String id){
        log.info("inside delete Review by id controller !!!!!!!!!!!!!!");

        return ResponseEntity.ok("Done");
    }
    /*
     * /review/{id}
     * - Method Delete
     * - endpoint to delete company by id
     */
    @PostMapping("edit")
    public ResponseEntity<String> editById(@RequestBody Review review){
        log.info("inside edit Review by id controller !!!!!!!!!!!!!!");
        reviewService.editReview(review);
        return ResponseEntity.ok("Done");
    }

}
