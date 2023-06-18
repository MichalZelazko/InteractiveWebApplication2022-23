import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Grade} from "./grade.model";
import {catchError, Observable, of} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class GradeService {

  private gradesUrl = 'http://localhost:8080/grades';
  constructor(private http: HttpClient) { }

  addGrade(grade: Grade): Observable<Grade> {
    return this.http.post<Grade>(this.gradesUrl, grade, httpOptions).pipe(
      catchError(this.handleError<Grade>('addGrade'))
    );
  }

  getGradesFromSubject(subjectId: number): Observable<Grade[]> {
    return this.http.get<Grade[]>(`${this.gradesUrl}/subject/${subjectId}`).pipe(
      catchError(this.handleError<Grade[]>('getGradesFromSubject', []))
    );
  }

  getStudentGrades(): Observable<Grade[]> {
    return this.http.get<Grade[]>(`${this.gradesUrl}/student`).pipe(
      catchError(this.handleError<Grade[]>('getStudentGrades', []))
    );
  }

  editGrade(grade: Grade): Observable<Grade> {
    return this.http.patch<Grade>(`${this.gradesUrl}/edit/${grade.id}`, grade.grade, httpOptions).pipe(
      catchError(this.handleError<Grade>('editGrade'))
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
    console.log('GradeService: ' + message);
  }

}
