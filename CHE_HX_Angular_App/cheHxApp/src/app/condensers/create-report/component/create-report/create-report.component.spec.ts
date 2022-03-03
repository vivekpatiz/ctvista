import { OverlayRef } from '@angular/cdk/overlay';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSelect } from '@angular/material/select';
import { ActivatedRoute, Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { MessageService } from 'primeng';
import { Observable, of } from 'rxjs';
import { CreateCondenserService } from 'src/app/condensers/create-condenser/service/create-condenser.service';
import { ChartService } from 'src/app/condensers/kpi-dashboard/service/chart.service';
import { ToastMessgaeService } from 'src/app/condensers/shared/toast-service/service/toast-messgae.service';
import { ReportService } from '../../service/report.service';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';

import { CreateReportComponent } from './create-report.component';
import { MatOption } from '@angular/material/core';
import { QueryList } from '@angular/core';

describe('CreateReportComponent', () => {
  let component: CreateReportComponent;
  let fixture: ComponentFixture<CreateReportComponent>;
  let service: ReportService;
  let service1: ChartService;
  let service2: CreateCondenserService;
  const formBuilder: FormBuilder = new FormBuilder();

  const urlJson = {
    "baseURL": "http://172.31.3.250:8082/",
    "getAPICondenser" : "v1/AssetManagement/assets/",
    "deleteAPICondenser" : "v1/AssetManagement/asset/",
    "baseChartURL": "http://172.31.3.250:8083/",
    "getChartAPI": "v1/TimeSeries/charts",
    "postCondenserAPI" : "v1/AssetManagement/asset",
    "baseURLCalc": "http://172.31.3.250:8085",
    "postCustomCalcAPI": "api/v1/runCustomDesignCalculation",
    "getCondenserByIsAPI" : "v1/AssetManagement/condenser-asset/",
    "patchCondenserAPI": "v1/AssetManagement/asset/",
    "chartKey":"v1/AssetManagement/condenser-chart-key/",
    "condenserLoad":"v1/AssetManagement/condenser-load/",
    "UOMBaseURL":"http://172.31.3.250:8092/UOM/",
    "UOMGetURL":"api/v1/uom",
    "getFacilities": "https://develop.ctvistaplus.com/service/accounts",
    "getPlantDataByFacility": "v1/AssetManagement/asset-data",
    "getUserProfile":"https://develop.ctvistaplus.com/service/profile",
    "baseReportURL":"http://172.31.3.250:8084/",
    "getReportList":"api/v1/ReportManagement/report-all/2?size=500&page=0&sort=createdAt&order=ASCENDING",
    "getLogo":"https://develop.ctvistaplus.com//logo/",
    "deleteReport":"api/v1/ReportManagement/reports/",
    "createReport":"api/v1/ReportManagement/report/",
    "getReportById":"api/v1/ReportManagement/report-id/",
    "reportUrl":"http://172.31.3.250:8086/condenser/report/preview",
    "downloadReport":"http://172.31.3.250:8091/pdf",
    "getAPICondenser_v2" : "v2/AssetManagement/assets/",
    "getReportList_v2" : "api/v1/ReportManagement/report-all/",
    "downloadMappingExcel": "api/v1/document/excel/mapping",
    "downloadHistorianExcel": "api/v1/document/excel/historian",
    "UpdatedYAxisLimits" : "v1/TimeSeries/charts/yaxis",
    "accountUrl": "https://develop.ctvistaplus.com/service/account",
    "getTroubleShootingGuide":"api/v1/document/doc",
    "getPopupContentForKpiOverview" : "api/v1/dashboard/design"
  }

  const uom = [
    {
      "id": "252",
      "name": " "
    },
    {
      "id": "602",
      "name": "$"
    },
    {
      "id": "454",
      "name": "$/day"
    },
    {
      "id": "603",
      "name": "$/MWhr"
    },
    
  ]

  const chartKeyArray = [
   
    {
      "key": "mw_dollar_lost_per_day",
      "id": "a595d6a9-e1b8-4cd7-9360-483951199f56",
      "uom": "252",
      "displayName": "MW $$$ Lost/Day",
      "type": "RAW",
      "computed": true,
      "category": "KPI"
    },
    {
      "key": "production_deviation_overall",
      "id": "ab62499d-88d3-47ee-8849-03bc1215de59",
      "uom": "602",
      "displayName": "Production Deviation Overall (MWhrs)",
      "type": "RAW",
      "computed": true,
      "category": "KPI"
    },
    {
      "key": "tr_deviation",
      "id": "477d1565-c6c6-4fce-9668-ab7080a248fe",
      "uom": "454",
      "displayName": "TR Deviation (°F)",
      "type": "RAW",
      "computed": true,
      "category": "KPI"
    },
    {
      "key": "ttd_deviation",
      "id": "8823d045-3599-48c0-8887-b452f6be417d",
      "uom": "603",
      "displayName": "TTD Deviation (°F)",
      "type": "RAW",
      "computed": true,
      "category": "KPI"
    }
  ]

  const KPIChartData = {
    "timestamp": "09-02-2021 03:25:58",
    "data": {
      "chart_data": []
    }
  }  

  let matarialLayout = []
  beforeEach(() => {
    const reportServiceStub = () => ({
      matarialLayout: { find: () => ({}) },
      updateItemdata: (e, id) => ({}),
      deleteItem: id => ({}),
      addItem: (string, string1, object) => ({}),
      getReportData: () => of({}),
      emptyLayout: () => {},
      updateReportData: (url, id, data) => of({}),
      addReportData: (obj) => of({}),
      postReportData: (url, obj) => of({})
    });
    const chartServiceStub = () => ({
      getJSON: () => of(urlJson),
      getChart: (url, fD, eD, paramsObj) => of(KPIChartData),
      getchartKey: (url, condenserId, status) => of(chartKeyArray),
      getUOM: (url) => of(uom)
    });

    const createCondenserServiceStub = () => ({
      getUserProfile: () => of(
        {
          "id": 19009,
          "username": "DHRD",
          "fullname": "DHRD User",
          "firstname": "DHRD",
          "middlename": "",
          "lastname": "User",
          "address1": "",
          "city": "",
          "state": "",
          "postalcode": "",
          "country": "",
          "work": "",
          "workext": "",
          "email": "anirban.mudi@danaherdigital.com",
          "status": 0,
          "dateformat": "MM-DD-YYYY",
          "timeformat": "24",
          "language": "",
          "defaultchartrange": 2,
          "attachedsignature": null,
          "signature": null,
          "avator": null
        }
      )
    })

    const activatedRouteStub = () => ({
      snapshot: { paramMap: { get: () => ({}) } },
      getCurrentNavigation: () => ({
        extras: {
          state: {
            cID: 'abc',
            toDate: '2020-06-18T01:00:01.000+00:00',
            fromDte: '2020-06-10T01:00:01.000+00:00',
            value: 'abc',
            highVale: 'xyz'
          }
        }
      })
    });
    const routerStub = () => ({
                                navigate: array => ({}),
                                snapshot: {
                                  paramMap: {
                                    get: (data) => 'abc'
                                  }
                                },
                                getCurrentNavigation: () => ({
                                  extras: {
                                    state: {
                                      cID: 'abc',
                                      toDate: '2020-06-18T01:00:01.000+00:00',
                                      fromDte: '2020-06-10T01:00:01.000+00:00',
                                      value: 'abc',
                                      highVale: 'xyz'
                                    }
                                  }
                                })
                              });
    const matDialogStub = () => ({
      open: (dataDrivinModalComponent, object) => ({})
    });
    const toastMessgaeServiceStub = () => ({
      showSuccess: toastSuccessMesage => ({})
    });

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      declarations: [ CreateReportComponent ],
      providers: [MessageService,
        { provide: ReportService, useFactory: reportServiceStub },
        { provide: ChartService, useFactory: chartServiceStub },
        {provide: CreateCondenserService, useFactory: createCondenserServiceStub},
        { provide: ActivatedRoute, useFactory: activatedRouteStub },
        { provide: Router, useFactory: routerStub },
        { provide: MatDialog, useFactory: matDialogStub },
        { provide: ToastMessgaeService, useFactory: toastMessgaeServiceStub },
        { provide: FormBuilder, useValue: formBuilder }
      ]
    });
    fixture = TestBed.createComponent(CreateReportComponent);
    // TestBed.inject(ReportService);
    // TestBed.inject(ChartService);
    TestBed.inject(CreateCondenserService);
    component = fixture.componentInstance;
    service = TestBed.inject(ReportService);
    service1 = TestBed.inject(ChartService);
    service2 = TestBed.inject(CreateCondenserService);
    service.report = {
      name: 'reportName',
      cID: '',
      id: '',
      cName: '',
      value : '',
      highValue : '',
      fromeDte : '',
      toDate : '',
    systemName: '',
  facilityName : '',
  createdBy: '',
  createdAt: '2020-06-18T01:00:01.000+00:00'
    };
    service.previewLayout = [];
    service.matarialLayout = [];
    component.myGroup.controls.reportName.setValue('data');
     fixture.detectChanges();
  });

  // it('should create', () => {
  //    fixture.detectChanges();
  //   expect(component).toBeTruthy();
  // });
  it('should addEditorData', () => {
   component.addEditorData({}, '');
  });
  it('should oninit', () => {
    component.state = 'dashboard';
    component.fromeDte = new Date('2020-06-18T01:00:01.000+00:00');
    component.toDate = new Date('2020-06-30T02:00:01.000+00:00');
    component.serviceURl = urlJson;
    component.ngOnInit();
   });
  it('should ngAfterViewInit', () => {
    component.ngAfterViewInit();
   });
  it('should saveReport', () => {
    component.serviceURl = {  'baseReportURL': 'http://34.207.125.13:8083/',
    'createReport': 'v1/TimeSeries/charts'};
    service.matarialLayout = [{id: ''}];
    component.saveReport();
   });
  it('should createchartData', () => {
    const chart = [
      {
        date: '2020-06-18T01:00:01.000+00:00',
        load: 437.5189541087431,
        value: -27.86
      },
      {
        date: '2020-06-18T02:00:01.000+00:00',
        load: 357.99897522857225,
        value: -27.97
      },
      {
        date: '2020-06-18T03:00:01.000+00:00',
        load: 309.53405104417067,
        value: -28.1
      }];
    component.createchartData(chart, '', '', '', true);
   });
  it('should createchartData', () => {
    const chart = [
      {
        date: '2020-06-18T01:00:01.000+00:00',
        load: 437.5189541087431,
        value: -27.86
      },
      {
        date: '2020-06-18T02:00:01.000+00:00',
        load: 357.99897522857225,
        value: -27.97
      },
      {
        date: '2020-06-18T03:00:01.000+00:00',
        load: 309.53405104417067,
        value: -28.1
      }];
    component.createchartData(chart, '', '', '', false);
   });
  it('should call method ', async(() => {
    spyOn(component, 'getChart').and.callThrough();
    const jsn = {  'baseChartURL': 'http://34.216.125.13:8083/',
      'getChartAPI': 'v1/TimeSeries/charts'};
    component.getChart(jsn, '', '', '', '');
     fixture.detectChanges();
    expect(component.getChart).toBeTruthy();
  }));
  it('should call getChart method', () => {
    const url = {  baseChartURL: 'http://34.216.124.13:8083/',
    getChartAPI: 'v1/TimeSeries/charts'};
    component.getChart(url, '', '', '', '');
     fixture.detectChanges();
    spyOn(component, 'getChart');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.getChart).toHaveBeenCalled();
    });
  });
  it('should getChart', () => {
    const url = {  baseChartURL: 'http://34.200.125.13:8083/',
    getChartAPI: 'v1/TimeSeries/charts'};
    component.getChart(url, '', '', '', '');
   });
  it('should setTroubleshooting', () => {
    component.setTroubleshooting({});
   });
  // it('should setTroubleshooting', () => {
  //   service.matarialLayout = [{
  //     layoutType: 'troubleshooting',
  //     data: {}
  //   }];
  //   component.setTroubleshooting(undefined);
  //  });
  it('should somethingChanged', () => {
    component.allChartdata = [{
      chartLabel: 't'
    }];
    component.somethingChanged('t', true);
    // component.somethingChanged('t',false);
   });
   it('should somethingChanged', () => {
    service.matarialLayout = [{
        headerTitle: 't'
    }];
    component.somethingChanged('t',false);
   });
  it('should customChartSelect', () => {
    component.recentUserAccId = '1207';
    component.condenserId = '';
    component.serviceURl =  {  baseChartURL: 'http://34.200.125.13:8083/',
    getChartAPI: 'v1/TimeSeries/charts'};
    component.customChartSelect(true, {
      category: "CUSTOM",
      computed: false,
      displayName: "Ambient Temp (°F)",
      id: "928924e9-7a19-4fd8-ae59-fe2c99cf9034",
      key: "ambient_temp",
      type: "RAW",
      uom: "124"
    });
   });
   it('should customChartSelect', () => {
    component.recentUserAccId = '1207';
    component.condenserId = '';
    component.serviceURl =  {  baseChartURL: 'http://34.200.125.13:8083/',
    getChartAPI: 'v1/TimeSeries/charts'};
    service.matarialLayout = [{
        headerTitle: 'Ambient Temp (°F)'
    }];
    component.customChartSelect(false, {
      category: "CUSTOM",
      computed: false,
      displayName: "Ambient Temp (°F)",
      id: "928924e9-7a19-4fd8-ae59-fe2c99cf9034",
      key: "ambient_temp",
      type: "RAW",
      uom: "124"
    });
   });
  it('should deleteItem', () => {
    component.condenserId = '';
    const url =  { layoutType: 'troubleshooting'};
    component.allSelectedvalue = true;
    component.deleteItem(url);
   });
  it('should manageCharts1', () => {
    const KPI = {
        data: [ {
          date: '2020-06-18T01:00:01.000+00:00',
          load: 437.5189541087431,
          value: -27.86
        }],
        name: 'tr_deviation'
      };
    component.manageCharts(KPI);
   });
  it('should manageCharts2', () => {
    const KPI = {
        data: [ {
          date: '2020-06-18T01:00:01.000+00:00',
          load: 437.5189541087431,
          value: -27.86
        }],
        name: 'ttd_deviation'
      };
    component.manageCharts(KPI);
   });
  it('should manageCharts2', () => {
    const KPI = {
        data: [ {
          date: '2020-06-18T01:00:01.000+00:00',
          load: 437.5189541087431,
          value: -27.86
        }],
        name: 'production_deviation_overall'
      };
    component.manageCharts(KPI);
   });
  it('should manageCharts2', () => {
    const KPI = {
        data: [ {
          date: '2020-06-18T01:00:01.000+00:00',
          load: 437.5189541087431,
          value: -27.86
        }],
        name: 'production_deviation_overall'
      };
    component.manageCharts(KPI);
   });
  it('should be able to get TandEData', () => {
     component.getTandEData();
   });
