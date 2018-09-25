import { OrganizationSummary } from './index';
import { DepartmentSummary } from './index';
export class Employee{
  id:string;
  name:string;
  birthDate:Date;
  position:string;
  organization:OrganizationSummary;
  department:DepartmentSummary;
}
