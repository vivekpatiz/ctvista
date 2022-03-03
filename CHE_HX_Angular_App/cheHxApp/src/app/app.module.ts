import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler, Injectable } from '@angular/core';
import {ToastModule} from 'primeng/toast';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BreadcrumbComponent } from './condensers/shared//bread-crumb/breadcrumb.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastMessageComponent } from './condensers/shared/toast-service/component/toast-message.component';
import { MessageService } from 'primeng/api';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { CanDeactivateGuard, CanDeactivateGuardViewCondenser } from './condensers/shared/guards/can-deactivate.guard';
import { ConfirmationModalComponent } from './condensers/shared/confirmation-modal/confirmation-modal.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
// import { MatGridListModule } from '@angular/material/grid-list';
import { DataDrivinModalComponent } from './condensers/shared/data-drivin-modal/data-drivin-modal.component';
import { HttpConfigInterceptor } from './http-config.interceptor';
import { YAxisLimitModalComponent } from './condensers/shared/y-axes-limit-modal/y-axes-limit-modal.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { KpiOverviewModalComponent } from "./condensers/shared/kpi-overview-modal/kpi-overview-modal.component";
import { ToastMessgaeService } from './condensers/shared/toast-service/service/toast-messgae.service';


@NgModule({
  declarations: [
    AppComponent,
    BreadcrumbComponent,
    ToastMessageComponent,
    ConfirmationModalComponent,
    DataDrivinModalComponent,
    YAxisLimitModalComponent,
    KpiOverviewModalComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    MatDialogModule,
    // MatGridListModule,
    MatButtonModule,
    ToastModule,
    BrowserAnimationsModule,
    FormsModule
  ],
  providers: [MessageService, CanDeactivateGuard, CanDeactivateGuardViewCondenser,
    { provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }