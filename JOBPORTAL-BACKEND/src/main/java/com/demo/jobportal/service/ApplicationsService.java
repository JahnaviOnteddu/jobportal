package com.demo.jobportal.service;

import com.demo.jobportal.dao.ApplicantProfileDao;
import com.demo.jobportal.dao.ApplicationsDao;
import com.demo.jobportal.dao.DigitalResumeDao;
import com.demo.jobportal.dao.JobPostingDao;
import com.demo.jobportal.dao.entity.ApplicationsEntity;
import com.demo.jobportal.pojos.ApplicationPojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ApplicationsService {

    @Autowired
    private ApplicantProfileDao applicantProfileDao;

    @Autowired
    private JobPostingDao jobPostingDao;

    @Autowired
    private ApplicationsDao applicationsDao;

    @Autowired
    private DigitalResumeDao digitalResumeDao ; 

     public ApplicationsEntity apply(Long applicantId, Long jobId, String coverLetter, Long resumeId) {
        var applicant = applicantProfileDao.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        var job = jobPostingDao.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        var resume = digitalResumeDao.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Digital resume not found"));

        // Prevent duplicate applications
        if (applicationsDao.existsByApplicantProfileAndJobPosting(applicant, job)) {
            throw new RuntimeException("Already applied to this job");
        }

        ApplicationsEntity app = new ApplicationsEntity();
        app.setApplicantProfile(applicant);
        app.setJobPosting(job);
        app.setDigitalResume(resume);
        app.setCoverLetter(coverLetter);
        app.setStatus("PENDING");
        app.setAppliedAt(LocalDateTime.now());

        return applicationsDao.save(app);
    }
    public ApplicationPojo toDto(ApplicationsEntity entity) {
        ApplicationPojo dto = new ApplicationPojo();
        dto.setApplicationId(entity.getApplicationId());
        // dto.setApplicantId(entity.get);
        dto.setJobId(entity.getJobPosting().getJobId());
        dto.setStatus(entity.getStatus());
        dto.setAppliedAt(entity.getAppliedAt());
        dto.setCoverLetter(entity.getCoverLetter());
        return dto;
    }

    public List<ApplicationPojo> getApplicantsForJob(Long jobId) {
    return applicationsDao.findByJobPosting_JobId(jobId)
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public List<ApplicationPojo> getApplicationsByRecruiter(Long recruiterId) {
        List<ApplicationsEntity> apps = applicationsDao.findByRecruiterId(recruiterId);

    return apps.stream()
        .map(app -> {
            ApplicationPojo dto = new ApplicationPojo();
            dto.setApplicationId(app.getApplicationId());
            // dto.setJobTitle(app.getJobPosting().getJobTitle());
            // dto.setApplicantName(app.getApplicantProfile().getUser().getFirstName());
            dto.setStatus(app.getStatus());
            dto.setCoverLetter(app.getCoverLetter());
            // dto.setAppliedAt(app.getAppliedAt().toString());
            
            // if (app.getDigitalResume() != null) {
            //     dto.setDigitalResume(app.getDigitalResume().getResumeId());
            // }

            return dto;
        })
        .collect(Collectors.toList());
}

    public List<ApplicationsEntity> getApplicationsByApplicant(Long applicantId) {
        List<ApplicationsEntity> apps = applicationsDao.findByApplicantProfile_ApplicantId(applicantId);

        // return apps.stream().map(app -> {
        //     ApplicationPojo dto = new ApplicationPojo();
        //     dto.setApplicationId(app.getApplicationId());
        //     dto.setStatus(app.getStatus());
        //     dto.setCoverLetter(app.getCoverLetter());
        //     dto.setJobId(app.getJobPosting().getJobId());
        //     dto.setResumeId(app.getDigitalResume().getResumeId());
        //     return dto;
        // }).collect(Collectors.toList());
        return apps ; 
    }

    public boolean deleteApplication(Long applicationId) {
        if (applicationsDao.existsById(applicationId)) {
            applicationsDao.deleteById(applicationId);
            return true;
        }
        return false;
    }



}

