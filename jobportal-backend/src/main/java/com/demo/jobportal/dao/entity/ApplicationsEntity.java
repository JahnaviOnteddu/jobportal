package com.demo.jobportal.dao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Applications")
public class ApplicationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;


    @Column(name = "applied_at") 
    private LocalDateTime appliedAt ; 

    @Column(name = "status") 
    private String status ; 


    @Column(name = "cover_letter")
    private String coverLetter ; 

    
    @ManyToOne
    @JoinColumn(name ="applicant_id") 
    private ApplicantProfileEntity applicantProfile ; 


    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobPostingEntity jobPosting ; 

    @ManyToOne
    @JoinColumn(name = "digital_resume_id" , nullable = false) 
    private DigitalResumeEntity digitalResume ; 

    
}
