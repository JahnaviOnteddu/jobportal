package com.demo.jobportal.dao;

import com.demo.jobportal.dao.entity.ApplicantProfileEntity;
import com.demo.jobportal.dao.entity.ApplicationsEntity;
import com.demo.jobportal.dao.entity.JobPostingEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApplicationsDao extends JpaRepository<ApplicationsEntity, Long> {
    // Fetch all applications for a specific applicant
    
    boolean existsByDigitalResume_User_UserIdAndJobPosting_JobId(Long userId, Long jobId);
    boolean existsByDigitalResumeAndJobPosting(ApplicantProfileEntity digitalResume, JobPostingEntity jobPosting);
    boolean existsByApplicantProfileAndJobPosting(ApplicantProfileEntity applicant, JobPostingEntity job);
    List<ApplicationsEntity> findByDigitalResume_User_UserId(Long userId);
    List<ApplicationsEntity> findByJobPosting_JobId(Long jobId);
    List<ApplicationsEntity> findByApplicantProfile_ApplicantId(Long applicantId);
   // List<ApplicationsEntity> findByJobPosting_Recruiter_RecruiterId(Long recruiterId);
    @Query("SELECT a FROM ApplicationsEntity a WHERE a.jobPosting.recruiter.recruiterId = :recruiterId")
    List<ApplicationsEntity> findByRecruiterId(@Param("recruiterId") Long recruiterId);
   //  List<ApplicationsEntity> findByJobPostingRecruiterProfileRecruiterId(Long recruiterId);
    List<ApplicationsEntity> findByJobPosting_Recruiter_RecruiterId(Long recruiterId);


     @Query("""
        SELECT a 
        FROM ApplicationsEntity a 
        JOIN FETCH a.jobPosting jp 
        WHERE a.applicantProfile.applicantId = :applicantId
    """)
    List<ApplicationsEntity> findApplicationsByApplicant(@Param("applicantId") Long applicantId);
}

