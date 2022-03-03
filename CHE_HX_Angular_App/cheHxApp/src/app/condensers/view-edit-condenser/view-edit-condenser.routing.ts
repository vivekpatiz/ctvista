import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ViewEditCondenserComponent } from './component/view-edit-condenser.component';
import { CanDeactivateGuardViewCondenser } from '../shared/guards/can-deactivate.guard';

const routes: Routes = [
  {
    path: '',
    component: ViewEditCondenserComponent,
    data: {
      breadcrumb: '',
      path: ''
  },
    canDeactivate: [ CanDeactivateGuardViewCondenser ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ViewEditCondenserRoutingModule { }
