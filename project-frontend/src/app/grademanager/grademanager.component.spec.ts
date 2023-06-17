import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrademanagerComponent } from './grademanager.component';

describe('GrademanagerComponent', () => {
  let component: GrademanagerComponent;
  let fixture: ComponentFixture<GrademanagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GrademanagerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GrademanagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
