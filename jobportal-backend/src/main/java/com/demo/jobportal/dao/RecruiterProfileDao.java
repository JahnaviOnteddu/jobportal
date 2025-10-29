package com.demo.jobportal.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.jobportal.dao.entity.RecruiterProfileEntity;
import com.demo.jobportal.dao.entity.UsersEntity;
import com.demo.jobportal.pojos.RecruiterProfilePojo;

public interface RecruiterProfileDao extends JpaRepository<RecruiterProfileEntity, Long> {
    RecruiterProfileEntity findByUserUserId(Long userId);

 @Query("""
    SELECT DISTINCT r 
    FROM RecruiterProfileEntity r
    LEFT JOIN FETCH r.jobPostings jp
    WHERE r.recruiterId = :recruiterId
""")
RecruiterProfileEntity findRecruiterWithJobPostings(@Param("recruiterId") Long recruiterId);
 RecruiterProfileEntity findByUser(UsersEntity user);


}
