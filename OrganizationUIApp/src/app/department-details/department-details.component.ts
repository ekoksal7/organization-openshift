import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Department } from '../_models/index';
import { AlertService,DepartmentService } from '../_services/index';

@Component({
  selector: 'app-department-details',
  templateUrl: './department-details.component.html',
  styleUrls: ['./department-details.component.css']
})
export class DepartmentDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute,private departmentService: DepartmentService,
              private alertService: AlertService) { }
  id:string;
  department:Department;
  
  ngOnInit() {
    
    this.route.params.subscribe(params => {
      console.log(params) //log the entire params object
      this.id=params['id'];
      console.log("id="+this.id) //log the value of id
      
      this.getDepartment(this.id);
    });
  }
  
  
    private getDepartment(id) {
        this.departmentService.getById(id).subscribe(department => { this.department = department; });
    }


}
