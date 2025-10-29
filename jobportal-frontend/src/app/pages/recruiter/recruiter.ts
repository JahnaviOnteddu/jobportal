import { Component, OnInit } from '@angular/core';
import { Auth } from '../../service/auth';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RecruiterService } from '../../service/recruiter-service';
import { Job } from '../../models/JobPosting.model';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-recruiter',
  imports: [FormsModule , CommonModule  , RouterLink , RouterOutlet],
  templateUrl: './recruiter.html',
  styleUrl: './recruiter.css',
})
export class Recruiter implements OnInit {
   approved = false;
  recruiterId : number | null = null ;
  constructor(private authService: Auth , private recruiterService: RecruiterService , private router : Router) {}

  ngOnInit(): void {
    const user = this.authService.getCurrentUser();
    if(user && user.role === 'RECRUITER') {
      this.approved = user.approvalStatus === 'APPROVED';
    }
    this.recruiterId = this.authService.getRecruiterId() ;
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


  logout(): void {
    // clear session and navigate to login
    // localStorage.clear();
    this.router.navigate(['/login']);
  }
}
