import { NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { CondenserListviewComponent } from './component/condenser-listview.component';
import { CondenserListviewRoutingModule } from './condenser-listview.routing';
import { DhrDataTableModule } from 'dhr-data-table';
import { ConfirmationService } from 'primeng/api';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  imports: [
    CommonModule,
    CondenserListviewRoutingModule,
    DhrDataTableModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatButtonModule
     ],
  declarations: [CondenserListviewComponent],
  providers: [ConfirmationService],
  schemas: []
})
export class CondenserListviewModule { }
