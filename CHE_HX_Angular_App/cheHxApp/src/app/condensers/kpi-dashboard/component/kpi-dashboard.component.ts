import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Options, LabelType } from 'ng5-slider';
import { SelectItem } from 'primeng/api';
import { ChartService } from '../service/chart.service';
import * as moment from 'moment';
import { FormControl } from '@angular/forms';
import { ToastMessgaeService } from '../../shared/toast-service/service/toast-messgae.service';
import { CondenserListviewService } from '../../condenser-listview/service/condenser-listview.service';
import { Router } from '@angular/router';
import { KpiOverviewModalComponent } from '../../shared/kpi-overview-modal/kpi-overview-modal.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-kpi-dashboard',
  templateUrl: './kpi-dashboard.component.html',
  styleUrls: ['./kpi-dashboard.component.scss'],
})
export class KpiDashboardComponent implements OnInit, AfterViewInit {
  @ViewChild('screen') screen: ElementRef;
  @ViewChild('canvas') canvas: ElementRef;
  @ViewChild('downloadLink') downloadLink: ElementRef;
  confirmDlg: MatDialogRef<KpiOverviewModalComponent>;
  initDate = { startDate: moment().subtract(3, 'months'), endDate: moment() };
  alwaysShowCalendars: boolean;
  ranges: any;
  invalidDates: moment.Moment[] = [];
  // data set to populate chart
  loadminVal: any;
  loadmaxVal: any;
  disableLoader = true;
  showSpinner: boolean;
  filterdate = [];
  loadPlantFiltrVal = [];
  tabSelected = 'KPI Charts';
  value = 0;
  highValue = 100;
  fromeDte = new Date();
  toDate = new Date();
  options: Options;
  showNoRcrdFnd = false;
  condensers: any = [];
  condenserParsedList: SelectItem[] = [];
  customCharts: SelectItem[];
  serviceURl = {};
  chartArray: any = [];
  CustomchartArray: any = [];
  assetID = '';
  assetName = '';
  facilityName = '';
  systemName = '';
  UOMArray: any = [];
  kpiChartsDetails: any = [];
  recentUserAccName = '';
  recentUserAccId = '';
  recentUserAccFacilityName = '';
  recentUserAccFacilityId = '';
  recentUserAccSystemName = '';
  recentUserAccSystemId = '';

  selectedCondenser = '';
  selectedCharts: any[] = [''];

  chartControl = new FormControl();
  tabName: string;
  selectedChartid: string;
  allCharts: any = [];
  url = 'condenser/dashboard/advancedCharts';
  kpiChartFirstLoad = true;
  customChartFirstLoad = true;
  colorCodeValue;
  colorCodeValueForGroup;
  kpiOverviewPopUpContent;
  fromDateToBeShownOnCards;
  toDateToBeShownOnCards;

  constructor(
    private router: Router,
    private chartService: ChartService,
    private toastService: ToastMessgaeService,
    private condenserListviewService: CondenserListviewService,
    private dialog: MatDialog
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
    this.invalidDates = [
      moment().add(2, 'days'),
      moment().add(3, 'days'),
      moment().add(5, 'days'),
    ];
    this.chartService.getJSON().subscribe((url) => {
      this.serviceURl = url;
      this.getcondenserList();
      this.getUOM();
      this.tabSelected = 'KPI Overview';
      this.dateToBeShownOnCards();
    });
    const par: any = parent;
    if (par.ctvpapp !== undefined){
    const account = par.get_storage('recent-' + par.ctvpapp.user.id);
    this.setAccounts(account);
    }
  }
  setAccounts(account): void{
    if (account.account){
      this.recentUserAccName = account.account.name;
      this.recentUserAccId = account.account.id;
     }
 
    if (account.facility){
         this.recentUserAccFacilityName = account.facility.name;
         this.recentUserAccFacilityId = account.facility.id;
     }
    if (account.system){
       this.recentUserAccSystemName = account.system.name;
       this.recentUserAccSystemId = account.system.id;
   }
  }
  ngAfterViewInit(): void{
  // document.getElementsByClassName('ng5-slider-floor')[0].style.color = "blue";

  }
  downloadImage(scale): void{
    const a = scale;
    html2canvas(this.screen.nativeElement, {
      scrollX: 0,
        scrollY: 0,
      scale: a,
      // scrollY: -window.scrollY
    }).then(canvas => {
      this.canvas.nativeElement.src = canvas.toDataURL();
      this.downloadLink.nativeElement.href = canvas.toDataURL('image/png');
      this.downloadLink.nativeElement.download = 'KPI-overview.png';
      this.downloadLink.nativeElement.click();
    });
  }
  getcondenserList(): void{
  const paramsobj = {
      size: 100,
      page: 0,
      sort: 'name',
      order: 'ASCENDING',
       facilityName: this.recentUserAccFacilityName,
      systemName: this.recentUserAccSystemName,
      tenantId : this.recentUserAccId
    };

  this.condenserListviewService.getCondenser_v2(this.serviceURl, paramsobj).subscribe((clist) => {
      if (clist.data.content){
      this.condensers = JSON.parse(JSON.stringify(clist.data.content));
      const parseCondensers = clist.data.content;
      parseCondensers.forEach(element => {
        element['label'] = element['name'];
        element['value'] = element['id'];
        delete element['id'];
        delete element['createdAt']; 
        delete element['createdBy']; 
        delete element['facilityName']; 
        delete element['name']; 
        delete element['status']; 
        delete element['systemName']; 
        delete element['updatedAt']; 
        delete element['updatedBy']; 
        delete element['accountName'];
      });
      this.condenserParsedList = parseCondensers;
      const newData =   this.condensers[0];
      this.selectedCondenser = newData.name;
      this.assetID = newData.id;
      this.assetName = newData.name;
      this.systemName = newData.systemName;
      this.facilityName = newData.facilityName;
      this.getCharLoad();
      this.getKPIchartDeatils(this.serviceURl);
      }
    });
  }

