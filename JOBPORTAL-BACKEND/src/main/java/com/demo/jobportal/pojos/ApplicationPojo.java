package com.demo.jobportal.pojos;



import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationPojo {
    private Long applicationId;
    private LocalDateTime appliedAt;
    private String status;
    private String coverLetter;

    // job info
    private Long jobId;
    // applicant info
    private Long applicantId;
    private Long resumeId ; 
  
}


    // private String jobTitle;
    // private String jobLocation;
    // private String jobType;
    // private Boolean onsite;
    // private String companyName;
    //   private String applicantName;