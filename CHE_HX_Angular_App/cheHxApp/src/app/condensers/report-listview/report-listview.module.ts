import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';
import { DhrDataTableModule } from 'dhr-data-table';
import { ReportListviewComponent } from './component/report-listview.component';
import { RouterModule, Routes } from '@angular/router';
import { ConfirmationService } from 'primeng/api';


const routes: Routes = [
  { path: '', component: ReportListviewComponent
}
];


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    DhrDataTableModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatButtonModule
  ],
  declarations: [ReportListviewComponent],
  providers: [ConfirmationService ],
  schemas: []
})
export class ReportListviewModule { }
