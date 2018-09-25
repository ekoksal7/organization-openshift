import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { routing } from './app.routing';

import { AlertComponent } from './_directives/alert/index';
import { AuthGuard } from './_guards/index';
import { JwtInterceptor } from './_helpers/index';
import { AlertService, AuthenticationService, 
        OrganizationService, DepartmentService, EmployeeService } from './_services/index';
import { HomeComponent } from './home/index';
import { LoginComponent } from './login/index';
import { NewOrganizationComponent } from './new-organization/new-organization.component';
import { OrganizationListComponent } from './organization-list/organization-list.component';
import { OrganizationDetailsComponent } from './organization-details/organization-details.component';
import { DepartmentListComponent } from './department-list/department-list.component';
import { DepartmentListTemplateComponent } from './department-list-template/department-list-template.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeListTemplateComponent } from './employee-list-template/employee-list-template.component';
import { DepartmentDetailsComponent } from './department-details/department-details.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { NewDepartmentComponent } from './new-department/new-department.component';
import { NewEmployeeComponent } from './new-employee/new-employee.component';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        routing
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        HomeComponent,
        LoginComponent,
        AlertComponent,
        NewOrganizationComponent,
        OrganizationListComponent,
        OrganizationDetailsComponent,
        DepartmentListComponent,
        DepartmentListTemplateComponent,
        EmployeeListComponent,
        EmployeeListTemplateComponent,
        DepartmentDetailsComponent,
        EmployeeDetailsComponent,
        NewDepartmentComponent,
        NewEmployeeComponent
    ],
    providers: [
        AuthGuard,
        AlertService,
        AuthenticationService,
        OrganizationService,
        DepartmentService,
        EmployeeService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: JwtInterceptor,
            multi: true
        },
        
        
        // provider used to create fake backend
        // fakeBackendProvider
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }
