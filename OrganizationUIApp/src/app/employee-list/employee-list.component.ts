import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Employee } from '../_models/index';
import { AlertService,EmployeeService } from '../_services/index';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: Employee[] = [];

  constructor(private router: Router,
              private employeeService: EmployeeService ,
              private alertService: AlertService) { }
  
  ngOnInit() {
        this.getAllEmployees();
    }


    private getAllEmployees() {
        this.employeeService.getAll().subscribe(employees => { this.employees = employees; });
    }

}
