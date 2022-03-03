import { NgModule } from '@angular/core';
import { DhrDataTableComponent } from './dhr-data-table.component';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { HttpClientModule } from '@angular/common/http';
import { SplitButtonModule } from 'primeng/splitbutton';
import { FormsModule } from '@angular/forms';
import { PaginatorModule } from 'primeng/paginator';
import { InputTextModule } from 'primeng/inputtext';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { CommonModule } from '@angular/common';
import * as i0 from "@angular/core";
var DhrDataTableModule = /** @class */ (function () {
    function DhrDataTableModule() {
    }
    DhrDataTableModule.ɵmod = i0.ɵɵdefineNgModule({ type: DhrDataTableModule });
    DhrDataTableModule.ɵinj = i0.ɵɵdefineInjector({ factory: function DhrDataTableModule_Factory(t) { return new (t || DhrDataTableModule)(); }, providers: [ConfirmationService], imports: [[
                CommonModule,
                TableModule,
                ButtonModule,
                HttpClientModule,
                SplitButtonModule,
                FormsModule,
                ConfirmDialogModule,
                PaginatorModule,
                InputTextModule,
                ProgressSpinnerModule
            ]] });
    return DhrDataTableModule;
}());
export { DhrDataTableModule };
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && i0.ɵɵsetNgModuleScope(DhrDataTableModule, { declarations: [DhrDataTableComponent], imports: [CommonModule,
        TableModule,
        ButtonModule,
        HttpClientModule,
        SplitButtonModule,
        FormsModule,
        ConfirmDialogModule,
        PaginatorModule,
        InputTextModule,
        ProgressSpinnerModule], exports: [DhrDataTableComponent] }); })();
