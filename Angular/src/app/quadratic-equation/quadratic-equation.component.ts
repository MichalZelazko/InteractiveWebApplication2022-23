import {Component, OnInit} from '@angular/core';
import {QuadraticEquation} from "./quadratic-equation.model";

@Component({
  selector: 'app-quadratic-equation',
  templateUrl: './quadratic-equation.component.html',
  styleUrls: ['./quadratic-equation.component.css']
})
export class QuadraticEquationComponent implements OnInit{
  equation: QuadraticEquation;


  constructor() {
    this.equation = new QuadraticEquation(0, 0, 0)
  }

  getRoots(a: HTMLInputElement, b:HTMLInputElement, c:HTMLInputElement): boolean {
    if(a.value == '' || b.value == '' || c.value == '') {
      alert('Please fill in all fields');
    } else {
      this.equation.a = a.valueAsNumber;
      this.equation.b = b.valueAsNumber;
      this.equation.c = c.valueAsNumber;
      a.value = '';
      b.value = '';
      c.value = '';
      this.equation.getRoots();
    }
    return false;
  }

  ngOnInit() {}
}
