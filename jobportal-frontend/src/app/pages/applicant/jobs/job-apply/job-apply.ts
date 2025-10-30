import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { JobService } from '../../../../service/job-service';
import { Auth } from '../../../../service/auth';
import { environment } from '../../../../../environments/environment';

@Component({
  selector: 'app-job-apply',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, RouterModule],
  templateUrl: './job-apply.html',
  styleUrls: ['./job-apply.css']
})
export class JobApply implements OnInit {

  jobId!: number;
  applicantId: number | null = null;
  coverLetter: string = '';
  jobDetails: any;
  userResumeId: number | null = null;
  hasResume: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private jobService: JobService,
    private router: Router,
    private auth: Auth
  ) {}

  ngOnInit() {
    this.applicantId = this.auth.getApplicantId();
    this.jobId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadJobDetails();
    this.loadUserResume();
  }

  // 🔹 Fetch job details by ID
  loadJobDetails() {
    this.jobService.getJobById(this.jobId).subscribe({
      next: (data) => {
        console.log('✅ Job details loaded:', data);
        this.jobDetails = data;
      },
      error: (err) => console.error('❌ Error loading job:', err)
    });
  }

  // 🔹 Fetch user's digital resume
  loadUserResume() {
    this.http.get(`${environment.apiUrl}/resume/${this.applicantId}`).subscribe({
      next: (res: any) => {
        console.log('✅ Resume found:', res);
        this.userResumeId = res.resumeId;
        this.hasResume = true;
      },
      error: (err) => {
        console.warn('⚠️ No resume found for user:', err);
        this.hasResume = false;
      }
    });
  }

  // 🔹 Apply to job
  applyNow() {
    if (!this.userResumeId) {
      alert('Please create your digital resume before applying!');
      return;
    }

    const req = {
      applicantId: this.applicantId,
      jobId: this.jobId,
      coverLetter: this.coverLetter,
      digitalResumeId: this.userResumeId
    };

    console.log('📤 Applying with request:', req);

    this.http.post(`${environment.apiUrl}/applications`, req).subscribe({
      next: () => {
        alert('✅ Application submitted successfully!');
        this.router.navigate(['/applicant/applications']);
      },
      error: (err) => {
        console.error('❌ Failed to submit application:', err);
        alert('Failed to submit application. Please try again.');
      }
    });
  }

  // 🔹 Resume navigation actions
  editResume() {
    this.router.navigate(['/applicant/resume']);
  }

  createResume() {
    this.router.navigate(['/applicant/resume']);
  }
}
