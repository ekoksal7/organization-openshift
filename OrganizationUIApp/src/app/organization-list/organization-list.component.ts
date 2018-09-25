import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Organization } from '../_models/index';
import { AlertService,OrganizationService } from '../_services/index';

@Component({
  selector: 'app-organization-list',
  templateUrl: './organization-list.component.html',
  styleUrls: ['./organization-list.component.css']
})
export class OrganizationListComponent implements OnInit {
  
  organizations: Organization[] = [];

  constructor(private router: Router,
              private organizationService: OrganizationService,
              private alertService: AlertService) { }
  
  ngOnInit() {
        this.getAllOrganizations();
    }


    private getAllOrganizations() {
        this.organizationService.getAll(false).subscribe(organizations => { this.organizations = organizations; });
    }

}
