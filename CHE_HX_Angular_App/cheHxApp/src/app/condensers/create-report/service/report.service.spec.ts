import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';
import { ReportService } from './report.service';

describe('ReportService', () => {
  let service: ReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ReportService]
    });
    service = TestBed.get(ReportService);
  });

  it('can load instance', () => {
    expect(service).toBeTruthy();
  });

  it(`matarialLayout has default value`, () => {
    expect(service.matarialLayout).toEqual([]);
  });

  it('should be able to delete Item', () => {
    service.matarialLayout = [{id: 'xyz'}];
    service.deleteItem('abc');
  });
  it('should be able to add Item', () => {
    service.addItem('abc', 'xyz', {});
  });
  it('should be able updateItemData', () => {
    service.matarialLayout = [{id: 'xyz'}];
    service.updateItemdata({}, 'xyz');
  });
  it('should be able emptyLayout', () => {
    service.emptyLayout();
  });
  it('should be able getReportData', () => {
    service.getReportData();
  });
  it('should be able addReportData', () => {
    const report = {
      name: 'name',
      id: 'id',
      condenserId: 'condenserId',
      cName: 'condensername',
      value : 'value',
      highValue : 'highValue',
      fromeDte : 'fromeDte',
      toDate : 'toDate',
    systemName: 'systemName',
    facilityName : 'facilityName',
  createdBy: 'createdBy',
  createdAt: 'createdAt'

    };
    service.addReportData(report);
  });
  it('should be able to postReportData', () => {
    const url = {
      baseReportURL: 'http://dummpy.url',
      createReport : '/dummy/url'
    };
    service.postReportData(url, {}).subscribe(res => {});
  });
  it('should be able to updateReportData', () => {
    const url = {
      baseReportURL: 'http://dummpy.url',
      createReport : '/dummy/url'
    };
    service.updateReportData(url, '', {}).subscribe(res => {});
  });
});
