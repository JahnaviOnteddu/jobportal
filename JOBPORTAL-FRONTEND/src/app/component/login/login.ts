import { Component } from '@angular/core';
import { Auth } from '../../service/auth';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
  standalone: true
})
export class Login {

  email: string = '';
  password: string = '';
  error: string = '';

  constructor(private authService: Auth, private router: Router) { }

  Login() {
    this.authService.login({ email: this.email, password: this.password }).subscribe({
      next: (res: any) => {
        console.log('Login response:', res);

        // ✅ Store token, userId, and role
        localStorage.setItem('token', res.token);
        localStorage.setItem('userId', res.userId);
        localStorage.setItem('role', res.role);
        localStorage.setItem('token', res.token);


        if (res.applicantId) {
          localStorage.setItem('applicantId', res.applicantId);
        }
        if (res.recruiterId) {
          localStorage.setItem('recruiterId', res.recruiterId);
        }
        const applicantId = localStorage.getItem('applicantId');


        const role = res.role?.toUpperCase();
        const status = res.status?.toUpperCase();

        // ✅ Navigate based on role & approval status
        if (role === 'RECRUITER') {
          if (status === 'PENDING_APPROVAL') {
            alert('Your account is waiting for admin approval.');
          } else if (status === 'REJECTED') {
            alert('Your recruiter account was rejected by the admin.');
          } else {
            this.router.navigate(['/recruiter']);
          }
        } else if (role === 'APPLICANT') {
          this.router.navigate(['/applicant']);
        } else if (role === 'ADMIN') {
          this.router.navigate(['/admin']);
        }
      },
      error: (err) => {
        this.error = err.error?.message || 'Login failed';
      }
    });
  }
}




