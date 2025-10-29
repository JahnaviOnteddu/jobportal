import { User } from "./user.model";

export interface DigitalResume {
  userId : number;
  resumeId?: number;
  careerObjective: string;   
  education: string;
  workExperience: string;
  courses: string;
  extracurricular: string;
  skills: string;
  certifications: string;
  projects: string;
}