/*@__PURE__*/ (function () { i0.ɵsetClassMetadata(DhrDataTableModule, [{
        type: NgModule,
        args: [{
                declarations: [DhrDataTableComponent],
                imports: [
                    CommonModule,
                    TableModule,
                    ButtonModule,
                    HttpClientModule,
                    SplitButtonModule,
                    FormsModule,
                    ConfirmDialogModule,
                    PaginatorModule,
                    InputTextModule,
                    ProgressSpinnerModule
                ],
                providers: [ConfirmationService],
                exports: [DhrDataTableComponent]
            }]
    }], null, null); })();
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZGhyLWRhdGEtdGFibGUubW9kdWxlLmpzIiwic291cmNlUm9vdCI6Im5nOi8vZGhyLWRhdGEtdGFibGUvIiwic291cmNlcyI6WyJsaWIvZGhyLWRhdGEtdGFibGUubW9kdWxlLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBLE9BQU8sRUFBRSxRQUFRLEVBQUUsTUFBTSxlQUFlLENBQUM7QUFDekMsT0FBTyxFQUFFLHFCQUFxQixFQUFFLE1BQU0sNEJBQTRCLENBQUM7QUFHbkUsT0FBTyxFQUFFLFdBQVcsRUFBRSxNQUFNLGVBQWUsQ0FBQztBQUM1QyxPQUFPLEVBQUUsWUFBWSxFQUFFLE1BQU0sZ0JBQWdCLENBQUM7QUFDOUMsT0FBTyxFQUFFLGdCQUFnQixFQUFFLE1BQU0sc0JBQXNCLENBQUM7QUFDeEQsT0FBTyxFQUFFLGlCQUFpQixFQUFFLE1BQU0scUJBQXFCLENBQUM7QUFDeEQsT0FBTyxFQUFFLFdBQVcsRUFBRSxNQUFNLGdCQUFnQixDQUFDO0FBQzdDLE9BQU8sRUFBRSxlQUFlLEVBQUUsTUFBTSxtQkFBbUIsQ0FBQztBQUNwRCxPQUFPLEVBQUUsZUFBZSxFQUFFLE1BQU0sbUJBQW1CLENBQUM7QUFDcEQsT0FBTyxFQUFFLHFCQUFxQixFQUFFLE1BQU0seUJBQXlCLENBQUM7QUFDaEUsT0FBTyxFQUFDLG1CQUFtQixFQUFDLE1BQU0sdUJBQXVCLENBQUM7QUFDMUQsT0FBTyxFQUFFLG1CQUFtQixFQUFFLE1BQU0sYUFBYSxDQUFDO0FBQ2xELE9BQU8sRUFBRSxZQUFZLEVBQUUsTUFBTSxpQkFBaUIsQ0FBQzs7QUFJL0M7SUFBQTtLQWlCbUM7MERBQXRCLGtCQUFrQjt1SEFBbEIsa0JBQWtCLG1CQUhuQixDQUFDLG1CQUFtQixDQUFDLFlBWnRCO2dCQUNQLFlBQVk7Z0JBQ1osV0FBVztnQkFDWCxZQUFZO2dCQUNaLGdCQUFnQjtnQkFDaEIsaUJBQWlCO2dCQUNqQixXQUFXO2dCQUNYLG1CQUFtQjtnQkFDbkIsZUFBZTtnQkFDZixlQUFlO2dCQUNmLHFCQUFxQjthQUN0Qjs2QkEvQkg7Q0FtQ21DLEFBakJuQyxJQWlCbUM7U0FBdEIsa0JBQWtCO3dGQUFsQixrQkFBa0IsbUJBaEJkLHFCQUFxQixhQUVsQyxZQUFZO1FBQ1osV0FBVztRQUNYLFlBQVk7UUFDWixnQkFBZ0I7UUFDaEIsaUJBQWlCO1FBQ2pCLFdBQVc7UUFDWCxtQkFBbUI7UUFDbkIsZUFBZTtRQUNmLGVBQWU7UUFDZixxQkFBcUIsYUFHYixxQkFBcUI7a0RBRXBCLGtCQUFrQjtjQWpCOUIsUUFBUTtlQUFDO2dCQUNSLFlBQVksRUFBRSxDQUFDLHFCQUFxQixDQUFDO2dCQUNyQyxPQUFPLEVBQUU7b0JBQ1AsWUFBWTtvQkFDWixXQUFXO29CQUNYLFlBQVk7b0JBQ1osZ0JBQWdCO29CQUNoQixpQkFBaUI7b0JBQ2pCLFdBQVc7b0JBQ1gsbUJBQW1CO29CQUNuQixlQUFlO29CQUNmLGVBQWU7b0JBQ2YscUJBQXFCO2lCQUN0QjtnQkFDRCxTQUFTLEVBQUMsQ0FBQyxtQkFBbUIsQ0FBQztnQkFDL0IsT0FBTyxFQUFFLENBQUMscUJBQXFCLENBQUM7YUFDakMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyBOZ01vZHVsZSB9IGZyb20gJ0Bhbmd1bGFyL2NvcmUnO1xyXG5pbXBvcnQgeyBEaHJEYXRhVGFibGVDb21wb25lbnQgfSBmcm9tICcuL2Roci1kYXRhLXRhYmxlLmNvbXBvbmVudCc7XHJcbmltcG9ydCB7IEJyb3dzZXJNb2R1bGUgfSBmcm9tICdAYW5ndWxhci9wbGF0Zm9ybS1icm93c2VyJztcclxuaW1wb3J0IHsgQnJvd3NlckFuaW1hdGlvbnNNb2R1bGUgfSBmcm9tICdAYW5ndWxhci9wbGF0Zm9ybS1icm93c2VyL2FuaW1hdGlvbnMnO1xyXG5pbXBvcnQgeyBUYWJsZU1vZHVsZSB9IGZyb20gJ3ByaW1lbmcvdGFibGUnO1xyXG5pbXBvcnQgeyBCdXR0b25Nb2R1bGUgfSBmcm9tICdwcmltZW5nL2J1dHRvbic7XHJcbmltcG9ydCB7IEh0dHBDbGllbnRNb2R1bGUgfSBmcm9tICdAYW5ndWxhci9jb21tb24vaHR0cCc7XHJcbmltcG9ydCB7IFNwbGl0QnV0dG9uTW9kdWxlIH0gZnJvbSAncHJpbWVuZy9zcGxpdGJ1dHRvbic7XHJcbmltcG9ydCB7IEZvcm1zTW9kdWxlIH0gZnJvbSAnQGFuZ3VsYXIvZm9ybXMnO1xyXG5pbXBvcnQgeyBQYWdpbmF0b3JNb2R1bGUgfSBmcm9tICdwcmltZW5nL3BhZ2luYXRvcic7XHJcbmltcG9ydCB7IElucHV0VGV4dE1vZHVsZSB9IGZyb20gJ3ByaW1lbmcvaW5wdXR0ZXh0JztcclxuaW1wb3J0IHsgUHJvZ3Jlc3NTcGlubmVyTW9kdWxlIH0gZnJvbSAncHJpbWVuZy9wcm9ncmVzc3NwaW5uZXInO1xyXG5pbXBvcnQge0NvbmZpcm1EaWFsb2dNb2R1bGV9IGZyb20gJ3ByaW1lbmcvY29uZmlybWRpYWxvZyc7XHJcbmltcG9ydCB7IENvbmZpcm1hdGlvblNlcnZpY2UgfSBmcm9tICdwcmltZW5nL2FwaSc7XHJcbmltcG9ydCB7IENvbW1vbk1vZHVsZSB9IGZyb20gJ0Bhbmd1bGFyL2NvbW1vbic7XHJcblxyXG5cclxuXHJcbkBOZ01vZHVsZSh7XHJcbiAgZGVjbGFyYXRpb25zOiBbRGhyRGF0YVRhYmxlQ29tcG9uZW50XSxcclxuICBpbXBvcnRzOiBbXHJcbiAgICBDb21tb25Nb2R1bGUsXHJcbiAgICBUYWJsZU1vZHVsZSxcclxuICAgIEJ1dHRvbk1vZHVsZSxcclxuICAgIEh0dHBDbGllbnRNb2R1bGUsXHJcbiAgICBTcGxpdEJ1dHRvbk1vZHVsZSxcclxuICAgIEZvcm1zTW9kdWxlLFxyXG4gICAgQ29uZmlybURpYWxvZ01vZHVsZSxcclxuICAgIFBhZ2luYXRvck1vZHVsZSxcclxuICAgIElucHV0VGV4dE1vZHVsZSxcclxuICAgIFByb2dyZXNzU3Bpbm5lck1vZHVsZVxyXG4gIF0sXHJcbiAgcHJvdmlkZXJzOltDb25maXJtYXRpb25TZXJ2aWNlXSxcclxuICBleHBvcnRzOiBbRGhyRGF0YVRhYmxlQ29tcG9uZW50XVxyXG59KVxyXG5leHBvcnQgY2xhc3MgRGhyRGF0YVRhYmxlTW9kdWxlIHsgfVxyXG4iXX0=