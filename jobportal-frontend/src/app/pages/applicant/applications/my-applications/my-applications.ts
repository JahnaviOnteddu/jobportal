import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApplicationService } from '../../../../service/application-service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Auth } from '../../../../service/auth';
import { environment } from '../../../../../environments/environment';

@Component({
  selector: 'app-my-applications',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './my-applications.html',
  styleUrls: ['./my-applications.css']
})
export class MyApplications implements OnInit {
  applications: any[] = [];
  applicantId: number | null = null;
  message = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private applicationService: ApplicationService,
    private auth: Auth
  ) {}

  ngOnInit(): void {
    this.applicantId = this.auth.getApplicantId();

    if (this.applicantId) {
      this.loadApplications();
    } else {
      this.message = '⚠️ Please log in to view your applications.';
      console.error('Applicant ID is undefined!');
    }
  }

  loadApplications(): void {
    this.http
      .get<any[]>(`${environment.apiUrl}/applications/applicant/${this.applicantId}`)
      .subscribe({
        next: (data) => {
          this.applications = data;
          if (data.length === 0) {
            this.message = 'No applications found.';
          }
        },
        error: (err) => {
          console.error('Error fetching applications:', err);
          this.message = 'Failed to load applications. Please try again.';
        }
      });
  }

  viewJobDetails(jobId: number): void {
    if (jobId) {
      this.router.navigate(['/job', jobId]);
    } else {
      console.error('Invalid job ID!');
    }
  }

  // ✅ Withdraw an application
  // ✅ Withdraw an application
withdraw(applicationId: number): void {
  if (confirm('Are you sure you want to withdraw this application?')) {
    this.applicationService.deleteApplication(applicationId).subscribe({
      next: () => {
        this.applications = this.applications.filter(app => app.applicationId !== applicationId);
        alert('Application withdrawn successfully!');
      },
      error: (err) => console.error('Error withdrawing application:', err)
    });
  }
}

}
