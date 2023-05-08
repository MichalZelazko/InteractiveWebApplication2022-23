import { Routes, RouterModule } from '@angular/router';

import {AppComponent} from "./app.component";
import {QuadraticEquationComponent} from "./quadratic-equation/quadratic-equation.component";
import {RedditComponent} from "./reddit/reddit.component";
import {HomeComponent} from "./home/home.component";
import {StudentsComponent} from "./students/students.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'quadratic-equation', component: QuadraticEquationComponent },
  { path: 'reddit', component: RedditComponent},
  { path: 'students', component: StudentsComponent},

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

export const appRoutingModule = RouterModule.forRoot(routes);
