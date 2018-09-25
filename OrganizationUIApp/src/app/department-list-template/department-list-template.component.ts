import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { Department } from '../_models/index';
import { AlertService } from '../_services/index';

@Component({
  selector: 'app-department-list-template',
  templateUrl: './department-list-template.component.html',
  styleUrls: ['./department-list-template.component.css']
})
export class DepartmentListTemplateComponent implements OnInit {

  @Input() departments: Department[] = [];
  
  @Input() isFromOrganization:boolean;

  constructor(private router: Router,
              private alertService: AlertService) { }
  
  ngOnInit() {
   
  }


}
