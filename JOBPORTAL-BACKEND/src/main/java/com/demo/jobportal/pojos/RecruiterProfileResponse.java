package com.demo.jobportal.pojos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterProfileResponse {
    private Long recruiterId;
    private String companyName;
    private String companyAddress;
    private String companyDescription;
    private LocalDateTime createdAt;
    private String message; // status message like waiting, approved
}

