import {Student} from "../student/student.model";
import {Subject} from "../subject/subject.model";

export class Grade {

  id?: number;
  grade: number;
  subject: Subject;
  student: Student;

  constructor(grade: number, subject: Subject, student: Student) {
    this.grade = grade;
    this.subject = subject;
    this.student = student;
  }
}
