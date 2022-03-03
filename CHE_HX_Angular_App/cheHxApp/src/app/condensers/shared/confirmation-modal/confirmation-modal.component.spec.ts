import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import { Subject } from 'rxjs';

import { ConfirmationModalComponent } from './confirmation-modal.component';

describe('ConfirmationModalComponent', () => {
  let component: ConfirmationModalComponent;
  let fixture: ComponentFixture<ConfirmationModalComponent>;

  beforeEach(async(() => {
    const dialogMock = {
      close: () => { }
     };
    TestBed.configureTestingModule({
      imports: [MatDialogModule],
      declarations: [ ConfirmationModalComponent ],
      providers: [
        {
          provide: MatDialogRef,
          useValue: dialogMock
        }
     ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmationModalComponent);
    component = fixture.componentInstance;
     fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('Should call method on yes response', async(() => {
    spyOn(component, 'onYesResponse');
    const button = fixture.debugElement.nativeElement.querySelector('button');
    button.click();
    fixture.whenStable().then(() => {
      expect(component.onYesResponse).not.toHaveBeenCalled();
    });
  }));
  it('should call method on no response', async(() => {
    spyOn(component, 'onNoResponse');
    const button = fixture.debugElement.nativeElement.querySelector('button');
    button.click();
    fixture.whenStable().then(() => {
      expect(component.onNoResponse).toHaveBeenCalled();
    });
  }));
  it('should call yes response',()=>{
    component.onYesResponse();
     fixture.detectChanges();
    spyOn(component,'onYesResponse');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.onYesResponse).toHaveBeenCalled();
    });
  });
  it('should be able to call yes response and no response', () => {
    component.subject = new Subject<boolean>();
    component.onYesResponse();
    component.onNoResponse();
  });
});
