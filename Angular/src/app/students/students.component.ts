import { Component, OnInit } from '@angular/core';
import {Student} from "./student.model";
import {StudentService} from "./student.service";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {
  studentList?: Student[];
  student?: Student;

  constructor(private studentService: StudentService) { }

  ngOnInit() { this.getStudents();   }

  getStudents(): void {
    this.studentService.getStudents()
      .subscribe(studentList => this.studentList = studentList);
  }

  add(name: string, surname: string, email: string, telephone: string): void {
    name = name.trim();
    surname = surname.trim();
    email = email.trim();
    telephone = telephone.trim();
    this.studentService.addStudent({ name, surname, email, telephone } as Student)
      .subscribe({
        next: (student: Student) => { this.studentList?.push(student); },
        error: () => {},
        complete: () => {
          if (this.studentList != undefined) {
            this.studentService.totalItems.next(this.studentList.length);
            console.log(this.studentList.length);
          }
        }
  });
  }

  delete(student: Student): void {
    this.studentList = this.studentList?.filter(c => c !== student);
    this.studentService.deleteStudent(student).subscribe(() => {
        // for automatic update of number of students in parent component
      if(this.studentList != undefined) {
        this.studentService.totalItems.next(this.studentList.length);
        console.log(this.studentList.length);
      }
      }
    );
  }

  deleteAll(): void {
    this.studentService.deleteStudents().subscribe(() => {
      if(this.studentList != undefined) {
        this.studentList.length = 0;
      }
      }
    );
  }

  update(name: string, surname: string, email: string, telephone: string, chosenToUpdateStudent:Student):void {
    let id = chosenToUpdateStudent.id;
    name = name.trim();
    surname = surname.trim();
    email = email.trim();
    telephone = telephone.trim();
    //if any field is empty call patch method
    if (name == "" || surname == "" || email == "" || telephone == "") {
      this.patch(name, surname, email, telephone, chosenToUpdateStudent)
    } else {
      console.log(id);
      if (id != undefined) {
        this.studentService.updateStudent({name, surname, email, telephone} as Student, id)
          .subscribe({
            next: (student: Student) => {
              if (this.studentList != undefined) {
                let index = this.studentList?.indexOf(chosenToUpdateStudent);
                this.studentList[index] = student;
              }
            },
            error: () => {
            },
            complete: () => {
              if (this.studentList != undefined) {
                this.studentService.totalItems.next(this.studentList.length);
                console.log(this.studentList.length);
                window.location.reload();
              }
            }
          })
      }
    }
  }

  updateAll(name: string, surname: string, email: string, telephone: string): void {
    name = name.trim();
    surname = surname.trim();
    email = email.trim();
    telephone = telephone.trim();

    let studentListTest: Student[] = [];

    let studentListTestLength: number = 0;
    while (this.studentList != undefined && studentListTestLength != this.studentList?.length) {
      this.studentList[studentListTestLength].name = name;
      this.studentList[studentListTestLength].surname = surname;
      this.studentList[studentListTestLength].email = email;
      this.studentList[studentListTestLength].telephone = telephone;
      studentListTestLength++;
    }
    if (this.studentList != undefined) {
      studentListTest = this.studentList;
    }

    this.studentService.updateStudents(studentListTest)
      .subscribe({
          error: () => {},
          complete: () => {
            if (this.studentList != undefined) {
              this.studentService.totalItems.next(this.studentList.length);
              console.log(this.studentList.length);
              window.location.reload();
            }
          }
        }
      );

  }

  patch(name: string, surname: string, email: string, telephone: string, chosenToPatchStudent: Student): void {
    let id = chosenToPatchStudent.id;
    let index = this.studentList?.indexOf(chosenToPatchStudent);

    if (id != undefined && this.studentList != undefined && index != undefined) {
      if (name.trim() !== "") {
        name = name.trim();
      } else {
        name = this.studentList[index].name;
      }
      if (surname.trim() !== "") {
        surname = surname.trim();
      } else {
        surname = this.studentList[index].surname;
      }
      if (email.trim() !== "") {
        email = email.trim();
      } else {
        email = this.studentList[index].email;
      }
      if (telephone.trim() !== "") {
        telephone = telephone.trim();
      } else {
        telephone = this.studentList[index].telephone;
      }
      console.log(id);
    }

    if (id != undefined) {
      this.studentService.patchStudent({name, surname, email, telephone} as Student, id)
        .subscribe({
            next: (student: Student) => {
              if (this.studentList != undefined) {
                let index = this.studentList?.indexOf(chosenToPatchStudent);
                this.studentList[index] = student;
              }
            },
            error: () => {},
            complete: () => {
              if (this.studentList != undefined) {
                this.studentService.totalItems.next(this.studentList.length);
                console.log(this.studentList.length);
                window.location.reload();
              }
            }
          }
        );
    }
  }

  pasteInform(): void {
    alert('The data is about to be pasted into the form field.');
  }

  copyInform(): void {
    alert('The data will be copied from the form field.');
  }

  increaseFontSize(event: any): void {
    let fontSize = parseInt(window.getComputedStyle(event.target).fontSize);
    fontSize += 2;
    event.target.style.fontSize = fontSize + 'px';
  }
}

