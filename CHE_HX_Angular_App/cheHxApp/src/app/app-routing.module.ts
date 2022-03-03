import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'condenser',
    children: [
      {
        path: 'configurations',
        loadChildren: () =>
          import('./condensers/condenser-listview/condenser-listview.module')
            .then((m) => m.CondenserListviewModule)
            .catch((err) => console.log(err))
      },
      {
        path: 'create',
        loadChildren: () =>
          import('./condensers/create-condenser/create-condenser.module')
            .then((m) => m.CreateCondenserModule)
            .catch((err) => console.log(err))
      },
      {
        path: 'configurations/:id',
        loadChildren: () =>
          import('./condensers/view-edit-condenser/view-edit-condenser.module')
            .then((m) => m.ViewEditCondenserModule)
            .catch((err) => console.log(err))
      },
      {
        path: 'dashboard',
        loadChildren: () =>
          import('./condensers/kpi-dashboard/kpi-dashboard.module')
            .then((m) => m.KpiDashboardModule)
            .catch((err) => console.log(err))
      },
      {
        path: 'report',
        loadChildren: () =>
          import('./condensers/create-report/create-report.module')
            .then((m) => m.CreateReportModule)
            .catch((err) => console.log(err))
      },
      {
        path: 'reports',
        loadChildren: () =>
          import('./condensers/report-listview/report-listview.module')
            .then((m) => m.ReportListviewModule)
            .catch((err) => console.log(err))
      },
      {
        path: 'dashboard/advancedCharts',
        loadChildren: () =>
        import('./condensers/advanced-charts/advanced-charts-module')
            .then((m) => m.AdvancedChartsModule)
            .catch((err) => console.log(err))
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
