package com.demo.jobportal.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jobportal.dao.ApplicationsDao;
import com.demo.jobportal.dao.JobPostingDao;
import com.demo.jobportal.dao.RecruiterProfileDao;
import com.demo.jobportal.dao.UserDao;
import com.demo.jobportal.dao.entity.ApplicantProfileEntity;
import com.demo.jobportal.dao.entity.ApplicationsEntity;
import com.demo.jobportal.dao.entity.JobPostingEntity;
import com.demo.jobportal.dao.entity.RecruiterProfileEntity;
import com.demo.jobportal.dao.entity.UsersEntity;
import com.demo.jobportal.pojos.ApplicantProfilePojo;
import com.demo.jobportal.pojos.JobPostingPojo;
import com.demo.jobportal.pojos.RecruiterProfilePojo;
import com.demo.jobportal.pojos.RecruiterProfileRequest;
import com.demo.jobportal.pojos.RecruiterProfileResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruiterProfileService {

    @Autowired
    RecruiterProfileDao recruiterProfileDao;

    @Autowired
    UserDao usersDao;

    @Autowired
    JobPostingDao jobPostingDao ; 

    @Autowired
    ApplicationsDao applicationsDao ; 

    // Create profile
    public RecruiterProfileEntity createProfile(Long userId, RecruiterProfileRequest request) {
        UsersEntity user = usersDao.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Only for recruiters
        if (user.getRole() != UsersEntity.Role.RECRUITER) {
            throw new RuntimeException("Only recruiters can create profile");
        }

        RecruiterProfileEntity profile = new RecruiterProfileEntity();
        profile.setUser(user);
        profile.setCompanyName(request.getCompanyName());
        profile.setCompanyAddress(request.getCompanyAddress());
        profile.setCompanyDescription(request.getCompanyDescription());
        profile.setCreatedAt(null); // will set after approval

        return recruiterProfileDao.save(profile);
    }

    // Get profile (anyone can view their own)
    public RecruiterProfileResponse getProfile(Long userId) {
       RecruiterProfileEntity profile = recruiterProfileDao.findByUserUserId(userId); 



        String message;
        switch (profile.getUser().getApprovalStatus()) {
            case WAITING -> message = "Waiting for admin approval";
            case REJECTED -> message = "Recruiter registration rejected";
            case APPROVED -> message = "Approved";
            default -> message = "Unknown";
        }

        return new RecruiterProfileResponse(
            profile.getRecruiterId(),
            profile.getCompanyName(),
            profile.getCompanyAddress(),
            profile.getCompanyDescription(),
            profile.getCreatedAt(),
            message
        );
    }

    // Admin-only: update profile
    public RecruiterProfileEntity updateProfile(Long recruiterId, RecruiterProfileRequest request) {
        RecruiterProfileEntity profile = recruiterProfileDao.findById(recruiterId)
            .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setCompanyName(request.getCompanyName());
        profile.setCompanyAddress(request.getCompanyAddress());
        profile.setCompanyDescription(request.getCompanyDescription());

        return recruiterProfileDao.save(profile);
    }

    // Admin-only: delete profile
    public void deleteProfile(Long recruiterId) {
        RecruiterProfileEntity profile = recruiterProfileDao.findById(recruiterId)
            .orElseThrow(() -> new RuntimeException("Profile not found"));
        recruiterProfileDao.delete(profile);
    }
     public List<JobPostingEntity> getJobsPosted(Long recruiterId) {
        return jobPostingDao.findByRecruiter_RecruiterId(recruiterId);
    }

    // ✅ Get all applicants who applied to recruiter's jobs
    public List<ApplicationsEntity> getApplicantsForRecruiter(Long recruiterId) {
        return applicationsDao.findByJobPosting_Recruiter_RecruiterId(recruiterId);
    }



//     public RecruiterProfilePojo getRecruiterWithJobs(Long recruiterId) {
//         RecruiterProfileEntity recruiterEntity =
//                 recruiterProfileDao.findRecruiterWithFullDetails(recruiterId);

//         if (recruiterEntity == null)
//             throw new RuntimeException("Recruiter not found with id: " + recruiterId);

//         // ✅ Explicit typing fixes the Stream<Object> issue
// List<JobPostingPojo> jobPostings = recruiterEntity.getJobPostings()
//     .stream()
//     .map((JobPostingEntity job) -> {
//         // map applicants (as before)
//         List<ApplicantProfilePojo> applicants = job.getApplications()
//             .stream()
//             .map((ApplicationsEntity app) -> {
//                 ApplicantProfileEntity ap = app.getApplicantProfile();
//                 if (ap == null) return null;
//                 ApplicantProfilePojo applicantPojo = new ApplicantProfilePojo();
//                 applicantPojo.setApplicantId(ap.getApplicantId());
//                 applicantPojo.setExperienceYears(ap.getExperienceYears());
//                 applicantPojo.setEducation(ap.getEducation());
//                 applicantPojo.setSkills(ap.getSkills());
//                 applicantPojo.setAboutYou(ap.getAboutYou());
//                 applicantPojo.setProjectUrl(ap.getProjectUrl());
//                 applicantPojo.setUserId(ap.getUser().getUserId());
//                 return applicantPojo;
//             })
//             .filter(Objects::nonNull)
//             .collect(Collectors.toList());

//         // build JobPostingPojo via setters (no-args)
//         JobPostingPojo jp = new JobPostingPojo();
//         jp.setJobId(job.getJobId());
//         jp.setRecruiterId(recruiterEntity.getRecruiterId());
//         jp.setJobTitle(job.getJobTitle());
//         jp.setJobDescription(job.getJobDescription());
//         jp.setJobLocation(job.getJobLocation());
//         jp.setJobType(job.getJobType()); // ensure types align
//         jp.setJobSalaryRange(job.getJobSalaryRange());
//         jp.setJobRequirements(job.getJobRequirements());
//         jp.setYearsOfExperience(job.getYearsOfExperience());
//         jp.setOnsite(job.isOnsite());
//         // jp.setApplicants(applicants);
//         jp.setActive(job.isActive());

//         return jp;
//     })
//     .collect(Collectors.toList());
//         // ✅ Finally, return recruiter profile pojo
//         return new RecruiterProfilePojo(
//                 recruiterEntity.getRecruiterId(),
//                 recruiterEntity.getCompanyName(),
//                 recruiterEntity.getCompanyAddress(),
//                 recruiterEntity.getCompanyDescription(),
//                 recruiterEntity.getUser().getUserId(),
//                 jobPostings
//         );
//     }

}




