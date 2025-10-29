import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-job-details',
  imports: [],
  templateUrl: './job-details.html',
  styleUrl: './job-details.css',
})
export class JobDetails implements OnInit{
  job: any;

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    const jobId = this.route.snapshot.paramMap.get('id');
    if (jobId) {
      this.http.get(`http://localhost:8080/api/jobs/${jobId}`)
        .subscribe({
          next: (data) => this.job = data,
          error: (err) => console.error('Error loading job details:', err)
        });
    }
  }
}
