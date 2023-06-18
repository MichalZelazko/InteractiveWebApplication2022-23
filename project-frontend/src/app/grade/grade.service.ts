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
      catchError(this.handleError<Grade>('addGrade', 'Error while adding the grade!'))
    );
  }

  getGradesFromSubject(subjectId: number): Observable<Grade[]> {
    return this.http.get<Grade[]>(`${this.gradesUrl}/subject/${subjectId}`).pipe(
      catchError(this.handleError<Grade[]>('getGradesFromSubject', 'Error while getting grades', []))
    );
  }

  getStudentGrades(): Observable<Grade[]> {
    return this.http.get<Grade[]>(`${this.gradesUrl}/student`).pipe(
      catchError(this.handleError<Grade[]>('getStudentGrades', 'Error while getting grades!',[]))
    );
  }

  editGrade(grade: Grade): Observable<Grade> {
    return this.http.patch<Grade>(`${this.gradesUrl}/edit/${grade.id}`, grade.grade, httpOptions).pipe(
      catchError(this.handleError<Grade>('editGrade', 'Editing grade failed!'))
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
    console.log('GradeService: ' + message);
  }

}
