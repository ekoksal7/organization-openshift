import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Department } from '../_models/index';
import { AlertService,DepartmentService } from '../_services/index';

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent implements OnInit {

  departments: Department[] = [];

  constructor(private router: Router,
              private departmentService: DepartmentService ,
              private alertService: AlertService) { }
  
  ngOnInit() {
        this.getAllDepartments();
    }


    private getAllDepartments() {
        this.departmentService.getAll().subscribe(departments => { this.departments = departments; });
    }

}
