package com.demo.jobportal.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.demo.jobportal.dao.entity.UsersEntity;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponse {
    private String message;
    private String token; 
    private UsersEntity user;
     // optional, return only if login successful
    private Long applicantId;
    private Long recruiterId;
}

