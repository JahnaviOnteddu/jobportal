import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { Register } from '../component/register/register';
import { RegisterModel } from '../models/Register.model';
import { environment } from '../../environments/environment';




@Injectable({
  providedIn: 'root'
})
export class Auth {
   
private baseUrl = environment.apiUrl;
  private baseUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  register(user: RegisterModel): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user);
  }

  login(data : any): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, data);
  }

  getCurrentUser(): any {
    return JSON.parse(localStorage.getItem('user') || '{}');
  }

  setCurrentUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  getApplicantId(): number | null {
    const id = localStorage.getItem('applicantId');
    return id ? Number(id) : null;
  }

  getRecruiterId(): number | null {
    const id = localStorage.getItem('recruiterId');
    return id ? Number(id) : null;
  }
}
