import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-data-drivin-modal',
  templateUrl: './data-drivin-modal.component.html',
  styleUrls: ['./data-drivin-modal.component.scss']
})
export class DataDrivinModalComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<DataDrivinModalComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    console.log(this.data);
  }

  onYesResponse(): any {
    this.dialogRef.close(true);
  }

  onNoResponse(): any {
    this.dialogRef.close(false);
  }
}
