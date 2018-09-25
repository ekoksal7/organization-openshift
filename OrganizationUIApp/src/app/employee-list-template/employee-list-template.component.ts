import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { Employee } from '../_models/index';
import { AlertService } from '../_services/index';

@Component({
  selector: 'app-employee-list-template',
  templateUrl: './employee-list-template.component.html',
  styleUrls: ['./employee-list-template.component.css']
})
export class EmployeeListTemplateComponent implements OnInit {

  @Input() employees: Employee[] = [];
  
  @Input() isFromDepartment:boolean;

  constructor(private router: Router,
              private alertService: AlertService) { }
  
  ngOnInit() {
   
  }
}
