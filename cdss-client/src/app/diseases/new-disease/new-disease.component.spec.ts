import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDiseaseComponent } from './new-disease.component';

describe('NewDiseaseComponent', () => {
  let component: NewDiseaseComponent;
  let fixture: ComponentFixture<NewDiseaseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewDiseaseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewDiseaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