  createReport(): void{
    this.assetName = encodeURIComponent(this.assetName.trim());
    this.router.navigate([`/condenser/report/create/${this.assetName}`], { state: { cID: this.assetID,
      value : this.value,
      highValue : this.highValue,
      fromeDte : this.fromeDte,
      toDate : this.toDate,
    systemName: this.systemName,
  facilityName : this.facilityName,
  state: 'dashboard'
} });
  }

  getUOM(): void{
    this.chartService.getUOM(this.serviceURl).subscribe((uoms) => {
      this.UOMArray = uoms;
    });
  }
  findUOMName(id): string{
    const UOM =  this.UOMArray.filter((node) => {
      return node.id === id;
     });
    return UOM[0].name;
  }
  findSysNFac(id): any{
    const UOM =  this.condensers.filter((node) => {
      return node.id === id;
     });
    return UOM[0];
  }

getCharLoad(): void{
  this.chartService.getchartload(this.serviceURl, this.assetID).subscribe((load) => {
    if (load.minVal || load.maxVal){
      this.loadminVal = load.minVal;
      this.loadmaxVal = load.maxVal;
      this.loadSlider(load.minVal, load.maxVal);
    }
  });
}

  getchartKey(url): void {
    this.customCharts = [];
    this.showSpinner = true;
    this.chartService.getchartKey(url, this.assetID, false).subscribe((res) => {
      this.showSpinner = false;
      this.customCharts = res;
    },
    err => {
      this.showSpinner = false;
    });
  }

