import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTabsModule } from '@angular/material/tabs';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {DropdownModule} from 'primeng/dropdown';

import { ViewEditCondenserComponent } from './component/view-edit-condenser.component';
import { ViewEditCondenserRoutingModule } from './view-edit-condenser.routing';
import { RouterModule } from '@angular/router';
import { ViewCondenserStepperComponent } from './component/view-condenser-stepper/view-condenser-stepper.component';
import { NgxCurrencyModule } from 'ngx-currency';
import { MatStepperModule } from '@angular/material/stepper';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCheckboxModule } from '@angular/material/checkbox';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatCheckboxModule,
    MatButtonModule,
    ReactiveFormsModule ,
    MatCardModule,
    MatTabsModule,
    MatIconModule,
    ButtonModule,
    MatStepperModule,
    DropdownModule,
    MatFormFieldModule,
    InputTextModule,
    MatGridListModule,
    NgxCurrencyModule,
    ViewEditCondenserRoutingModule
  ],
  declarations: [ViewEditCondenserComponent, ViewCondenserStepperComponent]
})
export class ViewEditCondenserModule { }
