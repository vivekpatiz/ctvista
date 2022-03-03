import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CreateCondenserService {
  constructor(private httpClient: HttpClient) {}
  public getJSON(): Observable<any> {
    const condenser = environment.assetData;
    return this.httpClient.get(condenser);
  }

  postCondenserData(jsonUrl, data): Observable<any> {
    const baseUrl = jsonUrl.baseURL;
    const path = jsonUrl.postCondenserAPI;
    return this.httpClient.post(baseUrl.concat(path), data);
  }

  public getFacilityDropdown(jsonUrl): Observable<any> {
    const url = jsonUrl.getFacilities;
    //return this.httpClient.get('../../../../assets/facility.json');
    return this.httpClient.get(url);
  }


  postCustomCalcData(jsonUrl, data): Observable<any> {
    const baseUrl = jsonUrl.baseURLCalc;
    const path = jsonUrl.postCustomCalcAPI;
    return this.httpClient.post(baseUrl.concat(path), data);
  }


  public getUserProfile(jsonUrl): Observable<any> {
    const url = jsonUrl.getUserProfile;
    return this.httpClient.get(url);
  }

  downloadHistorianExcel(jsonUrl, body): Observable<any> {
    const baseUrl = jsonUrl.baseReportURL;
    const path = jsonUrl.downloadMappingExcel;
    return this.httpClient.post(baseUrl.concat(path), body, {
      responseType: 'blob',
    });
  }

  downloadFinalHistorianExcel(jsonUrl, body): Observable<any> {
    const baseUrl = jsonUrl.baseReportURL;
    const path = jsonUrl.downloadHistorianExcel;
    return this.httpClient.post(baseUrl.concat(path), body, {
      responseType: 'blob',
    });
  }

  setStatusFlag(url): Observable<any> {
    return this.httpClient.get(url);
  } 
}
