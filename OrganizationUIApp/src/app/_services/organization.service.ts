import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Organization } from '../_models/index';

@Injectable()
export class OrganizationService {

  private baseUrl = 'http://localhost:8079/organization-service';
  //private baseUrl = 'http://localhost:8082';
    constructor(private http: HttpClient) { }

    getAll(isWithDepartments) {
        return this.http.get<Organization[]>(this.baseUrl + '/organizations?isWithDepartments='+isWithDepartments);
    }

    getById(id: number, isWithDepartments:boolean, isWithEmployees:boolean) {
        return this.http.get<Organization>(this.baseUrl + '/organization/' + 
          id+"?isWithDepartments="+isWithDepartments+"&isWithEmployees="+isWithEmployees);
    }

    create(organization: Organization) {
        return this.http.post(this.baseUrl + '/organizations', organization);
    }

}
