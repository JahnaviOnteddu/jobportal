package com.demo.jobportal.controller;

import com.demo.jobportal.dao.entity.ApplicationsEntity;
import com.demo.jobportal.pojos.ApplicationPojo;
import com.demo.jobportal.service.ApplicationsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:4200" , allowCredentials =  "true") 
public class ApplicationsController {

    @Autowired
    ApplicationsService applicationsService;

    //*************
    @GetMapping("/job/{jobId}")
    public List<ApplicationPojo> getApplicantsForJob(@PathVariable Long jobId) {
        return applicationsService.getApplicantsForJob(jobId);
    }

    @GetMapping("/recruiter/{recruiterId}")
    public ResponseEntity<List<ApplicationPojo>> getApplicationsByRecruiter(@PathVariable Long recruiterId) {
        List<ApplicationPojo> applications = applicationsService.getApplicationsByRecruiter(recruiterId);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/applicant/{applicantId}")
    public List<ApplicationsEntity> getApplicationsByApplicant(@PathVariable Long applicantId) {
        return applicationsService.getApplicationsByApplicant(applicantId);
    }

    @PostMapping
    public ApplicationPojo apply(@RequestBody Map<String, Object> req) {
    Long applicantId = Long.valueOf(req.get("applicantId").toString());
    Long jobId = Long.valueOf(req.get("jobId").toString());
    Long resumeId = Long.valueOf(req.get("digitalResumeId").toString());
    String coverLetter = (String) req.get("coverLetter");

    ApplicationsEntity app = applicationsService.apply(applicantId, jobId, coverLetter, resumeId);
        return applicationsService.toDto(app);
    }


    public static record ApplyRequest(Long applicantId, Long jobId, String coverLetter, Object applicantProfile) {} 

   

    

    

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Map<String, String>> deleteApplication(@PathVariable Long applicationId) {
        boolean deleted = applicationsService.deleteApplication(applicationId);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Application withdrawn successfully"));
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "Application not found"));
        }
    }


}
