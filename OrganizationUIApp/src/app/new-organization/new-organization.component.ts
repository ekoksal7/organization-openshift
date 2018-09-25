import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Organization } from '../_models/index';
import { AlertService,OrganizationService } from '../_services/index';

@Component({
  selector: 'app-new-organization',
  templateUrl: './new-organization.component.html',
  styleUrls: ['./new-organization.component.css']
})
export class NewOrganizationComponent{

    model: any = {};
    loading = false;

    constructor(
        private router: Router,
        private organizationService: OrganizationService,
        private alertService: AlertService) { }

    private create() {
        this.loading = true;
        this.organizationService.create(this.model)
            .subscribe(
                data => {
                    this.alertService.success('Organization created successfully', true);
                    this.router.navigate(['/organizations']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }

}
