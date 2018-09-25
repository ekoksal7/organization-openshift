import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Organization, Department } from '../_models/index';
import { AlertService,OrganizationService, DepartmentService } from '../_services/index';

@Component({
  selector: 'app-new-department',
  templateUrl: './new-department.component.html',
  styleUrls: ['./new-department.component.css']
})
export class NewDepartmentComponent implements OnInit {

    department:Department;
    selectedOrganization:Organization;
    organizations: Organization[] = [];
    loading = false;
  
    ngOnInit() {
        this.department=new Department();
        this.selectedOrganization=new Organization();
        this.getAllOrganizations();
      
    }

    constructor(
        private router: Router,
        private organizationService: OrganizationService,
        private departmentService: DepartmentService,
        private alertService: AlertService) { }

    private create() {
        this.loading = true;
        this.department.organization=this.selectedOrganization;
        this.departmentService.create(this.department)
            .subscribe(
                data => {
                    this.alertService.success('Department created successfully', true);
                    this.router.navigate(['/departments']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
    private getAllOrganizations() {
        this.organizationService.getAll(false).subscribe(organizations => { this.organizations = organizations; 
        
          if(this.organizations!=null && this.organizations[0]!=null){
            this.selectedOrganization=this.organizations[0];
          }
        });
    }

}
