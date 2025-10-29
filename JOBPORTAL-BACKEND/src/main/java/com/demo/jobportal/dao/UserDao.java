package com.demo.jobportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.jobportal.dao.entity.UsersEntity;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UsersEntity, Long> {
    Optional<UsersEntity> findByEmail(String email);
    Optional<UsersEntity> findByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
