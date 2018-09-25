import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Organization } from '../_models/index';
import { AlertService,OrganizationService } from '../_services/index';

@Component({
  selector: 'app-organization-details',
  templateUrl: './organization-details.component.html',
  styleUrls: ['./organization-details.component.css']
})
export class OrganizationDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute,private organizationService: OrganizationService,
              private alertService: AlertService) { }
  id:string;
  organization:Organization;
  
  ngOnInit() {
    
    this.route.params.subscribe(params => {
      console.log(params) //log the entire params object
      this.id=params['id'];
      console.log("id="+this.id) //log the value of id
      
      this.getOrganization(this.id);
    });
  }
  
  
    private getOrganization(id) {
        this.organizationService.getById(id,true,false).subscribe(organization => { this.organization = organization; });
    }

}
