package com.crazzyy.coding.job.controller;

import com.crazzyy.coding.job.model.Company;
import com.crazzyy.coding.job.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    CompanyService companyService;

    /*
     * /company
     * - Method Get
     * - endpoint to fetch all the company
     */
    @GetMapping
    public ResponseEntity<List<Company>> fetchCompany(){
        log.info("inside fetch company controller !!!!!!!!!!!!!!!");
        return ResponseEntity.ok(companyService.fetchCompany());
    }

    /*
     * /company
     * - Method Post
     * - endpoint to add company
     */
    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company){
        log.info("inside add company controller !!!!!!!!!!!!!!!!!");
        return ResponseEntity.ok(companyService.addCompany(company));
    }

    /*
     * /company/{id}
     * - Method Get
     * - endpoint to fetch company by id
     */
    @GetMapping("{id}")
    public ResponseEntity<Company> fetchById(@PathVariable("id") String id) {
        log.info("inside fetch job by id controller !!!!!!!!!!!!!!");
        return ResponseEntity.ok(companyService.fetchById(id));
    }

    /*
     * /company/{id}
     * - Method Delete
     * - endpoint to delete company by id
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String id){
        log.info("inside delete company by id controller !!!!!!!!!!!!!!");
        return ResponseEntity.ok("Done");
    }
    /*
     * /company/{id}
     * - Method Delete
     * - endpoint to delete company by id
     */
    @PutMapping
    public ResponseEntity<String> editById(@RequestBody Company company){
        log.info("inside edit company by id controller !!!!!!!!!!!!!!");
        return ResponseEntity.ok("Done");
    }

}
