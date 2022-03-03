import { Component, OnInit, AfterViewInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CondenserListviewService } from '../service/condenser-listview.service';
import { ToastMessgaeService } from '../../shared/toast-service/service/toast-messgae.service';
import { FormGroup } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-condenser-listview',
  templateUrl: './condenser-listview.component.html',
  styleUrls: ['./condenser-listview.component.scss'],
})
export class CondenserListviewComponent implements OnInit, AfterViewInit {
  title = 'CTvista';
  cols = [];
  tableData: any = [];
  showSpinner: boolean;
  paginator = true;
  searchPlaceHolder = 'Search by Condenser Name';
  row = 10;
  showCurrentPageReport = true;
  rowsPerPageOptions = [10, 25, 50];
  scrollable = true;
  scrollHeight = '400px';
  styleClass = 'p-datatable-striped';
  sortField = 'updatedAt';
  sortOrder = -1;
  globalFilterFields = ['name'];
  headertext = '';
  subHeadertext = '';
  actionbtn = true;
  showDeleteAtnBtn = true;
  showDuplicateAtnBtn = true;
  showViewAtnBtn = true;
  showDownloadAtnBtn = false;
  showShareAtnBtn = false;
  copyData = [];
  recentUserAccName = '';
  recentUserAccId;
  recentUserAccFacilityName = '';
  recentUserAccFacilityId = '';
  recentUserAccSystemName = '';
  recentUserAccSystemId = '';
  public date = '';
  public URl = '';

  constructor(
    public httpClient: HttpClient,
    private router: Router,
    private condensrService: CondenserListviewService,
    private toastService: ToastMessgaeService
  ) { }
  setSystem(account): void {
    console.log(`'recent'`, account);
    if (account.account) {
      this.recentUserAccName = account.account.name;
      this.recentUserAccId = account.account.id;
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
  ngOnInit(): void {
    this.condensrService.getJSON().subscribe(data => {
      this.getCondenserDataTable(data);
    });
    const par: any = parent;
    // console.log(par);
    if (par.ctvpapp !== undefined) {
      const account = par.get_storage('recent-' + par.ctvpapp.user.id);
      this.setSystem(account);
    }
    this.cols = [
      {
        field: 'name',
        header: 'Condenser Name',
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
        field: 'facilityName',
        header: 'Facility',
        sortOpt: true,
        type: 'text',
      },
      { field: 'systemName', header: 'System', sortOpt: true, type: 'text' },
      {
        field: 'createdBy',
        header: 'Created by',
        sortOpt: true,
        type: 'text',
      }
    ];
  }
  ngAfterViewInit(): void {
    setTimeout(() => {
      if (document.querySelector('p-dropdown')) {
        document.querySelector('p-dropdown').prepend('Show Entries: ');
      }
    }, 100);
  }
  getCondenserDataTable(params): void {
    this.showSpinner = true;
    const paramsobj = {
      size: 100,
      page: 0,
      sort: 'updatedAt',
      order: 'DESCENDING',
      facilityName: this.recentUserAccFacilityName,
      systemName: this.recentUserAccSystemName,
      tenantId: this.recentUserAccId,
    };

    this.condensrService.getCondenser_v2(params, paramsobj).subscribe(
      (list: any) => {
        if (list.data) {
          list.data.content.forEach((element) => {
            element.status
              ? (element.status = 'Complete')
              : (element.status = false);
            if (element.hasOwnProperty('updatedAt')) {
              let formattedDate = element.updatedAt.split(' ').join('T') + '.000+00:00';
              const parsedDate = new Date(formattedDate);
              element.updatedAt =
                parsedDate.getMonth() + 1 +
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
            element.systemName = element.systemName.split('~')[0];
          });
        }
        else {
          this.showSpinner = false;
        }
        if (list.data.content) {
          this.tableData = list.data.content;
        }
        this.headertext = 'Account:' + ' ' + this.recentUserAccName;
        this.showSpinner = false;
      },
      (error) => {
       this.getCondenserDataError(error);
      }
    );
  }
  getCondenserDataError(error): void{
    this.showSpinner = false;
    const toastErrorMesage = {
      severity: 'error',
      summary: `${error.message}`,
      key: 'getError',
    };
    this.toastService.showError(toastErrorMesage);
  }
  deleData(data): void {
    this.condensrService.getJSON().subscribe(jsonURl => {
      this.condensrService.deleteCondenser(data.id, jsonURl).subscribe(
        (res) => {
          const toastSuccessMesage = {
            severity: 'success',
            summary: `${data.name}`,
            key: 'delete',
          };
          this.toastService.showSuccess(toastSuccessMesage);
          this.getCondenserDataTable(jsonURl);
        },
        (error) => {
         this.deleteDataerror(data);
        }
      );
    });
  }
  deleteDataerror(data): void{
    const toastErrorMesage = {
      severity: 'error',
      summary: `${data.name}`,
      key: 'deleteError',
    };
    this.toastService.showError(toastErrorMesage);
  }

  duplicateData(duplicatedata): void {
    this.copyData = JSON.parse(JSON.stringify(duplicatedata));
    this.condensrService.getJSON().subscribe((url) => {
      this.condensrService.getCondenserDataById(url, this.copyData['id']).subscribe((data) => {
        data.data.Condenser.condenserName = data.data.Condenser.condenserName + '_copy';
        delete data.data.Condenser.id;

        const duplicateobj = {
          unitData: data.data.unitData,
          plantData: {},
          historianMap: data.data.historianMap,
          condenserName: data.data.Condenser.condenserName,
          facilityName: data.data.Condenser.facilityName,
          systemName: data.data.Condenser.systemName,
          tenantId: data.data.Condenser.tenantId,
          orgId: data.data.Condenser.tenantId,
          createdBy: data.data.Condenser.createdBy
        };

        console.log(duplicateobj);

        this.condensrService.duplicateCondenser(url, duplicateobj).subscribe(
          (res) => {
            const toastSuccessMesage = {
              severity: 'success',
              summary: `${data.data.Condenser.condenserName}`,
              key: 'save',
            };
            this.toastService.showSuccess(toastSuccessMesage);
            this.getCondenserDataTable(url);
          },
          (error) => {
            const toastErrorMesage = {
              severity: 'error',
              summary: `${data.name}`,
              key: 'saveError',
            };
            this.toastService.showError(toastErrorMesage);
          }
        );
      });
    });
  }

  viewData(d): void {
    console.log(d);
    const name = encodeURIComponent(d.name)
    this.router.navigate([`condenser/configurations/${name}`], { state: { cID: d.id } });
  }

  onCreate(): void {
    this.router.navigate(['condenser/create']);
  }
}
