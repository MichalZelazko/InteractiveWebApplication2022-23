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
    return this.http.get<Teacher[]>(this.teachersUrl).pipe(
      catchError(this.handleError<Teacher[]>('getTeachers', 'Error while getting teachers'))
    );
  }

  deleteTeacher(teacher: Teacher | number): Observable<Teacher> {
    const id = typeof teacher === 'number' ? teacher : teacher.id;
    const url = `${this.teachersUrl}/${id}`;
    return this.http.delete<Teacher>(url, httpOptions).pipe(
      tap(_ => alert(`Successfully deleted teacher with id=${id}`)),
      catchError(this.handleError<Teacher>('deleteStudent', 'Error while deleting student'))
    );
  }

  private handleError<T>(operation = 'operation', communicate: string, result?: T) {
    return (error: any): Observable<T> => {
      this.log(`${operation} failed: ${error.message}`);
      alert(`${communicate}`);
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log('StudentService: ' + message);
  }
}
