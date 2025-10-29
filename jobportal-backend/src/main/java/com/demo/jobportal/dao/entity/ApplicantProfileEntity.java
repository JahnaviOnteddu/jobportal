package com.demo.jobportal.dao.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
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


@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "Applicant_Profile")
public class ApplicantProfileEntity {
    @Id
    @Column(name = "applicant_id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantId;


    @Column(name = "experience_years")
    private int experienceYears;

    @Column(name = "education" , columnDefinition = "TEXT")
    private String education;

    @Column(name =  "skills" , columnDefinition = "TEXT")
    private String skills; 
    // doubt in skills 


    @Column(name = "about_you")
    private String aboutYou ; 

    @Column(name = "project_urls")
    private String projectUrl ; 


    @OneToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user; // links to Users table

    // @OneToMany(mappedBy = "applicantProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<ApplicationsEntity> applications;

}
