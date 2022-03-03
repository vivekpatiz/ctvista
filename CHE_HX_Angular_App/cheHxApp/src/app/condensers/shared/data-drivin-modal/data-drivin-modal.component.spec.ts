import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DataDrivinModalComponent } from './data-drivin-modal.component';

describe('DataDrivinModalComponent', () => {
  let component: DataDrivinModalComponent;
  let fixture: ComponentFixture<DataDrivinModalComponent>;

  beforeEach(() => {
    const matDialogRefStub = () => ({ close: arg => ({}) });
    TestBed.configureTestingModule({
      declarations: [DataDrivinModalComponent],
      providers: [{ provide: MatDialogRef, useFactory: matDialogRefStub },
                   {provide: MAT_DIALOG_DATA, useValue: []}]
    });
    fixture = TestBed.createComponent(DataDrivinModalComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
    component.ngOnInit();
  });

  describe('onYesResponse', () => {
    it('makes expected calls', () => {
      const matDialogRefStub: MatDialogRef<any> = fixture.debugElement.injector.get(
        MatDialogRef
      );
      spyOn(matDialogRefStub, 'close').and.callThrough();
      component.onYesResponse();
      expect(matDialogRefStub.close).toHaveBeenCalled();
    });
  });

  describe('onNoResponse', () => {
    it('makes expected calls', () => {
      const matDialogRefStub: MatDialogRef<any> = fixture.debugElement.injector.get(
        MatDialogRef
      );
      spyOn(matDialogRefStub, 'close').and.callThrough();
      component.onNoResponse();
      expect(matDialogRefStub.close).toHaveBeenCalled();
    });
  });
});
