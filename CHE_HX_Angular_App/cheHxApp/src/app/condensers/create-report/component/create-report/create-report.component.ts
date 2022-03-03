import { Component, OnInit, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import { ReportService } from '../../service/report.service';
import { MatOption } from '@angular/material/core';
import { MatSelect } from '@angular/material/select';
import { ChartService } from 'src/app/condensers/kpi-dashboard/service/chart.service';
import {environment} from '../../../../../environments/environment';
import { Checkbox } from 'primeng';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DataDrivinModalComponent } from 'src/app/condensers/shared/data-drivin-modal/data-drivin-modal.component';
import { ToastMessgaeService } from 'src/app/condensers/shared/toast-service/service/toast-messgae.service';
import { CONTENT_ATTR } from '@angular/compiler';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { CreateCondenserService } from 'src/app/condensers/create-condenser/service/create-condenser.service';
import { ReportListviewService } from 'src/app/condensers/report-listview/service/report-listview.service';
import { stringify } from '@angular/compiler/src/util';

@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.scss']
})
export class CreateReportComponent implements OnInit, AfterViewInit {
  [x: string]: {};
  date = new Date();
  kpiCharts: FormControl;
  myGroup: FormGroup;
  showSpinner: boolean;
  envURl: any;
  datany: any;
  reportDta: any;
  reportSaved = true;
  condenserId = '';
  reportId = 'id';
  condensername = '';
  pagedivision = [];
  value = 0;
  highValue = 1000;
  fromeDte: any = '';
  toDate: any = '';
  state = '';
  systemName = '';
  facilityName = '';
  recentUserAccName = '';
  recentUserAccId = '';
  recentUserAccFacilityName = '';
  recentUserAccFacilityId = '';
  recentUserAccSystemName = '';
  recentUserAccSystemId = '';
  UOMArray: any = [];
  userDetalis: any;
  troubleshootingCheckBox = false;
  kpiOverviewCheckBox = false;
  serviceURl: any;
  @ViewChild('content') content: ElementRef;
  @ViewChild('allSelected') public allSelected: MatSelect;
  @ViewChild('customSelected') public customSelected: MatSelect;
  confirmDlg: MatDialogRef<DataDrivinModalComponent>;
  imgdata = '../../../assets/img/grahs.PNG';
  allChartdata = [];
  allSelectedvalue = false;
  kpiChartList = [];
  customChartList = [];
  chartData = [];
  get layout(): any[] {
    if (this.reportService.matarialLayout.length > 0) {
      return this.reportService.matarialLayout;
    }
  }
  htmlContent = '';
  config: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: '15rem',
    minHeight: '5rem',
    placeholder: 'Enter text here...',
    translate: 'no',
    defaultParagraphSeparator: 'p',
    defaultFontName: 'Segoe UI',
    toolbarHiddenButtons: [
      ['bold']
      ],
    customClasses: [
      {
        name: 'quote',
        class: 'quote',
      },
      {
        name: 'redText',
        class: 'redText'
      },
      {
        name: 'titleText',
        class: 'titleText',
        tag: 'h1',
      },
    ]
  };

  enableDrag = false;
  termAndConditionLink = '';
  customChartSelection = [];
  kpiChartSelection = [];

  constructor(public reportService: ReportService,
              private route: ActivatedRoute,
              private router: Router,
              private fb: FormBuilder,
              public chartService: ChartService,
              private toastService: ToastMessgaeService,
              private createCondenserService: CreateCondenserService,
              private reportListviewService: ReportListviewService,
              private dialog: MatDialog) {
                this.myGroup = this.fb.group({
                  reportName: ''});

                this.condensername = decodeURIComponent(this.route.snapshot.paramMap.get('rid'));
                if (this.router.getCurrentNavigation() !== null || undefined){
                  this.condenserId = this.router.getCurrentNavigation().extras.state.cID;
                  this.toDate = this.router.getCurrentNavigation().extras.state.toDate;
                  this.fromeDte = this.router.getCurrentNavigation().extras.state.fromeDte;
                  this.value = this.router.getCurrentNavigation().extras.state.value;
                  this.highValue = this.router.getCurrentNavigation().extras.state.highValue;
                  this.systemName = this.router.getCurrentNavigation().extras.state.systemName;
                  this.facilityName = this.router.getCurrentNavigation().extras.state.facilityName;
                  this.state = this.router.getCurrentNavigation().extras.state.state;
                }
               }

  ngOnInit(): void {
    this.envURl = environment;
     const par: any = parent;
     this.kpiCharts = new FormControl([]);
     // this.termAndConditionLink = `<a href = ${location.origin}/legal/content/en>Terms and Conditions<a/>`
    // console.log(par);
    if (par.ctvpapp !== undefined){
    const account = par.get_storage('recent-' + par.ctvpapp.user.id);
    this.setAcounts(account);
}
    if (this.state === 'dashboard'){
      this.reportService.emptyLayout();
      this.reportService.report = {};
      this.myGroup.setValue({
        reportName: `${this.condensername}_${
          this.fromeDte.getFullYear() +
          '/' +
          ('0' + (this.fromeDte.getMonth() + 1)).slice(-2) +
          '/' +
          ('0' + this.fromeDte.getDate()).slice(-2)
        }-${
          this.toDate.getFullYear() +
          '/' +
          ('0' + (this.toDate.getMonth() + 1)).slice(-2) +
          '/' +
          ('0' + this.toDate.getDate()).slice(-2)
        }`
     });
    } else{
      this.myGroup.setValue({
        reportName: this.reportService.report.name});
      if (this.reportService.report.id !== undefined)
        {
           this.reportId = this.reportService.report.id;
          }
      let haveTroubleShootingGuide = this.reportService.matarialLayout.filter(item => item['layoutType'] === 'troubleshooting');
      if(haveTroubleShootingGuide.length) {
        this.troubleshootingCheckBox = true;
      }
    }
    this.chartService.getJSON().subscribe((url) => {
        this.serviceURl = url;
        this.getChart(url, this.fromeDte, this.toDate, this.value, this.highValue);
        this.getTandEData();
        this.getUOM();
        this.getUserProfile();
    });
    // this.chainTeamFunction();
  }
  setAcounts(account): void{
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
    this.datany = this.reportService.matarialLayout;
  }
  getTandEData(): void{
      this.reportService.getReportData().subscribe((data) => {
        this.reportDta = data;
        if (this.state === 'dashboard'){
        this.reportService.addItem('executive', 'Disclaimer', this.reportDta);
        }
      });
  }

  saveReport(): void{
    if (this.reportService.report.name === undefined || this.reportService.report.name !== this.myGroup.get('reportName').value
    || this.reportService.report.createdAt !== '' ){
      this.calcularePx();
    }
    const tempLayout = this.layout.map(item => {
      if (item['headerTitle'] === 'Disclaimer') {
        item['data']['executiveSummary'] = `<p>This report should be reviewed with your ChemTreat account representative. Results are derived via analysis of plant parameters and may vary dependent on quality of these parameters as well as other unforeseen variables. This Report is subject to the CTVista+ Terms and Conditions available at <a target = \"_blank\" href = \"${location.origin}/legal/content/en\">Terms and Conditions<a/>`
      }
      return item;
      });
    const obj = {
      reportName: this.reportService.report.name,
      assetId: this.reportService.report.cID,
      assetName: this.reportService.report.cName,
      minLoad : this.reportService.report.value,
      maxLoad : this.reportService.report.highValue,
      fromDate : this.reportService.report.fromeDte,
      toDate : this.reportService.report.toDate,
      systemName: this.reportService.report.systemName,
      facilityName : this.reportService.report.facilityName,
      createdBy: this.reportService.report.createdBy,
      isActive: true,
      tenantId: this.recentUserAccId,
      reportJson: {
        reportview: this.reportService.previewLayout,
        reportLayout: tempLayout
      }
    };
    if (this.reportService.report.id !== 'id'){
      this.editReport(obj);

    } else {
      this.AddReport(obj);
    }
  }
  editReport(obj): void{
    this.reportService.updateReportData(this.serviceURl, this.reportService.report.id, obj).subscribe((res) => {
     this.editReportMsg();
    }, (error) => {
      const errorMsg = `&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Failed to update&nbsp;'<strong>${this.reportService.report.name}</strong>'&nbsp;report`;
      this.errorTostmsg(errorMsg);

    });
  }
  AddReport(obj): void{
    this.reportService.postReportData(this.serviceURl, obj).subscribe((res) => {
     this.AddReportMsg(res);
    }, (error) => {
     this.AddReportErrorMsg(error);
     });
  }
  editReportMsg(): void{
    const msg = `&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Report&nbsp;'<strong>${this.reportService.report.name}</strong>'&nbsp;has been updated successfully`;
    this.successTostmsg(msg);
    this.router.navigate([`condenser/report/preview`], { queryParams: { id: this.reportId,
      mode: 'view', state: '' }});
  }
  AddReportMsg(res): void{
    const msg = `&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Report&nbsp;'<strong>${this.reportService.report.name}</strong>'&nbsp;has been saved successfully`;
    this.successTostmsg(msg);
    this.reportId = res.data;
    this.reportService.report.createdAt = res.timestamp;
    this.reportService.report.id = res.data;
    this.router.navigate([`condenser/report/preview`], { queryParams: { id: this.reportId,
      mode: 'view', state: '' }});
  }
  AddReportErrorMsg(error): void{
    let errorMsg = '';
    if (error.message){
      errorMsg = `&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'<strong>${this.reportService.report.name}</strong>'&nbsp;allready exist`;
    } else{
      errorMsg = `&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Failed to save&nbsp;'<strong>${this.reportService.report.name}</strong>'&nbsp;report`;
    }
    this.errorTostmsg(errorMsg);
  }
  successTostmsg(msg): void{
    const toastSuccessMesage = {
      severity: 'success',
      summary: msg,
      key: 'generalSuccess',
    };
    this.toastService.showSuccess(toastSuccessMesage);
  }
  errorTostmsg(msg): void{
    const toastSuccessMesage = {
      severity: 'error',
      summary: msg,
      key: 'generalError',
    };
    this.toastService.showSuccess(toastSuccessMesage);
  }
  addEditorData(e , id): void {
    this.reportService.updateItemdata(e, id);
  }
  previewReport(): void {
    this.calcularePx();
    this.condensername = this.condensername.trim();
    this.router.navigate([`condenser/report/preview`], { queryParams: { id: this.reportId,
      mode: 'view', state: 'dashboard' } });
  }

  calcularePx(): void{
    this.pagedivision = [];
    // console.log(this.layout);
    const totelPx = 600;
    let tempps = [];
    let count = 0;
    const repoData = {
      name: this.myGroup.get('reportName').value,
      id: this.reportId,
      condenserId: this.condenserId,
      condensername: this.condensername,
      value : this.value,
      highValue : this.highValue,
      fromeDte : this.fromeDte,
      toDate : this.toDate,
      systemName: this.systemName,
      facilityName : this.facilityName,
      createdBy: this.userDetalis ? this.userDetalis.username : '' ,
      createdAt: '',
    };
    this.reportService.addReportData(repoData);
    this.layout.forEach((data) => {
    //  console.log(document.getElementById(data.id).offsetHeight);
    if (data.id){
      count = count + document.getElementById(data.id).offsetHeight;
    }
    if (count < totelPx){
      tempps.push(data.id);
     }else{
      count = 0;
      this.pagedivision.push(tempps);
      tempps = [];
      tempps.push(data.id);
     }
  });
    if (tempps.length > 0){
      this.pagedivision.push(tempps);
   }
    // console.log(this.pagedivision);
    this.reportService.previewLayout = this.pagedivision;
  }

  getchartKey(url): void {
    this.chartService.getchartKey(url, this.condenserId, true).subscribe((kpiRes) => {
      this.kpiChartList = kpiRes;
      let kpiCharts = this.reportService.matarialLayout.filter(item => {
        let isKpiChart = kpiRes.filter( chart => chart['displayName'] === item['headerTitle']);
        return isKpiChart.length;
      })
      this.kpiCharts.setValue(kpiCharts.map(item => item['headerTitle']));
      this.chartService.getchartKey(url, this.condenserId, false).subscribe((res) => {
        let tempKPIChartKey = this.kpiChartList.map(item => item.displayName);
        this.customChartList = res.filter(item => !tempKPIChartKey.includes(item.displayName));
        let customCharts = this.reportService.matarialLayout.filter(item => {
          let isCustomChart = this.customChartList.filter( chart => chart['displayName'] === item['headerTitle']);
          return isCustomChart.length;
        })
        this.customChartSelection = customCharts.map(item => item['headerTitle']);
        // this.customChartSelection = customCharts;
      });
    });
  }
  deleteItem(iteam): void {
   if (iteam.layoutType === 'troubleshooting'){
    this.troubleshootingCheckBox = false;
   }
   if (iteam.layoutType === 'chart' ){
    this.reportService.deleteItem(iteam.id);
    this.allSelected.options.forEach((select: MatOption) => {
      if (iteam.headerTitle === select.value){
          select.deselect();
    }
  });
    this.customSelected.options.forEach((customselect: MatOption) => {
      if (iteam.headerTitle === customselect.value){
        customselect.deselect();
    }
  });
    if (this.allSelectedvalue === true){
      this.allSelectedvalue = false;
      this.allSelected.options.first.deselect();
  }
   } else {
    this.reportService.deleteItem(iteam.id);
   }
}
  // chainTeamFunction(): void {
  //   this.chartService.getChinateamAccounts().subscribe((data) => {
  //     console.log('getChinateamAccounts Data ', data);
  //   });
  // }
  setTroubleshooting(troubleshooting): void{
    if (troubleshooting){
      this.troubleshootingCheckBox = true;
      this.reportService.addItem('troubleshooting', 'Troubleshooting', this.reportDta);
    } else{
      const removalData = this.reportService.matarialLayout.find((e) => e.layoutType === 'troubleshooting');
      this.deleteItem(removalData);
    }
  }

  createchartData(chart , Xlabel, YLabel, ChartLabel, isKPI): void{
    const value = [];
    let dataObject = {};
    chart.forEach(
      (element) => {
        value.push([
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
      },
      (err) => {
        console.log(err);
      }
    );
    dataObject = {
      xlabel: Xlabel,
      yLabel: YLabel,
      chartLabel: ChartLabel,
      chartdata: value
    };
    if (isKPI){
      this.allChartdata.push(dataObject);
    } else{
      const singlecutomChartArray = [];
      singlecutomChartArray.push(dataObject);
      this.reportService.addItem('chart', ChartLabel, singlecutomChartArray);
    }
  }

  getChart(url, fD, eD, fPL, tPL): void {
    this.showSpinner = true;
    const paramsObj = {
      tenantId: this.recentUserAccId,
      assetId: this.condenserId,
      assetParamName: '',
      isKpi: 'true',
      fromLoad: fPL ,
      toLoad: tPL
    };
    this.chartService.getChart(url, fD, eD, paramsObj).subscribe(
      (chart: any) => {
        if (chart.data !== undefined && chart.data.chart_data !== undefined) {
          this.chartData = chart.data.chart_data;
          this.chartData.forEach(element => {
            this.manageCharts(element);
          });
        }
        this.showSpinner = false;
      },
      (error) => {
        this.showSpinner = false;
      }
    );
  }
  getUOM(): void{
    this.chartService.getUOM(this.serviceURl).subscribe((uoms) => {
      this.getchartKey(this.serviceURl);
      // console.log(uoms);
      this.UOMArray = uoms;
    });
  }

  findUOMName(id): string{
    const UOM =  this.UOMArray.filter((node) => {
      return node.id === id;
     });
    return UOM[0].name;
  }
  manageCharts(element): void{
    const KPIDetails = this.kpiChartList.filter((node) => {
      return node.key === element.name;
     });
    if (this.kpiChartList.length !== 0){
      this.createchartData(element.data, this.findUOMName(KPIDetails[0].uom), KPIDetails[0].displayName, KPIDetails[0].displayName, true);
    }
  }
  drop(event: CdkDragDrop<string[]>): void {
    moveItemInArray(this.reportService.matarialLayout, event.previousIndex, event.currentIndex);
  }
  somethingChanged(kipCharts, selected): void{
    let isPresent = this.reportService.matarialLayout.filter(item => item.headerTitle === kipCharts);
    if (selected && !isPresent.length) {
    const obj = this.allChartdata.filter((node) => {
      return node.chartLabel === kipCharts;
  });
    this.datany = obj;
    // console.log(this.datany);
    this.reportService.addItem('chart', this.datany[0].chartLabel, obj);
  }else if(!selected) {
    const obj: any = this.layout.filter((node) => {
      return node.headerTitle === kipCharts;
  });
    this.reportService.deleteItem(obj[0].id);
  }
  }
  customChartSelect(selected, chartValues): void {
    let isPresent = this.reportService.matarialLayout.filter(item => item.headerTitle === chartValues.displayName);
    if (selected && !isPresent.length) {
    const paramsObj = {
      tenantId: this.recentUserAccId,
      assetId: this.condenserId,
      assetParamName: chartValues.id,
      isKpi: 'false',
      fromLoad: this.value,
      toLoad: this.highValue
    };
    this.chartService.getChart(this.serviceURl, this.fromeDte, this.toDate, paramsObj).subscribe(
      (chart: any) => {
        const customeChart = chart.data.chart_data[0];
        if (customeChart &&  customeChart.name){
          // console.log(chartValues);
          this.createchartData(customeChart.data, this.findUOMName(chartValues.uom), chartValues.displayName,
          chartValues.displayName, false);
        }else{
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
    }else if(!selected) {
      const obj: any = this.layout.filter((node) => {
        return node.headerTitle === chartValues.displayName;
    });
      this.reportService.deleteItem(obj[0].id);
    }
  }
  toggleAllSelection(): void {
    this.allSelectedvalue = !this.allSelectedvalue;
    if (this.allSelectedvalue) {
      this.allSelected.options.forEach((item: MatOption) => item.select());
    } else {
      this.allSelected.options.forEach((item: MatOption) => item.deselect());
    }
  }
  dmodelAfterCloseresponceMsg(reportName): void{
    const toastSuccessMesage = {
      severity: 'success',
      summary: `${reportName}`,
      key: 'deleteReport',
    };
    this.toastService.showSuccess(toastSuccessMesage);
    this.router.navigate([`condenser/reports`]);
  }
  dmodelAfterCloseErrorMsg(reportName): void{
    const toastSuccessMesage = {
      severity: 'error',
      summary: `${reportName}`,
      key: 'reportNotDeleted',
    };
    this.toastService.showSuccess(toastSuccessMesage);
  }
  openModel(d): void {
    if (d === 'Delete'){
     this.confirmDlg = this.dialog.open(DataDrivinModalComponent, {
      disableClose: true,
      position: { top: '3%', left: '35%' },
      data: { pagetext: `This will delete report ${this.myGroup.get('reportName').value} from your records. You can't undo this.`,
      pageTitle: 'Delete the report',
      leavepagebtn: 'Delete',
      staypagebtn: 'Cancel'
     }
    });
     this.confirmDlg.afterClosed().subscribe(async (response) => {
      if (response) {
        this.reportListviewService.deleteReport(this.reportService.report.id, this.serviceURl).subscribe((value) => {
         this.dmodelAfterCloseresponceMsg(this.myGroup.get('reportName').value)
        }, (error) => {
         
          this.dmodelAfterCloseErrorMsg(this.myGroup.get('reportName').value);
        });
     }
  });
} else{
  this.confirmDlg = this.dialog.open(DataDrivinModalComponent, {
    disableClose: true,
    position: { top: '3%', left: '35%' },
    data: { pagetext: `This will discard report ${this.myGroup.get('reportName').value} changes. You can't undo this.`,
    pageTitle: 'Discard the report',
    leavepagebtn: 'Discard',
    staypagebtn: 'Cancel'
   }
  });
  this.confirmDlg.afterClosed().subscribe(async (response) => {
    if (response) {
        this.modelElaseresponceMsg(this.myGroup.get('reportName').value)
   }
});
}

}
modelElaseresponceMsg(reportName): void{
  const toastSuccessMesage = {
    severity: 'success',
    summary: `${reportName}`,
    key: 'discardReport',
  };
  this.toastService.showSuccess(toastSuccessMesage);
  this.router.navigate([`condenser/dashboard`]);
}
getUserProfile(): void {
   this.createCondenserService.getUserProfile(this.serviceURl).subscribe((res) => {
     this.userDetalis = res;
    //  console.log( this.userDetalis);
    });
 }

 enableEditorDrag() {
   this.enableDrag = !this.enableDrag
 }
}
