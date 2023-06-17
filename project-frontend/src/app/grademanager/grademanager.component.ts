import {Component, OnInit} from '@angular/core';
import {Student} from "../student/student.model";
import {StudentService} from "../student/student.service";

@Component({
  selector: 'app-grademanager',
  templateUrl: './grademanager.component.html',
  styleUrls: ['./grademanager.component.css']
})
export class GrademanagerComponent implements OnInit {
  studentList?: Student[];
  student?: Student;


  constructor(private studentService: StudentService) {}

  ngOnInit() {
    this.getStudents();
  }

  getStudents(): void {
    this.studentService.getStudents()
      .subscribe(studentList => this.studentList = studentList);
  }
}
