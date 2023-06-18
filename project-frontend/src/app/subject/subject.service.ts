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

  getSubject(id: number): Observable<Subject> {
    const url = `${this.subjectsUrl}/${id}`;
    return this.http.get<Subject>(url);
  }

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
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log('SubjectService: ' + message);
  }
}
