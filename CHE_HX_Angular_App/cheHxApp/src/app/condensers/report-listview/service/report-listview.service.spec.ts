import { TestBed } from '@angular/core/testing';
import { ReportListviewService } from './report-listview.service';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';

describe('ReportListviewService', () => {
  let service: ReportListviewService;
  let httpMock: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReportListviewService],
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(ReportListviewService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('be able to retrieve report list from the API  GET', () => {
    const dummyReport: any[] = [
      {
        createdAt: '2020-10-08T12:37:58.000+00:00',
        updatedAt: '2020-10-08T12:37:58.000+00:00',
        createdBy: 'Kevin',
        updatedBy: null,
        id: '940b544b-a120-4713-945a-3817cbb3b28a',
        minLoad: 22.23,
        maxLoad: 100,
        fromDate: '2020-10-06T00:00:00.000+00:00',
        toDate: '2020-10-10T00:00:00.000+00:00',
        assetId: '1231233',
        assetName: 'Condenser-1',
        reportName: 'Condenser-33_2020/10/10',
        reportJson: {
          minLoad: '22.23',
          maxLoad: '100.00',
          fromDate: '2020-10-06',
        },
        tenantId: 2,
        isActive: true,
      },
    ];
    service.getJSON().subscribe((jsonUrl) => {
      const url = jsonUrl.baseReportURL;
      const path = jsonUrl.getReportList;
      service.getReportList(url).subscribe((data) => {
        expect(data).not.toBeNull();
        const request = httpMock.expectOne(url.concat(path));
        expect(request.request.method).toBe('GET');
        request.flush(dummyReport);
      });
    });
  });

  it('should call get report', () => {
    const url = {
      baseReportURL: 'http://dummpy.url',
      getReportList: '/dummy/url',
    };
    service.getReportList(url).subscribe((data) => {
      expect(data).not.toBeNull();
    });
  });

  it('should call delete report by id', () => {
    const param = '8fec8813-b059-48ef-8415-09fa305bbe45';
    const url = {
      baseReportURL: 'http://dummpy.url',
      getReportList: '/dummy/url/',
      param
    };

    service.deleteReport('8fec8813-b059-48ef-8415-09fa305bbe45', url).subscribe((data) => {
      expect(data).not.toBeNull();
    });
  });
  it('should call getReportById', () => {
    const param = '8fec8813-b059-48ef-8415-09fa305bbe45';
    const url = {
      baseReportURL: 'http://dummpy.url',
      getReportById: '/dummy/url/',
    };

    service.getReportById(url, '8fec8813-b059-48ef-8415-09fa305bbe45').subscribe((data) => {
    });
  });
  it('should call downloadReportPost', () => {
    const url = {
      downloadReport: 'http://dummpy.url',
      reportUrl: '/dummy/url/',
    };

    service.downloadReportPost({id: '8fec8813-b059-48ef-8415-09fa305bbe45'}, url).subscribe((data) => {
    });
  });
  it('should be able downloadReport', () => {
    const url = {
      downloadReport: 'http://dummpy.url',
      reportUrl: '/dummy/url/',
    };

    const report = {
      id: 'id',
      mode: 'mode',
      state: 'state',
      format : 'format',
      printBackground : 'true',
      displayHeaderFooter : 'false',
      landscape : 'false',
      scale: '1',
    accountLogo: 'createdBy',
  createdAt: 'createdAt'

    };
    service.downloadReport(report, url);
  });

  it('should be able getReportList_v2', () => {
    const report = {
      name: 'name',
      id: 'id',
      size: 'size',
      page: 'page',
      sort : 'sort',
      order : 'order',
      fromeDte : 'fromeDte',
      toDate : 'toDate',
    systemName: 'systemName',
    facilityName : 'facilityName',
  createdBy: 'createdBy',
  createdAt: 'createdAt'

    };
    const url = {
      getReportList_v2: 'http://dummpy.url',
      baseReportURL: '/dummy/url/',
    };
    service.getReportList_v2(url, report);
  });
  it('should be able getReportList_v2', () => {
    const report = {
      name: 'name',
      id: 'id',
      size: 'size',
      page: 'page',
      sort : 'sort',
      order : 'order',
      fromeDte : 'fromeDte',
      toDate : 'toDate',
    systemName: 'systemName',

    };
    const url = {
      getReportList_v2: 'http://dummpy.url',
      baseReportURL: '/dummy/url/',
    };
    service.getReportList_v2(url, report);
  });
  it('should be able getReportList_v2', () => {
    const report = {
      name: 'name',
      id: 'id',
      size: 'size',
      page: 'page',
      sort : 'sort',
      order : 'order',
      fromeDte : 'fromeDte',
      toDate : 'toDate',
      facilityName: 'facilityName',

    };
    const url = {
      getReportList_v2: 'http://dummpy.url',
      baseReportURL: '/dummy/url/',
    };
    service.getReportList_v2(url, report);
  });
});
