import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ChartService } from 'src/app/condensers/kpi-dashboard/service/chart.service';
import { ReportListviewService } from 'src/app/condensers/report-listview/service/report-listview.service';
import { ToastMessgaeService } from 'src/app/condensers/shared/toast-service/service/toast-messgae.service';
import { environment } from 'src/environments/environment';
import { ReportService } from '../../service/report.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DataDrivinModalComponent } from 'src/app/condensers/shared/data-drivin-modal/data-drivin-modal.component';
import { Observable, Observer } from 'rxjs';

@Component({
  selector: 'app-report-preview',
  templateUrl: './report-preview.component.html',
  styleUrls: ['./report-preview.component.scss']
})
export class ReportPreviewComponent implements OnInit {

  envURl: any;
  recentUserAccid = '';
  logo = '';
  accLogo = '';
  serviceURl: any;
  state = '';
  pdfState = false;
  reportId = '';
  showSpinner: boolean;
  showSpinner1: boolean;

  accountLogo = '';
  singleLogo = false;
  get layout(): any[] {
    if (this.reportService.matarialLayout.length > 0) {
      return this.reportService.matarialLayout;
    }
  }
  previewLayout = [];
  confirmDlg: MatDialogRef<DataDrivinModalComponent>;
  termAndConditionLink = '';
  constructor(public reportService: ReportService,
    public chartService: ChartService,
    private toastService: ToastMessgaeService,
    private reportListviewService: ReportListviewService,
    private dialog: MatDialog,
    private route: ActivatedRoute,
    private router: Router) {
    this.route.queryParams.subscribe((par: any) => {
      this.reportId = par.id;
      this.state = par.state;
      if (par.mode === 'pdf') {
        this.pdfState = true;
      }
    });
  }

  ngOnInit(): void {
    this.envURl = environment;
    this.termAndConditionLink = `<a target = "_blank" href = ${location.origin}/legal/content/en>Terms and Conditions<a/>`;
    const par: any = parent;
    if (par.ctvpapp !== undefined) {
      const account = par.get_storage('recent-' + par.ctvpapp.user.id);
      if (account.account) {
        this.recentUserAccid = account.account.id;
      }
    }
    this.chartService.getJSON().subscribe((url) => {
      this.getLogo(url);
      this.serviceURl = url;
      if (this.pdfState === true) {
        document.body.style.height = '100%';
        document.body.style.overflow = 'auto';
        document.body.style.background = '#FFFFFF';
      }
      if (this.reportId !== '' && this.reportId !== 'id' && this.state !== 'dashboard') {
        this.getReport();
      }
      else {
        this.createPreviewLayout();
      }
    });
  }
  getReport(): void {
    this.reportService.report = {};
    this.showSpinner = true;
    this.reportListviewService.getReportById(this.serviceURl, this.reportId).subscribe((rdata) => {
      const repoData = {
        name: rdata.reportName,
        id: rdata.id,
        condenserId: rdata.assetId,
        condensername: rdata.assetName,
        value: rdata.minLoad,
        highValue: rdata.maxLoad,
        fromeDte: new Date(rdata.fromDate),
        toDate: new Date(rdata.toDate),
        systemName: rdata.systemName,
        facilityName: rdata.facilityName,
        createdBy: rdata.createdBy,
        createdAt: rdata.createdAt,
      };
      if (rdata.reportJson.reportview !== undefined) {
        this.reportService.previewLayout = rdata.reportJson.reportview;
      }
      if (rdata.reportJson.reportLayout !== undefined) {
        this.reportService.matarialLayout = rdata.reportJson.reportLayout;
      }
      this.reportService.addReportData(repoData);
      this.createPreviewLayout();
      console.log(this.reportService.report.createdAt);
      this.showSpinner = false;
    });
  }

