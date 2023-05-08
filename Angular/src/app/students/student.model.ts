export class Student {
  id?: number;
  name: string;
  surname: string;
  email: string;
  telephone: string;

  constructor(name: string, surname: string, email: string, telephone: string) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.telephone = telephone;
  }

}
