package com.demo.jobportal.controller;



import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.jobportal.dao.entity.JobPostingEntity;
import com.demo.jobportal.pojos.JobPostingPojo;
import com.demo.jobportal.service.JobPostingService;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobPostingController {

    @Autowired
    private JobPostingService jobPostingService;


    @PostMapping("/create")
    public JobPostingEntity createJob(@RequestBody JobPostingPojo jobRequest) {
        jobRequest.setJobId(null);
        return jobPostingService.createJob(jobRequest);
    }



    @GetMapping("/active")
    public Page<JobPostingEntity> getAllActiveJobs(
        @RequestParam(required = false) String jobTitle,
        @RequestParam(required = false) String jobLocation,
        @RequestParam(required = false) JobPostingEntity.JobType jobType,
        @RequestParam(required = false) Boolean onsite,
        @RequestParam(required = false) Integer minExp,
        @RequestParam(required = false) Integer maxExp,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "postedAt") String sortBy
    ) {
        return jobPostingService.getFilteredJobs(jobTitle, jobLocation, jobType, onsite, minExp, maxExp, page, size, sortBy);
    }
     @GetMapping("/{id}")
    public JobPostingEntity getJobById(@PathVariable Long id) {
        return jobPostingService.getJobById(id);
    }

    @PostMapping("/post")
    public JobPostingEntity postJob(@RequestBody JobPostingPojo jobPojo) {
        return jobPostingService.postJob(jobPojo);
    }

    @GetMapping("recruiter/{recruiterId}")
    public List<JobPostingEntity> getJobsByRecruiter(@PathVariable Long recruiterId) {
        return jobPostingService.getJobsByRecruiter(recruiterId);
    }


}

