import {Component, OnInit} from '@angular/core';
import {Teacher} from "../teacher/teacher.model";
import {Student} from "../student/student.model";
import {TeacherService} from "../teacher/teacher.service";
import {StudentService} from "../student/student.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit{
  teacherList?: Teacher[];
  studentList?: Student[];

  constructor(private teacherService: TeacherService, private studentService: StudentService) { }

  ngOnInit() {
    this.getTeachers();
    this.getStudents();
  }

  getTeachers(): void{
    this.teacherService.getTeachers()
      .subscribe(teacherList => this.teacherList = teacherList);
  }

  getStudents(): void {
    this.studentService.getStudents()
      .subscribe(studentList => this.studentList = studentList);
  }

  deleteTeacher(teacher: Teacher): void {
    this.teacherService.deleteTeacher(teacher).subscribe(
      () => this.getTeachers()
    );
  }

  deleteStudent(student: Student): void {
    this.studentService.deleteStudent(student).subscribe(
      () => this.getStudents()
    );
  }
}
