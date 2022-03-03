import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { Options, LabelType } from 'ng5-slider';
import { SelectItem } from 'primeng/api';
import { ChartService } from '../../kpi-dashboard/service/chart.service';
import * as moment from 'moment';
import { FormControl, RequiredValidator } from '@angular/forms';
import { ToastMessgaeService } from '../../shared/toast-service/service/toast-messgae.service';
import { ActivatedRoute, Router } from '@angular/router';
import { YAxisLimitModalComponent } from '../../shared/y-axes-limit-modal/y-axes-limit-modal.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import {environment} from '../../../../environments/environment';
 
@Component({
  selector: 'app-advanced-charts',
  templateUrl: './advanced-charts.component.html',
  styleUrls: ['./advanced-charts.component.scss']
})
export class AdvancedChartsComponent implements OnInit {
  confirmDlg: MatDialogRef<YAxisLimitModalComponent>;
  //initDate = { startDate: moment().subtract(3, 'months'), endDate: moment() };
  initDate = {};
  envURl;
  alwaysShowCalendars: boolean;
  ranges: any;
  invalidDates: moment.Moment[] = [
    moment().add(2, 'days'),
    moment().add(3, 'days'),
    moment().add(5, 'days'),
  ];
  // data set to populate chart
  loadminVal: any;
  loadmaxVal: any;
  disableLoader = true;
  showSpinner: boolean;
  filterdate = [];
  loadPlantFiltrVal = [];
  value;
  highValue;
  fromeDte = new Date();
  toDate = new Date();
  options: Options;
  showNoRcrdFnd = false;
  condensers: any = [];
  customCharts: SelectItem[];
  serviceURl = {};
  chartArray: any = [];
  CustomchartArray: any = [];
  assetID = '';
  assetName = '';
  facilityName = '';
  systemName = '';
  UOMArray: any = [];
  multiSerieschartdata: any = [];
  multiSerieschartXlabels: any = [];
  multiSerieschartYlabels: any = [];
  selectedChartsId: any = [];
  comparedChartData = {
      "chart_data": []
    }
  condeserName: string;
  minLoadRange : number;
  maxLoadRange : number;
  chartControl = new FormControl();
  tabName: string;
  selectechartID: string; 
  kpiCharts : any = [];
  loggedInUserTenantID: string;
  allCharts: any = [];
  selectedFromDate;
  selectedToDate;
  
  constructor(
    private chartService: ChartService,
    private toastService: ToastMessgaeService,
    public route: ActivatedRoute,
    private dialog: MatDialog,
    private router: Router,
  ) {
    const d1S = new Date();
    const d1E = new Date();
    const d3S = new Date();
    const d3E = new Date();
    const d6S = new Date();
    const d6E = new Date();
    this.ranges = {
      'Last Week': [moment().subtract(6, 'days'), moment()],
      'Last 15 days': [moment().subtract(14, 'days'), moment()],
      'Last Month': [
        new Date(d1S.setMonth(d1S.getMonth() - 1)).toISOString(),
        d1E.toISOString(),
      ],
      'Last 3 Months': [
        new Date(d3S.setMonth(d3S.getMonth() - 3)).toISOString(),
        d3E.toISOString(),
      ],
      'Last 6 Month': [
        new Date(d6S.setMonth(d6S.getMonth() - 6)).toISOString(),
        d6E.toISOString(),
      ],
    };
    this.fromeDte.setMonth(this.fromeDte.getMonth() - 3);

    this.options = {
      floor: 0,
      ceil: 100
    };
    this.alwaysShowCalendars = true;
  }

