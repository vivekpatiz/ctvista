import { HttpClient } from '@angular/common/http';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ChartService } from 'src/app/condensers/kpi-dashboard/service/chart.service';
import { Router } from '@angular/router';
import { ReportListviewService } from '../../report-listview/service/report-listview.service';
import { ToastMessgaeService } from '../../shared/toast-service/service/toast-messgae.service';
import { ReportService } from '../../create-report/service/report.service';
@Component({
  selector: 'app-report-listview',
  templateUrl: './report-listview.component.html',
  styleUrls: ['./report-listview.component.scss'],
})
export class ReportListviewComponent implements OnInit, AfterViewInit {
  title = 'CTvista';
  cols = [];
  tableData: any = [];
  showSpinner: boolean;
  showSpinner1: boolean;
  paginator = true;
  searchPlaceHolder = 'Search by Report Name';
  row = 10;
  showCurrentPageReport = true;
  rowsPerPageOptions = [10, 25, 50];
  scrollable = true;
  scrollHeight = '400px';
  styleClass = 'p-datatable-striped';
  sortField = 'updatedAt';
  sortOrder = -1;
  globalFilterFields = ['reportName'];
  headertext = '';
  accountLogo = '';
  subHeadertext = '';
  actionbtn = true;
  showDeleteAtnBtn = true;
  showDuplicateAtnBtn = false;
  showViewAtnBtn = true;
  showDownloadAtnBtn = true;
  
  showShareAtnBtn = false;
  copyData = [];
  recentUserAccName = '';
  recentUserAccId = '';
  recentUserAccFacilityName = '';
  recentUserAccFacilityId = '';
  recentUserAccSystemName = '';
  recentUserAccSystemId = '';
  public date = '';
  public URl = '';
  reportDateRange: any;
  serviceURL: any;
  input: any;
  constructor(
    public httpClient: HttpClient,
    public chartService: ChartService,
    private router: Router,
    private reportService: ReportListviewService,
    private toastService: ToastMessgaeService,
    public reportServices: ReportService
  ) {
    this.cols = [
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
    ];
  }

  ngOnInit(): void {
    this.reportService.getJSON().subscribe((data) => {
      this.serviceURL = data;
      this.getReportDataTable(data);
    });
    const par: any = parent;
    if (par.ctvpapp !== undefined) {
      const account = par.get_storage('recent-' + par.ctvpapp.user.id);
      this.setAccountDetail(account);
    }
  }
  setAccountDetail(account): void{
    if (account.account) {
      this.recentUserAccName = account.account.name;
      this.recentUserAccId = account.account.id;
      console.log(`recentUserAccName`, this.recentUserAccName);
      console.log(`recentUserAccId`, this.recentUserAccId);
       this.chartService.getJSON().subscribe((url) => {
        this.getLogo(url);
      });
    }
    if (account.facility) {
      this.recentUserAccFacilityName = account.facility.name;
      this.recentUserAccFacilityId = account.facility.id;
     
    }
    if (account.system) {
      this.recentUserAccSystemName = account.system.name;
      this.recentUserAccSystemId = account.system.id;
    }
  }
  ngAfterViewInit(): void {
    setTimeout(() => {
      if (document.querySelector('p-dropdown')) {
        document.querySelector('p-dropdown').prepend('Show Entries: ');
      }
    }, 100);
  }
  downloadReport(event): void {
    // console.log(event);
    this.showSpinner1 = true;
    if (this.accountLogo == undefined || this.accountLogo.includes('PCFET0NUWVBFIGh0bWw+CjxodG1sPgogICAgPGhlYWQ+CiAgICAgICAgPG1ldGEgY2hhcnNldD0iVVRGLTgiIC8+CiAgICAgICAgPG1ldGEgaHR0cC1lcXVpdj0iQ29udGVudC1UeXBlIiBjb250ZW50PSJ0ZXh0L2h0bWw7IGNoYXJzZXQ9dXRmLTgiIC8+CiAgICAgICAgPG1ldGEgbmFtZT0idmlld3BvcnQiIGNvbnRlbnQ9IndpZHRoPWRldmljZS13aWR0aCwgaW5pdGlhbC1zY2FsZT0xLjAsIG1heGltdW0tc2NhbGU9MS4wLCB1c2VyLXNjYWxhYmxlPTAiIC8+CiAgICAgICAgPCEtLVtpZi') || this.accountLogo.length < 200) {
           this.accountLogo = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAALMAAABYCAYAAAC+l8ayAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAD0SURBVHhe7dIBDQAADMOg+ze9+2hAAzeIkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmYjtARN4ODx5GkvRAAAAAElFTkSuQmCC';
        }
    const paramsobj = {
      id: event.id,
      mode: 'pdf',
      state: '',
      printBackground: 'true',
      displayHeaderFooter: 'true',
      landscape: 'false',
      scale: 0.88,
      format: 'A4',
      accountLogo: this.accountLogo

    };
    this.reportService.downloadReportPost(paramsobj, this.serviceURL).subscribe((data) => {
      // console.log(data);
      const blob = new Blob([data], { type: 'application/pdf' });
      const link = document.createElement('a');
      link.href = URL.createObjectURL(blob);
      link.download = `${event.name}.pdf`;
      document.body.appendChild(link);
      link.click();
      window.setTimeout(() => {
        URL.revokeObjectURL(link.href);
        document.body.removeChild(link);
      }, 0);
      this.showSpinner1 = false;
    });
  }

