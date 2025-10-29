package com.demo.jobportal.pojos;

import java.util.List;

import com.demo.jobportal.dao.entity.JobPostingEntity.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingPojo {
    private Long jobId ; 
    private Long recruiterId;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private JobType jobType;
    private String jobSalaryRange;
    private String jobRequirements;
    private int yearsOfExperience;
    private boolean onsite;
    private boolean isActive ; 

}
