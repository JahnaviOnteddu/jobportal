package com.demo.jobportal.dao.entity;




import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "Recruiter_Profile")
public class RecruiterProfileEntity {
    
    @Id
    @Column(name = "recruiter_id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruiterId;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "company_address" )
    private String companyAddress;

   
    @Column(name =  "company_description")
    private String companyDescription ; 

    @Column(name = "created_at")
    private LocalDateTime createdAt ; 
    // after approval of admin 

    
    @OneToOne
    @JoinColumn(name = "user_id" ,unique = true)
    private UsersEntity user; // link to Users table

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @Fetch(FetchMode.SUBSELECT)
    private List<JobPostingEntity> jobPostings; 
    
}
