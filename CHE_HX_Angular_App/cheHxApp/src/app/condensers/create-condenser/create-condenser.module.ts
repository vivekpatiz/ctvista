import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { MatGridListModule } from '@angular/material/grid-list';
import { InputTextModule } from 'primeng/inputtext';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ButtonModule } from 'primeng/button';
import { MatStepperModule } from '@angular/material/stepper';
import { MatInputModule } from '@angular/material/input';
import { DropdownModule } from 'primeng/dropdown';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { NgxCurrencyModule } from "ngx-currency";

import { CreateCondenserRoutingModule } from './create-condenser.routing';
import { CreateCondenserComponent } from './component/create-condenser.component';
import { RouterModule } from '@angular/router';
import { InputStepperComponent } from './component/input-stepper/input-stepper.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    RouterModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatCardModule,
    MatTabsModule,
    MatIconModule,
    ButtonModule,
    MatInputModule,
    DropdownModule,
    MatStepperModule,
    InputTextModule,
    MatFormFieldModule,
    MatGridListModule,
    NgxCurrencyModule,
    CreateCondenserRoutingModule
  ],
  declarations: [CreateCondenserComponent, InputStepperComponent]
})
export class CreateCondenserModule { }
