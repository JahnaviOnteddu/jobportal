export interface User {
  userId : number ; 
  firstName: string;
  lastName?: string;
  email: string;
  phoneNumber?: string;
  password: string;
  role?: string;
  approvalStatus?: string;
}
