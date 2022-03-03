import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ChartService {
  httpParams = new HttpParams();
  constructor(private httpClient: HttpClient) {}

  public getJSON(): Observable<any> {
    const condenser = environment.assetData;
    return this.httpClient.get(condenser);
  }

  getChart(urlJson, fD, tD, paramsObj): Observable<any> {
    if (fD || tD) {
      const url = urlJson.baseChartURL;
      const path = urlJson.getChartAPI;
      const params = this.httpParams
        .set(
          'fromDate',
          `${
            fD.getFullYear() +
            '/' +
            ('0' + (fD.getMonth() + 1)).slice(-2) +
            '/' +
            ('0' + fD.getDate()).slice(-2)
          }`
        )
        .set(
          'toDate',
          `${
            tD.getFullYear() +
            '/' +
            ('0' + (tD.getMonth() + 1)).slice(-2) +
            '/' +
            ('0' + tD.getDate()).slice(-2)
          }`
        )
        .set('tenantId', paramsObj.tenantId)
        .set('assetId', paramsObj.assetId )
        .set('assetParamName', paramsObj.assetParamName)
        .set('isKpi', paramsObj.isKpi)
        .set('fromLoad', paramsObj.fromLoad)
        .set('toLoad', paramsObj.toLoad);
      return this.httpClient.get(url.concat(path), { params });
    } else {
      const d1S = new Date();
      const d1E = new Date();
      const d1SConversion = new Date(d1S.setMonth(d1S.getMonth() - 5));
      const url = urlJson.baseChartURL;
      const path = urlJson.getChartAPI;
      const params = this.httpParams
        .set(
          'fromDate',
          `${
            d1SConversion.getFullYear() +
            '/' +
            ('0' + (d1SConversion.getMonth() + 1)).slice(-2) +
            '/' +
            ('0' + d1SConversion.getDate()).slice(-2)
          }`
        )
        .set(
          'toDate',
          `${
            d1E.getFullYear() +
            '/' +
            ('0' + (d1E.getMonth() + 1)).slice(-2) +
            '/' +
            ('0' + d1E.getDate()).slice(-2)
          }`
        )
        .set('tenantId', paramsObj.tenantId)
        .set('assetId', paramsObj.assetId )
        .set('assetParamName', paramsObj.assetParamName)
        .set('isKpi', paramsObj.isKpi)
        .set('fromLoad', paramsObj.fromLoad)
        .set('toLoad', paramsObj.toLoad);
      return this.httpClient.get(url.concat(path), { params });
    }
  }

  public getChinateamAccounts(): Observable<any> {
    const condenser = 'https://develop.ctvistaplus.com/service/accounts';
    return this.httpClient.get(condenser);
  }
  getchartKey(jsonUrl, id, isKpi): Observable<any> {
    const baseUrl = jsonUrl.baseURL;
    const path = jsonUrl.chartKey;
    const params = this.httpParams
    .set('isKpi', isKpi);
    return this.httpClient.get(baseUrl.concat(path).concat(id), { params });
  }
  getchartload(jsonUrl, id): Observable<any> {
    const baseUrl = jsonUrl.baseURL;
    const path = jsonUrl.condenserLoad;
    return this.httpClient.get(baseUrl.concat(path).concat(id));
  }
  getUOM(jsonUrl): Observable<any> {
    const baseUrl = jsonUrl.UOMBaseURL;
    const path = jsonUrl.UOMGetURL;
    return this.httpClient.get(baseUrl.concat(path));
  }
  getLogo(jsonUrl, aid): Observable<any> {
    const baseUrl = jsonUrl.getLogo;
    return this.httpClient.get(baseUrl.concat(aid));
  }
  postUpdatedYAxisLimits(jsonUrl, data): Observable<any> {
    const baseUrl = jsonUrl.baseChartURL;
    const path = jsonUrl.UpdatedYAxisLimits;
    return this.httpClient.post(baseUrl.concat(path), data);
  }

  getTroubleShootingGuide(jsonUrl): Observable<any> {
    let params = new HttpParams();
    params = params.append('filename', 'Condenser-Troubleshooting-Guide.pdf');
    const baseUrl = jsonUrl.baseReportURL;
    const path = jsonUrl.getTroubleShootingGuide;
    return this.httpClient.get(baseUrl.concat(path), { params: params, responseType: 'blob' });
  }

  getPopupContentForKpiOverview(jsonUrl, data): Observable<any>{
    let params = new HttpParams();
    params = params.append('asset_id', data.asset_id);
    params = params.append('type', data.type);
    params = params.append('asset_type', data.asset_type);
    params = params.append('tenant_id', data.tenant_id);
    const baseUrl = jsonUrl.baseReportURL;
    const path = jsonUrl.getPopupContentForKpiOverview;
    return this.httpClient.get(baseUrl.concat(path), {params: params});
  }

  getSequenecForKPI(data): any {
    const kpiChartResponse = data;
    const sequenceforKPI = [];
    const sequenceDetails = {};
    for (let variable in kpiChartResponse) {
      let details = kpiChartResponse[variable]
      if (details.name && details.name === 'bp_deviation_overall') {
        sequenceDetails['0'] = details;
      }
      else if (details.name === 'tr_deviation') {
        sequenceDetails['1'] = details;
      }
      else if (details.name === 'ttd_deviation') {
        sequenceDetails['2'] = details;
      }
      else if (details.name === 'production_deviation_overall') {
        sequenceDetails['3'] = details;
      }
      else if (details.name === 'mw_dollar_lost_per_day') {
        sequenceDetails['4'] = details;
      }
      else if (details.name === 'heat_rate_deviation_overall') {
        sequenceDetails['5'] = details;
      }
      else if (details.name === 'est_excess_fuel_burned_overall') {
        sequenceDetails['6'] = details;
      }
      else if (details.name === 'est_excess_fuel_dollar_overall') {
        sequenceDetails['7'] = details;
      }
      else if (details.name === 'est_excess_co2_emissions_overall') {
        sequenceDetails['8'] = details;
      }
    }
    for (let i = 0; i < 9; i++) {
      sequenceforKPI.push(sequenceDetails[i.toString()]);
    }
    return sequenceforKPI;
  }
}
