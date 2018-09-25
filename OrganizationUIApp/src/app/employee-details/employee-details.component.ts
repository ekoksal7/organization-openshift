import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Employee } from '../_models/index';
import { AlertService,EmployeeService } from '../_services/index';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute,private employeeService: EmployeeService,
              private alertService: AlertService) { }
  id:string;
  employee:Employee;
  
  ngOnInit() {
    
    this.route.params.subscribe(params => {
      console.log(params) //log the entire params object
      this.id=params['id'];
      console.log("id="+this.id) //log the value of id
            
      this.getEmployee(this.id);
    });
  }
  
  
    private getEmployee(id) {
      
        console.log("ert");
        this.employeeService.getById(id).subscribe(employee => { 
                  this.employee = employee;         
        });
    }

}
