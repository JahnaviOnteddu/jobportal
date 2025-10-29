package com.demo.jobportal.dao.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.demo.jobportal.pojos.ApplicationPojo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Job_Posting")
public class JobPostingEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "job_title"  , nullable = false, length = 150)
    private String jobTitle;

    @Column(name = "job_description" , columnDefinition = "TEXT", nullable = false)
    private String jobDescription;

    @Column(name = "job_location" , length = 100)
    private String jobLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type")
    private JobType jobType;

    @Column(name = "job_salaryrange", length = 100)
    private String jobSalaryRange;

    @Column(name = "job_requirements")
    private String jobRequirements ; 

   @Column(name = "yearsof_experience")
   private int yearsOfExperience ; 

   @Column(name = "onsite")
   private boolean onsite ; 

    @Column(name = "posted_at")
    private LocalDateTime postedAt;

    @Column(name = "is_active")
    private boolean isActive = true  ; 
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "recruiter_id", nullable = false)
    private RecruiterProfileEntity recruiter; 

    @PrePersist
    protected void onCreate() {
        this.postedAt = LocalDateTime.now();
    }

    public enum JobType {
        FULL_TIME,
        PART_TIME,
        INTERNSHIP,
    }

 @OneToMany(mappedBy = "jobPosting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @Fetch(FetchMode.SUBSELECT)
    private List<ApplicationsEntity> applications;

    
    
}
