import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewIngredientComponent } from './new-ingredient.component';

describe('NewIngredientComponent', () => {
  let component: NewIngredientComponent;
  let fixture: ComponentFixture<NewIngredientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewIngredientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewIngredientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
