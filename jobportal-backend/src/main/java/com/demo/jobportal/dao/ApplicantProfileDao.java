package com.demo.jobportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.jobportal.dao.entity.ApplicantProfileEntity;
import com.demo.jobportal.dao.entity.UsersEntity;

@Repository
public interface ApplicantProfileDao extends JpaRepository<ApplicantProfileEntity, Long> {
    ApplicantProfileEntity findByUser_UserId(Long userId);
    ApplicantProfileEntity findByUser(UsersEntity user);

}
