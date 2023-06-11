import {Grade} from "../grade/grade.model";

export class Student {
  id?: number;
  name: string;
  surname: string;
  grades: Grade[];

  constructor(name: string, surname: string) {
    this.name = name;
    this.surname = surname;
    this.grades = [];
  }
}