//   it('should be able to preview report', () => {
//       component.condensername = 'abc';
//       service.matarialLayout = [{id: '1'}, {id: '2'}];
//       const link = document.createElement('div');
//       link.id = '1';
//       document.body.appendChild(link);
//       const link1 = document.createElement('div');
//       link.id = '2';
//       document.body.appendChild(link1);
//       component.previewReport();
//       document.body.removeChild(link1);
//       // component.reportService.report.name = undefined;
//       // component.previewReport();
//    });
  it('should be able to get UOM data', () => {
    component.serviceURl = {
      UOMBaseURL: 'http://localhost:5000',
      UOMGetURL: 'UOM'
    };
    component.getUOM();
   });
  it('should be able to find UOM Node', () => {
     component.UOMArray = [{
       name: 'a',
       id: 'a'
     }];
     component.findUOMName('a');
   });
  it('should be able to get chart key', () => {
    const serviceURl = {
      baseURL: 'http://localhost:4000',
      chartKey: 'chartKey',
    };
    component.condenserId = 'a';
    component.getchartKey(serviceURl);
   });
  it('should editReport', () => {
    component.serviceURl = {
      baseReportURL: 'http://localhost:4000',
      createReport: 'chartKey',
    };
    component.condenserId = 'a';
    component.editReport({});
   });
  // it('should AddReport', () => {
  //   component.serviceURl = {
  //     baseReportURL: 'http://localhost:4000',
  //     createReport: 'chartKey',
  //   };
  //   component.condenserId = 'a';
  //   component.AddReport({});
  //  });
  it('should successTostmsg', () => {
    component.successTostmsg('msg');
   });
  it('should errorTostmsg', () => {
    component.errorTostmsg('msg');
   });
  it('should errorTostmsg', () => {
    component.myGroup.setValue({reportName: 'report name'}),
    component.reportId = '';
    component.condenserId = '1';
    component.condensername = 'condenser1';
    component.value = 2;
    component.highValue = 100;
    component.fromeDte = '';
    component.toDate = '';
    component.systemName = '';
    component.facilityName = '';
    component.userDetalis = {};
    component.errorTostmsg('msg');
   });
  it('should AddReportMsg', () => {
    component.AddReportMsg({timestamp: '', data: ''});
   });
  it('should AddReportErrorMsg', () => {
    component.AddReportErrorMsg({message: ''});
    component.AddReportErrorMsg({message: 'abc'});
   });
  it('should editReportMsg', () => {
    component.editReportMsg();
   });
  it('should getUserProfile', () => {
    component.serviceURl = {  'baseReportURL': 'http://34.216.125.13:8083/',
    'createReport': 'v1/TimeSeries/charts'};
    component.getUserProfile();
   });
   it('should be able enable editor drag', () => {
     component.enableEditorDrag()
   })
   it('should be able to AddReport', () => {
     component.AddReport({})
   })
//    it('should be able to preview report', () => {
//      component.condensername = 'abc';
//      component.reportId = 'xyz';
//     //  component.layout = []
//      component.previewReport();
//    })
  // it('should test toggle selection', () => {
  //   component.allSelectedvalue = true;
  //   component.toggleAllSelection();
  //  });
  it('should test modelElaseresponceMsg', () => {
    component.modelElaseresponceMsg('Delete');
   });
   it('should test dmodelAfterCloseresponceMsg', () => {
    component.dmodelAfterCloseresponceMsg('reportName');
   });
   it('should test dmodelAfterCloseErrorMsg', () => {
    component.dmodelAfterCloseErrorMsg('reportName');
   });
   it('should test setAcounts', () => {
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
    component.setAcounts(account);
   });
});
