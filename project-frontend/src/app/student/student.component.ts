import {Component, OnInit} from '@angular/core';
import {Student} from "./student.model";
import {StudentService} from "./student.service";

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit{
  studentList?: Student[];
  student?: Student;

  constructor(private studentService: StudentService) { }

  ngOnInit() { this.getStudents(); }

  getStudents(): void {
    this.studentService.getStudents()
      .subscribe(studentList => this.studentList = studentList);
  }

  deleteStudent(student: Student): void {
    this.studentService.deleteStudent(student).subscribe(
      () => this.getStudents()
    );
  }

}
