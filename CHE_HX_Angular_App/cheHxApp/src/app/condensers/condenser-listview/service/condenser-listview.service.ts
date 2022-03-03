import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from './../../../../environments/environment';
// import { CONDENSERAPI } from '../../../../assets/api-urls';

@Injectable({
  providedIn: 'root',
})
export class CondenserListviewService {
  constructor(private httpClient: HttpClient) {}

  public getJSON(): Observable<any> {
    const condenser = environment.assetData;
    return this.httpClient.get(condenser);
  }

  getCondenser(jsonUrl): Observable<any> {
    const baseUrl = jsonUrl.baseURL;
    const path = jsonUrl.getAPICondenser;
    const tenant = '2';
    return this.httpClient.get(baseUrl.concat(path).concat(tenant).concat('/condenser'));
  }


  getCondenser_dd(jsonUrl,tenantId): Observable<any> {
    const baseUrl = jsonUrl.baseURL;
    const path = jsonUrl.getAPICondenser;
    const tenant = tenantId;
    return this.httpClient.get(baseUrl.concat(path).concat(tenant).concat('/condenser'));
  }

  getCondenser_v2( jsonUrl, paramsobj ): Observable<any>{
    const baseURL = jsonUrl.baseURL;
    const path = jsonUrl.getAPICondenser_v2;
    let parems;
    // .concat(paramsobj.id)
     const tenant = paramsobj.tenantId;
    //const tenant = 1207;
    if (paramsobj.facilityName && paramsobj.systemName ){
       parems = `?size=${paramsobj.size}&page=${paramsobj.page}&sort=${paramsobj.sort}&order=${paramsobj.order}&facilityName=${encodeURIComponent(paramsobj.facilityName)}&systemName=${encodeURIComponent(paramsobj.systemName)}`;
    }
    else if(paramsobj.facilityName && !paramsobj.systemName ){
       parems = `?size=${paramsobj.size}&page=${paramsobj.page}&sort=${paramsobj.sort}&order=${paramsobj.order}&facilityName=${encodeURIComponent(paramsobj.facilityName)}`;
    }
   else{
     parems = `?size=${paramsobj.size}&page=${paramsobj.page}&sort=${paramsobj.sort}&order=${paramsobj.order}`;
   }
    return this.httpClient.get(baseURL.concat(path).concat(tenant).concat('/condenser').concat(parems));
  }

  deleteCondenser(params , jsonUrl): Observable<any>{
    const baseURL = jsonUrl.baseURL;
    const path = jsonUrl.deleteAPICondenser;
    return this.httpClient.delete(baseURL.concat(path).concat(params));
  }
  duplicateCondenser(jsonUrl, data): Observable<any> {
    const baseUrl = jsonUrl.baseURL;
    const path = jsonUrl.postCondenserAPI;
    return this.httpClient.post(baseUrl.concat(path), data);
  }
  getCondenserDataById(jsonUrl, id): Observable<any> {
    const baseUrl = jsonUrl.baseURL;
    const path = jsonUrl.getCondenserByIsAPI;
    return this.httpClient.get(baseUrl.concat(path).concat(id));
  }
}
