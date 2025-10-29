package com.demo.jobportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.jobportal.dao.entity.JobPostingEntity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;



@Repository
public interface JobPostingDao extends JpaRepository<JobPostingEntity, Long> {
     @Query("""
        SELECT j FROM JobPostingEntity j
        WHERE j.isActive = true
          AND (:jobTitle IS NULL OR LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :jobTitle, '%')))
          AND (:jobLocation IS NULL OR LOWER(j.jobLocation) LIKE LOWER(CONCAT('%', :jobLocation, '%')))
          AND (:jobType IS NULL OR j.jobType = :jobType)
          AND (:onsite IS NULL OR j.onsite = :onsite)
          AND (:minExp IS NULL OR j.yearsOfExperience >= :minExp)
          AND (:maxExp IS NULL OR j.yearsOfExperience <= :maxExp)
        """)
    Page<JobPostingEntity> findFilteredJobs(
        @Param("jobTitle") String jobTitle,
        @Param("jobLocation") String jobLocation,
        @Param("jobType") JobPostingEntity.JobType jobType,
        @Param("onsite") Boolean onsite,
        @Param("minExp") Integer minExp,
        @Param("maxExp") Integer maxExp,
        Pageable pageable
    );
    // List<JobPostingEntity> findByRecruiter_RecruiterId(Long recruiterId);
   // List<JobPostingEntity> findByRecruiterProfileRecruiterId(Long recruiterId);
    List<JobPostingEntity> findByRecruiter_RecruiterId(Long recruiterId);

    }


