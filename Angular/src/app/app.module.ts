import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HelloWorldComponent } from './hello-world/hello-world.component';
import { UserItemComponent } from './user-item/user-item.component';
import { UserListComponent } from './user-list/user-list.component';
import { ArticleComponent } from './article/article.component';
import { QuadraticEquationComponent } from './quadratic-equation/quadratic-equation.component';
import { HttpClientModule } from "@angular/common/http";
import { RedditComponent } from './reddit/reddit.component';
import { StudentsComponent } from './students/students.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {httpInterceptorProviders} from "./auth/auth-interceptor";
import {RoleGuard} from "./guards/role.guard";
import {authGuard} from "./guards/auth.guard";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'students', component: StudentsComponent },
  { path: 'quadratic-equation', component: QuadraticEquationComponent },
  { path: 'reddit', component: RedditComponent },
  { path: 'user', component: UserComponent, canActivate: [RoleGuard], data: { roles: ['ROLE_USER','ROLE_ADMIN'] },},
  { path: 'admin', component: AdminComponent, canActivate: [authGuard], data: { roles: ['ROLE_ADMIN'] },},
  { path: 'auth/login', component: LoginComponent },
  { path: 'signup', component: RegisterComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];


@NgModule({
  declarations: [
    AppComponent,
    HelloWorldComponent,
    UserItemComponent,
    UserListComponent,
    ArticleComponent,
    QuadraticEquationComponent,
    RedditComponent,
    StudentsComponent,
    HomeComponent,
    AdminComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent
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
