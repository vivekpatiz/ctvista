import { NgModule } from '@angular/core';
import { DhrChartComponent } from './dhr-chart.component';
import { NgxEchartsModule } from 'ngx-echarts';
import { CommonModule } from '@angular/common';
import * as i0 from "@angular/core";
import * as i1 from "ngx-echarts";
var DhrChartModule = /** @class */ (function () {
    function DhrChartModule() {
    }
    DhrChartModule.ɵmod = i0.ɵɵdefineNgModule({ type: DhrChartModule });
    DhrChartModule.ɵinj = i0.ɵɵdefineInjector({ factory: function DhrChartModule_Factory(t) { return new (t || DhrChartModule)(); }, imports: [[
                CommonModule,
                NgxEchartsModule.forRoot({
                    echarts: function () { return import('echarts'); },
                }),
            ]] });
    return DhrChartModule;
}());
export { DhrChartModule };
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && i0.ɵɵsetNgModuleScope(DhrChartModule, { declarations: [DhrChartComponent], imports: [CommonModule, i1.NgxEchartsModule], exports: [DhrChartComponent] }); })();
/*@__PURE__*/ (function () { i0.ɵsetClassMetadata(DhrChartModule, [{
        type: NgModule,
        args: [{
                declarations: [DhrChartComponent],
                imports: [
                    CommonModule,
                    NgxEchartsModule.forRoot({
                        echarts: function () { return import('echarts'); },
                    }),
                ],
                exports: [DhrChartComponent]
            }]
    }], null, null); })();
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZGhyLWNoYXJ0Lm1vZHVsZS5qcyIsInNvdXJjZVJvb3QiOiJuZzovL2Roci1jaGFydC8iLCJzb3VyY2VzIjpbImxpYi9kaHItY2hhcnQubW9kdWxlLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBLE9BQU8sRUFBRSxRQUFRLEVBQUUsTUFBTSxlQUFlLENBQUM7QUFDekMsT0FBTyxFQUFFLGlCQUFpQixFQUFFLE1BQU0sdUJBQXVCLENBQUM7QUFDMUQsT0FBTyxFQUFFLGdCQUFnQixFQUFFLE1BQU0sYUFBYSxDQUFDO0FBQy9DLE9BQU8sRUFBRSxZQUFZLEVBQUUsTUFBTSxpQkFBaUIsQ0FBQzs7O0FBRy9DO0lBQUE7S0FVK0I7c0RBQWxCLGNBQWM7K0dBQWQsY0FBYyxrQkFSaEI7Z0JBQ1AsWUFBWTtnQkFDWixnQkFBZ0IsQ0FBQyxPQUFPLENBQUM7b0JBQ3ZCLE9BQU8sRUFBRSxjQUFNLE9BQUEsTUFBTSxDQUFDLFNBQVMsQ0FBQyxFQUFqQixDQUFpQjtpQkFDakMsQ0FBQzthQUNIO3lCQWJIO0NBZ0IrQixBQVYvQixJQVUrQjtTQUFsQixjQUFjO3dGQUFkLGNBQWMsbUJBVFYsaUJBQWlCLGFBRTlCLFlBQVksa0NBS0osaUJBQWlCO2tEQUVoQixjQUFjO2NBVjFCLFFBQVE7ZUFBQztnQkFDUixZQUFZLEVBQUUsQ0FBQyxpQkFBaUIsQ0FBQztnQkFDakMsT0FBTyxFQUFFO29CQUNQLFlBQVk7b0JBQ1osZ0JBQWdCLENBQUMsT0FBTyxDQUFDO3dCQUN2QixPQUFPLEVBQUUsY0FBTSxPQUFBLE1BQU0sQ0FBQyxTQUFTLENBQUMsRUFBakIsQ0FBaUI7cUJBQ2pDLENBQUM7aUJBQ0g7Z0JBQ0QsT0FBTyxFQUFFLENBQUMsaUJBQWlCLENBQUM7YUFDN0IiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyBOZ01vZHVsZSB9IGZyb20gJ0Bhbmd1bGFyL2NvcmUnO1xyXG5pbXBvcnQgeyBEaHJDaGFydENvbXBvbmVudCB9IGZyb20gJy4vZGhyLWNoYXJ0LmNvbXBvbmVudCc7XHJcbmltcG9ydCB7IE5neEVjaGFydHNNb2R1bGUgfSBmcm9tICduZ3gtZWNoYXJ0cyc7XHJcbmltcG9ydCB7IENvbW1vbk1vZHVsZSB9IGZyb20gJ0Bhbmd1bGFyL2NvbW1vbic7XHJcblxyXG5cclxuQE5nTW9kdWxlKHtcclxuICBkZWNsYXJhdGlvbnM6IFtEaHJDaGFydENvbXBvbmVudF0sXHJcbiAgaW1wb3J0czogW1xyXG4gICAgQ29tbW9uTW9kdWxlLFxyXG4gICAgTmd4RWNoYXJ0c01vZHVsZS5mb3JSb290KHtcclxuICAgICAgZWNoYXJ0czogKCkgPT4gaW1wb3J0KCdlY2hhcnRzJyksXHJcbiAgICB9KSxcclxuICBdLFxyXG4gIGV4cG9ydHM6IFtEaHJDaGFydENvbXBvbmVudF1cclxufSlcclxuZXhwb3J0IGNsYXNzIERockNoYXJ0TW9kdWxlIHsgfVxyXG4iXX0=