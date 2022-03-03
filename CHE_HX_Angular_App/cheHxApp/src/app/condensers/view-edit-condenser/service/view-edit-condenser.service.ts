import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ViewEditCondenserService {

  constructor(private httpClient: HttpClient) { }

  public getJSON(): Observable<any> {
    const condenser = environment.assetData;
    return this.httpClient.get(condenser);
  }

  getCondenserDataById(jsonUrl, id): Observable<any> {
    const baseUrl = jsonUrl.baseURL;
    const path = jsonUrl.getCondenserByIsAPI;
    return this.httpClient.get(baseUrl.concat(path).concat(id));
  }
  patchCondenserDataById(jsonUrl, id, body): Observable<any> {
    const baseUrl = jsonUrl.baseURL;
    const path = jsonUrl.patchCondenserAPI;
    return this.httpClient.put(baseUrl.concat(path).concat(id), body);
  }

    postCustomCalcData(jsonUrl, data): Observable<any> {
    const baseUrl = jsonUrl.baseURLCalc;
    const path = jsonUrl.postCustomCalcAPI;
    return this.httpClient.post(baseUrl.concat(path), data);
  }

  public getFacilityDropdown(jsonUrl): Observable<any>{
    const url = jsonUrl.getFacilities;
    // return this.httpClient.get('../../../../assets/facility.json');
    return this.httpClient.get(url);
  }

   public getUserProfile(jsonUrl): Observable<any>{
    const url = jsonUrl.getUserProfile;
    return this.httpClient.get(url);
  }

  downloadHistorianExcel(jsonUrl, body): Observable<any> {
    const baseUrl = jsonUrl.baseReportURL;
    const path = jsonUrl.downloadMappingExcel;
    return this.httpClient.post(baseUrl.concat(path), body, {responseType: 'blob'});
  }

  downloadFinalHistorianExcel(jsonUrl, body): Observable<any> {
    const baseUrl = jsonUrl.baseReportURL;
    const path = jsonUrl.downloadHistorianExcel;
    return this.httpClient.post(baseUrl.concat(path), body, {responseType: 'blob'});
  }

  setStatusFlag(url): Observable<any> {
    return this.httpClient.get(url);
  } 
}
