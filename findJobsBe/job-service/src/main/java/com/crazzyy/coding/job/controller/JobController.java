package com.crazzyy.coding.job.controller;

import com.crazzyy.coding.job.model.Job;
import com.crazzyy.coding.job.service.JobService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobController {


    JobService jobService;

    /*
     * /job 
     * - Method Get 
     * - endpoint to fetch all the jobs
     */
    @GetMapping
    public ResponseEntity<List<Job>> getJobs(){
        log.info("inside fetch job controller !!!!!!!!!!!!!!");
        return ResponseEntity.ok(jobService.fetchJobs());
    }

    /*
     * /job
     * - Method Post
     * - endpoint to add Jobs
     */
    @PostMapping
    public Job addJob(@RequestBody Job job){
        log.info("inside add job controller !!!!!!!!!!!!!!");
        return jobService.addJob(job);
    }
    /*
     * /job/{id}
     * - Method Get
     * - endpoint to fetch Job by id
     */
    @GetMapping("{id}")
    public ResponseEntity<Job> fetchById(@PathVariable("id") long id) throws Exception {
        log.info("inside fetch job by id controller !!!!!!!!!!!!!!");
        return ResponseEntity.ok(jobService.fetchById(id));
    }

    /*
     * /job/{id}
     * - Method Delete
     * - endpoint to delete Job by id
     */
    @DeleteMapping("{id}")
    public List<Job> deleteById(@PathVariable("id") long id){
        log.info("inside delete job by id controller !!!!!!!!!!!!!!");
        return jobService.deleteJob(id);
    }

}
