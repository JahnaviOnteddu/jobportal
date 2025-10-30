// import { HttpClient } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { Job } from '../models/JobPosting.model';
// import { Observable } from 'rxjs';
// import { Applicant } from '../pages/applicant/applicant';
// import { ApplicationDTO } from '../models/Application.model';

// @Injectable({
//   providedIn: 'root'
// })
// export class RecruiterService {



//    private apiUrl = 'http://localhost:8080/api/jobs';

//   constructor(private http: HttpClient) {}

//   getJobsByRecruiter(recruiterId: number): Observable<Job[]> {
//     return this.http.get<Job[]>(`${this.apiUrl}/recruiter/1`);
//   }

//   postJob(job: Job): Observable<Job> {
//     return this.http.post<Job>(`${this.apiUrl}`, job);
//   }

//   getApplicantsByJob(jobId: number): Observable<any[]> {
//     return this.http.get<any[]>(`${this.apiUrl}/${jobId}/applications`);
//   }

//   getMyJobs(recruiterId: number): Observable<Job[]> {
//     return this.http.get<Job[]>(`${this.apiUrl}/${recruiterId}/jobs`);
//   }
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Job } from '../models/JobPosting.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; // ✅ added import
import { ApplicationDTO } from '../models/Application.model';

@Injectable({
  providedIn: 'root'
})
export class RecruiterService {

  private apiUrl = `${environment.apiUrl}/jobs`; // ✅ use environment variable

  constructor(private http: HttpClient) {}

  getJobsByRecruiter(recruiterId: number): Observable<Job[]> {
    return this.http.get<Job[]>(`${this.apiUrl}/recruiter/${recruiterId}`);
  }

  postJob(job: Job): Observable<Job> {
    return this.http.post<Job>(`${this.apiUrl}`, job);
  }

  getApplicantsByJob(jobId: number): Observable<ApplicationDTO[]> {
    return this.http.get<ApplicationDTO[]>(`${this.apiUrl}/${jobId}/applications`);
  }

  getMyJobs(recruiterId: number): Observable<Job[]> {
    return this.http.get<Job[]>(`${this.apiUrl}/recruiter/${recruiterId}/jobs`);
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('recruiterId');
  }
}

//   logout(): void {
//     // localStorage.removeItem('token');
//     // localStorage.removeItem('recruiterId');
//   }
// }
