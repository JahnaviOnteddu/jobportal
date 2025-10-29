import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RecruiterService } from '../../../service/recruiter-service';
import { Applicant } from '../../applicant/applicant';
import { CommonModule } from '@angular/common';
import { ApplicationDTO } from '../../../models/Application.model';
import { ApplicationService } from '../../../service/application-service';
import { RecruiterApplication } from '../../../service/recruiter-application';
import { Job } from '../../../models/JobPosting.model';
import { Auth } from '../../../service/auth';


@Component({
  selector: 'app-recruiter-applications',
  imports: [CommonModule],
  templateUrl: './recruiter-applications.html',
  styleUrl: './recruiter-applications.css',
})
export class RecruiterApplications implements OnInit {

 recruiterId : number | null = null ; // later dynamic
  applications: ApplicationDTO[] = [];

  constructor(private appService: RecruiterApplication, private recruiterService : RecruiterService , private auth : Auth) {}

  ngOnInit(): void {
    // this.loadApplications();
    this.recruiterId = this.auth.getRecruiterId() ;
    this.loadJobs();
    
  }



 
    jobs: Job[] = [];
  
    
  
  
    loadJobs() {
    this.recruiterService.getJobsByRecruiter(this.recruiterId!).subscribe({
      next: (data) => {
        this.jobs = data;
        console.log('Recruiter Jobs:', this.jobs); // ✅ Move here
      },
      error: (err) => console.error('Error loading jobs', err)
    });
  }

  updateStatus(applicationId: number, status: string): void {
    this.appService.updateApplicationStatus(applicationId, status).subscribe({
      next: () => this.loadJobs(),
      error: (err) => console.error('Error updating status:', err)
    });
  }
  
}
