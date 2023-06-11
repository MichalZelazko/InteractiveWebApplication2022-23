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
import { StudentComponent } from './student/student.component';
import { TeacherComponent } from './teacher/teacher.component';
import { GradeComponent } from './grade/grade.component';
import { SubjectComponent } from './subject/subject.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'students', component: StudentComponent, canActivate: [authGuard], data: { roles: ['ROLE_ADMIN', 'ROLE_STUDENT'] },},
  { path: 'subjects', component: SubjectComponent, canActivate: [authGuard], data: { roles: ['ROLE_ADMIN', "ROLE_TEACHER"] },},
  { path: 'auth/signin', component: LoginComponent },
  { path: 'auth/signup', component: RegisterComponent, canActivate: [authGuard], data: { roles: ['ROLE_ADMIN'] },},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    StudentComponent,
    TeacherComponent,
    GradeComponent,
    SubjectComponent
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