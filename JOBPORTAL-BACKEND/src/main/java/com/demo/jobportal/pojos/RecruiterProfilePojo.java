package com.demo.jobportal.pojos;

import java.util.List;

import com.demo.jobportal.dao.JobPostingDao;
import com.demo.jobportal.dao.entity.JobPostingEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterProfilePojo {
    private Long recruiterId;
    private String companyName;
    private String companyAddress;
    private String companyDescription;
    private Long userId; 

}
