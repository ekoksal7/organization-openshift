import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/index';
import { LoginComponent } from './login/index';
import { AuthGuard } from './_guards/index';
import { NewOrganizationComponent } from './new-organization/index';
import { OrganizationListComponent } from './organization-list/index';
import { OrganizationDetailsComponent } from './organization-details/index';
import { NewDepartmentComponent } from './new-department/index';
import { DepartmentListComponent } from './department-list/index';
import { DepartmentDetailsComponent } from './department-details/index';
import { NewEmployeeComponent } from './new-employee/index';
import { EmployeeListComponent } from './employee-list/index';
import { EmployeeDetailsComponent } from './employee-details/index';

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'organization', component: NewOrganizationComponent , canActivate: [AuthGuard]},
    { path: 'organization/:id' , component: OrganizationDetailsComponent , canActivate: [AuthGuard]},
    { path: 'organizations', component: OrganizationListComponent , canActivate: [AuthGuard]},
    { path: 'department', component: NewDepartmentComponent , canActivate: [AuthGuard]},
    { path: 'department/:id' , component: DepartmentDetailsComponent , canActivate: [AuthGuard]},
    { path: 'departments', component: DepartmentListComponent , canActivate: [AuthGuard]},
    { path: 'employee', component: NewEmployeeComponent , canActivate: [AuthGuard]},
    { path: 'employee/:id' , component: EmployeeDetailsComponent , canActivate: [AuthGuard]},
    { path: 'employees', component: EmployeeListComponent , canActivate: [AuthGuard]},



    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