  ngOnInit(): void {
    this.envURl = environment;
    const paramObjects = {
      condenserName: localStorage.getItem('condenserName'),
      minLoadRange: parseInt(localStorage.getItem('minLoadRange')),
      maxLoadRange: parseInt(localStorage.getItem('maxLoadRange')),
      assetId: localStorage.getItem('assetID'),
      loggedInUserTenantID: localStorage.getItem('loggedInUserTenantID'),
      selectechartID: localStorage.getItem('selectedChartID'),
      todate: localStorage.toDate,
      fromdate: localStorage.fromDate
    }
    this.selectedToDate = (new Date(paramObjects.todate));
    this.selectedFromDate = new Date((paramObjects.fromdate));
    this.initDate = { startDate: moment(this.selectedFromDate), endDate: moment(this.selectedToDate) };
    this.condeserName = paramObjects.condenserName;
    this.value = paramObjects.minLoadRange;
    this.highValue = paramObjects.maxLoadRange;
    this.assetID = paramObjects.assetId;
    this.loggedInUserTenantID = paramObjects.loggedInUserTenantID;
    this.chartService.getJSON().subscribe((url) => {
      this.serviceURl = url;
      this.getAllChartsOnLoad();
    });
  }

  getAllChartsOnLoad(): void {
    this.allCharts = [];
    this.chartService.getchartKey(this.serviceURl, this.assetID, false).subscribe((res) => {
      this.allCharts = res;
    });
  }
  
  getMutiSeriesChart(data): void {
    this.multiSerieschartYlabels = [];
    this.multiSerieschartdata = [];
    this.multiSerieschartXlabels = [];
    let demoYlabel = {};
    // let minOfMin = data.chart_data[0].min;
    // let maxOfMax = data.chart_data[0].max;
    data.chart_data.forEach(element => {
      const generatedobject = this.updateDataOfMultiSeriesChart(element.data, element.display_name, element.min, element.max);
      // if (minOfMin > generatedobject.min) {
      //   minOfMin = generatedobject.min;
      // }
      // if (maxOfMax < generatedobject.max) {
      //   maxOfMax = generatedobject.max;
      // }
      this.multiSerieschartdata.push(generatedobject.chartdata);
      demoYlabel = { name: generatedobject.yLabel, min: element.min , max: element.max};
      this.multiSerieschartYlabels.push(demoYlabel);
    });
    // this.multiSerieschartYlabels.forEach(element => {
    //   element.min = minOfMin;
    //   element.max = maxOfMax;
    // });
    this.multiSerieschartXlabels = this.getXaxis(data.chart_data[0].data);
  }
  getXaxis(e): any {
    const tempArray = [];
    e.forEach(
      (element) => {
        tempArray.push([
          new Date(element.date).getUTCMonth() +
          1 +
          '-' +
          new Date(element.date).getUTCDate() +
          '-' +
          new Date(element.date)
            .getUTCFullYear()
            .toString()
            .substr(2) +
          ' ' +
          ('0' + new Date(element.date).getUTCHours()).slice(-2) +
          ':' +
          ('0' + new Date(element.date).getUTCMinutes()).slice(-2)
        ]);
      });
    return tempArray;
  }
  updateDataOfMultiSeriesChart(e, name, min, max): any {
    const tempArray = [];
    e.forEach(
      (element) => {
        tempArray.push(
          element.value
        );
      });
    return {
      yLabel: name,
      chartdata: tempArray,
      min: min,
      max: max
    };
  }

  getCharLoad(): void {
    this.chartService.getchartload(this.serviceURl, this.assetID).subscribe((load) => {
      if (load.minVal) {
        this.loadminVal = load.minVal;
        this.loadmaxVal = load.maxVal;
        this.loadSlider(this.value, this.highValue);
      }
    });
  }

  getchartKey(url): void {
    this.allCharts = [];
    this.chartService.getchartKey(url, this.assetID, false).subscribe((res) => {
      this.allCharts = res;
    });
  }
  
  // method to get initial chart data
  getChart(url, fD, eD, fPL, tPL): void {
    this.showNoRcrdFnd = true;
    this.showSpinner = true;
    this.chartArray = [];
    const paramsObj = {
      tenantId: this.loggedInUserTenantID,
      assetId: this.assetID,
      isKpi: false,
      fromLoad: fPL,
      toLoad: tPL
    };
    this.chartService.getChart(url, fD, eD, paramsObj).subscribe(
      (chart: any) => {
        if (chart.data.chart_data) {
          const chartArray = chart.data.chart_data;
        }
        this.loadSlider(this.value, this.highValue);
        this.showSpinner = false;
      },
      (error) => {
        this.showSpinner = false;
        this.showNoRcrdFnd = true;
        this.disableLoader = true;
      }
    );
  }

