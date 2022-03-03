import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CondenserListviewComponent } from './component/condenser-listview.component';

const routes: Routes = [
  {
    path: '',
    component: CondenserListviewComponent,
    data: {
      breadcrumb: 'Condenser Configuration',
      path: 'condenser/configurations'
     }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CondenserListviewRoutingModule { }
