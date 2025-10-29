import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApplicationDTO } from '../models/Application.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {
   private baseUrl = 'http://localhost:8080/api/applications';

  constructor(private http: HttpClient) {}

  applyJob(applicantId: number, jobId: number, coverLetter: string): Observable<any> {
    const req = { applicantId, jobId, coverLetter };
    return this.http.post(`${this.baseUrl}`, req);
  }

 getByApplicant(applicantId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/applicant/${applicantId}`);
  }

  

  getApplicationsByJobId(jobId: number): Observable<ApplicationDTO[]> {
    return this.http.get<ApplicationDTO[]>(`${this.baseUrl}/job/${jobId}`);
  }

  deleteApplication(applicationId: number) {
  return this.http.delete(`${this.baseUrl}/${applicationId}`);
}


  
}
