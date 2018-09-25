import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Department,Organization } from '../_models/index';

@Injectable()
export class DepartmentService {

  private baseUrl = 'http://localhost:8079/department-service';
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Department[]>(this.baseUrl + '/departments');
    }

    getById(id: number) {
        return this.http.get<Department>(this.baseUrl + '/department/' + id+"?isWithEmployees=true");
    }

    create(department: Department) {
        return this.http.post(this.baseUrl + '/departments', department);
    }

}
