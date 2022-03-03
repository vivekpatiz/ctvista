import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { CreateCondenserService } from './create-condenser.service';

describe('CreateCondenserService', () => {
    let service: CreateCondenserService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
        });
        service = TestBed.inject(CreateCondenserService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it('should be able to getJSON', () => {
      service.getJSON().subscribe(res => {});
    });

    it('should be Post Condenser Data', () => {
        service.postCondenserData({ baseURL: 'http://localhost:5000', postCondenserAPI: 'condenser' }, {}).subscribe(res => { });
    });
    it('should be postCustomCalcData', () => {
        service.postCustomCalcData({ baseURLCalc: 'http://localhost:5000', postCustomCalcAPI: 'condenser' }, {}).subscribe(res => { });
    });
    it('should be downloadHistorianExcel', () => {
        service.downloadHistorianExcel({ baseReportURL: 'http://localhost:5000', downloadMappingExcel: 'condenser' }, {})
            .subscribe(res => { });
    });
    it('should be downloadFinalHistorianExcel', () => {
        service.downloadFinalHistorianExcel({baseReportURL: 'http://localhost:5000', downloadHistorianExcel: 'condenser'}, {})
        .subscribe(res => {});
      });

    it('should be able to get facility dropdown', () => {
      service.getFacilityDropdown({getFacilities: 'http://localhost:5000'}).subscribe(res => {});
    });

    it('should be able to get setStatusFlag', () => {
        service.setStatusFlag( 'http://localhost:5000').subscribe(res => {});
      });
    it('should call getUserProfile', () => {
        const url =
        {
            getUserProfile: 'http://dummpy.url/dummy/url',
        };
        service.getUserProfile(url).subscribe((data) => {
        });
    });
    // describe('getJSON', () => {
    //     it('makes expected calls', () => {
    //         service.getJSON().subscribe(res => {
    //         });
    //     });
    // });
});
