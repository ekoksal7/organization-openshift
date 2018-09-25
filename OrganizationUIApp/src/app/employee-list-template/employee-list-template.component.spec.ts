import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeListTemplateComponent } from './employee-list-template.component';

describe('EmployeeListTemplateComponent', () => {
  let component: EmployeeListTemplateComponent;
  let fixture: ComponentFixture<EmployeeListTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeListTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeListTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
