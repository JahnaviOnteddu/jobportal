package com.demo.jobportal.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigitalResumePojo {
    private Long resumeId ; 
    private Long userId;
    private String careerObjective;
    private String education;
    private String workExperience;
    private String courses;
    private String extracurricular;
    private String skills;
    private String certifications;
    private String projects;
}
