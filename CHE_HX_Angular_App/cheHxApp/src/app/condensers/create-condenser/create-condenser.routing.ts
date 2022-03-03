import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CreateCondenserComponent } from './component/create-condenser.component';
import { CanDeactivateGuard } from '../shared/guards/can-deactivate.guard';

const routes: Routes = [
  {
    path: '',
    component: CreateCondenserComponent,
    data: {
      breadcrumb: 'New Consenser',
      path: 'condenser/create'
   },
    canDeactivate: [ CanDeactivateGuard ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CreateCondenserRoutingModule {}
