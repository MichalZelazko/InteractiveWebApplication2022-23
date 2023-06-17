import {Component, OnInit} from '@angular/core';
import {Student} from "../student/student.model";
import {StudentService} from "../student/student.service";
import {GradeService} from "../grade/grade.service";
import {Grade} from "../grade/grade.model";
import {ActivatedRoute} from "@angular/router";
import {Subject} from "../subject/subject.model";
import {SubjectService} from "../subject/subject.service";

@Component({
  selector: 'app-grademanager',
  templateUrl: './grademanager.component.html',
  styleUrls: ['./grademanager.component.css']
})

export class GrademanagerComponent implements OnInit {
  form: any = {};
  studentList?: Student[];
  student?: Student;
  gradeToAdd?: Grade;

  gradeList?: Grade[];

  grade?: Grade;

  subject?: Subject;

  errorMessage = '';

  subjectId?: number;


  constructor(private studentService: StudentService, private gradeService: GradeService, private subjectService: SubjectService, private route: ActivatedRoute) {}

  ngOnInit() {
    this.getStudents();
    this.subjectId = Number(this.route.snapshot.paramMap.get('id'));
    this.subjectService.getSubject(this.subjectId).subscribe(subject => this.subject = subject);
    this.getGrades();
  }

  getStudents(): void {
    this.studentService.getStudents()
      .subscribe(studentList => this.studentList = studentList);
  }

  getGrades(): void {
    if(this.subjectId){
    this.gradeService.getGradesFromSubject(this.subjectId)
      .subscribe(gradeList => this.gradeList = gradeList);
    }
  }

  addGrade(): void {
    this.student = this.studentList?.find(student => student.id === Number(this.form.student));
    if (this.subject && this.student) {
      this.gradeToAdd = new Grade(this.form.grade, this.subject, this.student);
    }
    if(this.gradeToAdd) {
      this.gradeService.addGrade(this.gradeToAdd).subscribe(
        data => {
          console.log(data);
          alert('Grade added successfully!');
          this.reloadPage();
        },
        error => {
          console.log(error);
          this.errorMessage = error.error.message;
        }
      );
    }
  }

  reloadPage(): void {
    window.location.reload();
  }
}
