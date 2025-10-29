package com.demo.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.jobportal.dao.entity.DigitalResumeEntity;
import com.demo.jobportal.pojos.*;
import com.demo.jobportal.service.DigitalResumeService;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "*")
public class DigitalResumeController {

    @Autowired
    private DigitalResumeService digitalResumeService;


    @GetMapping("/{userId}")
    public DigitalResumeEntity getResume(@PathVariable Long userId) {
        return digitalResumeService.getResumeByUser(userId);
    }

    
    @PostMapping("/save")
    public DigitalResumeEntity saveResume(@RequestBody DigitalResumePojo resumeRequest) {
        return digitalResumeService.createOrUpdateResume(resumeRequest);
    }


    @DeleteMapping("/{userId}")
    public String deleteResume(@PathVariable Long userId) {
        digitalResumeService.deleteResume(userId);
        return "Resume deleted successfully";
    }
}
