import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ToastMessageComponent } from './toast-message.component';
import { MessageService } from 'primeng/api';
import { ToastMessgaeService } from '../service/toast-messgae.service';

describe('ToastMessageComponent', () => {
  let component: ToastMessageComponent;
  let fixture: ComponentFixture<ToastMessageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ToastMessageComponent],
      providers: [ToastMessgaeService, MessageService],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ToastMessageComponent);
    component = fixture.componentInstance;
     fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
