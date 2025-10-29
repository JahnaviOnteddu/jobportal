import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DigitalResume } from '../models/DigitalResume.model';

@Injectable({
  providedIn: 'root'
})
export class ResumeService {
  private baseUrl = 'http://localhost:8080/api/resume';

  constructor(private http: HttpClient) {}

  
  getResume(userId: number): Observable<DigitalResume> {
    return this.http.get<DigitalResume>(`${this.baseUrl}/${userId}`);
  }

  // 🟢 Create or Update Resume
  saveResume(resume: DigitalResume): Observable<DigitalResume> {
    return this.http.post<DigitalResume>(`${this.baseUrl}/save`, resume);
  }

  // 🔴 Delete Resume
  deleteResume(userId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${userId}`, { responseType: 'text' });
  }
  getApplicationsByJobId(jobId: number): Observable<any[]> {
  return this.http.get<any[]>(`http://localhost:8080/api/applications/job/${jobId}`);
}

}
