import { TestBed } from '@angular/core/testing';
import { DownloadToExcel } from './download-to-excel.service';

describe('DownloadToExcel', () => {
  let service: DownloadToExcel;

  beforeEach(() => {
    TestBed.configureTestingModule({ providers: [DownloadToExcel] });
    service = TestBed.inject(DownloadToExcel);
  });

  it('can load instance', () => {
    expect(service).toBeTruthy();
  });

  it('should be export excel', () => {
    service.exportAsExcelFile([{name: 'abhishek'}, {name: 'Ram'}], [{name: 'abhishek'}, {name: 'Ram'}], 'excel');
  });
});