  getKPIchartDeatils(url): void {
    this.kpiChartsDetails = [];
    this.chartService.getchartKey(url, this.assetID, true).subscribe((res) => {
      this.kpiChartsDetails = res;
      if (this.tabName !== 'Custom Charts' && this.kpiChartsDetails.length !== 0){
        this.getChart(this.serviceURl, this.fromeDte, this.toDate, 0, 100);
      }
    });
  }
  updateDateOfChart(item, Xlabel, YLabel, ChartLabel, isKPI, avgKpi, chartName): void {
    const tempArray = [];
    item.forEach(
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
          ('0' + new Date(element.date).getUTCMinutes()).slice(-2),
          element.value,
          element.load,
        ]);
  });
    const dataObject = {
     xlabel: Xlabel,
     yLabel: YLabel,
     chartLabel: ChartLabel,
     chartdata: tempArray,
     avgKpi : avgKpi,
     chartname : chartName
  };
    if (isKPI){
    this.chartArray.push(dataObject);
    } else {
      this.CustomchartArray.push(dataObject);
    }
}
manageCharts(element): void{
  const KPIDetails = this.kpiChartsDetails.filter((node) => {
    return node.key === element.name;
   });
  if (this.kpiChartsDetails.length !== 0){
    this.updateDateOfChart(element.data, this.findUOMName(KPIDetails[0].uom), KPIDetails[0].displayName, KPIDetails[0].displayName, true,element.avgKpi, element.name);

  }
 }
  // method to get initial chart data
  getChart(url, fD, eD, fPL, tPL): void {
    this.showNoRcrdFnd = true;
    this.showSpinner = true;
    this.chartArray = [];
    const paramsObj = {
      tenantId: this.recentUserAccId,
      assetId: this.assetID,
      isKpi: true,
      fromLoad: fPL,
      toLoad: tPL
    };
    this.chartService.getChart(url, fD, eD, paramsObj).subscribe(
      (chart: any) => {
        if (chart.data.chart_data.length !== 0) {
          const chartArray = this.chartService.getSequenecForKPI(chart.data.chart_data);
          chartArray.forEach(element => {
            this.manageCharts(element);
          });
        }
        this.disableLoader = this.chartArray.length === 0 ? true : false;
        this.chartArray.length ? this.loadSlider(this.loadminVal, this.loadmaxVal) : this.loadSlider(0, 100);
        this.showSpinner = false;
      },
      (error) => {
        this.showSpinner = false;
        this.showNoRcrdFnd = true;
        this.chartArray = [];
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
       readOnly: this.disableLoader,
      translate: (value: number, label: LabelType): string => {
        switch (label) {
          case LabelType.Low:
            if (this.disableLoader){
              return '';
            }
            return value + ' ' + '%';
          case LabelType.High:
            if (this.disableLoader){
              return '';
            }
            return value + ' ' + '%';
          default:
            return value + ' ' + '%';
        }
      },
    };
  }
  getChartByLoad(): void{
    if (this.tabSelected === 'KPI Charts'){
      this.customChartFirstLoad = true;
      this.getChart(this.serviceURl, this.fromeDte, this.toDate, this.value, this.highValue);
    }
    if (this.tabSelected === 'Custom Charts'){
      this.kpiChartFirstLoad = true;
      this.getchartKey(this.serviceURl);
      this.CustomchartArray = [];
    }
    if (this.tabSelected === 'KPI Overview'){
      this.customChartFirstLoad = true;
      this.kpiChartFirstLoad = true;
      this.getChart(this.serviceURl, this.fromeDte, this.toDate, this.value, this.highValue);
    }
  }
  // method to update min max value on slider position change
  plantLoadFltr(e): void {
    this.loadPlantFiltrVal[0] = e.value;
    this.loadPlantFiltrVal[1] = e.highValue;
  }
  // method to generate chart based on drop down selection(Condenser level)
  cdsDrpDwnChange(e): void {
    this.kpiChartFirstLoad = true;
    this.customChartFirstLoad = true;
    this.assetID = e.value;
    this.assetName = e.originalEvent.target.textContent;
    this.facilityName = this.findSysNFac(e.value).facilityName;
    this.systemName = this.findSysNFac(e.value).systemName;
    if (this.tabSelected === 'KPI Charts'){
      this.getCharLoad();
      this.getKPIchartDeatils(this.serviceURl);
      }
    if (this.tabSelected === 'Custom Charts'){
      this.getchartKey(this.serviceURl);
      this.CustomchartArray = [];
    }
    if (this.tabSelected === 'KPI Overview'){
      this.getCharLoad();
      this.getKPIchartDeatils(this.serviceURl);
      }
  }
  // method to generate chart based on date filter
  changeDate(e): void {
    if (e.endDate != null) {
      this.fromeDte = e.startDate._d;
      this.toDate = e.endDate._d;
      this.dateToBeShownOnCards();
      this.showSpinner = true;
      this.kpiChartFirstLoad = true;
      this.customChartFirstLoad = true;
      // this.chartService.getJSON().subscribe((url) => {
      //   this.getChart(url, e.startDate._d, e.endDate._d, this.value, this.highValue);
      // });
      if (this.tabSelected === 'KPI Charts'){
        this.getCharLoad();
        this.getKPIchartDeatils(this.serviceURl);
        }
      if (this.tabSelected === 'Custom Charts'){
        this.getchartKey(this.serviceURl);
        this.CustomchartArray = [];
      }
      if (this.tabSelected === 'KPI Overview'){
        this.getCharLoad();
        this.getKPIchartDeatils(this.serviceURl);
        }
    }
  }

  // custom chart dropdowm menu filter method
  customChartSelect(chartsid , selected, chartValues): void {
    if (selected){
    this.showSpinner = true;
    const paramsObj = {
      tenantId: this.recentUserAccId,
      assetId: this.assetID,
      assetParamName: chartsid,
      isKpi: false,
      fromLoad: this.value,
      toLoad: this.highValue
    };
    this.chartService.getChart(this.serviceURl, this.fromeDte, this.toDate, paramsObj).subscribe(
      (chart: any) => {
        const customeChart = chart.data.chart_data[0];
        if (customeChart && customeChart.name){
          this.updateDateOfChart(customeChart.data, this.findUOMName(chartValues.uom), chartValues.displayName,
           chartValues.displayName, false, customeChart.avgKpi, customeChart.name);
          this.showSpinner = false;
        } else{
            const toastSuccessMesage = {
              severity: 'error',
              summary: ` No data available for ${chartValues.displayName} chart`,
              key: 'chartError',
            };
            this.toastService.showSuccess(toastSuccessMesage);
            this.showSpinner = false;
        }
        // this.reportService.addItem('chart', 'chart', chart);
      });
    } else {
      this.CustomchartArray =  this.CustomchartArray.filter((node) => {
        return node.chartLabel !== chartValues.displayName;
    });
    }
  }
  // method to generate chart based on tabs(KPI and Custom charts )
  onTabChanged(e): void {
    this.tabSelected = e.tab.textLabel;
    this.tabName = e.tab.textLabel;
    // this.initDate = { startDate: moment().subtract(3, 'months'), endDate: moment() };
    if (e.tab.textLabel === 'Custom Charts') {
      this.kpiChartFirstLoad = false;
      if(this.customChartFirstLoad) {
        this.customChartFirstLoad = false;
        this.CustomchartArray = [];
        this.getchartKey(this.serviceURl);
      }
      // this.chartControl.setValue(['tr-deviation', 'ttd-deviation', 'bp-penalty', 'mw-penalty', 'cd-excess']);

     }
     // else if (this.kpiChartFirstLoad){
    //   this.kpiChartFirstLoad = false;
    //   this.chartService.getJSON().subscribe((url) => {
    //     this.getChart(url, this.fromeDte, this.toDate, this.value, this.highValue);
    //   });
    // }
  }
  viewCondenser(): void{
    this.router.navigate([`condenser/configurations/${encodeURIComponent(this.assetName)}`], { state: { cID: this.assetID } });
  }

  viewAdvancedCharts(): void {
    let paramObject = {
      condenserName: localStorage.setItem('condenserName', this.assetName),
      minLoadRange: localStorage.setItem('minLoadRange', this.value.toString()),
      maxLoadRange: localStorage.setItem('maxLoadRange', this.highValue.toString()),
      selectedChartid: localStorage.setItem('selectedChartID', this.selectedChartid),
      assetId: localStorage.setItem('assetID', this.assetID),
      loggedInUserTenantID: localStorage.setItem('loggedInUserTenantID', this.recentUserAccId),
      toDate :this.toDate,
      fromDate :this.fromeDte
    }
    localStorage.toDate = paramObject.toDate;
    localStorage.fromDate = paramObject.fromDate;
  }

  openModel(e, item): any {
    const paramObject = {
      asset_id: '6ddb6a59-da6d-4066-9185-c91c95f61d37',
      type: 'kpi',
      asset_type: 'condenser',
      tenant_id: this.recentUserAccId
    }
    this.chartService.getPopupContentForKpiOverview(this.serviceURl, paramObject).subscribe(
      data => {
        let selectedKpichart;
        this.kpiOverviewPopUpContent = data.data.kpi_cofig;
        for (let i = 0; i < this.kpiOverviewPopUpContent.length; i++) {
          if (this.kpiOverviewPopUpContent[i].name === item.chartname) {
            selectedKpichart = this.kpiOverviewPopUpContent[i].troubleshooting;
          }
        }
        this.confirmDlg = this.dialog.open(KpiOverviewModalComponent, {
          disableClose: true,
          position: { top: '5%', left: '25%' },
          data: {
            pagetext: selectedKpichart,
            pageTitle: item.chartLabel,
          }
        });
      }
    );
  }

  viewTroubleShootingGuide() {
    this.chartService.getTroubleShootingGuide(this.serviceURl).subscribe(
      data => {
        var file = new Blob([data], { type: 'application/pdf' });
        var fileURL = URL.createObjectURL(file);
        window.open(fileURL);
      }
    );
  }

  dateToBeShownOnCards(){
    const fd = this.fromeDte;
    const momentFDate = moment(fd);
    this.fromDateToBeShownOnCards = moment(fd).format('MM/DD/YYYY');
    const td = this.toDate;
    const momentTDate = moment(td);
    this.toDateToBeShownOnCards = moment(td).format('MM/DD/YYYY');
  }
}