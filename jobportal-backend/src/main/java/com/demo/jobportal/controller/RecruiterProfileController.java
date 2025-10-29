package com.demo.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jobportal.dao.RecruiterProfileDao;
import com.demo.jobportal.dao.entity.ApplicationsEntity;
import com.demo.jobportal.dao.entity.JobPostingEntity;
import com.demo.jobportal.dao.entity.RecruiterProfileEntity;
import com.demo.jobportal.pojos.RecruiterProfilePojo;
import com.demo.jobportal.pojos.RecruiterProfileRequest;
import com.demo.jobportal.pojos.RecruiterProfileResponse;
import com.demo.jobportal.service.RecruiterProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recruiter-profile")
@RequiredArgsConstructor
public class RecruiterProfileController {


    @Autowired
    RecruiterProfileService recruiterProfileService;

    // Create profile (recruiter only, will be WAITING)
    @PostMapping("/create/{userId}")
    public ResponseEntity<RecruiterProfileResponse> createProfile(
            @PathVariable Long userId,
            @RequestBody RecruiterProfileRequest request) {

        RecruiterProfileEntity profile = recruiterProfileService.createProfile(userId, request);
        return ResponseEntity.ok(new RecruiterProfileResponse(
                profile.getRecruiterId(),
                profile.getCompanyName(),
                profile.getCompanyAddress(),
                profile.getCompanyDescription(),
                profile.getCreatedAt(),
                "Waiting for admin approval"
        ));
    }

    // Get profile (own profile)
    @GetMapping("/get/{userId}")
    public ResponseEntity<RecruiterProfileResponse> getProfile(@PathVariable Long userId) {
        RecruiterProfileResponse response = recruiterProfileService.getProfile(userId);
        return ResponseEntity.ok(response);
    }

    // Admin-only: update
    @PutMapping("/update/{recruiterId}")
    public ResponseEntity<RecruiterProfileEntity> updateProfile(
            @PathVariable Long recruiterId,
            @RequestBody RecruiterProfileRequest request) {

        RecruiterProfileEntity updated = recruiterProfileService.updateProfile(recruiterId, request);
        return ResponseEntity.ok(updated);
    }

    // Admin-only: delete
    @DeleteMapping("/delete/{recruiterId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long recruiterId) {
        recruiterProfileService.deleteProfile(recruiterId);
        return ResponseEntity.ok("Profile deleted successfully");
    }

    @GetMapping("/{recruiterId}/jobs")
    public ResponseEntity<List<JobPostingEntity>> getJobs(@PathVariable Long recruiterId) {
        return ResponseEntity.ok(recruiterProfileService.getJobsPosted(recruiterId));
    }

    // ✅ Get all applicants for recruiter's jobs
    @GetMapping("/{recruiterId}/applicants")
    public ResponseEntity<List<ApplicationsEntity>> getApplicants(@PathVariable Long recruiterId) {
        return ResponseEntity.ok(recruiterProfileService.getApplicantsForRecruiter(recruiterId));
    }
// @GetMapping("/with-jobs/{recruiterId}")
// public ResponseEntity<RecruiterProfilePojo> getRecruiterWithJobs(@PathVariable Long recruiterId) {
//     return ResponseEntity.ok(recruiterProfileService.getRecruiterWithJobs(recruiterId));
// }


}
