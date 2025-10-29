import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-applicant',
  imports: [CommonModule,RouterOutlet , RouterModule],
  templateUrl: './applicant.html',
  styleUrl: './applicant.css',
})
export class Applicant {
  
 constructor(private router: Router) {}
   isDropdownOpen = false;

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }
  
  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
