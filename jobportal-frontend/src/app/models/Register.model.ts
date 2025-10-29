export enum Role {
  APPLICANT = 'APPLICANT',
  RECRUITER = 'RECRUITER' , 
  ADMIN = 'ADMIN'
}

export interface RegisterModel {
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  password: string;
  role: Role;
}
