package com.demo.jobportal.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyRequest {
    private Long applicantId ; 
    private Long jobId ; 
    private String coverLetter ; 
}


