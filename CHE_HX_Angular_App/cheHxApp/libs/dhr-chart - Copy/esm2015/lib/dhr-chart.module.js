import { NgModule } from '@angular/core';
import { DhrChartComponent } from './dhr-chart.component';
import { NgxEchartsModule } from 'ngx-echarts';
import { CommonModule } from '@angular/common';
import * as i0 from "@angular/core";
import * as i1 from "ngx-echarts";
export class DhrChartModule {
}
DhrChartModule.ɵmod = i0.ɵɵdefineNgModule({ type: DhrChartModule });
DhrChartModule.ɵinj = i0.ɵɵdefineInjector({ factory: function DhrChartModule_Factory(t) { return new (t || DhrChartModule)(); }, imports: [[
            CommonModule,
            NgxEchartsModule.forRoot({
                echarts: () => import('echarts'),
            }),
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && i0.ɵɵsetNgModuleScope(DhrChartModule, { declarations: [DhrChartComponent], imports: [CommonModule, i1.NgxEchartsModule], exports: [DhrChartComponent] }); })();
/*@__PURE__*/ (function () { i0.ɵsetClassMetadata(DhrChartModule, [{
        type: NgModule,
        args: [{
                declarations: [DhrChartComponent],
                imports: [
                    CommonModule,
                    NgxEchartsModule.forRoot({
                        echarts: () => import('echarts'),
                    }),
                ],
                exports: [DhrChartComponent]
            }]
    }], null, null); })();
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZGhyLWNoYXJ0Lm1vZHVsZS5qcyIsInNvdXJjZVJvb3QiOiJuZzovL2Roci1jaGFydC8iLCJzb3VyY2VzIjpbImxpYi9kaHItY2hhcnQubW9kdWxlLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBLE9BQU8sRUFBRSxRQUFRLEVBQUUsTUFBTSxlQUFlLENBQUM7QUFDekMsT0FBTyxFQUFFLGlCQUFpQixFQUFFLE1BQU0sdUJBQXVCLENBQUM7QUFDMUQsT0FBTyxFQUFFLGdCQUFnQixFQUFFLE1BQU0sYUFBYSxDQUFDO0FBQy9DLE9BQU8sRUFBRSxZQUFZLEVBQUUsTUFBTSxpQkFBaUIsQ0FBQzs7O0FBYS9DLE1BQU0sT0FBTyxjQUFjOztrREFBZCxjQUFjOzJHQUFkLGNBQWMsa0JBUmhCO1lBQ1AsWUFBWTtZQUNaLGdCQUFnQixDQUFDLE9BQU8sQ0FBQztnQkFDdkIsT0FBTyxFQUFFLEdBQUcsRUFBRSxDQUFDLE1BQU0sQ0FBQyxTQUFTLENBQUM7YUFDakMsQ0FBQztTQUNIO3dGQUdVLGNBQWMsbUJBVFYsaUJBQWlCLGFBRTlCLFlBQVksa0NBS0osaUJBQWlCO2tEQUVoQixjQUFjO2NBVjFCLFFBQVE7ZUFBQztnQkFDUixZQUFZLEVBQUUsQ0FBQyxpQkFBaUIsQ0FBQztnQkFDakMsT0FBTyxFQUFFO29CQUNQLFlBQVk7b0JBQ1osZ0JBQWdCLENBQUMsT0FBTyxDQUFDO3dCQUN2QixPQUFPLEVBQUUsR0FBRyxFQUFFLENBQUMsTUFBTSxDQUFDLFNBQVMsQ0FBQztxQkFDakMsQ0FBQztpQkFDSDtnQkFDRCxPQUFPLEVBQUUsQ0FBQyxpQkFBaUIsQ0FBQzthQUM3QiIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IE5nTW9kdWxlIH0gZnJvbSAnQGFuZ3VsYXIvY29yZSc7XHJcbmltcG9ydCB7IERockNoYXJ0Q29tcG9uZW50IH0gZnJvbSAnLi9kaHItY2hhcnQuY29tcG9uZW50JztcclxuaW1wb3J0IHsgTmd4RWNoYXJ0c01vZHVsZSB9IGZyb20gJ25neC1lY2hhcnRzJztcclxuaW1wb3J0IHsgQ29tbW9uTW9kdWxlIH0gZnJvbSAnQGFuZ3VsYXIvY29tbW9uJztcclxuXHJcblxyXG5ATmdNb2R1bGUoe1xyXG4gIGRlY2xhcmF0aW9uczogW0RockNoYXJ0Q29tcG9uZW50XSxcclxuICBpbXBvcnRzOiBbXHJcbiAgICBDb21tb25Nb2R1bGUsXHJcbiAgICBOZ3hFY2hhcnRzTW9kdWxlLmZvclJvb3Qoe1xyXG4gICAgICBlY2hhcnRzOiAoKSA9PiBpbXBvcnQoJ2VjaGFydHMnKSxcclxuICAgIH0pLFxyXG4gIF0sXHJcbiAgZXhwb3J0czogW0RockNoYXJ0Q29tcG9uZW50XVxyXG59KVxyXG5leHBvcnQgY2xhc3MgRGhyQ2hhcnRNb2R1bGUgeyB9XHJcbiJdfQ==