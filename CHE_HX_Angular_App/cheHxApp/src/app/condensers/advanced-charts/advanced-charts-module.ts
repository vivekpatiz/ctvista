import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { MultiSelectModule } from 'primeng/multiselect';
import { NgxDaterangepickerMd } from 'ngx-daterangepicker-material';
import { Ng5SliderModule } from 'ng5-slider';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { NgxEchartsModule } from 'ngx-echarts';
import { DhrChartModule } from 'dhr-chart'

import { AdvancedChartsComponent } from './component/advanced-charts.component';
import { RouterModule } from '@angular/router';
import { AdvancedChartsRoutingModule } from './advanced-charts-routing';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    RouterModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatSelectModule,
    MatCardModule,
    MatTabsModule,
    MatIconModule,
    ButtonModule,
    MultiSelectModule,
    MatProgressSpinnerModule,
    DropdownModule,
    MatFormFieldModule,
    Ng5SliderModule,
    InputTextModule,
    MatGridListModule,
    AdvancedChartsRoutingModule,
    DhrChartModule,
    NgxDaterangepickerMd.forRoot(),
  ],
  exports :[MatFormFieldModule, FormsModule],
  declarations: [AdvancedChartsComponent],
})
export class AdvancedChartsModule {}
