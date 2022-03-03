import { Injectable } from '@angular/core';
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';

const EXCEL_TYPE = 'charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';

@Injectable({
  providedIn: 'root'
})

export class DownloadToExcel {

  constructor() { }

  public exportAsExcelFile(json: any[], sheet2Json: any[], excelFileName: string): void {
            console.log('expor excel1 ', json);
        console.log('expor useracc ',sheet2Json);
  
    let transposeExcel = []; 
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);
    const worksheet2: XLSX.WorkSheet = XLSX.utils.json_to_sheet(sheet2Json);
    const range = XLSX.utils.decode_range(worksheet['!ref']);
    for(var C = range.s.c; C <= range.e.c; ++C) {
      var ta = new Array(range.e.r - range.s.r + 1);
      for(var R = range.s.r; R <= range.e.r; ++R) {
        var cell = worksheet[XLSX.utils.encode_cell({r:R, c:C})];
        ta[R - range.s.r] = cell.v;
      }
      transposeExcel.push(ta);
    }
    var ws = XLSX.utils.aoa_to_sheet(transposeExcel);
    const workbook: XLSX.WorkBook = { Sheets: { 'Sheet1': ws, 'Sheet2': worksheet2 }, SheetNames: ['Sheet1','Sheet2'] };
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array', cellStyles: true});
    this.saveAsExcelFile(excelBuffer, excelFileName);
  }

  private saveAsExcelFile(buffer: any, fileName: string): void {
    const data: Blob = new Blob([buffer], {
      type: EXCEL_TYPE
    });
    FileSaver.saveAs(data, fileName + EXCEL_EXTENSION);
  }

}
