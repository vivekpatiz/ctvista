import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-y-axes-limit-modal',
  templateUrl: './y-axes-limit-modal.component.html',
  styleUrls: ['./y-axes-limit-modal.component.scss']
})
export class YAxisLimitModalComponent implements OnInit {
  disableYaxisButton = true;
  message;
  intialPageData = {};

  constructor(private dialogRef: MatDialogRef<YAxisLimitModalComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    // console.log(this.data);
    this.intialPageData = this.data.pagetext.map(item => { return {...item}})
  }  

  onYesResponse(): any {
    this.data.pagetext = this.intialPageData;
    this.dialogRef.close({form: undefined, intialData: this.intialPageData});
  }

  onNoResponse(myForm): any {
    this.dialogRef.close({form: myForm});
  }

  onValueChange(myForm) {
    let chartDetails = myForm.value;
    var output = [];
    let chartInfo = { name: '', min: '', max: '' };
    var x = [];
    for (let key in chartDetails) {
      if (chartInfo.name && chartInfo.name === (key.split('+'))[0]) {
        if (x[1] === 'min') {
          chartInfo.min = chartDetails[key];
        } else {
          chartInfo.max = chartDetails[key]
          output.push(chartInfo);
          chartInfo = { name: '', min: '', max: '' };
        }
      } else {
        if (!chartInfo.name) {
          let x = key.split('+')
          chartInfo.name = x[0];
          if (x[1] === 'min') {
            chartInfo.min = chartDetails[key];
          }
          else {
            chartInfo.max = chartDetails[key]
          }
        }
      }
    }

    for (let i = 0; i < output.length; i++) {
      if (output[i].min >= output[i].max) {
        this.disableYaxisButton = true;
        this.message = 'Minimum value cannot be greater than or equal to Maximum value';
        break;
      }
      if (output[i].min === null || output[i].max === null) {
        this.disableYaxisButton = true;
        this.message = 'Values Cannot Be Empty';
        break;
      }
      else {
        this.disableYaxisButton = false;
        this.message = '';
      }
    }
  }

}