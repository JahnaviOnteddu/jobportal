package com.demo.jobportal.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantProfilePojo {
    private Long applicantId;
    private int experienceYears;
    private String education;
    private String skills;       // keep it as comma-separated list
    private String aboutYou;
    private String projectUrl;
    private Long userId;         // to link with UsersEntity
}