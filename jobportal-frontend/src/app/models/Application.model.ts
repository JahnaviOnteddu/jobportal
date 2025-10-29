// export interface ApplicationDTO {
//   applicationId: number;
//   appliedAt: string;
//   status: string;
//   coverLetter?: string;
//   jobId?: number;
//   jobTitle?: string;
//   companyName?: string;
//   applicantId?: number;
//   applicantName?: string;
// }



export interface ApplicationDTO {
  applicationId: number;
  jobTitle: string;
  applicantName: string;
  status: string;
  appliedAt: string;
  coverLetter?: string;
  digitalResumeUrl?: string;
  jobId : number ; 

}
