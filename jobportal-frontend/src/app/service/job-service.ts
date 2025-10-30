import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Job } from '../models/JobPosting.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';  // ✅ import environment

@Injectable({
  providedIn: 'root'
})
export class JobService {
  private baseUrl = `${environment.apiUrl}/jobs`;  // ✅ updated

  constructor(private http: HttpClient) {}

  getJobs(page = 0, size = 10, filters: any = {}): Observable<any> {
    let params = new HttpParams().set('page', String(page)).set('size', String(size));
    Object.keys(filters || {}).forEach(k => {
      if (filters[k] !== null && filters[k] !== undefined && filters[k] !== '') {
        params = params.set(k, String(filters[k]));
      }
    });
    return this.http.get<any>(`${this.baseUrl}/active`, { params });
  }

  getJobById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  applyJob(formData: FormData): Observable<any> {
    return this.http.post(`${environment.apiUrl}/applications`, formData);  // ✅ updated
  }

  postJob(job: Job): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, job);
  }

  getJobsByRecruiter(recruiterId: number): Observable<Job[]> {
    return this.http.get<Job[]>(`${this.baseUrl}/recruiter/${recruiterId}`);
  }
}
