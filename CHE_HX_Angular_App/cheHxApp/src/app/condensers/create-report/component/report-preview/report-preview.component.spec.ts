import { HttpClientTestingModule } from '@angular/common/http/testing';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ActivatedRoute, convertToParamMap, Router } from '@angular/router';
import { ChartService } from 'src/app/condensers/kpi-dashboard/service/chart.service';
import { ReportService } from '../../service/report.service';
import { ReportListviewService } from 'src/app/condensers/report-listview/service/report-listview.service';
import { ReportPreviewComponent } from './report-preview.component';
import { APP_BASE_HREF } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { ToastMessgaeService } from 'src/app/condensers/shared/toast-service/service/toast-messgae.service';
import { MessageService } from 'primeng';
import { Observable, of } from 'rxjs';

describe('ReportPreviewComponent', () => {
  let component: ReportPreviewComponent;
  let fixture: ComponentFixture<ReportPreviewComponent>;


  beforeEach(async(() => {
    const routerStub = () => ({ getCurrentNavigation: () => ({
      extras: {
        state: {
          state: ''
        }
      }
    }),
    queryParams : new Observable(observer => {
      const urlParams = {
        id: '', state: '', mode: 'pdf'
      };
      observer.next(urlParams);
      observer.complete();
    }),
    paramMap : of(convertToParamMap({
      id: '', state: '', mode: ''
    }))
   });
    const reportListviewServiceStub = () => ({
          getReportById: (serviceURl, reportId) => ({ subscribe: f => f({
            createdAt: '2021-02-08T08:18:42.000+00:00',
            updatedAt: '2021-02-08T08:19:43.000+00:00',
            createdBy: '',
            updatedBy: null,
            id: '7867a133-f0b8-4056-9393-2205e486e6ff',
            minLoad: 0,
            maxLoad: 100,
            fromDate: '2020-11-08T08:09:24.000+00:00',
            toDate: '2021-02-08T08:09:24.000+00:00',
            assetId: '4272739a-a628-4e8a-8cdc-0c5fa34ef1c3',
            assetName: 'Condenser_KingsMountain',
            reportName: 'Condenser Kings Mountain-08/02/2021',
            reportJson: {
              reportLayout: [
                {
                  id: 'aa6ea0ba-e20e-c225-8861-f14eb92c8764',
                  headerTitle: 'BP Deviation Overall (inHga)',
                  layoutType: 'chart',
                  data: [
                    {
                      xlabel: 'inHg(a)',
                      yLabel: 'BP Deviation Overall (inHga)',
                      chartLabel: 'BP Deviation Overall (inHga)',
                      chartdata: [
                        [
                          '11-9-20 05:30',
                          12.537644333333335,
                          0
                        ],
                        [
                          '11-9-20 05:45',
                          12.537644333333335,
                          0
                        ],
                        [
                          '11-9-20 06:00',
                          12.537644333333335,
                          0
                        ],
                        [
                          '11-9-20 06:15',
                          12.538152666666667,
                          0
                        ],
                        [
                          '11-9-20 06:30',
                          12.548152666666667,
                          0
                        ]
                      ]
                    }
                  ]
                }
              ],
              reportview: [
                [
                  'aa6ea0ba-e20e-c225-8861-f14eb92c8764'
                ]
              ]
            },
            tenantId: 1207,
            isActive: true,
            facilityName: 'Demo Facility',
            systemName: 'HP Boiler Test - Samir~Condenser_KingsMountain'
          }) }),
          downloadReportPost: (paramsobj, serviceURl) => ({
            subscribe: f => f({})
          }),
          deleteReport: (id, serviceURl) => ({ subscribe: f => f({}) })
        });
    const chartServiceStub = () => ({
      getJSON: () => ({ subscribe: f => f({
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
      }) }),
      getLogo: (url, recentUserAccid) => ({ subscribe: f => f({}) })
    });
    const matDialogStub = () => ({
      open: (dataDrivinModalComponent, object) => ({})
    });
    const toastMessgaeServiceStub = () => ({
      showSuccess: toastSuccessMesage => ({})
    });
    const reportServiceStub = () => ({
      previewLayout: { filter: () => ({}) },
      matarialLayout: { filter: () => ([]) },
      addReportData: repoData => ({}),
      report: {
        cName: { trim: () => ({}) },
        cID: {},
        value: {},
        highValue: {},
        fromeDte: {},
        toDate: {},
        systemName: {},
        facilityName: {},
        name: ''
      }
    });
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      declarations: [ ReportPreviewComponent ],
      providers: [
        MessageService,
        { provide: ChartService, useFactory: chartServiceStub },
        { provide: ReportService, useFactory: reportServiceStub },
        {
                      provide: ReportListviewService,
                      useFactory: reportListviewServiceStub
                    },
        {provide: APP_BASE_HREF, useValue : '/' },
        { provide: MatDialog, useFactory: matDialogStub },
        { provide: ToastMessgaeService, useFactory: toastMessgaeServiceStub },
        { provide: ActivatedRoute, useFactory: routerStub },
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it(`accountLogo has default value`, () => {
    component.accountLogo = 'https://develop.ctvistaplus.com/';
    expect(component.accountLogo).toEqual('https://develop.ctvistaplus.com/');
  });

  it(`previewLayout has default value`, () => {
    component.previewLayout = [];
    fixture.detectChanges();
    expect(component.previewLayout).toEqual([]);
  });

  it('makes expected calls', () => {
      component.pdfState = true;
      component.state = 'pdf';
      component.reportId = 'aa6ea0ba-e20e-c225-8861-f14eb92c876';
      component.ngOnInit();
    });


  it('createPreviewLayout makes expected calls', () => {
     component.createPreviewLayout();
       });
  it('editReport makes expected calls', () => {
      component.editReport();
    });
  it('elseMessage makes calls', () => {
      component.elseMessage();
    });
  it('successModel makes calls', () => {
      component.successModel();
    });
  // it('errorModel makes calls', () => {
  //     component.errorModel();
  //   });
  it('getReport makes calls', () => {
    component.getReport();
    });
  it('downloadReport makes calls', () => {
      component.downloadReport();
      });
  it('getLogo makes calls', () => {
    component.recentUserAccid = '1207';
    component.getLogo('http://172.31.3.250:8082/');
   });
  it('errorModel makes calls', () => {
    component.errorModel();
    });
});
