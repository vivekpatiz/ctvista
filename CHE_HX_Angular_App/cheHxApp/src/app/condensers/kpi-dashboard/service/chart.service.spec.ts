import { TestBed } from '@angular/core/testing';

import { ChartService } from './chart.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ChartService', () => {
  let service: ChartService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(ChartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  it('should call getJSOn method',()=>{
    service.getJSON().subscribe((url)=>{
      expect(url).not.toBeNull()
    });
  });
  it('should call get chart method',()=>{
    service.getChart({baseChartURL : 'http://localhost:5000', getChartAPI: 'chartAPI'}, new Date('25/10/2020'), new Date('30/10/2020'),
     {
        tenantId : 'a',
        assetId : 'b',
        assetParamName: 'c',
        isKpi: 'd',
        fromLoad: '20/202/2020',
        toLoad: '25/20/2020'
      }).subscribe(res => {});
    });
  it('should be able to get china team account', () => {
    service.getChinateamAccounts().subscribe(res => {});
  });
  it('should be able to call getUOM', () => {
    service.getUOM({UOMBaseURL: 'http://localhost:5000', UOMGetURL: 'getUOM'}).subscribe(res => {});
  });
  it('should be able to call postUpdatedYAxisLimits', () => {
    service.postUpdatedYAxisLimits({baseChartURL: 'http://localhost:5000', UpdatedYAxisLimits: 'postUpdatedYAxisLimits'}, {
      tenantid: 1207,
        assetId: 23,
        minLoadRange: 3,
        maxLoadRange: 10,
        fromdate : '20/202/2020',
        todate: '25/20/2020',
        data : [{name: "air_enthalpy", min: 45, max: 66.06, assetParamName: "882c6b4f-1874-45fe-b18b-810cd0bb0be1"}]
    }).subscribe(res => {});
  });
  it('should be able to call getTroubleShootingGuide', () => {
    service.getTroubleShootingGuide({baseReportURL: 'http://34.216.125.13:8083/', getTroubleShootingGuide: 'getTroubleShootingGuide'}).subscribe(res => {});
  });
  it('should be able to call getPopupContentForKpiOverview', () => {
    service.getPopupContentForKpiOverview({baseReportURL: 'http://34.216.125.13:8083/', getPopupContentForKpiOverview: 'getPopupContentForKpiOverview'},{
      asset_id: '6ddb6a59-da6d-4066-9185-c91c95f61d37',
      type: 'kpi',
      asset_type: 'condenser',
      tenant_id: 1207,
    }).subscribe(res => {});
  });
  it('should be able to call getSequenecForKPI', () => {
    let inputdata = [
      {
        avgKpi: 2.83,
        data : [
          {
            date: "2020-11-08T00:00:00.000+00:00",
            load: 0,
            value: 13.590860000000001,
          },
          {
            date: "2020-12-11T06:45:00.000+00:00", 
            load: 84.39, 
            value: -1.0565653333333334
          },
          {
            date: "2020-12-11T07:15:00.000+00:00", 
            load: 97.79, 
            value: -1.3965653333333332
          }
      ],
        display_name: "BP Deviation Overall (inHga)",
        max: 13.84,
        min: -1.56,
        name: "bp_deviation_overall"
      },
      {
        avgKpi: -2574.11,
        data : [
          {
            date: "2020-11-08T00:00:00.000+00:00",
            load: 0,
            value: 13.590860000000001,
          },
          {
            date: "2020-12-11T06:45:00.000+00:00", 
            load: 84.39, 
            value: -1.0565653333333334
          },
          {
            date: "2020-12-11T07:15:00.000+00:00", 
            load: 97.79, 
            value: -1.3965653333333332
          }
      ],
        display_name: "Est. Excess CO2 Emissions  Overall (lbs/hr)",
        max: 2353,
        min: -5833,
        name: "est_excess_co2_emissions_overall"
      },
      {
        avgKpi: -13413723.81,
        data : [
          {
            date: "2020-11-08T00:00:00.000+00:00",
            load: 0,
            value: 13.590860000000001,
          },
          {
            date: "2020-12-11T06:45:00.000+00:00", 
            load: 84.39, 
            value: -1.0565653333333334
          },
          {
            date: "2020-12-11T07:15:00.000+00:00", 
            load: 97.79, 
            value: -1.3965653333333332
          }
      ],
        display_name: "Est. Excess Fuel Burned Overall (btu/hr)",
        max: 12567673,
        min: -31691148,
        name: "est_excess_fuel_burned_overall"
      },
      {
        avgKpi: -804.82,
        data : [
          {
            date: "2020-11-08T00:00:00.000+00:00",
            load: 0,
            value: 13.590860000000001,
          },
          {
            date: "2020-12-11T06:45:00.000+00:00", 
            load: 84.39, 
            value: -1.0565653333333334
          },
          {
            date: "2020-12-11T07:15:00.000+00:00", 
            load: 97.79, 
            value: -1.3965653333333332
          }
      ],
        display_name: "Est. Excess Fuel $$$ Overall ($/day)",
        max: 754.06,
        min: -1901.47,
        name: "est_excess_fuel_dollar_overall"
      },
      {
        avgKpi: 198.34,
        data : [
          {
            date: "2020-11-08T00:00:00.000+00:00",
            load: 0,
            value: 13.590860000000001,
          },
          {
            date: "2020-12-11T06:45:00.000+00:00", 
            load: 84.39, 
            value: -1.0565653333333334
          },
          {
            date: "2020-12-11T07:15:00.000+00:00", 
            load: 97.79, 
            value: -1.3965653333333332
          }
      ],
        display_name: "Heat Rate Deviation Overall (btu/kWhr)",
        max: 969.13,
        min: -109.28,
        name: "heat_rate_deviation_overall"
      },
      {
        avgKpi: -4245.84,
        data : [
          {
            date: "2020-11-08T00:00:00.000+00:00",
            load: 0,
            value: 13.590860000000001,
          },
          {
            date: "2020-12-11T06:45:00.000+00:00", 
            load: 84.39, 
            value: -1.0565653333333334
          },
          {
            date: "2020-12-11T07:15:00.000+00:00", 
            load: 97.79, 
            value: -1.3965653333333332
          }
      ],
        display_name: "MW $$$ Lost/Day",
        max: 3978.04,
        min: -10031.2,
        name: "mw_dollar_lost_per_day"
      },
      {
        avgKpi: -3.93,
        data : [
          {
            date: "2020-11-08T00:00:00.000+00:00",
            load: 0,
            value: 13.590860000000001,
          },
          {
            date: "2020-12-11T06:45:00.000+00:00", 
            load: 84.39, 
            value: -1.0565653333333334
          },
          {
            date: "2020-12-11T07:15:00.000+00:00", 
            load: 97.79, 
            value: -1.3965653333333332
          }
      ],
        display_name: "Production Deviation Overall (MWhrs)",
        max: 3.68,
        min: -9.29,
        name: "production_deviation_overall"
      },
      {
        avgKpi: 2.82,
        data : [
          {
            date: "2020-11-08T00:00:00.000+00:00",
            load: 0,
            value: 13.590860000000001,
          },
          {
            date: "2020-12-11T06:45:00.000+00:00", 
            load: 84.39, 
            value: -1.0565653333333334
          },
          {
            date: "2020-12-11T07:15:00.000+00:00", 
            load: 97.79, 
            value: -1.3965653333333332
          }
      ],
        display_name: "TR Deviation (°F)",
        max: 32.03,
        min: -3.84,
        name: "tr_deviation"
      },
      {
        avgKpi: 6.21,
        data : [
          {
            date: "2020-11-08T00:00:00.000+00:00",
            load: 0,
            value: 13.590860000000001,
          },
          {
            date: "2020-12-11T06:45:00.000+00:00", 
            load: 84.39, 
            value: -1.0565653333333334
          },
          {
            date: "2020-12-11T07:15:00.000+00:00", 
            load: 97.79, 
            value: -1.3965653333333332
          }
      ],
        display_name: "TTD Deviation (°F)",
        max: 123.74,
        min: -31.2,
        name: "ttd_deviation"
      }
    ]
    let outputData = service.getSequenecForKPI(inputdata)
  });
});