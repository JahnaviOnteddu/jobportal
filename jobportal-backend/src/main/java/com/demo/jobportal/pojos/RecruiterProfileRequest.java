package com.demo.jobportal.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterProfileRequest {
    private String companyName;
    private String companyAddress;
    private String companyDescription;
}
