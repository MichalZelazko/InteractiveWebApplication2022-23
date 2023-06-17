import {Component, OnInit} from '@angular/core';
import {SubjectService} from "./subject.service";
import {Subject} from "./subject.model";

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit{

  form: any = {};
  subjectList?: Subject[];
  subject?: Subject;
  subjectToAdd?: Subject;
  isAddFailed = false;
  errorMessage = '';
  constructor(private subjectService: SubjectService ) { }

  ngOnInit() { this.getSubjects(); }

  getSubjects(): void {
    this.subjectService.getTeachersSubjects()
      .subscribe(subjectList => this.subjectList = subjectList);
  }

  addSubject(): void {
    this.subjectToAdd = new Subject(this.form.name);
    this.subjectService.addSubject(this.subjectToAdd).subscribe(
      data => {
        console.log(data);
        this.isAddFailed = false;
        this.errorMessage = '';
        this.reloadPage();
        alert('Subject added successfully!');
      },
      error => {
        console.log(error);
        this.isAddFailed = true;
        this.errorMessage = error.error.message;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }
}
