import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DigitalResume } from '../models/DigitalResume.model';
import { environment } from '../../environments/environment'; // ✅ Import environment

@Injectable({
  providedIn: 'root'
})
export class ResumeService {
  private baseUrl = `${environment.apiUrl}/resume`; // ✅ use environment URL

  constructor(private http: HttpClient) {}

  // 🟢 Get Resume by User ID
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

  // 🟢 Get Applications by Job ID
  getApplicationsByJobId(jobId: number): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/applications/job/${jobId}`);
  }
}
