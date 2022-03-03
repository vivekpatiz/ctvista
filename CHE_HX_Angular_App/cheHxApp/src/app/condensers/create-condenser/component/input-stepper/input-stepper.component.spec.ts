import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputStepperComponent } from './input-stepper.component';

describe('InputStepperComponent', () => {
  let component: InputStepperComponent;
  let fixture: ComponentFixture<InputStepperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputStepperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputStepperComponent);
    component = fixture.componentInstance;
     fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should increment count',()=>{
    component.step = 1;
    component.value = 1;
    component.max =5;
    component.toggleMore();
    spyOn(component,'toggleMore');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.renderedValue).toBe(component.value);
    });
  });
  it('should decrement count',()=>{
    component.step = 1;
    component.value = 2;
    component.min = 0;
    component.toggleLess();
    spyOn(component,'toggleLess');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.renderedValue).toBe(component.value);
    });
  });
  it('should should emit field lable',()=>{
    component.emitInputVal('evnt');
    spyOn(component,'emitInputVal');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.noOfSelectedField.emit).toHaveBeenCalledWith(['anything()', 'anything()']);
    });
  });
});
