export class QuadraticEquation {

  a: number;
  b: number;
  c: number;

  x1: number;
  x2: number;
  roots: string;

  constructor(a: number, b: number, c: number) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.x1 = 0;
    this.x2 = 0;
    this.roots = "";
  }

  getDiscriminant() {
    return (this.b * this.b) - (4 * this.a * this.c);
  }

  getRoots() {
    if (this.a == 0) {
      this.linearEquationCase();
    } else
      this.quadraticEquationCase();
  }

  linearEquationCase() {
    if (this.b == 0)
      if (this.c == 0) {
        this.roots = "The equation has infinite roots.";
      } else {
        this.roots = "The equation has no real roots.";
      }
    this.x1 = (this.c / this.b);
    this.roots = "The equation has one root: " + this.x1;
  }

  quadraticEquationCase() {
    if (this.getDiscriminant() < 0) {
      this.roots = "The equation has no real roots.";
    } else if (this.getDiscriminant() == 0) {
      this.x1 = (-(this.b) / (2 * this.a));
    } else {
      this.x1 = ((-(this.b) + Math.sqrt(this.getDiscriminant())) / (2 * this.a));
      this.x2 = ((-(this.b) - Math.sqrt(this.getDiscriminant())) / (2 * this.a));
      this.roots = "The equation has two roots: " + this.x1 + " and " + this.x2;
    }
  }
}
