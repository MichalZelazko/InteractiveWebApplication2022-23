import { Routes, RouterModule } from '@angular/router';

import {AppComponent} from "./app.component";
import {QuadraticEquationComponent} from "./quadratic-equation/quadratic-equation.component";
import {RedditComponent} from "./reddit/reddit.component";

const routes: Routes = [
  { path: '', component: RedditComponent },
  { path: 'quadratic-equation', component: QuadraticEquationComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

export const appRoutingModule = RouterModule.forRoot(routes);
