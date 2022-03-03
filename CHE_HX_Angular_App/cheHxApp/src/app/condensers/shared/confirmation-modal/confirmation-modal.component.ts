import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation-modal',
  templateUrl: './confirmation-modal.component.html',
  styleUrls: ['./confirmation-modal.component.scss'],
})
export class ConfirmationModalComponent implements OnInit {
  public subject: Subject<boolean>;

  constructor(private dialogRef: MatDialogRef<ConfirmationModalComponent>) {}

  ngOnInit(): void {}

  onYesResponse(): any {
    if (this.subject) {
      this.subject.next(true);
      this.subject.complete();
    }
    this.dialogRef.close(true);
  }

  onNoResponse(): any {
    if (this.subject) {
      this.subject.next(false);
      this.subject.complete();
    }
    this.dialogRef.close(false);
  }
}
