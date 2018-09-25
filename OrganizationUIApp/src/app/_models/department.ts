import { OrganizationSummary } from './index';
import { Employee } from './index';

export class Department{
  id:string;
  name:string;
  organization:OrganizationSummary;
  employees:Employee[];
}
