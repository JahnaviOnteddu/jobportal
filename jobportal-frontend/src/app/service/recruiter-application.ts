import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationDTO } from '../models/Application.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';  // ✅ import environment

@Injectable({
  providedIn: 'root'
})
export class RecruiterApplication {
  private apiUrl = `${environment.apiUrl}/applications`;  // ✅ dynamic base URL

  constructor(private http: HttpClient) {}

  getApplicationsByRecruiter(recruiterId: number): Observable<ApplicationDTO[]> {
    return this.http.get<ApplicationDTO[]>(`${this.apiUrl}/recruiter/${recruiterId}`);
  }

  updateApplicationStatus(applicationId: number, status: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/${applicationId}/status`, { status });
  }
}
