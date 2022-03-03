import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ChartService } from '../service/chart.service';
import { ToastMessgaeService } from '../../shared/toast-service/service/toast-messgae.service';
import { CondenserListviewService } from '../../condenser-listview/service/condenser-listview.service';
import { Router, RouterModule } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { FormsModule, NgControl, ReactiveFormsModule } from '@angular/forms';
import { KpiDashboardComponent } from './kpi-dashboard.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { DhrChartModule } from 'dhr-chart';
import { MatSelectModule } from '@angular/material/select';
import { DropdownModule } from 'primeng/dropdown';
import { MultiSelectModule } from 'primeng/multiselect';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule } from '@angular/material/tabs';
import html2canvas from 'html2canvas';

describe('KpiDashboardComponent', () => {
  let component: KpiDashboardComponent;
  let fixture: ComponentFixture<KpiDashboardComponent>;

  beforeEach(() => {
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
      getUOM: serviceURl => ({ subscribe: f => f([{id: '393', name: ' '}, {id: '124', name: '$'}]) }),
      getchartload: (serviceURl, assetID) => ({ subscribe: f => f({
        minVal: 2, maxVal: 100
      }) }),
      getchartKey: (url, assetID, arg1) => ({ subscribe: f => f(
        [
          {
            key: 'bp_deviation_overall',
            id: '3b472f4e-21c0-484a-88d7-344b4dc5f0ad',
            uom: '393',
            displayName: 'BP Deviation Overall (inHga)',
            type: 'RAW',
            computed: true,
            category: 'KPI'
          },
          {
            key: 'ttd_deviation',
            id: 'bcc1d209-b3ba-4c63-8518-69670686e1ba',
            uom: '124',
            displayName: 'TTD Deviation (°F)',
            type: 'RAW',
            computed: true,
            category: 'KPI'
          }
        ]
      ) }),
      getChart: (url, fD, eD, paramsObj) => ({ subscribe: f => f({
        timestamp: '09-02-2021 12:03:14', data: {chart_data: []}
      }) }),
      getSequenecForKPI: (data) => (
        [{
          name: 'tr_deviation',
          key: '1'
        }]
      ),
      getPopupContentForKpiOverview: (serviceURl, paramObject) => ({
        subscribe: f => f({
          data: {
            kpi_cofig: [{name: 'kpi' , troubleshooting: ''}]
          }
        })
      }),
      getTroubleShootingGuide: serviceURl => ({ subscribe: f => f({}) })
    });
    const toastMessgaeServiceStub = () => ({
      showSuccess: toastSuccessMesage => ({})
    });
    const condenserListviewServiceStub = () => ({
      getCondenser_v2: (serviceURl, paramsobj) => ({ subscribe: f => f({
        data: {
          content: [
            {
              id: '24864cc1-4376-4b58-b9f8-9c94be1aaedb',
              createdAt: '2020-12-19 13:38:43',
              updatedAt: '2021-01-22 09:09:16',
              createdBy: 'vinayk',
              updatedBy: 'vinayk',
              name: 'Test_uom',
              status: true,
              facilityName: 'Demo Facility',
              accountName: 'Test_uom',
              systemName: 'KAES Enid'
            },
            {
              id: '6b879434-b970-4e9e-b61d-a566b7781e18',
              createdAt: '2021-01-15 10:52:42',
              updatedAt: '2021-01-22 06:54:38',
              createdBy: 'vinayk',
              updatedBy: 'vinayk',
              name: 'Test_UOM_Update',
              status: true,
              facilityName: 'Demo Facility',
              accountName: 'Test_UOM_Update',
              systemName: 'SkyBitz System'
            }
          ],
          pageable: {
            sort: {
              sorted: true,
              unsorted: false,
              empty: false
            },
            pageNumber: 0,
            pageSize: 100,
            offset: 0,
            unpaged: false,
            paged: true
          },
          last: true,
          totalPages: 1,
          totalElements: 60,
          first: true,
          sort: {
            sorted: true,
            unsorted: false,
            empty: false
          },
          numberOfElements: 60,
          size: 100,
          number: 0,
          empty: false
        }
      }) })
    });
    const routerStub = () => ({ navigate: (array, object) => ({}) });
    const matDialogStub = () => ({
      open: (kpiOverviewModalComponent, object) => ({})
    });
    const mockNgControl = jasmine.createSpyObj('ngControl', ['value']);
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule,
        MatSelectModule,
        MultiSelectModule,
        DropdownModule,
        MatTabsModule,
        DhrChartModule],
      declarations: [KpiDashboardComponent],
      providers: [
        { provide: NgControl, useValue: mockNgControl },
        { provide: ChartService, useFactory: chartServiceStub },
        { provide: ToastMessgaeService, useFactory: toastMessgaeServiceStub },
        {
          provide: CondenserListviewService,
          useFactory: condenserListviewServiceStub
        },
        { provide: Router, useFactory: routerStub },
        { provide: MatDialog, useFactory: matDialogStub }
      ]
    });
    fixture = TestBed.createComponent(KpiDashboardComponent);
    TestBed.inject(ChartService)
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`invalidDates has default value`, () => {
    expect(component.invalidDates).toEqual([]);
  });

  it(`disableLoader has default value`, () => {
    expect(component.disableLoader).toEqual(true);
  });

  it(`filterdate has default value`, () => {
    expect(component.filterdate).toEqual([]);
  });

  it(`loadPlantFiltrVal has default value`, () => {
    expect(component.loadPlantFiltrVal).toEqual([]);
  });

  it(`tabSelected has default value`, () => {
    expect(component.tabSelected).toEqual(`KPI Charts`);
  });

  it(`value has default value`, () => {
    expect(component.value).toEqual(0);
  });

  it(`highValue has default value`, () => {
    expect(component.highValue).toEqual(100);
  });

  it(`showNoRcrdFnd has default value`, () => {
    expect(component.showNoRcrdFnd).toEqual(false);
  });

  it(`condensers has default value`, () => {
    expect(component.condensers).toEqual([]);
  });

  it(`condenserParsedList has default value`, () => {
    expect(component.condenserParsedList).toEqual([]);
  });

  it(`chartArray has default value`, () => {
    expect(component.chartArray).toEqual([]);
  });

  it(`CustomchartArray has default value`, () => {
    expect(component.CustomchartArray).toEqual([]);
  });

  it(`UOMArray has default value`, () => {
    expect(component.UOMArray).toEqual([]);
  });

  it(`kpiChartsDetails has default value`, () => {
    expect(component.kpiChartsDetails).toEqual([]);
  });

  it(`recentUserAccId has default value`, () => {
    expect(component.recentUserAccId).toEqual(``);
  });

  it(`selectedCharts has default value`, () => {
    expect(component.selectedCharts).toEqual([``]);
  });

  it(`allCharts has default value`, () => {
    expect(component.allCharts).toEqual([]);
  });

  it(`url has default value`, () => {
    expect(component.url).toEqual(`condenser/dashboard/advancedCharts`);
  });

  it(`kpiChartFirstLoad has default value`, () => {
    expect(component.kpiChartFirstLoad).toEqual(true);
  });

  it(`customChartFirstLoad has default value`, () => {
    expect(component.customChartFirstLoad).toEqual(true);
  });

  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.serviceURl = {
        UOMGetURL: 'http://34.216.125.13:8082/',
        UOMBaseURL : 'v1/AssetManagement/assets/',
    deleteAPICondenser : 'v1/AssetManagement/asset/',
    baseChartURL: 'http://34.216.125.13:8083/',
    getAPICondenser_v2 : 'v1/AssetManagement/asset/',
    baseURL: 'http://34.216.125.13:8083/',
      };
      component.assetID = 'ss323243';
      fixture.detectChanges();
      component.ngOnInit();
    });
  });

  describe('getcondenserList', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        getAPICondenser_v2 : 'v1/AssetManagement/asset/',
        baseURL: 'http://34.216.125.13:8083/',
      };
      component.assetID = 'ss323243';
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      fixture.detectChanges();
      const condenserListviewServiceStub: CondenserListviewService = fixture.debugElement.injector.get(
        CondenserListviewService
      );
      fixture.detectChanges();
      component.getcondenserList();
    });
  });

  describe('createReport', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        getAPICondenser_v2 : 'v1/AssetManagement/asset/',
        baseURL: 'http://34.216.125.13:8083/',
      };
      component.assetID = 'ss323243';
      component.assetName = 'name';
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      fixture.detectChanges();
      const routerStub: Router = fixture.debugElement.injector.get(Router);
      spyOn(routerStub, 'navigate').and.callThrough();
      component.createReport();
      expect(routerStub.navigate).toHaveBeenCalled();
    });
  });

  describe('getUOM', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        UOMGetURL: 'http://34.216.125.13:8082/',
        UOMBaseURL : 'v1/AssetManagement/assets/',
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      component.assetID = 'ss323243';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(chartServiceStub, 'getUOM').and.callThrough();
      component.getUOM();
      expect(chartServiceStub.getUOM).toHaveBeenCalled();
    });
  });

  describe('getCharLoad', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseURL: 'http://34.216.125.13:8082/',
        condenserLoad : 'v1/AssetManagement/assets/',
      };
      component.assetID = 'ss323243';
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(component, 'loadSlider').and.callThrough();
      spyOn(chartServiceStub, 'getchartload').and.callThrough();
      component.getCharLoad();
      expect(component.loadSlider).toHaveBeenCalled();
      expect(chartServiceStub.getchartload).toHaveBeenCalled();
    });
  });

  describe('getChartByLoad', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseChartURL: 'http://34.216.125.13:8082/',
        getChartAPI : 'v1/AssetManagement/assets/',
      };
      component.assetID = 'ss323243';
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      component.tabSelected  = 'KPI Charts';
      fixture.detectChanges();
      component.getChartByLoad();
    });
  });
  describe('getChartByLoad', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseChartURL: 'http://34.216.125.13:8082/',
        getChartAPI : 'v1/AssetManagement/assets/',
      };
      component.assetID = 'ss323243';
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      component.tabSelected  = 'Custom Charts';
      fixture.detectChanges();
      component.getChartByLoad();
    });
  });

  describe('viewCondenser', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseChartURL: 'http://34.216.125.13:8082/',
        getChartAPI : 'v1/AssetManagement/assets/',
      };
      component.assetID = 'ss323243';
      component.assetName = 'name';
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      fixture.detectChanges();
      const routerStub: Router = fixture.debugElement.injector.get(Router);
      spyOn(routerStub, 'navigate').and.callThrough();
      component.viewCondenser();
      expect(routerStub.navigate).toHaveBeenCalled();
    });
  });

  describe('viewTroubleShootingGuide', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(chartServiceStub, 'getTroubleShootingGuide').and.callThrough();
      component.viewTroubleShootingGuide();
      expect(chartServiceStub.getTroubleShootingGuide).toHaveBeenCalled();
    });
  });
  describe('dateToBeShownOnCards', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.dateToBeShownOnCards();
    });
  });
  describe('openModel', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.openModel({}, {chartname: 'kpi', chartLabel: '' });
    });
  });
  describe('viewAdvancedCharts', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.viewAdvancedCharts();
    });
  });
  describe('onTabChanged', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.onTabChanged({tab: {
        textLabel: ''
      }});
    });
  });
  describe('customChartSelect', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.customChartSelect('3b472f4e-21c0-484a-88d7-344b4dc5f0ad', true, {
        key: 'bp_deviation_overall',
        id: '3b472f4e-21c0-484a-88d7-344b4dc5f0ad',
        uom: '393',
        displayName: 'BP Deviation Overall (inHga)',
        type: 'RAW',
        computed: true,
        category: 'KPI'
      });
    });
  });
  describe('changeDate', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      component.tabSelected  = 'KPI Charts';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.changeDate({startDate: {_d: ''}, endDate: {_d: ''}});
    });
  });
  describe('downloadImage', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      component.tabSelected  = 'KPI Charts';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.downloadImage(0.7);
    });
  });
  describe('getchartKey', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      component.tabSelected  = 'KPI Charts';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.getchartKey(component.serviceURl);
    });
  });
  describe('findUOMName', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.UOMArray = [{id: '393', name: ' '}, {id: '124', name: '$'}];
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.findUOMName('393');
    });
  });
  describe('setAccounts', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.kpiChartsDetails = [{
        chartkey: '',
        key: '1'
      }];
      component.tabSelected  = 'KPI Charts';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
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
      component.setAccounts(account);
    });
  });
  describe('cdsDrpDwnChange', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.condensers = [ {
        id: '24864cc1-4376-4b58-b9f8-9c94be1aaedb',
        createdAt: '2020-12-19 13:38:43',
        updatedAt: '2021-01-22 09:09:16',
        createdBy: 'vinayk',
        updatedBy: 'vinayk',
        name: 'Test_uom',
        status: true,
        facilityName: 'Demo Facility',
        accountName: 'Test_uom',
        systemName: 'KAES Enid'
      }];
      component.tabSelected  = 'KPI Charts';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.cdsDrpDwnChange({value: '24864cc1-4376-4b58-b9f8-9c94be1aaedb', originalEvent: {target: {textContent: ''}}});
    });
  });
  describe('plantLoadFltr', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseReportURL: 'http://34.216.125.13:8084/',
        getTroubleShootingGuide: 'api/v1/document/doc'
      };
      component.condensers = [ {
        id: '24864cc1-4376-4b58-b9f8-9c94be1aaedb',
        createdAt: '2020-12-19 13:38:43',
        updatedAt: '2021-01-22 09:09:16',
        createdBy: 'vinayk',
        updatedBy: 'vinayk',
        name: 'Test_uom',
        status: true,
        facilityName: 'Demo Facility',
        accountName: 'Test_uom',
        systemName: 'KAES Enid'
      }];
      component.tabSelected  = 'KPI Charts';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      component.plantLoadFltr({value: 2, highValue: 100});
    });
  });
  it('should be able update date for chart', () => {
    component.chartArray = [];
    component.CustomchartArray = [];
    let item = [
      { date: "2020-11-12T00:00:00.000+00:00", load: 0, value: 47.01 },
      { date: "2020-11-12T00:00:00.000+00:00", load: 9, value: 47.01 }
    ]
    component.updateDateOfChart(item, "dew", "Air Enthalpy (kJ/kg air)", "Air Enthalpy (kJ/kg air)", false, 21.29, "air_enthalpy ");
  });
  it('should be able update date for chart', () => {
    component.chartArray = [];
    component.CustomchartArray = [];
    let item = [
      { date: "2020-11-12T00:00:00.000+00:00", load: 0, value: 47.01 },
      { date: "2020-11-12T00:00:00.000+00:00", load: 9, value: 47.01 }
    ]
    component.updateDateOfChart(item, "dew", "Air Enthalpy (kJ/kg air)", "Air Enthalpy (kJ/kg air)", true, 21.29, "air_enthalpy ");
  });
});
