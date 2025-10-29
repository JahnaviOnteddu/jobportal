

package com.demo.jobportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.jobportal.dao.JobPostingDao;
import com.demo.jobportal.dao.RecruiterProfileDao;
import com.demo.jobportal.dao.entity.ApplicationsEntity;
import com.demo.jobportal.dao.entity.JobPostingEntity;
import com.demo.jobportal.dao.entity.RecruiterProfileEntity;
import com.demo.jobportal.dao.entity.UsersEntity;
import com.demo.jobportal.pojos.ApplicationPojo;
import com.demo.jobportal.pojos.JobPostingPojo;

@Service
public class JobPostingService {

    @Autowired
    private JobPostingDao jobPostingDao;


    @Autowired
    private RecruiterProfileDao recruiterProfileDao;


    public JobPostingEntity createJob(JobPostingPojo jobRequest) {
        RecruiterProfileEntity recruiter = recruiterProfileDao.findById(jobRequest.getRecruiterId())
                .orElseThrow(() -> new RuntimeException("Recruiter profile not found"));

        UsersEntity user = recruiter.getUser();
        if (user.getApprovalStatus() != UsersEntity.ApprovalStatus.APPROVED) {
            throw new RuntimeException("Recruiter not approved to post jobs");
        }

        JobPostingEntity job = new JobPostingEntity();
        job.setRecruiter(recruiter);
        job.setJobTitle(jobRequest.getJobTitle());
        job.setJobDescription(jobRequest.getJobDescription());
        job.setJobLocation(jobRequest.getJobLocation());
        job.setJobType(jobRequest.getJobType());
        job.setJobSalaryRange(jobRequest.getJobSalaryRange());
        job.setJobRequirements(jobRequest.getJobRequirements());
        job.setYearsOfExperience(jobRequest.getYearsOfExperience());
        job.setOnsite(jobRequest.isOnsite());

        return jobPostingDao.save(job);
    }

    public Page<JobPostingEntity> getFilteredJobs(
            String jobTitle,
            String jobLocation,
            JobPostingEntity.JobType jobType,
            Boolean onsite,
            Integer minExp,
            Integer maxExp,
            int page,
            int size,
            String sortBy) {

        if (sortBy == null || sortBy.isBlank()) sortBy = "postedAt";

        // ✅ Correct usage of Sort and PageRequest
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));

        return jobPostingDao.findFilteredJobs(jobTitle, jobLocation, jobType, onsite, minExp, maxExp, pageable);
    }

    // public JobPostingEntity getJobById(Long id) {
    //     return jobPostingDao.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Job not found"));
    // }



    public JobPostingEntity getJobById(Long id) {
        return jobPostingDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }


    // // --- entity → pojo conversion ---
    // private JobPostingPojo convertToPojo(JobPostingEntity entity) {
    //     JobPostingPojo pojo = new JobPostingPojo();
    //     pojo.setJobId(entity.getJobId());
    //     pojo.setRecruiterId(entity.getRecruiter().getRecruiterId());
    //     pojo.setJobTitle(entity.getJobTitle());
    //     pojo.setJobDescription(entity.getJobDescription());
    //     pojo.setJobLocation(entity.getJobLocation());
    //     pojo.setJobType(entity.getJobType());
    //     pojo.setJobSalaryRange(entity.getJobSalaryRange());
    //     pojo.setJobRequirements(entity.getJobRequirements());
    //     pojo.setYearsOfExperience(entity.getYearsOfExperience());
    //     pojo.setOnsite(entity.isOnsite());
    //     pojo.setActive(entity.isActive());

    //     // Convert applications list
    //     if (entity.getApplications() != null) {
    //         List<ApplicationPojo> appPojos = entity.getApplications().stream()
    //                 .map(this::convertToApplicationPojo)
    //                 .toList();
    //         pojo.setApplicants(appPojos);
    //     }

    //     return pojo;
    // }

    private ApplicationPojo convertToApplicationPojo(ApplicationsEntity app) {
        ApplicationPojo pojo = new ApplicationPojo();
        pojo.setApplicationId(app.getApplicationId());
        pojo.setAppliedAt(app.getAppliedAt());
        pojo.setStatus(app.getStatus());
        pojo.setCoverLetter(app.getCoverLetter());
        pojo.setJobId(app.getJobPosting() != null ? app.getJobPosting().getJobId() : null);
        pojo.setApplicantId(app.getApplicantProfile() != null ? app.getApplicantProfile().getApplicantId() : null);
        pojo.setResumeId(app.getDigitalResume() != null ? app.getDigitalResume().getResumeId(): null);
        return pojo;
    }




     public JobPostingEntity postJob(JobPostingPojo pojo) {
        RecruiterProfileEntity recruiter = recruiterProfileDao.findById(pojo.getRecruiterId())
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        JobPostingEntity job = new JobPostingEntity();
        job.setJobTitle(pojo.getJobTitle());
        job.setJobDescription(pojo.getJobDescription());
        job.setJobLocation(pojo.getJobLocation());
        job.setJobType(pojo.getJobType());
        job.setJobSalaryRange(pojo.getJobSalaryRange());
        job.setJobRequirements(pojo.getJobRequirements());
        job.setYearsOfExperience(pojo.getYearsOfExperience());
        job.setOnsite(pojo.isOnsite());
        job.setActive(true);
        job.setRecruiter(recruiter);

        return jobPostingDao.save(job);
    }

    public List<JobPostingEntity> getJobsByRecruiter(Long recruiterId) {
        return jobPostingDao.findByRecruiter_RecruiterId(recruiterId);
    }

}
