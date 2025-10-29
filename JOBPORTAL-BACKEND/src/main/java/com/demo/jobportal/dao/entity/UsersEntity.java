package com.demo.jobportal.dao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "Users")
public class UsersEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId; 

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email" , unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "phone_no" , unique = true )
    private String phoneNumber ; 

    @Column(name = "password" , nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role" , nullable = false)
    private Role role;

    public enum Role {
        APPLICANT,
        RECRUITER,
        ADMIN
    }

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    // @Column(name = "is_approved")
    // private boolean isApproved ; 
    // // only for recuriter , default false. after approved it will change to true. 

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus;

    public enum ApprovalStatus {
        WAITING,
        APPROVED,
        REJECTED
    }



    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


    
}