  getReportDataTable(params): void {
    this.showSpinner = true;
    const filterobj = {
      size: 500,
      page: 0,
      sort: 'createdAt',
      order: 'DESCENDING',
      facilityName: this.recentUserAccFacilityName,
      systemName: this.recentUserAccSystemName,
      tenantId: this.recentUserAccId
    };
    this.reportService.getReportList_v2(params, filterobj).subscribe(
      (list: any) => {
        list.data.content.forEach((element) => {
          if (element.hasOwnProperty('updatedAt')) {
            const parsedDate = new Date(element.updatedAt);
            element.updatedAt =
              parsedDate.getMonth() +
              1 +
              '/' +
              parsedDate.getDate() +
              '/' +
              parsedDate.getFullYear() +
              ' ' +
              parsedDate.toLocaleString('en-US', {
                hour: 'numeric',
                minute: 'numeric',
                hour12: true,
              });
          }
          if (
            element.hasOwnProperty('fromDate') &&
            element.hasOwnProperty('toDate')
          ) {
            const parsedDate = new Date(element.fromDate);
            const parsedDateTo = new Date(element.toDate);
            element.fromDate =
              parsedDate.getMonth() +
              1 +
              '/' +
              parsedDate.getDate() +
              '/' +
              parsedDate.getFullYear();
            element.toDate =
              parsedDateTo.getMonth() +
              1 +
              '/' +
              parsedDateTo.getDate() +
              '/' +
              parsedDateTo.getFullYear();
            element.rangeDate = element.fromDate + '-' + element.toDate;
          }
          if (element.hasOwnProperty('reportName')) {
            element.name = element.reportName;
          }
        });
        if (list.data.content) {
          this.tableData = list.data.content;
        }
        console.log(JSON.stringify(list));
        this.headertext = 'Account:' + ' ' + this.recentUserAccName;
        this.showSpinner = false;
      },
      (error) => {
       this.reportdataError(error);
      }
    );
  }
  reportdataError(error): void{
    this.showSpinner = false;
    const toastErrorMesage = {
      severity: 'error',
      summary: `${error.message}`,
      key: 'getError',
    };
    this.toastService.showError(toastErrorMesage);
  }

  deleteData(data): void {
    this.reportService.getJSON().subscribe((jsonURl) => {
      this.reportService.deleteReport(data.id, jsonURl).subscribe(
        (res) => {
          const toastSuccessMesage = {
            severity: 'success',
            summary: `${data.reportName}`,
            key: 'delete',
          };
          this.toastService.showSuccess(toastSuccessMesage);
          this.getReportDataTable(jsonURl);
        },
        (error) => {
         this.reportdataError(data);
        }
      );
    });
  }
  deleatDataError(data): void{
    const toastErrorMesage = {
      severity: 'error',
      summary: `${data.reportName}`,
      key: 'deleteError',
    };
    this.toastService.showError(toastErrorMesage);
  }

  getLogo(url): void {

    if (this.recentUserAccId) {
      var img = new Image();
      var xhr = new XMLHttpRequest();
      xhr.onload = (event) => {
        const fileReader: FileReader = new FileReader();
        fileReader.onloadend = (event) => {
            this.accountLogo = fileReader.result as string;
        };
        fileReader.readAsDataURL(xhr.response);
      };

      xhr.open('GET', `${url.getLogo}logo-${this.recentUserAccId}.png`);
      xhr.responseType = 'blob';
      xhr.send();
    }
  }

  viewReport(d): void {
    // console.log(d);
    this.showSpinner = true;
    // this.router.navigate([`/condenser/report/preview/${d.id}`], { state: {  state: ''}});
    this.router.navigate([`condenser/report/preview`], {
      queryParams: {
        id: d.id,
        mode: 'view', state: ''
      }
    });
    this.showSpinner = false;
  }
}
