import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReportListviewService {

  constructor(private httpClient: HttpClient) { }

  public getJSON(): Observable<any> {
    const condenser = environment.assetData;
    return this.httpClient.get(condenser);
  }
  public getReportList(jsonUrl): Observable<any>{
    const url = jsonUrl.baseReportURL;
    const path = jsonUrl.getReportList;
    return this.httpClient.get(url.concat(path));
   // return this.httpClient.get('../../../../assets/reportList.json');
    }

   public getReportList_v2( jsonUrl, paramsobj ): Observable<any>{
      const baseURL = jsonUrl.baseReportURL;
      const path = jsonUrl.getReportList_v2;
      let parems;
      // .concat(paramsobj.id)
      const tenant = paramsobj.tenantId;
      if (paramsobj.facilityName && paramsobj.systemName ){
         parems = `?size=${paramsobj.size}&page=${paramsobj.page}&sort=${paramsobj.sort}&order=${paramsobj.order}&facilityName=${encodeURIComponent(paramsobj.facilityName)}&systemName=${encodeURIComponent(paramsobj.systemName)}`;
      }
     else if (paramsobj.facilityName && !paramsobj.systemName ){
         parems = `?size=${paramsobj.size}&page=${paramsobj.page}&sort=${paramsobj.sort}&order=${paramsobj.order}&facilityName=${encodeURIComponent(paramsobj.facilityName)}`;
      }
     else{
       parems = `?size=${paramsobj.size}&page=${paramsobj.page}&sort=${paramsobj.sort}&order=${paramsobj.order}`;
     }
      return this.httpClient.get(baseURL.concat(path).concat(tenant).concat(parems));
    }

    downloadReport(paramsobj , jsonUrl): Observable<any>{
      const baseURL = jsonUrl.downloadReport;
      const path = `${jsonUrl.reportUrl}?id=${paramsobj.id}&mode=${paramsobj.mode}&state=${paramsobj.state}`;
      // .concat(paramsobj.id)
      const parems = `?format=${paramsobj.format}&url=${path}&printBackground=${paramsobj.printBackground}&displayHeaderFooter=${paramsobj.displayHeaderFooter}&landscape=${paramsobj.landscape}&scale=${paramsobj.scale}&accountLogo=${encodeURIComponent(paramsobj.accountLogo)}`;
      return this.httpClient.get(baseURL.concat(parems), { responseType: 'blob' });
    }

   downloadReportPost(data, jsonUrl): Observable<any> {
      const baseUrl = jsonUrl.downloadReport;
       const path = `${jsonUrl.reportUrl}?id=${data.id}`;
       data.url = path;
      return this.httpClient.post(baseUrl, data,{
    responseType: "blob"
  });
    }

    deleteReport(params , jsonUrl): Observable<any>{
      const baseURL = jsonUrl.baseReportURL;
      const path = jsonUrl.deleteReport;
      return this.httpClient.delete(baseURL.concat(path).concat(params));
    }
    public getReportById(jsonUrl, params ): Observable<any>{
      const url = jsonUrl.baseReportURL;
      const path = jsonUrl.getReportById;
      return this.httpClient.get(url.concat(path).concat(params));
     // return this.httpClient.get('../../../../assets/reportList.json');
      }
      // ?format=A4&url=https://material.angular.io/components/table/overview&printBackground=true&displayHeaderFooter=true&landscape=false&scale=0.4
}
