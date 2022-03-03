import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { YAxisLimitModalComponent } from './y-axes-limit-modal.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

describe('YAxisLimitModalComponent', () => {
  let component: YAxisLimitModalComponent;
  let fixture: ComponentFixture<YAxisLimitModalComponent>;
  beforeEach(() => {
    const matDialogRefStub = () => ({ close: arg => ({}) });
    TestBed.configureTestingModule({
      declarations: [YAxisLimitModalComponent],
      imports : [FormsModule, ReactiveFormsModule],
      providers: [{ provide: MatDialogRef, useFactory: matDialogRefStub },
                   {provide: MAT_DIALOG_DATA, useValue: []}]
    });
    fixture = TestBed.createComponent(YAxisLimitModalComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    component.data = {pagetext: [{}]}
    expect(component).toBeTruthy();
  });

  it('can load instance', () => {
    component.data = {pagetext: []};
    component.ngOnInit();
  });

  describe('onYesResponse', () => {
    it('makes expected calls', () => {
      const matDialogRefStub: MatDialogRef<any> = fixture.debugElement.injector.get(
        MatDialogRef
      );
      spyOn(matDialogRefStub, 'close').and.callFake(() =>{});
      component.onYesResponse();
      expect(matDialogRefStub.close).toHaveBeenCalled();
    });
  });
  
  describe('onNoResponse', () => {
    let myForm = {
      value: {
        "abc +max": 63.38,
        "abc +min": -1.55,
        "xyz+max": 80.95,
        "xyz+min": 23.2
      }
    }
    it('makes expected calls', () => {
      const matDialogRefStub: MatDialogRef<any> = fixture.debugElement.injector.get(
        MatDialogRef
      );
      spyOn(matDialogRefStub, 'close').and.callThrough();
      component.onNoResponse(myForm);
      expect(matDialogRefStub.close).toHaveBeenCalled();
    });
  });

  describe('onValueChange', () => {
    it('should be able to call onValueChange', () => {
      let myForm = {
        value: {
          "abc +max": 63.38,
          "abc +min": -1.55,
          "xyz+max": 80.95,
          "xyz+min": 23.2
        }
      }
     component.onValueChange(myForm);
      
     let minMaxCheck = {
        value: {
          "air_enthalpy +max": null,
          "air_enthalpy +min": 63.38,
          "ambient_temp+max": 23.2,
          "ambient_temp+min": null
        }
      }
      component.onValueChange(minMaxCheck);
    });
  });
});


