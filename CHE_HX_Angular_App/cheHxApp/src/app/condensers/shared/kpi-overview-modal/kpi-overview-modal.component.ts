import { Inject } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-kpi-overview-modal',
  templateUrl: './kpi-overview-modal.component.html',
  styleUrls: ['./kpi-overview-modal.component.scss']
})
export class KpiOverviewModalComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<KpiOverviewModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

  onNoResponse(): any {
    this.dialogRef.close(false);
  }

}
