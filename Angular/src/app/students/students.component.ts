import {Component, OnInit} from '@angular/core';
import {StudentService} from "../student.service";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit{
  studentList?: Student[];
  student?: Student;

  constructor(private studentService: StudentService) { }

  ngOnInit(): void {
    this.getStudents();
  }

  getStudents(): void {
    this.studentService.getStudents()
      .subscribe(students => this.studentList = students);
  }

  add(firstname: string, lastname: string, email: string, telephone: string): void {
    firstname = firstname.trim();
    lastname = lastname.trim();
    email = email.trim();
    telephone = telephone.trim();
    if (!firstname || !lastname || !email || !telephone) { return; }
    this.studentService.addStudent({ firstname, lastname, email, age } as Student)
      .subscribe({
        next: (student: Student) => { this.studentList.push(student); },
        error: () => {},
        complete: () => {
          if (this.studentList != undefined) {
            this.studentService.totalItems.next(this.studentList.length);
            console.log(this.studentList.length);
          }
        }
      });
  }

  delete (student: Student): void {
    this.studentList = this.studentList?.filter(s => s !== student);
    this.studentService.deleteStudent(student.id).subscribe(() => {
      if (this.studentList != undefined) {
        this.studentService.totalItems.next(this.studentList.length);
        console.log(this.studentList.length);
      }
    });
  }

  deleteAll(): void {
    this.studentService.deleteStudents().subscribe(() => {
      if (this.studentList != undefined) {
        this.studentList.length = 0;
      }
    });
  }

  update(firstname: string, lastname: string, email: string, telephone: string, chosenToUpdateStudent: Student): void {
    let id = chosenToUpdateStudent.id;
    firstname = firstname.trim();
    lastname = lastname.trim();
    email = email.trim();
    telephone = telephone.trim();
    console.log(id);
    if (id != undefined) {
      this.studentService.updateStudent({id, firstname, lastname, email, telephone} as Student, id)
        .subscribe({
          next: (student: Student) => {
            if (this.studentList != undefined) {
              let index = this.studentList?.indexOf(chosenToUpdateStudent);
              this.studentList[index] = student;
            }
          },
          error: () => {},
          complete: () => {
            if (this.studentList != undefined) {
              this.studentService.totalItems.next(this.studentList.length);
              console.log(this.studentList.length);
            }
          }
        });
    }
  }
}
