import {Component, OnInit} from '@angular/core';
import {Student} from "../student/student.model";
import {Grade} from "../grade/grade.model";
import {Subject} from "../subject/subject.model";
import {GradeService} from "../grade/grade.service";
import {SubjectService} from "../subject/subject.service";

@Component({
  selector: 'app-gradebook',
  templateUrl: './gradebook.component.html',
  styleUrls: ['./gradebook.component.css']
})
export class GradebookComponent implements OnInit{
  subjectList?: Subject[];
  subject?: Subject;
  gradeList?: Grade[];

  grade?: Grade;

  filteredSubjectList: Subject[] = [];

  searchTerm: string = '';

  sortDirection: string = 'asc';


  constructor(private gradeService: GradeService, private subjectService: SubjectService) { }

  ngOnInit() {
    this.getSubjects();
    this.getGrades();
  }

  getSubjects(): void {
    this.subjectService.getSubjects().subscribe(subjects => {
      this.subjectList = subjects;
      this.filterSubjects();
    });
  }

  filterSubjects(): void {
    if (this.subjectList) {
      this.filteredSubjectList = this.subjectList.filter(subject =>
        subject.name.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }
  }

  getGrades(): void {
    this.gradeService.getStudentGrades().subscribe(gradeList => this.gradeList = gradeList);
  }

  sortByName(): void {
    if (this.sortDirection === 'asc') {
      this.filteredSubjectList?.sort((a, b) => a.name.localeCompare(b.name));
      this.sortDirection = 'desc';
    } else {
      this.filteredSubjectList?.sort((a, b) => b.name.localeCompare(a.name));
      this.sortDirection = 'asc';
    }
  }
}
