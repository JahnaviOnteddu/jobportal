import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationDTO } from '../models/Application.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecruiterApplication {
  private apiUrl = 'http://localhost:8080/api/applications';

  constructor(private http: HttpClient) {}

  getApplicationsByRecruiter(recruiterId: number): Observable<ApplicationDTO[]> {
    return this.http.get<ApplicationDTO[]>(`${this.apiUrl}/recruiter/${recruiterId}`);
  }

  updateApplicationStatus(applicationId: number, status: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/${applicationId}/status`, { status });
  }
}
