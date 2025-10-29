package com.demo.jobportal.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.jobportal.dao.entity.DigitalResumeEntity;

@Repository
public interface DigitalResumeDao extends JpaRepository<DigitalResumeEntity, Long> {
    Optional<DigitalResumeEntity> findByUserUserId(Long userId);
}

