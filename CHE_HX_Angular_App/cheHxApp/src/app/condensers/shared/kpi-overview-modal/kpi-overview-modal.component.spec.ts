import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { KpiOverviewModalComponent } from './kpi-overview-modal.component';

describe('KpiOverviewModalComponent', () => {
  let component: KpiOverviewModalComponent;
  let fixture: ComponentFixture<KpiOverviewModalComponent>;

  beforeEach(async(() => {
    const matDialogRefStub = () => ({ close: arg => ({}) });
    TestBed.configureTestingModule({
      declarations: [ KpiOverviewModalComponent ],
      providers: [{ provide: MatDialogRef, useFactory: matDialogRefStub },
        {provide: MAT_DIALOG_DATA, useValue: []}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    const matDialogRefStub = () => ({ close: arg => ({}) });
    TestBed.configureTestingModule({
      declarations: [KpiOverviewModalComponent],
      providers: [{ provide: MatDialogRef, useFactory: matDialogRefStub },
                   {provide: MAT_DIALOG_DATA, useValue: []}]
    });
    fixture = TestBed.createComponent(KpiOverviewModalComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
    component.ngOnInit();
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
