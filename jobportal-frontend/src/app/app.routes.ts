import { Routes } from '@angular/router';
import { Home } from './component/home/home';
import { Contact } from './component/contact/contact';
import { Login } from './component/login/login';
import { Register } from './component/register/register';
import { Applicant } from './pages/applicant/applicant';
import { Recruiter } from './pages/recruiter/recruiter';
import { JobListing } from './pages/applicant/jobs/job-listing/job-listing';
import { JobApply } from './pages/applicant/jobs/job-apply/job-apply';
import { MyApplications } from './pages/applicant/applications/my-applications/my-applications';
import { DigitalResumeComponent } from './pages/applicant/resume/digital-resume/digital-resume';
import { PostJob } from './pages/recruiter/post-job/post-job';
import { RecruiterApplications } from './pages/recruiter/recruiter-applications/recruiter-applications';
import { JobDetails } from './pages/applicant/jobs/job-details/job-details';
import { RecruiterService } from './service/recruiter-service';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'contact', component: Contact },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
 // {path : 'applicant' , component : Applicant} ,
  {path : 'recruiter' , component : Recruiter} ,
  //{path : '' , redirectTo :'/login' , pathMatch : 'full'}
   {
    path: 'applicant',
    component: Applicant,
    children: [
      // { path: '', redirectTo: 'jobs', pathMatch: 'full' },
      { path: 'jobs', component: JobListing },
      { path: 'jobs/apply/:id', component: JobApply },
      { path: 'applications', component: MyApplications },
      { path: 'resume', component: DigitalResumeComponent }
    ]
  },
  {path : 'job/:id' ,component :JobDetails} , 
 { path: 'recruiter/post-job', component:PostJob  },
  { path: 'recruiter/recruiter-applications', component: RecruiterApplications}
];
