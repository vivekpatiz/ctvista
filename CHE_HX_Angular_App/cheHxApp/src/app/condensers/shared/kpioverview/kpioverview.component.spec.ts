import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ChartService } from '../../kpi-dashboard/service/chart.service';
import { KPIOverviewComponent } from './kpioverview.component';

describe('KPIOverviewComponent', () => {
  let component: KPIOverviewComponent;
  let fixture: ComponentFixture<KPIOverviewComponent>;

  beforeEach(() => {
    const matDialogStub = () => ({
      open: (kpiOverviewModalComponent, object) => ({})
    });
    const chartServiceStub = () => ({
      getPopupContentForKpiOverview: (serviceURl, paramObject) => ({
        subscribe: f => f({
            data:
            {kpi_cofig: [{name: ''}, {name: ''}]}
        })
      })
    });
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [KPIOverviewComponent],
      providers: [
        { provide: MatDialog, useFactory: matDialogStub },
        { provide: ChartService, useFactory: chartServiceStub }
      ]
    });
    fixture = TestBed.createComponent(KPIOverviewComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });
  it('can load ngOnInit', () => {
    component.ngOnInit();
  });
  it('can load openModel', () => {
    component.openModel({}, {chartname: ''});
  });
  it('colorCodeForAvgValue', () => {
    const item = {
      chartLabel: 'TR Deviation (°F)',
      avgKpi: 2
    };
    component.colorCodeForAvgValue(item);
  });
  it('colorCodeForAvgValue', () => {
    const item = {
      chartLabel: 'TR Deviation (°F)',
      avgKpi: 4
    };
    component.colorCodeForAvgValue(item);
  });
  it('colorCodeForAvgValue', () => {
    const item = {
      chartLabel: 'TR Deviation (°F)',
      avgKpi: 6
    };
    component.colorCodeForAvgValue(item);
  });
  it('colorCodeForAvgValue', () => {
    const item = {
      chartLabel: 'BP Deviation Overall (inHga)',
      avgKpi: 0.6
    };
    component.colorCodeForAvgValue(item);
  });
  it('colorCodeForAvgValue', () => {
    const item = {
      chartLabel: 'BP Deviation Overall (inHga)',
      avgKpi: 2
    };
    component.colorCodeForAvgValue(item);
  });
  it('colorCodeForAvgValue', () => {
    const item = {
      chartLabel: 'BP Deviation Overall (inHga)',
      avgKpi: 0.2
    };
    component.colorCodeForAvgValue(item);
  });
});
