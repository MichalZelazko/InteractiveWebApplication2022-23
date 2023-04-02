import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuadraticEquationComponent } from './quadratic-equation.component';

describe('QuadraticEquationComponent', () => {
  let component: QuadraticEquationComponent;
  let fixture: ComponentFixture<QuadraticEquationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuadraticEquationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuadraticEquationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
