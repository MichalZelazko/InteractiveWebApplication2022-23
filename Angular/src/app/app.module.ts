import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HelloWorldComponent } from './hello-world/hello-world.component';
import { UserItemComponent } from './user-item/user-item.component';
import { UserListComponent } from './user-list/user-list.component';
import { ArticleComponent } from './article/article.component';
import { QuadraticEquationComponent } from './quadratic-equation/quadratic-equation.component';
import { HttpClientModule } from "@angular/common/http";
import {appRoutingModule} from "./app.routing";
import { RedditComponent } from './reddit/reddit.component';
import { StudentsComponent } from './students/students.component';
import { HomeComponent } from './home/home.component';

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
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    appRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
