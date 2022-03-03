import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateReportComponent } from './component/create-report/create-report.component';
// import { EditorWidjetComponent } from './component/editor-widjet/editor-widjet.component';
import { AngularEditorModule } from '@kolkov/angular-editor';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatCheckboxModule} from '@angular/material/checkbox';

import { DhrChartModule } from 'dhr-chart';
import { ReportPreviewComponent } from './component/report-preview/report-preview.component';
import { PreviewHeaderComponent } from './component/preview-header/preview-header.component';
import { ReportFooterComponent } from './component/report-footer/report-footer.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

const routes: Routes = [
  { path: 'create/:rid', component: CreateReportComponent
},
{ path: 'preview', component: ReportPreviewComponent
}
];


@NgModule({
  declarations: [CreateReportComponent, ReportPreviewComponent, PreviewHeaderComponent, ReportFooterComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatButtonModule,
    MatTableModule,
    MatIconModule,
    MatSelectModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    AngularEditorModule,
    DragDropModule,
    HttpClientModule,
    DhrChartModule,
    MatCheckboxModule,
    MatProgressSpinnerModule
  ],
  exports: [RouterModule],
})
export class CreateReportModule { }
