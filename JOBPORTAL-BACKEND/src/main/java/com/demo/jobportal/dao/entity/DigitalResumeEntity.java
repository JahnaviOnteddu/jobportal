package com.demo.jobportal.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "digital_resume")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DigitalResumeEntity {
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long resumeId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;

    @Column(name = "careerobjective" , columnDefinition = "TEXT")
    private String careerObjective;

    @Column(name = "education"  , columnDefinition = "TEXT" )
    private String education;

    @Column(name = "work_experience" , columnDefinition = "TEXT")
    private String workExperience;

    @Column(name = "courses" , columnDefinition = "TEXT")
    private String courses;

    @Column(name = "extra_curricular" , columnDefinition = "TEXT")
    private String extracurricular;

    @Column(name = "skills" , columnDefinition = "TEXT")
    private String skills;

    @Column(name =  "certifications" , columnDefinition = "TEXT")
    private String certifications;

    @Column(name = " projects" , columnDefinition = "TEXT")
    private String projects;



}

