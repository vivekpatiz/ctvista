import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root',
})
export class ToastMessgaeService {
  constructor(private messageService: MessageService) {}
  showSuccess(message): any {
    this.messageService.add(message);
  }
  showError(message): any {
    this.messageService.add(message);
  }
}
