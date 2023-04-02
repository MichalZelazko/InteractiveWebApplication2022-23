import {Component, OnInit} from '@angular/core';
import {QuadraticEquation} from "./quadratic-equation.model";

@Component({
  selector: 'app-quadratic-equation',
  templateUrl: './quadratic-equation.component.html',
  styleUrls: ['./quadratic-equation.component.css']
})
export class QuadraticEquationComponent implements OnInit{

  equation?: QuadraticEquation;


  constructor() {}

  solve(a: HTMLInputElement, b:HTMLInputElement, c:HTMLInputElement): boolean {
    if(a.value == '' || b.value == '' || c.value == '') {
      alert('Please fill in all fields');
    } else {
      this.equation = new QuadraticEquation(a.valueAsNumber, b.valueAsNumber, c.valueAsNumber);
      a.value = '';
      b.value = '';
      c.value = '';
      this.equation.getRoots();
    }
    return false;
  }

  ngOnInit() {}
}
