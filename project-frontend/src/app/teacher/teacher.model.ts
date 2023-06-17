import {Subject} from "../subject/subject.model";

export class Teacher {
  id?: number;
  name: string;
  surname: string;
  subjects: Subject[];

  constructor(name: string, surname: string) {
    this.name = name;
    this.surname = surname;
    this.subjects = [];
  }
}
