import { Component, OnInit } from '@angular/core';
import { FormControl, FormsModule } from '@angular/forms';
import { JobService } from '../../../../service/job-service';
import { CommonModule } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-job-listing',
  imports: [FormsModule , CommonModule ,NgxPaginationModule , RouterLink],
  templateUrl: './job-listing.html',
  styleUrls: ['./job-listing.css'],
  standalone : true 
})
export class JobListing implements OnInit {
 jobs: any[] = [];
currentPage = 1; // ✅ start from 1 instead of 0
pageSize = 8;
totalJobs = 0;

filters: any = { jobTitle: '', jobLocation: '', jobType: '', onsite: '' };

constructor(private jobService: JobService) {}

ngOnInit() {
  this.loadJobs();
}

loadJobs() {
  // Backend expects 0-based index → send currentPage - 1
  this.jobService.getJobs(this.currentPage - 1, this.pageSize, this.filters)
    .subscribe(res => {
      this.jobs = res.content || res;
      this.totalJobs = res.totalElements ?? (this.jobs?.length ?? 0);
    });
}

onFilterChange() {
  this.currentPage = 1;
  this.loadJobs();
}

onPageChange(page: number) {
  this.currentPage = page;
  this.loadJobs();
}

}