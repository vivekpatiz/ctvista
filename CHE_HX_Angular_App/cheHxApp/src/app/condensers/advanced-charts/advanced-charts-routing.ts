import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdvancedChartsComponent } from './component/advanced-charts.component';

const routes: Routes = [
  {
    path: '',
    component: AdvancedChartsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdvancedChartsRoutingModule {}
