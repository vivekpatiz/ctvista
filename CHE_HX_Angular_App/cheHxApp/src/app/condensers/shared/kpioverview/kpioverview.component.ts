import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ChartService } from '../../kpi-dashboard/service/chart.service';
import { KpiOverviewModalComponent } from '../kpi-overview-modal/kpi-overview-modal.component';

@Component({
  selector: 'app-kpioverview',
  templateUrl: './kpioverview.component.html',
  styleUrls: ['./kpioverview.component.scss']
})
export class KPIOverviewComponent implements OnInit {
  confirmDlg: MatDialogRef<KpiOverviewModalComponent>;
  @Input() chartArray;
  @Input() fromDateToBeShownOnCards;
  @Input() toDateToBeShownOnCards;
  @Input() recentUserAccId;
  @Input() serviceURl;

  colorCodeValue;
  colorCodeValueForGroup;
  kpiOverviewPopUpContent;

  constructor( private chartService: ChartService,
               private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  openModel(e, item): any {
    const paramObject = {
      asset_id: '6ddb6a59-da6d-4066-9185-c91c95f61d37',
      type: 'kpi',
      asset_type: 'condenser',
      tenant_id: this.recentUserAccId
    };
    this.chartService.getPopupContentForKpiOverview(this.serviceURl, paramObject).subscribe(
      data => {
        let selectedKpichart;
        this.kpiOverviewPopUpContent = data.data.kpi_cofig;
        for (let i = 0; i < this.kpiOverviewPopUpContent.length; i++) {
          if (this.kpiOverviewPopUpContent[i].name === item.chartname) {
            selectedKpichart = this.kpiOverviewPopUpContent[i].troubleshooting;
          }
        }
        this.confirmDlg = this.dialog.open(KpiOverviewModalComponent, {
          disableClose: true,
          position: { top: '5%', left: '25%' },
          data: {
            pagetext: selectedKpichart,
            pageTitle: item.chartLabel,
          }
        });
      }
    );
  }
  colorCodeForAvgValue(item): void {
    if (item.chartLabel === 'TR Deviation (°F)' || item.chartLabel === 'TTD Deviation (°F)') {
      if (item.avgKpi <= 2) {
        this.colorCodeValue = {
          color: '#2BB14E'
        };
      }
      if (item.avgKpi > 2 && item.avgKpi < 5) {
        this.colorCodeValue = {
          color: '#EDAA00'
        };
      }
      if (item.avgKpi > 5) {
        this.colorCodeValue = {
          color: '#E04F4A'
        };
      }
      return this.colorCodeValue;
    }
    else {
      if (item.chartLabel === 'BP Deviation Overall (inHga)') {
        if (item.avgKpi <= 0.5) {
          this.colorCodeValueForGroup = {
            color: '#2BB14E'
          };
        }
        if (item.avgKpi > 0.5 && item.avgKpi < 1) {
          this.colorCodeValueForGroup = {
            color: '#EDAA00'
          };
        }
        if (item.avgKpi > 1) {
          this.colorCodeValueForGroup = {
            color: '#E04F4A'
          };
        }
      }
      return this.colorCodeValueForGroup;
    }
  }

}
