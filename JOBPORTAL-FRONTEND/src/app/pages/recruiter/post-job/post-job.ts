import { Component, Input, OnInit } from '@angular/core';
import { RecruiterService } from '../../../service/recruiter-service';
import { Job } from '../../../models/JobPosting.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { JobService } from '../../../service/job-service';
import { Auth } from '../../../service/auth';

@Component({
  selector: 'app-post-job',
  imports: [FormsModule , CommonModule ],
  templateUrl: './post-job.html',
  styleUrl: './post-job.css',
})
export class PostJob implements OnInit{
//  recruiterId : number | null = null ;

 constructor(private jobService: JobService , private auth : Auth) {}
 job!: Job;
 ngOnInit(): void {
      const recruiterId = this.auth.getRecruiterId();
    this.job = {
      recruiterId: recruiterId ?? 0, // default to 0 if null
      jobTitle: '',
      jobDescription: '',
      jobLocation: '',
      jobType: '',
      jobSalaryRange: '',
      jobRequirements: '',
      yearsOfExperience: 0,
      onsite: true,
      isActive: true
    };
 }
 

postJob() {
  this.jobService.postJob(this.job).subscribe({
    
    next: (response) => {
      console.log(this.job) ; 
      console.log('Job posted successfully:', response);
      alert('Job posted successfully!');
    },
    error: (error) => {
      console.log(this.job) ; 
      console.error('Error posting job:', error);
      alert('Failed to post job. Check console for details.');
    }
  });
}



  


 

  
}
