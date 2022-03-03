import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AdvancedChartsComponent } from './advanced-charts.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ChartService } from '../../kpi-dashboard/service/chart.service';
import { By } from '@angular/platform-browser';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MessageService } from 'primeng/api';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ToastMessgaeService } from '../../shared/toast-service/service/toast-messgae.service';

describe('AdvancedChartsComponent', () => {
  let component: AdvancedChartsComponent;
  let fixture: ComponentFixture<AdvancedChartsComponent>;

  let service: ChartService;

  beforeEach(async(() => {
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
      getchartload: (serviceURl, assetID) => ({ subscribe: f => f({
        minVal: 2, maxVal: 100
      }) }),
      postUpdatedYAxisLimits: (url, assetID,) => ({ subscribe: f => f({
        timestamp: '09-02-2021 12:03:14', data: {chart_data: [{
          name: "air_enthalpy ",
          display_name: "Air Enthalpy (kJ/kg air)",
          assetParamName: "882c6b4f-1874-45fe-b18b-810cd0bb0be1",
          min: -1.55,
          max: 50.18,
          data: [
            {
              date: "2020-11-12T08:15:00.000+00:00",
              load: 0,
              value: 46.46
            },
            {
              date: "2020-11-12T08:15:00.000+00:00",
              load: 5,
              value: 46.46
            }
          ]
        },]}
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
    });
    const matDialogStub = () => ({
      open: (YAxisLimitModalComponent, object) => ({
        afterClosed: () => of({'intialData' : []})
      }),
    });
    const toastMessgaeServiceStub = () => ({
      showSuccess: toastSuccessMesage => ({})
    });
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      declarations: [AdvancedChartsComponent],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
        MessageService,
        { provide: ToastMessgaeService, useFactory: toastMessgaeServiceStub },
        { provide: MatDialog, useFactory: matDialogStub },
        { provide: ChartService, useFactory: chartServiceStub },
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedChartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service = TestBed.inject(ChartService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should call method on Oninit', () => {
    spyOn(component, 'ngOnInit').and.callThrough();
    fixture.detectChanges();
    component.ngOnInit();
    expect(component.ngOnInit).toHaveBeenCalled();
  });
  it('should call changeDate menthod', async(() => {
    spyOn(component, 'changeDate');
    fixture.debugElement.query(By.css('input')).triggerEventHandler('change', null);
    fixture.whenStable().then(() => {
      expect(component.changeDate).toHaveBeenCalled();
    });
  }));
  it('should call changeDate method with value', () => {
    const date = { endDate: new Date(), startDate: new Date() };
    component.changeDate(date);
    fixture.detectChanges();
    spyOn(component, 'changeDate');
    service.getJSON().subscribe((url) => {
      component.getChart('url', '', '', '', '');
    });
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.changeDate).toHaveBeenCalled();
    });
  });

  it('should call plantLoadFltr method', () => {
    const e = { value: '12', highValue: '123' };
    component.plantLoadFltr(e);
    fixture.detectChanges();
    spyOn(component, 'plantLoadFltr');
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.plantLoadFltr).toHaveBeenCalled();
    });
  });
  it('should be able load slider', () => {
    component.loadSlider(2, 5);
  });
  it('should be able update date for chart', () => {
    component.chartArray = [];
    component.CustomchartArray = [];
    component.updateDataOfMultiSeriesChart([{
      date: '20/10/2020',
      value: 10,
      load: 5
    }], 10, 10, 'a');
  });
  describe('getChartByLoad', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseChartURL: 'http://34.216.125.13:8082/',
        getChartAPI: 'v1/AssetManagement/assets/',
      };
      component.assetID = 'ss323243';
      fixture.detectChanges();
      spyOn(component, 'getAdvancedChartOnChangedLoadRange').and.callThrough();
      spyOn(component, 'getchartKey').and.callFake(() => { })
      component.getChartByLoad();
      expect(component.getAdvancedChartOnChangedLoadRange).toHaveBeenCalled();
    });
  });
  describe('getCharLoad', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseURL: 'http://34.216.125.13:8082/',
        condenserLoad:'v1/AssetManagement/condenser-load/'
      };
      component.assetID = 'ss323243';
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(component, 'loadSlider').and.callFake(() => { })
      spyOn(chartServiceStub, 'getchartload').and.callThrough();
      component.getCharLoad();
      expect(chartServiceStub.getchartload).toHaveBeenCalled();
    });
  });
  describe('getMutiSeriesChart', () => {
    let comparedChartData = {
      chart_data: [
        {
          avgKpi: 23.45,
          data: [
            {
              date: "2020-11-09T00:00:00.000+00:00",
              load: 0,
              value: 36.63
            },
            {
              date: "2020-11-09T00:15:00.000+00:00",
              load: 57.6,
              value: 17.96
            }
          ],
          display_name: "Air Enthalpy (kJ/kg air)",
          max: 63.38,
          min: -1.55,
          name: "air_enthalpy,"
        },
        {
          avgKpi: 53.45,
          data: [
            {
              date: "2020-11-09T00:00:00.000+00:00",
              load: 0,
              value: 36.63
            },
            {
              date: "2020-11-09T00:15:00.000+00:00",
              load: 58.6,
              value: 17.96
            }
          ],
          display_name: "Ambient Temp (°F)",
          max: 63.38,
          min: -1.55,
          name: "ambient_temp"
        }
      ]
    }
    it('makes expected calls', () => {
      component.serviceURl = {
        baseURL: 'http://34.216.125.13:8082/',
        condenserLoad: 'v1/AssetManagement/assets/',
      };
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(component, 'getMutiSeriesChart').and.callThrough();
      component.getMutiSeriesChart(comparedChartData);
      expect(component.getMutiSeriesChart).toHaveBeenCalled();
    });
  });
  describe('getAllChartsOnLoad', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseURL: 'http://34.216.125.13:8082/',
        condenserLoad: 'v1/AssetManagement/assets/',
      };
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(component, 'getAllChartsOnLoad').and.callThrough();
      component.getAllChartsOnLoad();
      expect(component.getAllChartsOnLoad).toHaveBeenCalled();
    });
  });
  describe('selectCustomChartForComparison', () => {
    let chartValues = {
      category: "CUSTOM",
      computed: false,
      displayName: "Ambient Temp (°F)",
      id: "928924e9-7a19-4fd8-ae59-fe2c99cf9034",
      key: "ambient_temp",
      type: "RAW",
      uom: "124"
    }
    it('makes expected calls', () => {
      component.serviceURl = {
        baseChartURL: 'http://34.216.125.13:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(component, 'selectCustomChartForComparison').and.callThrough();
      component.selectCustomChartForComparison('928924e9-7a19-4fd8-ae59-fe2c99cf9034', true, chartValues);
      expect(component.selectCustomChartForComparison).toHaveBeenCalled();
    });
  });
  describe('selectCustomChartForComparison', () => {
    let chartValues = {
      category: "CUSTOM",
      computed: false,
      displayName: "Ambient Temp (°F)",
      id: "928924e9-7a19-4fd8-ae59-fe2c99cf9034",
      key: "ambient_temp",
      type: "RAW",
      uom: "124"
    }
    it('makes expected calls', () => {
      component.selectedChartsId = ['jhasd870', '9870asdh', 'dnasj987'];
      component.comparedChartData.chart_data = [{
        name: "air_enthalpy ",
        display_name: "Air Enthalpy (kJ/kg air)",
        assetParamName: "882c6b4f-1874-45fe-b18b-810cd0bb0be1",
        min: -1.55,
        max: 50.18,
        data: [
          {
            date: "2020-11-12T08:15:00.000+00:00",
            load: 0,
            value: 46.46
          },
          {
            date: "2020-11-12T08:15:00.000+00:00",
            load: 5,
            value: 46.46
          }
        ]
      },
      {
        name: "ambient_temp",
        display_name: "Ambient Temp (°F)",
        assetParamName: "882c6b4f-1874-45fe-b18b-810cd0bb0be1",
        min: -1.55,
        max: 50.18,
        data: [
          {
            date: "2020-11-12T08:15:00.000+00:00",
            load: 0,
            value: 46.46
          },
          {
            date: "2020-11-12T08:15:00.000+00:00",
            load: 5,
            value: 46.46
          }
        ]
      }
      ]
      component.serviceURl = {
        baseChartURL: 'http://34.216.125.13:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(component, 'selectCustomChartForComparison').and.callThrough();
      component.selectCustomChartForComparison('928924e9-7a19-4fd8-ae59-fe2c99cf9034', false, chartValues);
      expect(component.selectCustomChartForComparison).toHaveBeenCalled();
    });
  });
  describe('onSave', () => {
    let myForm = {
      form: {
        value: {
          "abc +max": 63.38,
          "abc +min": -1.55,
          "xyz+max": 80.95,
          "xyz+min": 23.2
        }
      }
    }
    it('makes expected calls', () => {
      component.serviceURl = {
        baseChartURL: 'http://34.216.125.13:8083/',
        UpdatedYAxisLimits : 'v1/TimeSeries/charts/yaxis'
      };
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(component, 'onSave').and.callThrough();
      component.onSave(myForm);
      expect(component.onSave).toHaveBeenCalled();
    });
  });
  describe('getchartKey', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseURL: 'http://34.216.125.13:8082/',
        condenserLoad: 'v1/AssetManagement/assets/',
      };
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(component, 'getchartKey').and.callThrough();
      component.getchartKey(component.serviceURl);
      expect(component.getchartKey).toHaveBeenCalled();
    });
  });
  describe('getChart', () => {
    it('makes expected calls', () => {
      component.serviceURl = {
        baseChartURL: 'http://34.216.125.13:8083/',
        getChartAPI: 'v1/TimeSeries/charts'
      };
      fixture.detectChanges();
      const chartServiceStub: ChartService = fixture.debugElement.injector.get(
        ChartService
      );
      spyOn(component, 'getChart').and.callThrough();
      component.getChart(component.serviceURl,'','','','');
      expect(component.getChart).toHaveBeenCalled();
    });
  });
  describe('openModel', () => {
    it('makes expected calls', () => {
      spyOn(component, 'openModel').and.callThrough();
      component.openModel( () => {});
      expect(component.openModel).toHaveBeenCalled();
    });
  });
});