  // slider value initialization
  loadSlider(min, max): void {
    this.loadPlantFiltrVal[0] = min;
    this.loadPlantFiltrVal[1] = max;

    this.options = {
      floor: min,
      ceil: max,
      step: 1,
      showTicks: false,
      autoHideLimitLabels: false,
      readOnly: false,
      translate: (value: number, label: LabelType): string => {
        switch (label) {
          case LabelType.Low:
            if (this.disableLoader) {
              return '';
            }
            return value + ' ' + '%';
          case LabelType.High:
            if (this.disableLoader) {
              return '';
            }
            return value + ' ' + '%';
          default:
            return value + ' ' + '%';
        }
        console.log('sliderevent');
      },
    };
  }
  getChartByLoad(): void {
    this.getAdvancedChartOnChangedLoadRange(this.serviceURl, this.selectedFromDate, this.selectedToDate, this.value, this.highValue);
  }
  getAdvancedChartOnChangedLoadRange(url, selectedFromDate, selectedToDate, lowRange, highRange) {
    this.comparedChartData.chart_data = [];
    const paramsObj = {
      tenantId: this.loggedInUserTenantID,
      assetId: this.assetID,
      isKpi: false,
      fromLoad: lowRange,
      toLoad: highRange,
      assetParamName : this.getchartKey(url)
    };
  }

  // method to update min max value on slider position change
  plantLoadFltr(e): void {
    this.loadPlantFiltrVal[0] = e.value;
    this.loadPlantFiltrVal[1] = e.highValue;
  }

  changeDate(e): void {
    if (e.endDate != null) {
      this.selectedFromDate = e.startDate._d;
      this.selectedToDate = e.endDate._d;
      this.showSpinner = true;
      this.chartService.getJSON().subscribe((url) => {
        this.getChart(url, e.startDate._d, e.endDate._d, this.value, this.highValue);
      });
    }
  }

  // select charts for comparison
  selectCustomChartForComparison(chartsid, selected, chartValues): void {
    if (selected && this.selectedChartsId.length <= 2) {
          this.selectedChartsId.push(chartsid);
          this.selectedChartsId =  this.selectedChartsId.filter((elem, index, self) => {
            return self.indexOf(elem) === index ;
          });
          // console.log(this.selectedChartsId);
          this.showSpinner = true;
          const paramsObj = {
            tenantId: this.loggedInUserTenantID,
            assetId: this.assetID,
            assetParamName: chartsid,
            isKpi: false,
            fromLoad: this.value,
            toLoad: this.highValue
          };
          this.chartService.getChart(this.serviceURl, this.selectedFromDate, this.selectedToDate, paramsObj).subscribe(
            (chart: any) => {
              if (chart.data.chart_data[0] === undefined || chart.data.chart_data[0] === 0) {
                const toastSuccessMesage = {
                  severity: 'error',
                  summary: ` No data available for ${chartValues.displayName} chart`,
                  key: 'chartError',
                };
                this.toastService.showSuccess(toastSuccessMesage);
                this.showSpinner = false;
                selected = false;
              } else {
                // console.log(chart.data.chart_data[0]);
                const customeChart = chart.data.chart_data[0];
                this.comparedChartData.chart_data.push(customeChart);
                // console.log(this.comparedChartData.chart_data);
                this.getMutiSeriesChart(this.comparedChartData);
                this.disableLoader = false;
                this.showSpinner = false;
              }
          }, (error) => {
            const toastSuccessMesage = {
              severity: 'error',
              summary: ` server error for ${chartValues.displayName} chart`,
              key: 'chartError',
            };
            this.toastService.showSuccess(toastSuccessMesage);
            this.showSpinner = false;
            selected = false;
          });
    } else {
      // debugger;
      this.selectedChartsId.splice(this.selectedChartsId.indexOf(chartsid), 1);
      this.comparedChartData.chart_data =  this.comparedChartData.chart_data.filter((node) => {
        return node.name !== chartValues.key;
    });
      // for (let i = 0; i <= this.comparedChartData.chart_data.length; i++) {
      //   if (chartValues.key === this.comparedChartData.chart_data[i].name) {
      //     this.comparedChartData.chart_data.splice(i, 1);
      //     break;
      //   }
      // }
      // this.getMutiSeriesChart(this.comparedChartData);
      if (this.comparedChartData['chart_data'].length === 1 ) {
        this.resetFirstChart();
      }
    }
  }

