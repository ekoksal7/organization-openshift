import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentListTemplateComponent } from './department-list-template.component';

describe('DepartmentListTemplateComponent', () => {
  let component: DepartmentListTemplateComponent;
  let fixture: ComponentFixture<DepartmentListTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DepartmentListTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DepartmentListTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
