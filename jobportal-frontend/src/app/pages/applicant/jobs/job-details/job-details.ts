import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../../../../environments/environment';

@Component({
  selector: 'app-job-details',
  standalone: true,
  imports: [],
  templateUrl: './job-details.html',
  styleUrls: ['./job-details.css']
})
export class JobDetails implements OnInit {
  job: any;

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    const jobId = this.route.snapshot.paramMap.get('id');
    if (jobId) {
      this.http.get(`${environment.apiUrl}/jobs/${jobId}`)
        .subscribe({
          next: (data) => this.job = data,
          error: (err) => console.error('❌ Error loading job details:', err)
        });
    }
  }
}
