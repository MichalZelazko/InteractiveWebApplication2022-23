import {Teacher} from "../teacher/teacher.model";

export class Subject {

  id?: number;
  name: string;
  teacher: Teacher | undefined;

  constructor(name: string) {
    this.name = name;
  }
}
