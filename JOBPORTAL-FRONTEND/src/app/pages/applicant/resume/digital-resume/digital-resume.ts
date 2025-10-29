import { Component, OnInit } from '@angular/core';
import { ResumeService } from '../../../../service/resume-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DigitalResume } from '../../../../models/DigitalResume.model';

@Component({
  selector: 'app-digital-resume',
  imports: [FormsModule , CommonModule],
  templateUrl: './digital-resume.html',
  styleUrl: './digital-resume.css',
})
export class DigitalResumeComponent implements OnInit {
  userId: number = 1; // 🔹 Replace later with logged-in user ID
  isEditMode: boolean = true;

  resume: DigitalResume = {
  userId : this.userId ,
  careerObjective: '',   
  education: '',
  workExperience: '',
  courses: '',
  extracurricular: '',
  skills: '',
  certifications: '',
  projects: ''
};

  constructor(private resumeService: ResumeService) {}

  ngOnInit(): void {
    this.loadResume();
  }

  loadResume(): void {
    this.resumeService.getResume(this.userId).subscribe({
      next: (data) => {
        if (data) {
          this.resume = data;
          console.log(this.resume) ; 
          this.isEditMode = false;
        }
      },
      error: () => console.log('No existing resume found for this user.')
    });
  }

  saveResume(): void {
    this.resume.userId = this.userId ; 
    this.resumeService.saveResume(this.resume).subscribe({
      next: () => {
        alert('✅ Resume saved successfully!');
        this.isEditMode = false;
      },
      
      error: () => alert('❌ Error saving resume!')
    });
  }

  toggleEdit(): void {
    this.isEditMode = !this.isEditMode;
  }
}
