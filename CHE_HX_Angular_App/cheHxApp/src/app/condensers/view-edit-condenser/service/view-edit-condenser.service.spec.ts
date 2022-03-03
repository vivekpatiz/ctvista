import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';
import { ViewEditCondenserService } from './view-edit-condenser.service';

describe('ViewEditCondenserService', () => {
  let service: ViewEditCondenserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ViewEditCondenserService]
    });
    service = TestBed.inject(ViewEditCondenserService);
  });

  it('can load instance', () => {
    expect(service).toBeTruthy();
  });

  it('should be able to getJSON', () => {
    service.getJSON().subscribe((res) => {});
  });

  it('should be Update Condenser Data', () => {
    service.patchCondenserDataById({baseURL: 'http://localhost:5000', getCondenserByIsAPI: 'condenser'}, 'xyz', {}).subscribe(res => {});
  });

  it('should be able to get facility dropdown', () => {
    service.getFacilityDropdown({baseURL: 'http://localhost:5000', getFacilities: 'condenser'}).subscribe(res => {});
  });

  it('should be able to get user profile', () => {
    service.getUserProfile({baseURL: 'http://localhost:5000', getCondenserByIsAPI: 'condenser'}).subscribe(res => {});
  });

  it('should be able to get Condenser data', () => {
    service.getCondenserDataById({baseURL: 'http://localhost:5000', getCondenserByIsAPI: 'condenser'}, 'xyz').subscribe(res => {});
  });

  it('should be postCustomCalcData', () => {
    service.postCustomCalcData({baseURLCalc: 'http://localhost:5000', postCustomCalcAPI: 'condenser'}, {}).subscribe(res => {});
  });

  it('should be able to downloadHistorianExcel', () => {
    service.downloadHistorianExcel({baseReportURL: 'http://localhost:5000', downloadMappingExcel: 'condenser'}, {}).subscribe(res => {});
  });

  it('should be able to downloadFinalHistorianExcel', () => {
    service.downloadFinalHistorianExcel({baseReportURL: 'http://localhost:5000', downloadHistorianExcel: 'condenser'}, {})
    .subscribe(res => {});
  });

  it('should be able to setStatusFlag', () => {
    service.setStatusFlag('http://localhost:5000').subscribe(res => {});
  });
});
