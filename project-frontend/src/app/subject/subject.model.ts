import {Teacher} from "../teacher/teacher.model";

export class Subject {

  id?: number;
  name: string;
  teacher: Teacher;

  constructor(name: string, teacher: Teacher) {
    this.name = name;
    this.teacher = teacher;
  }
}
