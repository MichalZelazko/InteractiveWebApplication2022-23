import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, of, pipe, tap} from "rxjs";
import {Subject} from "./subject.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  private subjectsUrl = 'http://localhost:8080/subjects';
  constructor(private http: HttpClient) {}

  getTeachersSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>(`${this.subjectsUrl}/teacher`);
  }

  getSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>(this.subjectsUrl);
  }


  addSubject(subject: Subject): Observable<Subject> {
    return this.http.post<Subject>(this.subjectsUrl, subject, httpOptions).pipe(
      catchError(this.handleError<Subject>('addSubject'))
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
