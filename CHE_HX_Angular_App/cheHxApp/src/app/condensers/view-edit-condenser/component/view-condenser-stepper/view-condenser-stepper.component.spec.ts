import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ViewCondenserStepperComponent } from './view-condenser-stepper.component';

describe('ViewCondenserStepperComponent', () => {
  let component: ViewCondenserStepperComponent;
  let fixture: ComponentFixture<ViewCondenserStepperComponent>;

  beforeEach(async () => {
    TestBed.configureTestingModule({
      declarations: [ViewCondenserStepperComponent]
    });
    fixture = TestBed.createComponent(ViewCondenserStepperComponent);
    component = fixture.componentInstance;
    component.checked = true;
    component.ngOnInit();
    component.ngOnChanges();
    await fixture.whenStable();
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
