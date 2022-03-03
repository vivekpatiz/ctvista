import { ComponentFixture, TestBed, fakeAsync } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { DhrDataTableModule } from 'dhr-data-table';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';
import { CondenserListviewComponent } from './condenser-listview.component';
import { RouterTestingModule } from '@angular/router/testing';
import { MessageService } from 'primeng/api';
import { of, Observable } from 'rxjs';
import { TableModule } from 'primeng';
import { MatCardModule } from '@angular/material/card';
import { CondenserListviewService } from './../service/condenser-listview.service';
import { ToastMessgaeService } from './../../shared/toast-service/service/toast-messgae.service';

describe('CondenserListviewComponent', () => {
  let component: CondenserListviewComponent;
  let fixture: ComponentFixture<CondenserListviewComponent>;
  beforeEach(() => {
    const routerStub = () => ({ navigate: (array, object) => ({}) });
    const CondenserListviewServiceStub = () => ({
      getCondenser: (data) => of(
        {
          data: [
                  {
                    status: 'Complete',
                    updatedAt: '25/10/2020'
                  }
          ]
        }
      ),
      getCondenser_v2: (params, paramsobj) => ({ subscribe: f => f({
        data: {
          content: [
            {
              id: '830a974b-fdec-4f4c-aeec-8bd51f3e2975',
              createdAt: '2021-02-09 14:28:56',
              updatedAt: '2021-02-09 15:54:52',
              createdBy: 'DHRD',
              updatedBy: 'DHRD',
              name: 'Excel_Template_Update',
              status: true,
              facilityName: 'Demo Facility',
              accountName: 'Excel_Template_Update',
              systemName: 'Paint Spray Booth~Excel_Template_Update'
            },
            {
              id: 'e7335441-9bfb-4064-bafa-6a9c32a74022',
              createdAt: '2021-02-06 06:50:46',
              updatedAt: '2021-02-06 06:52:02',
              createdBy: 'DHRD',
              updatedBy: 'DHRD',
              name: 'Kiamichi Block 2-slashdate',
              status: true,
              facilityName: 'Demo Facility',
              accountName: 'Kiamichi Block 2-slashdate',
              systemName: 'Paint Spray Booth~Kiamichi Block 2-slashdate'
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
          totalPages: 1,
          totalElements: 61,
          last: true,
          first: true,
          sort: {
            sorted: true,
            unsorted: false,
            empty: false
          },
          numberOfElements: 61,
          size: 100,
          number: 0,
          empty: false
        }
      }) }),
      get_storage: (data) => of({}),
      getJSON: () => of('http://localhost:4200'),
      duplicateCondenser: (url, data) => of({}),
      getCondenserDataById: (url, id) => of({
        data: {
          Condenser: {
            condenserName: 'xyz',
            id: 'abc',
            facilityName: 'abc',
            systemName: 'abc',
            tenantId: 'abc',
            orgId: 'abc',
            createdBy: 'abc'
          },
          unitData: 'a',
          plantData: 'b',
          historianMap: 'c'
        }
      }),
      deleteCondenser: (url, id) => of({})
    });

    const toastMessageServiceStub = () => ({
      showSuccess: (message) => {},
      showError: (message) => {}
    });
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, MatCardModule , DhrDataTableModule , TableModule , RouterTestingModule],
      // schemas: [NO_ERRORS_SCHEMA],
      declarations: [CondenserListviewComponent],
      providers: [
                  MessageService,
                  {
                    provide: CondenserListviewService, useFactory: CondenserListviewServiceStub,
                  },
                  {
                    provide: ToastMessgaeService, useFactory: toastMessageServiceStub
                  }
                ]
    });
    fixture = TestBed.createComponent(CondenserListviewComponent);
    component = fixture.componentInstance;
  });
  it('can load instance', () => {
    expect(component).toBeTruthy();
  });
  it(`title has default value`, () => {
    expect(component.title).toEqual(`CTvista`);
  });
  it(`cols has default value`, () => {
    expect(component.cols).toEqual([]);
  });
  it(`paginator has default value`, () => {
    expect(component.paginator).toEqual(true);
  });
  it(`row has default value`, () => {
    expect(component.row).toEqual(10);
  });
  it(`showCurrentPageReport has default value`, () => {
    expect(component.showCurrentPageReport).toEqual(true);
  });
  it(`rowsPerPageOptions has default value`, () => {
    expect(component.rowsPerPageOptions).toEqual([10, 25, 50]);
  });
  it(`scrollable has default value`, () => {
    expect(component.scrollable).toEqual(true);
  });
  it(`scrollHeight has default value`, () => {
    expect(component.scrollHeight).toEqual(`400px`);
  });
  it(`styleClass has default value`, () => {
    expect(component.styleClass).toEqual(`p-datatable-striped`);
  });
  it(`sortField has default value`, () => {
    expect(component.sortField).toEqual(`updatedAt`);
  });
  it(`globalFilterFields has default value`, () => {
    expect(component.globalFilterFields).toEqual([
      `name`
    ]);
  });
  it(`headertext has default value`, () => {
    component.headertext = 'Condenser Report';
    expect(component.headertext).toEqual(`Condenser Report`);
  });
  it(`actionbtn has default value`, () => {
    expect(component.actionbtn).toEqual(true);
  });
  it(`showDeleteAtnBtn has default value`, () => {
    expect(component.showDeleteAtnBtn).toEqual(true);
  });
  it(`showDuplicateAtnBtn has default value`, () => {
    expect(component.showDuplicateAtnBtn).toEqual(true);
  });
  it(`showViewAtnBtn has default value`, () => {
    expect(component.showViewAtnBtn).toEqual(true);
  });
  it(`showDownloadAtnBtn has default value`, () => {
    expect(component.showDownloadAtnBtn).toEqual(false);
  });
  it(`showShareAtnBtn has default value`, () => {
    expect(component.showShareAtnBtn).toEqual(false);
  });
  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      spyOn(component, 'ngOnInit');
      component.ngOnInit();
      expect(component.getCondenserDataTable).toBeTruthy();
      component.ngAfterViewInit();
    });
  });
  describe('ngAfterViewInit', () => {
    it('makes expected calls', () => {
      spyOn(component, 'ngAfterViewInit');
      component.ngAfterViewInit();
      fixture.whenStable().then(() => {
        expect(component.ngAfterViewInit).toBeTruthy();
      });
    });
  });
  it('should call duplicateData method with value', () => {
    const data = {
      accountName: 'Tenaska',
      createdAt: '2020-09-03 12:01:45',
      createdBy: 'Sandipan',
      facilityName: 'Facility-1',
      id: '7b485d9e-edb2-11ea-bcfc-06adf1264e4f',
      name: 'PB8521_Copy',
      status: 'Complete',
      systemName: 'Tenaska',
      updatedAt: '9/3/2020 12:01 PM',
      updatedBy: 'Sandipan'
    };
    component.duplicateData(data);
    fixture.detectChanges();
    spyOn(component, 'duplicateData');
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.duplicateData).toHaveBeenCalled();
    });
  });
  it('should call viewData method with value', () => {
    const id = '7b485d9e-edb2-11ea-bcfc-06adf1264e4f';

    component.viewData(id);
    fixture.detectChanges();
    spyOn(component, 'viewData');
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.viewData).toHaveBeenCalled();
    });
  });
  it('should call and changed route onCreate method', () => {
    component.onCreate();
  });
  // }));
  it('should call getCondenserDataTable method', () => {
    const params = [{
      baseURL: 'someurl',
      getAPICondenser: 'api'
    }];
    component.getCondenserDataTable(params[0]);
    fixture.detectChanges();
    spyOn(component, 'getCondenserDataTable');
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.getCondenserDataTable).toHaveBeenCalled();
    });
  });
  it('makes deleData call', () => {
    component.deleData({name: 'xyz', id: 'abc'});
  });
  it('makes setSystem call', () => {
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
    component.setSystem(account);
  });
  it('makes getCondenserDataError call', () => {
    component.getCondenserDataError({message: 'xyz'});
  });
  it('makes deleteDataerror call', () => {
    component.deleteDataerror({name: 'xyz'});
  });
});
