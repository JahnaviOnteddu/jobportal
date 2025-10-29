import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Job } from '../models/JobPosting.model';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})


export class JobService {
  base = 'http://localhost:8080/api/jobs';

  constructor(private http: HttpClient) {}

  getJobs(page = 0, size = 10, filters: any = {}): Observable<any> {
    let params = new HttpParams().set('page', String(page)).set('size', String(size));
    Object.keys(filters || {}).forEach(k => {
      if (filters[k] !== null && filters[k] !== undefined && filters[k] !== '') {
        params = params.set(k, String(filters[k]));
      }
    });
    return this.http.get<any>(`${this.base}/active`, { params });
  }

  getJobById(id: number): Observable<any> {
  return this.http.get(`${this.base}/${id}`);
}
  applyJob(formData: FormData): Observable<any> {
  return this.http.post('http://localhost:8080/api/applications', formData);
}



  
 postJob(job: Job): Observable<any> {
  return this.http.post(`${this.base}/create`, job);
}


  getJobsByRecruiter(recruiterId: number): Observable<Job[]> {
    return this.http.get<Job[]>(`${this.base}/recruiter/${recruiterId}`);
  }





}