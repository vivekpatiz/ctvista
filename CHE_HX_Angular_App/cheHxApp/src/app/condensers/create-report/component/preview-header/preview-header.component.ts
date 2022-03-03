import { Component, Input, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-preview-header',
  templateUrl: './preview-header.component.html',
  styleUrls: ['./preview-header.component.scss']
})
export class PreviewHeaderComponent implements OnInit {

  @Input() item: any;
  envURl: any;
  reportName = '';
  facalityName = '';
  systemName = '';
  condenserName = '';
  dateRange = '';
  loadRange = '';
  createdBy = '';
  constructor() { }

  ngOnInit(): void {
    this.envURl = environment;
    console.log(this.item);
    if (this.item){
      this.reportName = this.item.name;
      this.condenserName = this.item.cName;
      this.facalityName = this.item.facilityName;
      this.systemName = this.item.systemName;
      this.dateRange = `${
       ('0' + (this.item.fromeDte.getMonth() + 1)).slice(-2) +
        '/' +
        ('0' + this.item.fromeDte.getDate()).slice(-2) +
        '/' +  this.item.fromeDte.getFullYear()}-${
        ('0' + (this.item.toDate.getMonth() + 1)).slice(-2) +
        '/' +
        ('0' + this.item.toDate.getDate()).slice(-2) + '/' +
        this.item.toDate.getFullYear()
      }`;
      this.loadRange = `${this.item.value}%-${this.item.highValue}%`;
      if (this.item.createdBy)
      {
        this.createdBy = this.item.createdBy;
      }
    }
  }

}
