import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';
import { ChartService } from 'src/app/condensers/kpi-dashboard/service/chart.service';
import { Router } from '@angular/router';
import { ReportListviewService } from '../../report-listview/service/report-listview.service';
import { ToastMessgaeService } from '../../shared/toast-service/service/toast-messgae.service';
import { ReportService } from '../../create-report/service/report.service';
import { ReportListviewComponent } from './report-listview.component';
import { MatCardModule } from '@angular/material/card';
import { DhrDataTableModule } from 'dhr-data-table';
import { TableModule } from 'primeng';
import { RouterTestingModule } from '@angular/router/testing';

describe('ReportListviewComponent', () => {
  let component: ReportListviewComponent;
  let fixture: ComponentFixture<ReportListviewComponent>;

  beforeEach(() => {
    const chartServiceStub = () => ({
      getJSON: () => ({
        subscribe: f => f({
          baseURL: 'http://172.31.3.250:8082/',
          getAPICondenser: 'v1/AssetManagement/assets/',
          deleteAPICondenser: 'v1/AssetManagement/asset/',
          baseChartURL: 'http://172.31.3.250:8083/',
          getChartAPI: 'v1/TimeSeries/charts',
          postCondenserAPI: 'v1/AssetManagement/asset',
          baseURLCalc: 'http://172.31.3.250:8085',
          postCustomCalcAPI: 'api/v1/runCustomDesignCalculation',
          getCondenserByIsAPI: 'v1/AssetManagement/condenser-asset/',
          patchCondenserAPI: 'v1/AssetManagement/asset/',
          chartKey: 'v1/AssetManagement/condenser-chart-key/',
          condenserLoad: 'v1/AssetManagement/condenser-load/',
          UOMBaseURL: 'http://172.31.3.250:8092/UOM/',
          UOMGetURL: 'api/v1/uom',
          getFacilities: 'https://develop.ctvistaplus.com/service/accounts',
          getPlantDataByFacility: 'v1/AssetManagement/asset-data',
          getUserProfile: 'https://develop.ctvistaplus.com/service/profile',
          baseReportURL: 'http://172.31.3.250:8084/',
          getReportList: 'api/v1/ReportManagement/report-all/2?size=500&page=0&sort=createdAt&order=ASCENDING',
          getLogo: 'https://develop.ctvistaplus.com//logo/',
          deleteReport: 'api/v1/ReportManagement/reports/',
          createReport: 'api/v1/ReportManagement/report/',
          getReportById: 'api/v1/ReportManagement/report-id/',
          reportUrl: 'http://172.31.3.250:8086/condenser/report/preview',
          downloadReport: 'http://172.31.3.250:8091/pdf',
          getAPICondenser_v2: 'v2/AssetManagement/assets/',
          getReportList_v2: 'api/v1/ReportManagement/report-all/',
          downloadMappingExcel: 'api/v1/document/excel/mapping',
          downloadHistorianExcel: 'api/v1/document/excel/historian',
          UpdatedYAxisLimits: 'v1/TimeSeries/charts/yaxis',
          accountUrl: 'https://develop.ctvistaplus.com/service/account',
          getTroubleShootingGuide: 'api/v1/document/doc',
          getPopupContentForKpiOverview: 'api/v1/dashboard/design'

        })
      })
    });
    const routerStub = () => ({ navigate: (array, object) => ({}) });
    const reportListviewServiceStub = () => ({
      getJSON: () => ({
        subscribe: f => f({
          baseURL: 'http://172.31.3.250:8082/',
          getAPICondenser: 'v1/AssetManagement/assets/',
          deleteAPICondenser: 'v1/AssetManagement/asset/',
          baseChartURL: 'http://172.31.3.250:8083/',
          getChartAPI: 'v1/TimeSeries/charts',
          postCondenserAPI: 'v1/AssetManagement/asset',
          baseURLCalc: 'http://172.31.3.250:8085',
          postCustomCalcAPI: 'api/v1/runCustomDesignCalculation',
          getCondenserByIsAPI: 'v1/AssetManagement/condenser-asset/',
          patchCondenserAPI: 'v1/AssetManagement/asset/',
          chartKey: 'v1/AssetManagement/condenser-chart-key/',
          condenserLoad: 'v1/AssetManagement/condenser-load/',
          UOMBaseURL: 'http://172.31.3.250:8092/UOM/',
          UOMGetURL: 'api/v1/uom',
          getFacilities: 'https://develop.ctvistaplus.com/service/accounts',
          getPlantDataByFacility: 'v1/AssetManagement/asset-data',
          getUserProfile: 'https://develop.ctvistaplus.com/service/profile',
          baseReportURL: 'http://172.31.3.250:8084/',
          getReportList: 'api/v1/ReportManagement/report-all/2?size=500&page=0&sort=createdAt&order=ASCENDING',
          getLogo: 'https://develop.ctvistaplus.com//logo/',
          deleteReport: 'api/v1/ReportManagement/reports/',
          createReport: 'api/v1/ReportManagement/report/',
          getReportById: 'api/v1/ReportManagement/report-id/',
          reportUrl: 'http://172.31.3.250:8086/condenser/report/preview',
          downloadReport: 'http://172.31.3.250:8091/pdf',
          getAPICondenser_v2: 'v2/AssetManagement/assets/',
          getReportList_v2: 'api/v1/ReportManagement/report-all/',
          downloadMappingExcel: 'api/v1/document/excel/mapping',
          downloadHistorianExcel: 'api/v1/document/excel/historian',
          UpdatedYAxisLimits: 'v1/TimeSeries/charts/yaxis',
          accountUrl: 'https://develop.ctvistaplus.com/service/account',
          getTroubleShootingGuide: 'api/v1/document/doc',
          getPopupContentForKpiOverview: 'api/v1/dashboard/design'
        })
      }),
      downloadReportPost: (paramsobj, serviceURL) => ({
        subscribe: f => f({
          data: ''
        })
      }),
      getReportList_v2: (params, filterobj) => ({ subscribe: f => f({
        timestamp: '09-02-2021 10:28:05',
        data: {
          content: [
            {
              id: '7867a133-f0b8-4056-9393-2205e486e6ff',
              fromDate: '2020-11-08T08:09:24.000+00:00',
              toDate: '2021-02-08T08:09:24.000+00:00',
              reportName: 'Condenser Kings Mountain-08/02/2021',
              tenantId: 1207,
              assetName: 'Condenser_KingsMountain',
              isActive: true,
              facilityName: 'Demo Facility',
              systemName: 'HP Boiler Test - Samir~Condenser_KingsMountain',
              createdBy: '',
              updatedAt: '2021-02-08T08:19:43.000+00:00'
            }
          ],
          pageable: {
            sort: {
              sorted: true,
              unsorted: false,
              empty: false
            },
            pageNumber: 0,
            pageSize: 500,
            offset: 0,
            paged: true,
            unpaged: false
          },
          totalPages: 1,
          totalElements: 40,
          last: true,
          first: true,
          sort: {
            sorted: true,
            unsorted: false,
            empty: false
          },
          numberOfElements: 40,
          size: 500,
          number: 0,
          empty: false
        }
      }) }),
      deleteReport: (id, jsonURl) => ({ subscribe: f => f({}) })
    });
    const toastMessgaeServiceStub = () => ({
      showError: toastErrorMesage => ({}),
      showSuccess: toastSuccessMesage => ({})
    });
    const reportServiceStub = () => ({});
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, MatCardModule, DhrDataTableModule, TableModule, RouterTestingModule],
      declarations: [ReportListviewComponent],
      providers: [
        { provide: ChartService, useFactory: chartServiceStub },
        { provide: Router, useFactory: routerStub },
        {
          provide: ReportListviewService,
          useFactory: reportListviewServiceStub
        },
        { provide: ToastMessgaeService, useFactory: toastMessgaeServiceStub },
        { provide: ReportService, useFactory: reportServiceStub }
      ]
    });
    fixture = TestBed.createComponent(ReportListviewComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`title has default value`, () => {
    expect(component.title).toEqual(`CTvista`);
  });

  it(`cols has default value`, () => {
    expect(component.cols).toEqual([
      {
        field: 'name',
        header: 'Report Name',
        sortOpt: true,
        type: 'text',
      },
      {
        field: 'updatedAt',
        header: 'Last Modified',
        sortOpt: true,
        type: 'Date',
      },
      {
        field: 'assetName',
        header: 'Condenser Name',
        sortOpt: true,
        type: 'text',
      },
      { field: 'rangeDate', header: 'Date Range', sortOpt: true, type: 'text' },
      {
        field: 'createdBy',
        header: 'Created by',
        sortOpt: true,
        type: 'text',
      },
    ]);
  });

  it(`tableData has default value`, () => {
    expect(component.tableData).toEqual([]);
  });

  it(`paginator has default value`, () => {
    expect(component.paginator).toEqual(true);
  });

  it(`searchPlaceHolder has default value`, () => {
    expect(component.searchPlaceHolder).toEqual(`Search by Report Name`);
  });

  it(`row has default value`, () => {
    expect(component.row).toEqual(10);
  });

  it(`showCurrentPageReport has default value`, () => {
    expect(component.showCurrentPageReport).toEqual(true);
  });

  it(`rowsPerPageOptions has default value`, () => {
    expect(component.rowsPerPageOptions).toEqual([10, 25, 50]);
  });

  it(`scrollable has default value`, () => {
    expect(component.scrollable).toEqual(true);
  });

  it(`scrollHeight has default value`, () => {
    expect(component.scrollHeight).toEqual(`400px`);
  });

  it(`styleClass has default value`, () => {
    expect(component.styleClass).toEqual(`p-datatable-striped`);
  });

  it(`sortField has default value`, () => {
    expect(component.sortField).toEqual(`updatedAt`);
  });

  it(`globalFilterFields has default value`, () => {
    expect(component.globalFilterFields).toEqual([`reportName`]);
  });

  it(`actionbtn has default value`, () => {
    expect(component.actionbtn).toEqual(true);
  });

  it(`showDeleteAtnBtn has default value`, () => {
    expect(component.showDeleteAtnBtn).toEqual(true);
  });

  it(`showDuplicateAtnBtn has default value`, () => {
    expect(component.showDuplicateAtnBtn).toEqual(false);
  });

  it(`showViewAtnBtn has default value`, () => {
    expect(component.showViewAtnBtn).toEqual(true);
  });

  it(`showDownloadAtnBtn has default value`, () => {
    expect(component.showDownloadAtnBtn).toEqual(true);
  });

  it(`showShareAtnBtn has default value`, () => {
    expect(component.showShareAtnBtn).toEqual(false);
  });

  it(`copyData has default value`, () => {
    expect(component.copyData).toEqual([]);
  });

  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      component.serviceURL = {
        baseURL: 'http://172.31.3.250:8082/',
        getAPICondenser : 'v1/AssetManagement/assets/',
        deleteAPICondenser : 'v1/AssetManagement/asset/',
        baseChartURL: 'http://172.31.3.250:8083/',
        getChartAPI: 'v1/TimeSeries/charts',
        getReportList_v2: ''};
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      const reportListviewServiceStub: ReportListviewService = fixture.debugElement.injector.get(
        ReportListviewService
      );
      component.ngOnInit();
    });
  });
  describe('downloadReport', () => {
    it('makes expected calls', () => {
      component.serviceURL = {
        baseURL: 'http://172.31.3.250:8082/',
        getAPICondenser: 'v1/AssetManagement/assets/',
        deleteAPICondenser: 'v1/AssetManagement/asset/',
        baseChartURL: 'http://172.31.3.250:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      component.accountLogo = '';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      const reportListviewServiceStub: ReportListviewService = fixture.debugElement.injector.get(
        ReportListviewService
      );
      component.downloadReport({ id: '' });
    });
  });
  describe('viewReport', () => {
    it('makes expected calls', () => {
      component.serviceURL = {
        baseURL: 'http://172.31.3.250:8082/',
        getAPICondenser: 'v1/AssetManagement/assets/',
        deleteAPICondenser: 'v1/AssetManagement/asset/',
        baseChartURL: 'http://172.31.3.250:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      component.accountLogo = '';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      const reportListviewServiceStub: ReportListviewService = fixture.debugElement.injector.get(
        ReportListviewService
      );
      component.viewReport({ id: '' });
    });
  });
  describe('deleteData', () => {
    it('makes expected calls', () => {
      component.serviceURL = {
        baseURL: 'http://172.31.3.250:8082/',
        getAPICondenser: 'v1/AssetManagement/assets/',
        deleteAPICondenser: 'v1/AssetManagement/asset/',
        baseChartURL: 'http://172.31.3.250:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      component.accountLogo = '';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      const reportListviewServiceStub: ReportListviewService = fixture.debugElement.injector.get(
        ReportListviewService
      );
      component.deleteData({ id: '' , reportName: ''});
    });
  });
  describe('getReportDataTable', () => {
    it('makes expected calls', () => {
      component.serviceURL = {
        baseURL: 'http://172.31.3.250:8082/',
        getAPICondenser: 'v1/AssetManagement/assets/',
        deleteAPICondenser: 'v1/AssetManagement/asset/',
        baseChartURL: 'http://172.31.3.250:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      component.accountLogo = '';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      const reportListviewServiceStub: ReportListviewService = fixture.debugElement.injector.get(
        ReportListviewService
      );
      component.getReportDataTable(component.serviceURL);
    });
  });
  describe('ngAfterViewInit', () => {
    it('makes expected calls', () => {
      component.serviceURL = {
        baseURL: 'http://172.31.3.250:8082/',
        getAPICondenser: 'v1/AssetManagement/assets/',
        deleteAPICondenser: 'v1/AssetManagement/asset/',
        baseChartURL: 'http://172.31.3.250:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      component.accountLogo = '';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      const reportListviewServiceStub: ReportListviewService = fixture.debugElement.injector.get(
        ReportListviewService
      );
      component.ngAfterViewInit();
    });
  });
  describe('getLogo', () => {
    it('makes expected calls', () => {
      component.serviceURL = {
        baseURL: 'http://172.31.3.250:8082/',
        getAPICondenser: 'v1/AssetManagement/assets/',
        deleteAPICondenser: 'v1/AssetManagement/asset/',
        baseChartURL: 'http://172.31.3.250:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      component.accountLogo = '';
      component.recentUserAccId = '';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      const reportListviewServiceStub: ReportListviewService = fixture.debugElement.injector.get(
        ReportListviewService
      );
      component.getLogo(component.serviceURL);
    });
  });
  describe('reportdataError', () => {
    it('makes expected calls', () => {
      component.serviceURL = {
        baseURL: 'http://172.31.3.250:8082/',
        getAPICondenser: 'v1/AssetManagement/assets/',
        deleteAPICondenser: 'v1/AssetManagement/asset/',
        baseChartURL: 'http://172.31.3.250:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      component.accountLogo = '';
      component.recentUserAccId = '';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      const reportListviewServiceStub: ReportListviewService = fixture.debugElement.injector.get(
        ReportListviewService
      );
      component.reportdataError({message: ''});
    });
  });
  describe('setAccountDetail', () => {
    it('makes expected calls', () => {
      component.serviceURL = {
        baseURL: 'http://172.31.3.250:8082/',
        getAPICondenser: 'v1/AssetManagement/assets/',
        deleteAPICondenser: 'v1/AssetManagement/asset/',
        baseChartURL: 'http://172.31.3.250:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      component.accountLogo = '';
      component.recentUserAccId = '';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      const reportListviewServiceStub: ReportListviewService = fixture.debugElement.injector.get(
        ReportListviewService
      );
      const account = {
        account: {
          name: '',
          id: ''
        },
        facility: {
          name: '',
          id: ''
        },
        system: {
          name: '',
          id: ''
        }
      };
      component.setAccountDetail(account);
    });
  });
  describe('deleatDataError', () => {
    it('makes expected calls', () => {
      component.serviceURL = {
        baseURL: 'http://172.31.3.250:8082/',
        getAPICondenser: 'v1/AssetManagement/assets/',
        deleteAPICondenser: 'v1/AssetManagement/asset/',
        baseChartURL: 'http://172.31.3.250:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      component.accountLogo = '';
      component.recentUserAccId = '';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      const reportListviewServiceStub: ReportListviewService = fixture.debugElement.injector.get(
        ReportListviewService
      );
      component.deleatDataError({reportName: ''});
    });
  });
});
