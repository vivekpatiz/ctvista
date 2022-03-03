import { TestBed } from '@angular/core/testing';
import { MessageService } from 'primeng/api';

import { ToastMessgaeService } from './toast-messgae.service';

describe('ToastMessgaeService', () => {
  let service: ToastMessgaeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MessageService]
    });
    service = TestBed.inject(ToastMessgaeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  it('should show Error', () => {
    service.showError('xyz');
  });
  it('should show Success', () => {
    service.showSuccess('xyz');
  });
});
