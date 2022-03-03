import { TestBed } from '@angular/core/testing';
import { CondenserListviewService } from './condenser-listview.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
describe('CondenserListviewService', () => {
  let service: CondenserListviewService;
  let httpMock: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({ providers: [CondenserListviewService], imports: [HttpClientTestingModule], });
    service = TestBed.inject(CondenserListviewService);
    httpMock = TestBed.inject(HttpTestingController);
  });
  it('can load instance', () => {
    expect(service).toBeTruthy();
  });
  it('be able to retrieve condenser list from the API  GET', () => {
    const dummyCondenser: any[] = [{
      accountName: 'Tenaska',
      createdAt: '2020-08-01 11:55:45',
      createdBy: 'Sandipan',
      facilityName: 'Facility-1',
      id: '046cbdc5-ec4a-11ea-bcfc-06adf1264e4f',
      name: 'PB1658',
      status: true,
      systemName: 'Tenaska',
      updatedAt: '2020-08-01 11:55:50',
      updatedBy: 'Sandipan'
      }
  ];
    service.getJSON().subscribe((url) => {
    const baseUrl = url.baseURL;
    const path = url.getAPICondenser;
    service.getCondenser(url).subscribe((data) => {
      expect(data).not.toBeNull();
      const request = httpMock.expectOne(baseUrl.concat(path));
      expect(request.request.method).toBe('GET');
      request.flush(dummyCondenser);
    });
  });
    });

  it('should call get condenser', () => {
      const url = {
        baseURL: 'http://dummpy.url',
        getAPICondenser : '/dummy/url'
      };
      service.getCondenser(url).subscribe((data) => {
      expect(data).not.toBeNull();
    });
    });
  it('should call delete condenser', () => {
      const url = {
        baseURL: 'http://dummpy.url',
        getAPICondenser : '/dummy/url'
      };
      service.deleteCondenser('params', url).subscribe((data) => {
        expect(data).not.toBeNull();
      });
    });
  it('should be able to duplicate condenser', () => {
      const url = {
        baseURL: 'http://dummpy.url',
        postCondenserAPI : '/dummy/url'
      };
      service.duplicateCondenser(url, {}).subscribe((data) => {
      expect(data).not.toBeNull();
    });
  });
  it('should be able to get condenser by Id', () => {
      const url = {
        baseURL: 'http://dummpy.url',
        getCondenserByIsAPI : '/dummy/url'
      };
      service.getCondenserDataById(url, 'abc').subscribe((data) => {
      expect(data).not.toBeNull();
    });
  });

  it('should call get condenser by id', () => {
      const url = {
        baseURL: 'http://dummpy.url',
        getCondenserByIsAPI : '/dummy/url'
      };
      service.getCondenserDataById(url, '8fec8813-b059-48ef-8415-09fa305bbe45').subscribe((data) => {
      expect(data).not.toBeNull();
    });
    });

  it('should call duplicateCondenser', () => {
      const url = {
        baseURL: 'http://dummpy.url',
        getCondenserByIsAPI : '/dummy/url'
      };
      const data = '';
      service.duplicateCondenser(url, data).subscribe((data1) => {
      expect(data).not.toBeNull();
    });
    });
  it('should call getCondenser_dd', () => {
      const url = {
        baseURL: 'http://dummpy.url',
        getAPICondenser : '/dummy/url'
      };
      const data = '';
      service.getCondenser_dd(url, data).subscribe((data1) => {
    });
    });
  it('should call getCondenser_v2', () => {
      const url = {
        baseURL: 'http://dummpy.url',
        getAPICondenser_v2 : '/dummy/url'
      };
      const data = {
        tenantId: 'tenantId',
        facilityName: 'facilityName',
        systemName: 'systemName',
        size: 'size',
        page: 'page',
        sort: 'sort',
        order: 'order'
      };
      service.getCondenser_v2(url, data).subscribe((data1) => {
    });
    });
  it('should call getCondenser_v2', () => {
      const url = {
        baseURL: 'http://dummpy.url',
        getAPICondenser_v2 : '/dummy/url'
      };
      const data = {
        tenantId: 'tenantId',
        facilityName: 'facilityName',
        size: 'size',
        page: 'page',
        sort: 'sort',
        order: 'order'
      };
      service.getCondenser_v2(url, data).subscribe((data1) => {
    });
    });
  it('should call getCondenser_v2', () => {
      const url = {
        baseURL: 'http://dummpy.url',
        getAPICondenser_v2 : '/dummy/url'
      };
      const data = {
        tenantId: 'tenantId',
        size: 'size',
        page: 'page',
        sort: 'sort',
        order: 'order'
      };
      service.getCondenser_v2(url, data).subscribe((data1) => {
    });
    });
});
