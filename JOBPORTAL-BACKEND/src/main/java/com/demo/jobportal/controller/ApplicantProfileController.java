package com.demo.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.jobportal.dao.entity.ApplicantProfileEntity;
import com.demo.jobportal.pojos.ApplicantProfilePojo;
import com.demo.jobportal.service.ApplicantProfileService;

@RestController
@RequestMapping("/api/applicant/profile")
public class ApplicantProfileController {

    @Autowired
    private ApplicantProfileService applicantProfileService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApplicantProfileEntity> createProfile(@RequestBody ApplicantProfilePojo pojo) {
        return ResponseEntity.ok(applicantProfileService.createProfile(pojo));
    }

    // READ all
    @GetMapping
    public ResponseEntity<List<ApplicantProfileEntity>> getAllProfiles() {
        return ResponseEntity.ok(applicantProfileService.getAllProfiles());
    }

    // READ by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApplicantProfileEntity> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(applicantProfileService.getProfileById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApplicantProfileEntity> updateProfile(@PathVariable Long id, @RequestBody ApplicantProfilePojo pojo) {
        return ResponseEntity.ok(applicantProfileService.updateProfile(id, pojo));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        applicantProfileService.deleteProfile(id);
        return ResponseEntity.ok("Profile deleted successfully");
    }
}
