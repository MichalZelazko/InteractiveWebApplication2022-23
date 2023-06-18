import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import {httpInterceptorProviders} from "./auth/auth.interceptor";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {authGuard} from "./guards/auth.guard";
import { SubjectComponent } from './subject/subject.component';
import { AdminComponent } from './admin/admin.component';
import { GradebookComponent } from './gradebook/gradebook.component';
import { GrademanagerComponent } from './grademanager/grademanager.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'subjects', component: SubjectComponent, canActivate: [authGuard], data: { roles: ['ROLE_ADMIN', "ROLE_TEACHER"] },},
  { path: 'auth/signin', component: LoginComponent },
  { path: 'auth/signup', component: RegisterComponent, canActivate: [authGuard], data: { roles: ['ROLE_ADMIN'] },},
  { path: 'admin', component: AdminComponent, canActivate: [authGuard], data: { roles: ['ROLE_ADMIN'] },},
  { path: 'subjects/:id', component: GrademanagerComponent, canActivate: [authGuard], data: { roles: ['ROLE_ADMIN', "ROLE_TEACHER"] },},
  { path: 'gradebook', component: GradebookComponent, canActivate: [authGuard], data: { roles: ['ROLE_ADMIN', "ROLE_STUDENT"] },},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    SubjectComponent,
    AdminComponent,
    GradebookComponent,
    GrademanagerComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
