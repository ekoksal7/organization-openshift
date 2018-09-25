import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Organization, Department, Employee } from '../_models/index';
import { AlertService,OrganizationService, DepartmentService, EmployeeService } from '../_services/index';

@Component({
  selector: 'app-new-employee',
  templateUrl: './new-employee.component.html',
  styleUrls: ['./new-employee.component.css']
})
export class NewEmployeeComponent implements OnInit {

    employee:Employee;
    selectedDepartment:Department;
    selectedOrganization:Organization;
    organizations: Organization[] = [];
    departments: Department[];
  
    loading = false;
  
    ngOnInit() {
        this.employee=new Employee();
        //this.selectedDepartment=new Department();
        this.selectedOrganization=new Organization();
        this.getAllOrganizations();
      
    }

    constructor(
        private router: Router,
        private organizationService: OrganizationService,
        private departmentService: DepartmentService,
        private employeeService: EmployeeService,
        private alertService: AlertService) { }

    private create() {
        this.loading = true;
        this.employee.organization=this.selectedOrganization;
        this.employee.department=this.selectedDepartment;
        this.employeeService.create(this.employee)
            .subscribe(
                data => {
                    this.alertService.success('Employee created successfully', true);
                    this.router.navigate(['/employees']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
    private getAllOrganizations() {
        this.organizationService.getAll(true).subscribe(organizations => { this.organizations = organizations; 
        
          if(this.organizations!=null && this.organizations[0]!=null){
            this.selectedOrganization=this.organizations[0];
            if(this.selectedOrganization.departments!=null && 
              this.selectedOrganization.departments.length >0){
              this.departments=this.selectedOrganization.departments;
              this.selectedDepartment=this.departments[0];
            }
          }
        });
    }
  
    organizationChanged(newValue) {
      console.log("this.selectedOrganization="+this.selectedOrganization.id);
     
      this.departments=null;
      this.selectedDepartment=null;
      if(this.selectedOrganization.departments!=null && this.selectedOrganization.departments.length >0){
        
        this.departments=this.selectedOrganization.departments;
        this.selectedDepartment=this.departments[0];
      }
    }
}