  resetFirstChart(): void{
    const displayName = this.comparedChartData['chart_data'][0]['display_name']
    this.showSpinner = true;
    const paramsObj = {
      tenantId: this.loggedInUserTenantID,
      assetId: this.assetID,
      assetParamName: this.selectedChartsId[0],
      isKpi: false,
      fromLoad: this.value,
      toLoad: this.highValue
    };
    this.chartService.getChart(this.serviceURl, this.selectedFromDate, this.selectedToDate, paramsObj).subscribe(
      (chart: any) => {
        if (chart.data.chart_data[0] === undefined || chart.data.chart_data[0] === 0) {
          const toastSuccessMesage = {
            severity: 'error',
            summary: ` No data available for ${displayName} chart`,
            key: 'chartError',
          };
          this.toastService.showSuccess(toastSuccessMesage);
          this.showSpinner = false;
          // selected = false;
        } else {
          if (this.selectedChartsId.length === 1){
            this.comparedChartData.chart_data.pop();
            const customeChart = chart.data.chart_data[0];
            this.comparedChartData.chart_data.push(customeChart);
            this.getMutiSeriesChart(this.comparedChartData);
          }
          this.disableLoader = false;
          this.showSpinner = false;
        }
    });
  }

  openModel(e: any ): any {
    this.confirmDlg = this.dialog.open(YAxisLimitModalComponent, {
     disableClose: true,
     position: { top: '5%', left: '35%' },
     data: { pagetext: this.comparedChartData.chart_data,
     pageTitle: 'Set Y axes limit',
     leavepagebtn: 'Cancel',
     staypagebtn: 'Apply'
    }
   });
    this.confirmDlg.afterClosed().subscribe(async (response) => {
     if (response) {
       this.onSave(response);
    }
  });
  }

  onSave(response): any {
    if (response.form) {
      let chartDetails = response.form.value;
      console.log(chartDetails);
      var output = [];
      var tempObj = {name : '', min : '', max : '', assetParamName: ''};
      var x = [];
      for (let key in chartDetails) {
        if (tempObj.name && tempObj.name === (key.split('+'))[0]) {
          if (x[1] === 'min') {
            tempObj.min = chartDetails[key];
          } else {
            tempObj.max = chartDetails[key]
            for(let i=0; i<this.allCharts.length;i++){
              if (this.allCharts[i].key === tempObj.name){
                tempObj.assetParamName = this.allCharts[i].id;
              }
            }
            output.push(tempObj);
            tempObj = {name: '', min: '', max: '', assetParamName: ''};
          }
        } else{
          if (!tempObj.name){
            let x = key.split('+');
            tempObj.name = x[0];
            if (x[1] === 'min'){
              tempObj.min = chartDetails[key];

            }
            else{
              tempObj.max = chartDetails[key]
            }
          }
        }
      }
      this.showSpinner = true;
      const params = {
        tenantid: this.loggedInUserTenantID,
        assetId: this.assetID,
        minLoadRange: this.value,
        maxLoadRange: this.highValue,
        fromdate : this.selectedFromDate,
        todate: this.selectedToDate,
        data : output,
      };
      this.chartService.postUpdatedYAxisLimits(this.serviceURl, params).subscribe(
        (chart : any) => {
          this.showSpinner = false;
          const customeChart = chart.data;
          this.getMutiSeriesChart(customeChart);
        },
        (error) => {
        }
      );
    } else {
      this.comparedChartData.chart_data = [...response['intialData']];
    }
  }
}
