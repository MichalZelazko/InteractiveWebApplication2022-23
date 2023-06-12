import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {Teacher} from "./teacher.model";
import {Student} from "../student/student.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  private teachersUrl = 'http://localhost:8080/teachers';

  constructor(private http: HttpClient) { }

  getTeachers(): Observable<Teacher[]> {
    return this.http.get<Teacher[]>(this.teachersUrl);
  }

  deleteTeacher(teacher: Teacher | number): Observable<Teacher> {
    const id = typeof teacher === 'number' ? teacher : teacher.id;
    const url = `${this.teachersUrl}/${id}`;
    return this.http.delete<Teacher>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted student id=${id}`)),
      catchError(this.handleError<Teacher>('deleteStudent'))
    );
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a StudentService message with the MessageService */
  private log(message: string) {
    console.log('StudentService: ' + message);
  }
}
