import { OnInit, EventEmitter } from '@angular/core';
import { ConfirmationService, SortEvent } from 'primeng/api';
import * as i0 from "@angular/core";
export declare class DhrDataTableComponent implements OnInit {
    confirmDialog: ConfirmationService;
    cols: string[];
    tableData: any[];
    paginator: boolean;
    searchPlaceHolder: string;
    row: number;
    showCurrentPageReport: boolean;
    rowsPerPageOptions: any[];
    scrollable: boolean;
    scrollHeight: string;
    styleClass: string;
    sortField: string;
    sortOrder: number;
    globalFilterFields: any[];
    headertext: string;
    subHeadertext: string;
    contentText: string;
    actionbtn: boolean;
    showDeleteAtnBtn: boolean;
    showDuplicateAtnBtn: boolean;
    showViewAtnBtn: boolean;
    showDownloadAtnBtn: boolean;
    showShareAtnBtn: boolean;
    deleteEvent: EventEmitter<any>;
    duplicateEvent: EventEmitter<any>;
    viewEvent: EventEmitter<any>;
    downloadEvent: EventEmitter<any>;
    shareEvent: EventEmitter<any>;
    rs: any;
    constructor(confirmDialog: ConfirmationService);
    ngOnInit(): void;
    deleteRow(data: any): void;
    duplicateRow(data: any): void;
    viewRow(data: any): void;
    downloadRow(data: any): void;
    shareRow(data: any): void;
    showWrappedMenu(r: any, s: any): void;
    printFilteredUsers(e: any): void;
    customSort(event: SortEvent): void;
    static ɵfac: i0.ɵɵFactoryDef<DhrDataTableComponent, never>;
    static ɵcmp: i0.ɵɵComponentDefWithMeta<DhrDataTableComponent, "dhr-data-table", never, { "cols": "cols"; "tableData": "tableData"; "paginator": "paginator"; "searchPlaceHolder": "searchPlaceHolder"; "row": "row"; "showCurrentPageReport": "showCurrentPageReport"; "rowsPerPageOptions": "rowsPerPageOptions"; "scrollable": "scrollable"; "scrollHeight": "scrollHeight"; "styleClass": "styleClass"; "sortField": "sortField"; "sortOrder": "sortOrder"; "globalFilterFields": "globalFilterFields"; "headertext": "headertext"; "subHeadertext": "subHeadertext"; "contentText": "contentText"; "actionbtn": "actionbtn"; "showDeleteAtnBtn": "showDeleteAtnBtn"; "showDuplicateAtnBtn": "showDuplicateAtnBtn"; "showViewAtnBtn": "showViewAtnBtn"; "showDownloadAtnBtn": "showDownloadAtnBtn"; "showShareAtnBtn": "showShareAtnBtn"; }, { "deleteEvent": "deleteEvent"; "duplicateEvent": "duplicateEvent"; "viewEvent": "viewEvent"; "downloadEvent": "downloadEvent"; "shareEvent": "shareEvent"; }, never, never>;
}
