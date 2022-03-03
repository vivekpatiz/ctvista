import { Injectable } from '@angular/core';
import { CanDeactivate } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';

import { Subject, Observable } from 'rxjs';

import { CreateCondenserComponent } from '../../create-condenser/component/create-condenser.component';
import { ConfirmationModalComponent } from '../confirmation-modal/confirmation-modal.component';
import { ViewEditCondenserComponent } from '../../view-edit-condenser/component/view-edit-condenser.component';

@Injectable({
  providedIn: 'root',
})
export class CanDeactivateGuard
  implements CanDeactivate<CreateCondenserComponent> {
  confirmDlg: MatDialogRef<ConfirmationModalComponent>;

  constructor(private dialog: MatDialog) {}

  canDeactivate(component: CreateCondenserComponent): any {
    const subject = new Subject<boolean>();

    if (component.formGroup.dirty && !component.submitted) {
      console.log(component.submitted);
      this.confirmDlg = this.dialog.open(ConfirmationModalComponent, {
        disableClose: true,
        position: { top: '3%', left: '35%' },
      });
      this.confirmDlg.componentInstance.subject = subject;
      this.confirmDlg.afterClosed().subscribe(async (response) => {
        if (response) {
        } else {
        }
      });

      return subject.asObservable();
    }
    return true;
  }
}
@Injectable({
  providedIn: 'root',
})
export class CanDeactivateGuardViewCondenser
  implements CanDeactivate<ViewEditCondenserComponent> {
  confirmDlg: MatDialogRef<ConfirmationModalComponent>;

  constructor(private dialog: MatDialog) {}

  canDeactivate(component: ViewEditCondenserComponent): any {
    const subject = new Subject<boolean>();

    if (component.formGroup.dirty && !component.submitted) {
      this.confirmDlg = this.dialog.open(ConfirmationModalComponent, {
        disableClose: true,
        position: { top: '3%', left: '35%' },
      });
      this.confirmDlg.componentInstance.subject = subject;
      this.confirmDlg.afterClosed().subscribe(async (response) => {
        if (response) {
        } else {
        }
      });

      return subject.asObservable();
    }
    return true;
  }
}

