import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee,Department,Organization } from '../_models/index';

@Injectable()
export class EmployeeService {

    private baseUrl = 'http://localhost:8079/employee-service';
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Employee[]>(this.baseUrl + '/employees');
    }

    getById(id: number) {
        return this.http.get<Employee>(this.baseUrl + '/employee/' + id);
    }

    create(employee: Employee) {
        return this.http.post(this.baseUrl + '/employees', employee);
    }

}
