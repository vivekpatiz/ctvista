import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UUID } from 'angular2-uuid';
import { Observable, of } from 'rxjs';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  public report: any = {};
  public matarialLayout = [];
  public previewLayout = [];

  constructor(private httpClient: HttpClient) { }
  addItem(layoutype, title, itemData): void {
    this.matarialLayout.push({
      id: UUID.UUID(),
      headerTitle: title,
      layoutType: layoutype,
      data : itemData
    });
    // console.log(this.matarialLayout);
  }
  deleteItem(id: string): void {
    const item = this.matarialLayout.find(d => d.id === id);
    this.matarialLayout.splice(this.matarialLayout.indexOf(item), 1);
    }
    updateItemdata(newdata , id): void{
      this.matarialLayout = this.matarialLayout.map(obj =>
        obj.id === id ? { ...obj, data: newdata } : obj
    );
    }
    emptyLayout(): void{
      this.matarialLayout = [];
    }
    public getReportData(): Observable<any> {
      const condenser = environment.reportData;
      return this.httpClient.get(condenser);
    }
    addReportData(obj): void{
      this.report = {
        name: obj.name,
        id: obj.id,
        cID: obj.condenserId,
        cName: obj.condensername,
        value : obj.value,
        highValue : obj.highValue,
        fromeDte : obj.fromeDte,
        toDate : obj.toDate,
      systemName: obj.systemName,
    facilityName : obj.facilityName,
    createdBy: obj.createdBy,
    createdAt: obj.createdAt

      };
    }

    postReportData(jsonUrl, data): Observable<any> {
      const baseUrl = jsonUrl.baseReportURL;
      const path = jsonUrl.createReport;
      return this.httpClient.post(baseUrl.concat(path), data);
    }
    updateReportData(jsonUrl, id, data): Observable<any> {
      const baseUrl = jsonUrl.baseReportURL;
      const path = jsonUrl.createReport;
      return this.httpClient.put(baseUrl.concat(path).concat(id), data);
    }

}