  downloadReport(): void {
    this.showSpinner1 = true;

    if (this.accountLogo == undefined || this.accountLogo.includes('PCFET0NUWVBFIGh0bWw+CjxodG1sPgogICAgPGhlYWQ+CiAgICAgICAgPG1ldGEgY2hhcnNldD0iVVRGLTgiIC8+CiAgICAgICAgPG1ldGEgaHR0cC1lcXVpdj0iQ29udGVudC1UeXBlIiBjb250ZW50PSJ0ZXh0L2h0bWw7IGNoYXJzZXQ9dXRmLTgiIC8+CiAgICAgICAgPG1ldGEgbmFtZT0idmlld3BvcnQiIGNvbnRlbnQ9IndpZHRoPWRldmljZS13aWR0aCwgaW5pdGlhbC1zY2FsZT0xLjAsIG1heGltdW0tc2NhbGU9MS4wLCB1c2VyLXNjYWxhYmxlPTAiIC8+CiAgICAgICAgPCEtLVtpZi') || this.accountLogo.length < 200) {
           this.accountLogo = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAALMAAABYCAYAAAC+l8ayAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAD0SURBVHhe7dIBDQAADMOg+ze9+2hAAzeIkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmQyZyZCZDJnJkJkMmcmQmYjtARN4ODx5GkvRAAAAAElFTkSuQmCC';
        }
    const paramsobj = {
      id: this.reportId,
      mode: 'pdf',
      state: '',
      printBackground: 'true',
      displayHeaderFooter: 'true',
      landscape: 'false',
      scale: 0.88,
      format: 'A4',
      accountLogo: this.accountLogo
    };
    this.reportListviewService.downloadReportPost(paramsobj, this.serviceURl).subscribe((data) => {
      const blob = new Blob([data], { type: 'application/pdf' });
      const link = document.createElement('a');
      link.href = URL.createObjectURL(blob);
      link.download = `${this.reportService.report.name}.pdf`;
      document.body.appendChild(link);
      link.click();
      window.setTimeout(() => {
        URL.revokeObjectURL(link.href);
        document.body.removeChild(link);
      }, 0);
      this.showSpinner1 = false;

    });
  }
  createPreviewLayout(): void {
    let temparray = [];
    this.showSpinner = true;
    this.reportService.previewLayout.filter((pNode) => {
      temparray = [];
      pNode.filter((node) => {
        const obj = this.findLayout(node);
        temparray.push(obj);
      });
      this.previewLayout.push(temparray);
    });
    this.showSpinner = false;
  }
  findLayout(id): any {
    const UOM = this.layout.filter((node) => {
      return node.id === id;
    });
    return UOM[0];
  }
  getLogo(url): void {

    if (this.recentUserAccid) {
      var img = new Image();
      ;
      var xhr = new XMLHttpRequest();
      xhr.onload = (event) => {
        const fileReader: FileReader = new FileReader();
        fileReader.onloadend = (event) => {
          this.accountLogo = fileReader.result as string;
        };
        fileReader.readAsDataURL(xhr.response);
      };

      xhr.open('GET', `${url.getLogo}logo-${this.recentUserAccid}.png`);
      xhr.responseType = 'blob';
      xhr.send();
    }
  }
  parentURLChange(): void {
    let parentlocation = (window.parent.location).toString();
    if (window.self !== window.top) { // checking if it is an iframe
      const words = parentlocation.split('condenser/');
      parentlocation = `${words[0]}condenser/reports`;
      window.parent.location.href = parentlocation;

    } else {
      this.router.navigate([`condenser/reports`]);
    }
  }
  editReport(): void {
    const assetName = encodeURIComponent(this.reportService.report.cName.trim());
    this.router.navigate([`/condenser/report/create/${assetName}`], {
      state: {
        cID: this.reportService.report.cID,
        value: this.reportService.report.value,
        highValue: this.reportService.report.highValue,
        fromeDte: this.reportService.report.fromeDte,
        toDate: this.reportService.report.toDate,
        systemName: this.reportService.report.systemName,
        facilityName: this.reportService.report.facilityName,
        state: 'preview'
      }
    });
  }

  successModel(): void {
    const toastSuccessMesage = {
      severity: 'success',
      summary: `${this.reportService.report.name}`,
      key: 'deleteReport',
    };
    this.toastService.showSuccess(toastSuccessMesage);
    this.router.navigate([`condenser/reports`]);
  }
  errorModel(): void {
    const toastSuccessMesage = {
      severity: 'error',
      summary: `${this.reportService.report.name}`,
      key: 'reportNotDeleted',
    };
    this.toastService.showSuccess(toastSuccessMesage);
  }
  openModel(d): void {
    if (d === 'Delete') {
      this.confirmDlg = this.dialog.open(DataDrivinModalComponent, {
        disableClose: true,
        position: { top: '3%', left: '35%' },
        data: {
          pagetext: `This will delete report ${this.reportService.report.name} from your records. You can't undo this.`,
          pageTitle: 'Delete the report',
          leavepagebtn: 'Delete',
          staypagebtn: 'Cancel'
        }
      });
      this.confirmDlg.afterClosed().subscribe(async (response) => {
        if (response) {
          this.reportListviewService.deleteReport(this.reportService.report.id, this.serviceURl).subscribe((value) => {
            this.successModel();
          }, (error) => {
            this.errorModel();
          });
        }
      });
    } else {
      this.confirmDlg = this.dialog.open(DataDrivinModalComponent, {
        disableClose: true,
        position: { top: '3%', left: '35%' },
        data: {
          pagetext: `This will discard report ${this.reportService.report.name}. You can't undo this.`,
          pageTitle: 'Discard the report',
          leavepagebtn: 'Discard',
          staypagebtn: 'Cancel'
        }
      });
      this.confirmDlg.afterClosed().subscribe(async (response) => {
        if (response) {
          this.elseMessage();
        }
      });
    }

  }
  elseMessage(): void {
    const toastSuccessMesage = {
      severity: 'success',
      summary: `${this.reportService.report.name}`,
      key: 'discardReport',
    };
    this.toastService.showSuccess(toastSuccessMesage);
    this.router.navigate([`condenser/dashboard`]);
  }

}
