// export interface JobApplication {
//   // applicationId?: number;
//   // jobId: number;
//   // applicantId: number;
//   // coverLetter: string;
//   // resumeLink: string;
//   // appliedAt?: string;
//   applicationId: number;
//   jobId : number ; 
//   jobTitle: string;                  // property used in template
//   recruiter?: { companyName: string }; // nested object
//   status: string;                    // application status
//   appliedAt?: Date;
// }


export interface Application {
  jobId: number;
  jobTitle: string;
  jobLocation: string;
  jobType: 'FULL_TIME'|'PART_TIME'|'INTERNSHIP';
  jobSalaryRange?: string;
  yearsOfExperience?: number;
  onsite?: boolean;
  recruiter?: { recruiterId: number, companyName: string };
}