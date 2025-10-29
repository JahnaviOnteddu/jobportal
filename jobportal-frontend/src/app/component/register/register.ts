import { Component } from '@angular/core';
import { Auth } from '../../service/auth';
import { User } from '../../models/user.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RegisterModel, Role } from '../../models/Register.model';

@Component({
  selector: 'app-register',
  imports: [FormsModule , CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
  standalone : true 
})
export class Register {
  roles = ['APPLICANT', 'RECRUITER', 'ADMIN']; // dropdown
  registerData: RegisterModel = {
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    password: '',
    role: Role.APPLICANT
  };
  confirmPassword = '' ; 

  error = '' ; 

 

  constructor(private authService: Auth) {}

  register() {
    if (this.registerData.password !== this.confirmPassword) {
      this.error = "Passwords do not match";
      return;
    }

    this.authService.register(this.registerData).subscribe({
      
      next: res => alert('Registration successful!'),
      error: err => alert('Registration failed') 
    });
     console.log(this.registerData) ; 
  }

}
