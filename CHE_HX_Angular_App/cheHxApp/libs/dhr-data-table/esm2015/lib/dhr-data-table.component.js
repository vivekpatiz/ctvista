import { Component, Input, Output, EventEmitter } from '@angular/core';
import * as i0 from "@angular/core";
import * as i1 from "primeng/api";
import * as i2 from "primeng/table";
import * as i3 from "primeng/confirmdialog";
import * as i4 from "primeng/inputtext";
import * as i5 from "@angular/common";
import * as i6 from "primeng/button";
function DhrDataTableComponent_ng_template_2_Template(rf, ctx) { if (rf & 1) {
    const _r5 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "div", 6);
    i0.ɵɵelementStart(1, "h4", 7);
    i0.ɵɵtext(2);
    i0.ɵɵelement(3, "br");
    i0.ɵɵelementStart(4, "h5", 8);
    i0.ɵɵtext(5);
    i0.ɵɵelementEnd();
    i0.ɵɵelementEnd();
    i0.ɵɵelementStart(6, "span", 9);
    i0.ɵɵelement(7, "i", 10);
    i0.ɵɵelementStart(8, "input", 11);
    i0.ɵɵlistener("input", function DhrDataTableComponent_ng_template_2_Template_input_input_8_listener($event) { i0.ɵɵrestoreView(_r5); i0.ɵɵnextContext(); const _r0 = i0.ɵɵreference(1); return _r0.filterGlobal($event.target.value, "contains"); });
    i0.ɵɵelementEnd();
    i0.ɵɵelementEnd();
    i0.ɵɵelementEnd();
} if (rf & 2) {
    const ctx_r1 = i0.ɵɵnextContext();
    i0.ɵɵadvance(2);
    i0.ɵɵtextInterpolate(ctx_r1.headertext);
    i0.ɵɵadvance(3);
    i0.ɵɵtextInterpolate(ctx_r1.subHeadertext);
    i0.ɵɵadvance(3);
    i0.ɵɵproperty("placeholder", ctx_r1.searchPlaceHolder);
} }
function DhrDataTableComponent_ng_template_3_th_1_p_sortIcon_2_Template(rf, ctx) { if (rf & 1) {
    i0.ɵɵelement(0, "p-sortIcon", 17);
} if (rf & 2) {
    const col_r9 = i0.ɵɵnextContext().$implicit;
    i0.ɵɵproperty("field", col_r9.field);
} }
function DhrDataTableComponent_ng_template_3_th_1_Template(rf, ctx) { if (rf & 1) {
    i0.ɵɵelementStart(0, "th", 15);
    i0.ɵɵtext(1);
    i0.ɵɵtemplate(2, DhrDataTableComponent_ng_template_3_th_1_p_sortIcon_2_Template, 1, 1, "p-sortIcon", 16);
    i0.ɵɵelementEnd();
} if (rf & 2) {
    const col_r9 = ctx.$implicit;
    i0.ɵɵpropertyInterpolate("pSortableColumn", col_r9.sortOpt ? col_r9.field : null);
    i0.ɵɵadvance(1);
    i0.ɵɵtextInterpolate1(" ", col_r9.header, " ");
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngIf", col_r9.sortOpt);
} }
function DhrDataTableComponent_ng_template_3_th_2_Template(rf, ctx) { if (rf & 1) {
    i0.ɵɵelement(0, "th", 18);
} }
function DhrDataTableComponent_ng_template_3_Template(rf, ctx) { if (rf & 1) {
    i0.ɵɵelementStart(0, "tr", 12);
    i0.ɵɵtemplate(1, DhrDataTableComponent_ng_template_3_th_1_Template, 3, 3, "th", 13);
    i0.ɵɵtemplate(2, DhrDataTableComponent_ng_template_3_th_2_Template, 1, 0, "th", 14);
    i0.ɵɵelementEnd();
} if (rf & 2) {
    const columns_r6 = ctx.$implicit;
    const ctx_r2 = i0.ɵɵnextContext();
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngForOf", columns_r6);
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngIf", ctx_r2.actionbtn);
} }
function DhrDataTableComponent_ng_template_4_td_1_Template(rf, ctx) { if (rf & 1) {
    i0.ɵɵelementStart(0, "td");
    i0.ɵɵtext(1);
    i0.ɵɵpipe(2, "date");
    i0.ɵɵelementEnd();
} if (rf & 2) {
    const col_r17 = ctx.$implicit;
    const rowData_r12 = i0.ɵɵnextContext().$implicit;
    i0.ɵɵadvance(1);
    i0.ɵɵtextInterpolate1(" ", col_r17.type != "date" ? rowData_r12[col_r17.field] : i0.ɵɵpipeBind2(2, 1, rowData_r12[col_r17.field] * 1000, "short"), " ");
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template(rf, ctx) { if (rf & 1) {
    const _r27 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "a", 28);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template_a_click_0_listener() { i0.ɵɵrestoreView(_r27); const rowData_r12 = i0.ɵɵnextContext(3).$implicit; const ctx_r25 = i0.ɵɵnextContext(); return ctx_r25.viewRow(rowData_r12); });
    i0.ɵɵelement(1, "i", 29);
    i0.ɵɵtext(2, "\u00A0\u00A0View");
    i0.ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template(rf, ctx) { if (rf & 1) {
    const _r30 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "a", 28);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template_a_click_0_listener() { i0.ɵɵrestoreView(_r30); const rowData_r12 = i0.ɵɵnextContext(3).$implicit; const ctx_r28 = i0.ɵɵnextContext(); return ctx_r28.duplicateRow(rowData_r12); });
    i0.ɵɵelement(1, "i", 30);
    i0.ɵɵtext(2, "\u00A0\u00A0Duplicate");
    i0.ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template(rf, ctx) { if (rf & 1) {
    const _r33 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "a", 31);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template_a_click_0_listener() { i0.ɵɵrestoreView(_r33); const rowData_r12 = i0.ɵɵnextContext(3).$implicit; const ctx_r31 = i0.ɵɵnextContext(); return ctx_r31.deleteRow(rowData_r12); });
    i0.ɵɵelement(1, "i", 32);
    i0.ɵɵtext(2, "\u00A0\u00A0Delete");
    i0.ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template(rf, ctx) { if (rf & 1) {
    const _r36 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "a", 33);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template_a_click_0_listener() { i0.ɵɵrestoreView(_r36); const rowData_r12 = i0.ɵɵnextContext(3).$implicit; const ctx_r34 = i0.ɵɵnextContext(); return ctx_r34.downloadRow(rowData_r12); });
    i0.ɵɵelement(1, "i", 34);
    i0.ɵɵtext(2, "\u00A0\u00A0Download");
    i0.ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template(rf, ctx) { if (rf & 1) {
    const _r39 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "a", 33);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template_a_click_0_listener() { i0.ɵɵrestoreView(_r39); const rowData_r12 = i0.ɵɵnextContext(3).$implicit; const ctx_r37 = i0.ɵɵnextContext(); return ctx_r37.shareRow(rowData_r12); });
    i0.ɵɵelement(1, "i", 35);
    i0.ɵɵtext(2, "\u00A0\u00A0Share");
    i0.ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_Template(rf, ctx) { if (rf & 1) {
    i0.ɵɵelementStart(0, "div", 24);
    i0.ɵɵtemplate(1, DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template, 3, 0, "a", 25);
    i0.ɵɵtemplate(2, DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template, 3, 0, "a", 25);
    i0.ɵɵtemplate(3, DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template, 3, 0, "a", 26);
    i0.ɵɵtemplate(4, DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template, 3, 0, "a", 27);
    i0.ɵɵtemplate(5, DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template, 3, 0, "a", 27);
    i0.ɵɵelementEnd();
} if (rf & 2) {
    const ctx_r19 = i0.ɵɵnextContext(3);
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngIf", ctx_r19.showViewAtnBtn);
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngIf", ctx_r19.showDuplicateAtnBtn);
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngIf", ctx_r19.showDeleteAtnBtn);
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngIf", ctx_r19.showDownloadAtnBtn);
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngIf", ctx_r19.showShareAtnBtn);
} }
function DhrDataTableComponent_ng_template_4_td_2_Template(rf, ctx) { if (rf & 1) {
    const _r42 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "td", 18);
    i0.ɵɵelementStart(1, "button", 21);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_Template_button_click_1_listener() { i0.ɵɵrestoreView(_r42); const ctx_r41 = i0.ɵɵnextContext(); const rowData_r12 = ctx_r41.$implicit; const ri_r13 = ctx_r41.rowIndex; const ctx_r40 = i0.ɵɵnextContext(); return ctx_r40.showWrappedMenu(rowData_r12, ri_r13); });
    i0.ɵɵelementEnd();
    i0.ɵɵelementStart(2, "div", 22);
    i0.ɵɵtemplate(3, DhrDataTableComponent_ng_template_4_td_2_div_3_Template, 6, 5, "div", 23);
    i0.ɵɵelementEnd();
    i0.ɵɵelementEnd();
} if (rf & 2) {
    const ri_r13 = i0.ɵɵnextContext().rowIndex;
    const ctx_r16 = i0.ɵɵnextContext();
    i0.ɵɵadvance(3);
    i0.ɵɵproperty("ngIf", ri_r13 == ctx_r16.rs);
} }
function DhrDataTableComponent_ng_template_4_Template(rf, ctx) { if (rf & 1) {
    i0.ɵɵelementStart(0, "tr", 19);
    i0.ɵɵtemplate(1, DhrDataTableComponent_ng_template_4_td_1_Template, 3, 4, "td", 20);
    i0.ɵɵtemplate(2, DhrDataTableComponent_ng_template_4_td_2_Template, 4, 1, "td", 14);
    i0.ɵɵelementEnd();
} if (rf & 2) {
    const columns_r14 = ctx.columns;
    const ctx_r3 = i0.ɵɵnextContext();
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngForOf", columns_r14);
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngIf", ctx_r3.actionbtn);
} }
export class DhrDataTableComponent {
    constructor(confirmDialog) {
        this.confirmDialog = confirmDialog;
        this.deleteEvent = new EventEmitter();
        this.duplicateEvent = new EventEmitter();
        this.viewEvent = new EventEmitter();
        this.downloadEvent = new EventEmitter();
        this.shareEvent = new EventEmitter();
    }
    ngOnInit() {
    }
    deleteRow(data) {
        this.rs = null;
        if (document.getElementById('myDropdown')) {
            document.getElementById('myDropdown').remove();
        }
        this.confirmDialog.confirm({
            message: `This will delete ${this.contentText} '<span class="confrm-name">${data.name}</span>' from your records. You can't undo this.`,
            header: `Delete the ${this.contentText}`,
            acceptLabel: 'Delete',
            rejectLabel: 'Cancel',
            acceptIcon: null,
            rejectIcon: null,
            accept: () => {
                this.deleteEvent.emit(data);
            },
            reject: () => {
            }
        });
    }
    duplicateRow(data) {
        this.rs = null;
        if (document.getElementById('myDropdown')) {
            document.getElementById('myDropdown').remove();
        }
        this.confirmDialog.confirm({
            message: `This will create a copy of condenser '<span class="confrm-name">${data.name}</span>'. You will be able to edit it later.`,
            header: 'Duplicate the Condenser',
            acceptLabel: 'Duplicate',
            rejectLabel: 'Cancel',
            acceptIcon: null,
            rejectIcon: null,
            accept: () => {
                this.duplicateEvent.emit(data);
            },
            reject: () => {
            }
        });
    }
    viewRow(data) {
        this.viewEvent.emit(data);
        this.rs = null;
        if (document.getElementById('myDropdown')) {
            document.getElementById('myDropdown').remove();
        }
    }
    downloadRow(data) {
        this.downloadEvent.emit(data);
        this.rs = null;
        if (document.getElementById('myDropdown')) {
            document.getElementById('myDropdown').remove();
        }
    }
    shareRow(data) {
        this.shareEvent.emit(data);
        this.rs = null;
        if (document.getElementById('myDropdown')) {
            document.getElementById('myDropdown').remove();
        }
    }
    showWrappedMenu(r, s) {
        if (document.querySelector('.dropdown-content') == undefined || null) {
            this.rs = s;
        }
        else {
            this.rs = null;
            document.getElementById('myDropdown').remove();
        }
    }
    printFilteredUsers(e) {
        const data = { load: e };
        const event = new CustomEvent('dhr_table_filter_data', { detail: data });
        window.dispatchEvent(event);
    }
    // custom sorting for different field
    customSort(event) {
        console.log(event);
        event.data.sort((data1, data2) => {
            let value1 = data1[event.field];
            let value2 = data2[event.field];
            let result = null;
            console.log(value1, value2);
            if (value1 == null && value2 != null)
                result = -1;
            else if (value1 != null && value2 == null)
                result = 1;
            else if (value1 == null && value2 == null)
                result = 0;
            else if (event.field.includes('updatedAt')) {
                let v1 = new Date(value1);
                let v2 = new Date(value2);
                result = (v1 < v2) ? -1 : (v1 > v2) ? 1 : 0;
            }
            else if (typeof value1 === 'string' && typeof value2 === 'string')
                result = value1.localeCompare(value2);
            else
                result = (value1 < value2) ? -1 : (value1 > value2) ? 1 : 0;
            return (event.order * result);
        });
    }
}
DhrDataTableComponent.ɵfac = function DhrDataTableComponent_Factory(t) { return new (t || DhrDataTableComponent)(i0.ɵɵdirectiveInject(i1.ConfirmationService)); };
DhrDataTableComponent.ɵcmp = i0.ɵɵdefineComponent({ type: DhrDataTableComponent, selectors: [["dhr-data-table"]], inputs: { cols: "cols", tableData: "tableData", paginator: "paginator", searchPlaceHolder: "searchPlaceHolder", row: "row", showCurrentPageReport: "showCurrentPageReport", rowsPerPageOptions: "rowsPerPageOptions", scrollable: "scrollable", scrollHeight: "scrollHeight", styleClass: "styleClass", sortField: "sortField", sortOrder: "sortOrder", globalFilterFields: "globalFilterFields", headertext: "headertext", subHeadertext: "subHeadertext", contentText: "contentText", actionbtn: "actionbtn", showDeleteAtnBtn: "showDeleteAtnBtn", showDuplicateAtnBtn: "showDuplicateAtnBtn", showViewAtnBtn: "showViewAtnBtn", showDownloadAtnBtn: "showDownloadAtnBtn", showShareAtnBtn: "showShareAtnBtn" }, outputs: { deleteEvent: "deleteEvent", duplicateEvent: "duplicateEvent", viewEvent: "viewEvent", downloadEvent: "downloadEvent", shareEvent: "shareEvent" }, decls: 6, vars: 15, consts: [["currentPageReportTemplate", "Showing {first} to {last} of {totalRecords} entries", 3, "columns", "customSort", "value", "paginator", "rows", "showCurrentPageReport", "rowsPerPageOptions", "scrollable", "scrollHeight", "styleClass", "sortField", "sortOrder", "globalFilterFields", "sortFunction", "onFilter"], ["dt", ""], ["pTemplate", "caption"], ["pTemplate", "header"], ["pTemplate", "body"], ["defaultFocus", "none", 3, "position", "baseZIndex"], [1, "p-d-flex", "p-ai-center", "p-jc-between"], [1, "p-m-0"], [2, "margin", "3px"], [1, "p-input-icon-left"], [1, "pi", "pi-search"], ["pInputText", "", "type", "text", 3, "placeholder", "input"], [2, "font-size", "13px"], [3, "pSortableColumn", 4, "ngFor", "ngForOf"], ["style", "width:5rem", 4, "ngIf"], [3, "pSortableColumn"], [3, "field", 4, "ngIf"], [3, "field"], [2, "width", "5rem"], [2, "font-size", "12px"], [4, "ngFor", "ngForOf"], ["pButton", "", "pRipple", "", "icon", "pi pi-ellipsis-v", 1, "p-button-rounded", "p-button-success", "p-mr-2", 2, "position", "relative", 3, "click"], [1, "menuc", 2, "position", "absolute"], ["id", "myDropdown", "class", "dropdown-content", 4, "ngIf"], ["id", "myDropdown", 1, "dropdown-content"], ["onMouseOver", "this.style.color='#32cafc'", "onMouseOut", "this.style.color='black'", 3, "click", 4, "ngIf"], ["onMouseOver", "this.style.color='red'", "onMouseOut", "this.style.color='black'", 3, "click", 4, "ngIf"], ["onMouseOver", "this.style.color='#fcba03'", "onMouseOut", "this.style.color='black'", 3, "click", 4, "ngIf"], ["onMouseOver", "this.style.color='#32cafc'", "onMouseOut", "this.style.color='black'", 3, "click"], ["aria-hidden", "true", 1, "fa", "fa-eye"], ["aria-hidden", "true", 1, "fa", "fa-files-o"], ["onMouseOver", "this.style.color='red'", "onMouseOut", "this.style.color='black'", 3, "click"], ["aria-hidden", "true", 1, "fa", "fa-trash-o"], ["onMouseOver", "this.style.color='#fcba03'", "onMouseOut", "this.style.color='black'", 3, "click"], ["aria-hidden", "true", 1, "fa", "fa-download"], ["aria-hidden", "true", 1, "fa", "fa-share-alt"]], template: function DhrDataTableComponent_Template(rf, ctx) { if (rf & 1) {
        i0.ɵɵelementStart(0, "p-table", 0, 1);
        i0.ɵɵlistener("sortFunction", function DhrDataTableComponent_Template_p_table_sortFunction_0_listener($event) { return ctx.customSort($event); })("onFilter", function DhrDataTableComponent_Template_p_table_onFilter_0_listener($event) { return ctx.printFilteredUsers($event); });
        i0.ɵɵtemplate(2, DhrDataTableComponent_ng_template_2_Template, 9, 3, "ng-template", 2);
        i0.ɵɵtemplate(3, DhrDataTableComponent_ng_template_3_Template, 3, 2, "ng-template", 3);
        i0.ɵɵtemplate(4, DhrDataTableComponent_ng_template_4_Template, 3, 2, "ng-template", 4);
        i0.ɵɵelementEnd();
        i0.ɵɵelement(5, "p-confirmDialog", 5);
    } if (rf & 2) {
        i0.ɵɵproperty("columns", ctx.cols)("customSort", true)("value", ctx.tableData)("paginator", ctx.paginator)("rows", ctx.row)("showCurrentPageReport", ctx.showCurrentPageReport)("rowsPerPageOptions", ctx.rowsPerPageOptions)("scrollable", ctx.scrollable)("scrollHeight", ctx.scrollHeight)("styleClass", ctx.styleClass)("sortField", ctx.sortField)("sortOrder", ctx.sortOrder)("globalFilterFields", ctx.globalFilterFields);
        i0.ɵɵadvance(5);
        i0.ɵɵproperty("position", "top")("baseZIndex", 10000000);
    } }, directives: [i2.Table, i1.PrimeTemplate, i3.ConfirmDialog, i4.InputText, i5.NgForOf, i5.NgIf, i2.SortableColumn, i2.SortIcon, i6.ButtonDirective], pipes: [i5.DatePipe], styles: [".p-button.p-button-success[_ngcontent-%COMP%]{color:#000;background-color:Transparent;background-repeat:no-repeat;border:none;cursor:pointer;overflow:hidden;outline:0}.p-button[_ngcontent-%COMP%]:active, .p-button[_ngcontent-%COMP%]:hover{color:#ccc!important;background:no-repeat Transparent!important;border:none!important;cursor:pointer!important;overflow:hidden!important;outline:0!important}[_nghost-%COMP%]     .confrm-name{font-weight:700;font-size:14px}[_nghost-%COMP%]     .p-dialog-top .p-dialog{width:29%}[_nghost-%COMP%]     .p-dialog.p-confirm-dialog .p-confirm-dialog-message{margin:0;font-size:13px;font-weight:500}[_nghost-%COMP%]     .p-dialog .p-dialog-header{padding:.5em .5em .5em 1.5em;background:#dbe1e5}[_nghost-%COMP%]     .p-confirm-dialog .p-dialog-content{width:100%;height:6em}[_nghost-%COMP%]     .p-dialog .p-dialog-header .p-dialog-title{font-size:1em!important}[_nghost-%COMP%]     .p-dialog .p-dialog-footer .p-button .p-button-icon-left{margin-right:.5rem;display:none}[_nghost-%COMP%]     .p-dialog .p-dialog-footer{direction:rtl}[_nghost-%COMP%]     .p-confirm-dialog-accept{background:#5cb85c;color:#ceeace;border:1px solid #5cb85c}[_nghost-%COMP%]     .p-confirm-dialog-accept:hover{background:#4e994e;color:#ceeace;border:1px solid #4e994e}[_nghost-%COMP%]     .p-confirm-dialog-reject{background:#fff;color:#a1d6a1;border:1px solid #e2e2e2}[_nghost-%COMP%]     .p-confirm-dialog-reject:hover{background:#f1f1f1;color:#a1d6a1;border:1px solid #e2e2e2}[_nghost-%COMP%]    .p-button:focus{color:#ccc!important;outline:0!important;box-shadow:0 0 0 0 transparent!important}[_nghost-%COMP%]     .p-paginator{background:#f8f9fa!important;justify-content:flex-end;align-items:flex-end}[_nghost-%COMP%]     p-dropdown{z-index:1;margin-right:88%;margin-top:-2.75em}[_nghost-%COMP%]     .p-datatable .p-datatable-tbody>tr>td{text-align:left;border:1px solid #e9ecef;border-width:0 0 1px;word-break:break-all;padding:0 0 0 1.5em}[_nghost-%COMP%]     .p-paginator-current{font-size:13px;height:1.8rem}[_nghost-%COMP%]     .p-paginator .p-paginator-pages .p-paginator-page{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-first{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-last{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-prev{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-next{min-width:1.357rem;height:1.357rem}.dropdown-content[_ngcontent-%COMP%]{display:flex;flex-direction:column;justify-content:space-around;text-align:center;position:fixed;background-color:#fff;width:10em;height:8em;margin-top:-2em;margin-left:-10.3em;box-shadow:0 8px 16px 0 rgba(0,0,0,.2);transition:opacity .5s;z-index:1}.dropdown-content[_ngcontent-%COMP%]   a[_ngcontent-%COMP%]{color:#000;text-decoration:none;display:block;padding:8px;border-bottom:1px solid #ccc;text-align:left;cursor:pointer}"] });
/*@__PURE__*/ (function () { i0.ɵsetClassMetadata(DhrDataTableComponent, [{
        type: Component,
        args: [{
                selector: 'dhr-data-table',
                templateUrl: './dhr-data-table.component.html',
                styleUrls: ['./dhr-data-table.component.css']
            }]
    }], function () { return [{ type: i1.ConfirmationService }]; }, { cols: [{
            type: Input
        }], tableData: [{
            type: Input
        }], paginator: [{
            type: Input
        }], searchPlaceHolder: [{
            type: Input
        }], row: [{
            type: Input
        }], showCurrentPageReport: [{
            type: Input
        }], rowsPerPageOptions: [{
            type: Input
        }], scrollable: [{
            type: Input
        }], scrollHeight: [{
            type: Input
        }], styleClass: [{
            type: Input
        }], sortField: [{
            type: Input
        }], sortOrder: [{
            type: Input
        }], globalFilterFields: [{
            type: Input
        }], headertext: [{
            type: Input
        }], subHeadertext: [{
            type: Input
        }], contentText: [{
            type: Input
        }], actionbtn: [{
            type: Input
        }], showDeleteAtnBtn: [{
            type: Input
        }], showDuplicateAtnBtn: [{
            type: Input
        }], showViewAtnBtn: [{
            type: Input
        }], showDownloadAtnBtn: [{
            type: Input
        }], showShareAtnBtn: [{
            type: Input
        }], deleteEvent: [{
            type: Output
        }], duplicateEvent: [{
            type: Output
        }], viewEvent: [{
            type: Output
        }], downloadEvent: [{
            type: Output
        }], shareEvent: [{
            type: Output
        }] }); })();
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZGhyLWRhdGEtdGFibGUuY29tcG9uZW50LmpzIiwic291cmNlUm9vdCI6Im5nOi8vZGhyLWRhdGEtdGFibGUvIiwic291cmNlcyI6WyJsaWIvZGhyLWRhdGEtdGFibGUuY29tcG9uZW50LnRzIiwibGliL2Roci1kYXRhLXRhYmxlLmNvbXBvbmVudC5odG1sIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBLE9BQU8sRUFBRSxTQUFTLEVBQVUsS0FBSyxFQUFFLE1BQU0sRUFBRSxZQUFZLEVBQUUsTUFBTSxlQUFlLENBQUM7Ozs7Ozs7Ozs7SUNLdkUsOEJBQ0k7SUFBQSw2QkFBa0I7SUFBQSxZQUFjO0lBQUEscUJBQUk7SUFBQSw2QkFBeUI7SUFBQSxZQUFpQjtJQUFBLGlCQUFLO0lBQUEsaUJBQUs7SUFDeEYsK0JBQ0k7SUFBQSx3QkFBNEI7SUFDNUIsaUNBQ0o7SUFEa0MsK0xBQVMsc0NBQXFDLFVBQVUsQ0FBQyxJQUFDO0lBQXhGLGlCQUNKO0lBQUEsaUJBQU87SUFDWCxpQkFBTTs7O0lBTGdCLGVBQWM7SUFBZCx1Q0FBYztJQUE2QixlQUFpQjtJQUFqQiwwQ0FBaUI7SUFHZSxlQUFpQztJQUFqQyxzREFBaUM7OztJQU8zRyxpQ0FBaUU7OztJQUFqQyxvQ0FBbUI7OztJQUR0RSw4QkFDSTtJQUFBLFlBQWU7SUFBQSx3R0FBb0Q7SUFDdkUsaUJBQUs7OztJQUYyQixpRkFBb0Q7SUFDaEYsZUFBZTtJQUFmLDhDQUFlO0lBQVksZUFBbUI7SUFBbkIscUNBQW1COzs7SUFFbEQseUJBQThDOzs7SUFKbEQsOEJBQ0k7SUFBQSxtRkFDSTtJQUVKLG1GQUF5QztJQUM3QyxpQkFBSzs7OztJQUpHLGVBQTJCO0lBQTNCLG9DQUEyQjtJQUdSLGVBQWlCO0lBQWpCLHVDQUFpQjs7O0lBTXhDLDBCQUNRO0lBQUEsWUFDUjs7SUFBQSxpQkFBSzs7OztJQURHLGVBQ1I7SUFEUSx1SkFDUjs7OztJQUtNLDZCQUNpRTtJQUEzQix5UUFBMEI7SUFBQyx3QkFBNEM7SUFBQSxnQ0FB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QlIsaUJBQVU7UUFDVixxQ0FBa0c7O1FBL0NyRixrQ0FBZ0Isb0JBQUEsd0JBQUEsNEJBQUEsaUJBQUEsb0RBQUEsOENBQUEsOEJBQUEsa0NBQUEsOEJBQUEsNEJBQUEsNEJBQUEsOENBQUE7UUErQ1osZUFBa0I7UUFBbEIsZ0NBQWtCLHdCQUFBOztrRER2Q3RCLHFCQUFxQjtjQUxqQyxTQUFTO2VBQUM7Z0JBQ1QsUUFBUSxFQUFFLGdCQUFnQjtnQkFDMUIsV0FBVyxFQUFFLGlDQUFpQztnQkFDOUMsU0FBUyxFQUFFLENBQUMsZ0NBQWdDLENBQUM7YUFDOUM7O2tCQUdFLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUVMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUNMLEtBQUs7O2tCQUVMLE1BQU07O2tCQUNOLE1BQU07O2tCQUNOLE1BQU07O2tCQUNOLE1BQU07O2tCQUNOLE1BQU0iLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyBDb21wb25lbnQsIE9uSW5pdCwgSW5wdXQsIE91dHB1dCwgRXZlbnRFbWl0dGVyIH0gZnJvbSAnQGFuZ3VsYXIvY29yZSc7XHJcbmltcG9ydCB7IENvbmZpcm1hdGlvblNlcnZpY2UsIFNvcnRFdmVudCB9IGZyb20gJ3ByaW1lbmcvYXBpJztcclxuXHJcbkBDb21wb25lbnQoe1xyXG4gIHNlbGVjdG9yOiAnZGhyLWRhdGEtdGFibGUnLFxyXG4gIHRlbXBsYXRlVXJsOiAnLi9kaHItZGF0YS10YWJsZS5jb21wb25lbnQuaHRtbCcsXHJcbiAgc3R5bGVVcmxzOiBbJy4vZGhyLWRhdGEtdGFibGUuY29tcG9uZW50LmNzcyddXHJcbn0pXHJcbmV4cG9ydCBjbGFzcyBEaHJEYXRhVGFibGVDb21wb25lbnQgaW1wbGVtZW50cyBPbkluaXQge1xyXG5cclxuICBASW5wdXQoKSBjb2xzOiBzdHJpbmdbXTtcclxuICBASW5wdXQoKSB0YWJsZURhdGE6IGFueVtdO1xyXG4gIEBJbnB1dCgpIHBhZ2luYXRvcjogYm9vbGVhbjtcclxuICBASW5wdXQoKSBzZWFyY2hQbGFjZUhvbGRlcjogc3RyaW5nO1xyXG4gIEBJbnB1dCgpIHJvdzogbnVtYmVyO1xyXG4gIEBJbnB1dCgpIHNob3dDdXJyZW50UGFnZVJlcG9ydDogYm9vbGVhbjtcclxuICBASW5wdXQoKSByb3dzUGVyUGFnZU9wdGlvbnM6IGFueVtdO1xyXG4gIEBJbnB1dCgpIHNjcm9sbGFibGU6IGJvb2xlYW47XHJcbiAgQElucHV0KCkgc2Nyb2xsSGVpZ2h0OiBzdHJpbmc7XHJcbiAgQElucHV0KCkgc3R5bGVDbGFzczogc3RyaW5nO1xyXG4gIEBJbnB1dCgpIHNvcnRGaWVsZDogc3RyaW5nO1xyXG4gIEBJbnB1dCgpIHNvcnRPcmRlcjogbnVtYmVyO1xyXG4gIEBJbnB1dCgpIGdsb2JhbEZpbHRlckZpZWxkczogYW55W107XHJcbiAgQElucHV0KCkgaGVhZGVydGV4dDogc3RyaW5nO1xyXG4gIEBJbnB1dCgpIHN1YkhlYWRlcnRleHQ6IHN0cmluZztcclxuICBASW5wdXQoKSBjb250ZW50VGV4dDogc3RyaW5nO1xyXG4gIEBJbnB1dCgpIGFjdGlvbmJ0bjogYm9vbGVhbjtcclxuXHJcbiAgQElucHV0KCkgc2hvd0RlbGV0ZUF0bkJ0bjogYm9vbGVhbjtcclxuICBASW5wdXQoKSBzaG93RHVwbGljYXRlQXRuQnRuOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNob3dWaWV3QXRuQnRuOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNob3dEb3dubG9hZEF0bkJ0bjogYm9vbGVhbjtcclxuICBASW5wdXQoKSBzaG93U2hhcmVBdG5CdG46IGJvb2xlYW47XHJcblxyXG4gIEBPdXRwdXQoKSBkZWxldGVFdmVudCA9IG5ldyBFdmVudEVtaXR0ZXIoKTtcclxuICBAT3V0cHV0KCkgZHVwbGljYXRlRXZlbnQgPSBuZXcgRXZlbnRFbWl0dGVyKCk7XHJcbiAgQE91dHB1dCgpIHZpZXdFdmVudCA9IG5ldyBFdmVudEVtaXR0ZXIoKTtcclxuICBAT3V0cHV0KCkgZG93bmxvYWRFdmVudCA9IG5ldyBFdmVudEVtaXR0ZXIoKTtcclxuICBAT3V0cHV0KCkgc2hhcmVFdmVudCA9IG5ldyBFdmVudEVtaXR0ZXIoKTtcclxuICByczogYW55O1xyXG5cclxuICBjb25zdHJ1Y3RvcihwdWJsaWMgY29uZmlybURpYWxvZzogQ29uZmlybWF0aW9uU2VydmljZSkgeyB9XHJcblxyXG4gIG5nT25Jbml0KCk6IHZvaWQge1xyXG4gIH1cclxuICBkZWxldGVSb3coZGF0YSkge1xyXG4gICAgdGhpcy5ycyA9IG51bGw7XHJcbiAgICBpZiAoZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoJ215RHJvcGRvd24nKSkge1xyXG4gICAgICBkb2N1bWVudC5nZXRFbGVtZW50QnlJZCgnbXlEcm9wZG93bicpLnJlbW92ZSgpO1xyXG4gICAgfVxyXG4gICAgdGhpcy5jb25maXJtRGlhbG9nLmNvbmZpcm0oe1xyXG4gICAgICBtZXNzYWdlOiBgVGhpcyB3aWxsIGRlbGV0ZSAke3RoaXMuY29udGVudFRleHR9ICc8c3BhbiBjbGFzcz1cImNvbmZybS1uYW1lXCI+JHtkYXRhLm5hbWV9PC9zcGFuPicgZnJvbSB5b3VyIHJlY29yZHMuIFlvdSBjYW4ndCB1bmRvIHRoaXMuYCxcclxuICAgICAgaGVhZGVyOiBgRGVsZXRlIHRoZSAke3RoaXMuY29udGVudFRleHR9YCxcclxuICAgICAgYWNjZXB0TGFiZWw6ICdEZWxldGUnLFxyXG4gICAgICByZWplY3RMYWJlbDogJ0NhbmNlbCcsXHJcbiAgICAgIGFjY2VwdEljb246IG51bGwsXHJcbiAgICAgIHJlamVjdEljb246IG51bGwsXHJcbiAgICAgIGFjY2VwdDogKCkgPT4ge1xyXG4gICAgICAgIHRoaXMuZGVsZXRlRXZlbnQuZW1pdChkYXRhKTtcclxuICAgICAgfSxcclxuICAgICAgcmVqZWN0OiAoKSA9PiB7XHJcbiAgICAgIH1cclxuICAgIH0pO1xyXG4gIH1cclxuXHJcbiAgZHVwbGljYXRlUm93KGRhdGEpIHtcclxuICAgIHRoaXMucnMgPSBudWxsO1xyXG4gICAgaWYgKGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdteURyb3Bkb3duJykpIHtcclxuICAgICAgZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoJ215RHJvcGRvd24nKS5yZW1vdmUoKTtcclxuICAgIH1cclxuICAgIHRoaXMuY29uZmlybURpYWxvZy5jb25maXJtKHtcclxuICAgICAgbWVzc2FnZTogYFRoaXMgd2lsbCBjcmVhdGUgYSBjb3B5IG9mIGNvbmRlbnNlciAnPHNwYW4gY2xhc3M9XCJjb25mcm0tbmFtZVwiPiR7ZGF0YS5uYW1lfTwvc3Bhbj4nLiBZb3Ugd2lsbCBiZSBhYmxlIHRvIGVkaXQgaXQgbGF0ZXIuYCxcclxuICAgICAgaGVhZGVyOiAnRHVwbGljYXRlIHRoZSBDb25kZW5zZXInLFxyXG4gICAgICBhY2NlcHRMYWJlbDogJ0R1cGxpY2F0ZScsXHJcbiAgICAgIHJlamVjdExhYmVsOiAnQ2FuY2VsJyxcclxuICAgICAgYWNjZXB0SWNvbjogbnVsbCxcclxuICAgICAgcmVqZWN0SWNvbjogbnVsbCxcclxuICAgICAgYWNjZXB0OiAoKSA9PiB7XHJcbiAgICAgICAgdGhpcy5kdXBsaWNhdGVFdmVudC5lbWl0KGRhdGEpO1xyXG4gICAgICB9LFxyXG4gICAgICByZWplY3Q6ICgpID0+IHtcclxuICAgICAgfVxyXG4gICAgfSk7XHJcbiAgfVxyXG5cclxuICB2aWV3Um93KGRhdGEpIHtcclxuICAgIHRoaXMudmlld0V2ZW50LmVtaXQoZGF0YSk7XHJcbiAgICB0aGlzLnJzID0gbnVsbDtcclxuICAgIGlmIChkb2N1bWVudC5nZXRFbGVtZW50QnlJZCgnbXlEcm9wZG93bicpKSB7XHJcbiAgICAgIGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdteURyb3Bkb3duJykucmVtb3ZlKCk7XHJcbiAgICB9XHJcbiAgfVxyXG5cclxuICBkb3dubG9hZFJvdyhkYXRhKSB7XHJcbiAgICB0aGlzLmRvd25sb2FkRXZlbnQuZW1pdChkYXRhKTtcclxuICAgIHRoaXMucnMgPSBudWxsO1xyXG4gICAgaWYgKGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdteURyb3Bkb3duJykpIHtcclxuICAgICAgZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoJ215RHJvcGRvd24nKS5yZW1vdmUoKTtcclxuICAgIH1cclxuICB9XHJcblxyXG4gIHNoYXJlUm93KGRhdGEpIHtcclxuICAgIHRoaXMuc2hhcmVFdmVudC5lbWl0KGRhdGEpO1xyXG4gICAgdGhpcy5ycyA9IG51bGw7XHJcbiAgICBpZiAoZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoJ215RHJvcGRvd24nKSkge1xyXG4gICAgICBkb2N1bWVudC5nZXRFbGVtZW50QnlJZCgnbXlEcm9wZG93bicpLnJlbW92ZSgpO1xyXG4gICAgfVxyXG4gIH1cclxuXHJcbiAgc2hvd1dyYXBwZWRNZW51KHIsIHMpIHtcclxuICAgIGlmIChkb2N1bWVudC5xdWVyeVNlbGVjdG9yKCcuZHJvcGRvd24tY29udGVudCcpID09IHVuZGVmaW5lZCB8fCBudWxsKSB7XHJcbiAgICAgIHRoaXMucnMgPSBzO1xyXG4gICAgfVxyXG4gICAgZWxzZSB7XHJcbiAgICAgIHRoaXMucnMgPSBudWxsO1xyXG4gICAgICBkb2N1bWVudC5nZXRFbGVtZW50QnlJZCgnbXlEcm9wZG93bicpLnJlbW92ZSgpO1xyXG4gICAgfVxyXG4gIH1cclxuICBwcmludEZpbHRlcmVkVXNlcnMoZSkge1xyXG4gICAgY29uc3QgZGF0YSA9IHsgbG9hZDogZSB9O1xyXG4gICAgY29uc3QgZXZlbnQgPSBuZXcgQ3VzdG9tRXZlbnQoJ2Rocl90YWJsZV9maWx0ZXJfZGF0YScsIHsgZGV0YWlsOiBkYXRhIH0pO1xyXG4gICAgd2luZG93LmRpc3BhdGNoRXZlbnQoZXZlbnQpO1xyXG4gIH1cclxuXHJcbiAgLy8gY3VzdG9tIHNvcnRpbmcgZm9yIGRpZmZlcmVudCBmaWVsZFxyXG4gIGN1c3RvbVNvcnQoZXZlbnQ6IFNvcnRFdmVudCkge1xyXG4gICAgY29uc29sZS5sb2coZXZlbnQpXHJcbiAgICBldmVudC5kYXRhLnNvcnQoKGRhdGExLCBkYXRhMikgPT4ge1xyXG4gICAgICBsZXQgdmFsdWUxID0gZGF0YTFbZXZlbnQuZmllbGRdO1xyXG4gICAgICBsZXQgdmFsdWUyID0gZGF0YTJbZXZlbnQuZmllbGRdO1xyXG4gICAgICBsZXQgcmVzdWx0ID0gbnVsbDtcclxuICAgICAgY29uc29sZS5sb2codmFsdWUxLCB2YWx1ZTIpXHJcbiAgICAgIGlmICh2YWx1ZTEgPT0gbnVsbCAmJiB2YWx1ZTIgIT0gbnVsbClcclxuICAgICAgICByZXN1bHQgPSAtMTtcclxuICAgICAgZWxzZSBpZiAodmFsdWUxICE9IG51bGwgJiYgdmFsdWUyID09IG51bGwpXHJcbiAgICAgICAgcmVzdWx0ID0gMTtcclxuICAgICAgZWxzZSBpZiAodmFsdWUxID09IG51bGwgJiYgdmFsdWUyID09IG51bGwpXHJcbiAgICAgICAgcmVzdWx0ID0gMDtcclxuICAgICAgZWxzZSBpZiAoZXZlbnQuZmllbGQuaW5jbHVkZXMoJ3VwZGF0ZWRBdCcpKSB7XHJcbiAgICAgICAgbGV0IHYxID0gbmV3IERhdGUodmFsdWUxKTtcclxuICAgICAgICBsZXQgdjIgPSBuZXcgRGF0ZSh2YWx1ZTIpO1xyXG4gICAgICAgIHJlc3VsdCA9ICh2MSA8IHYyKSA/IC0xIDogKHYxID4gdjIpID8gMSA6IDA7XHJcbiAgICAgIH1cclxuICAgICAgZWxzZSBpZiAodHlwZW9mIHZhbHVlMSA9PT0gJ3N0cmluZycgJiYgdHlwZW9mIHZhbHVlMiA9PT0gJ3N0cmluZycpXHJcbiAgICAgICAgcmVzdWx0ID0gdmFsdWUxLmxvY2FsZUNvbXBhcmUodmFsdWUyKTtcclxuICAgICAgZWxzZVxyXG4gICAgICAgIHJlc3VsdCA9ICh2YWx1ZTEgPCB2YWx1ZTIpID8gLTEgOiAodmFsdWUxID4gdmFsdWUyKSA/IDEgOiAwO1xyXG5cclxuICAgICAgcmV0dXJuIChldmVudC5vcmRlciAqIHJlc3VsdCk7XHJcbiAgICB9KTtcclxuICB9XHJcblxyXG59IiwiPHAtdGFibGUgI2R0IFtjb2x1bW5zXT1cImNvbHNcIiAoc29ydEZ1bmN0aW9uKT1cImN1c3RvbVNvcnQoJGV2ZW50KVwiIFtjdXN0b21Tb3J0XT1cInRydWVcIiBbdmFsdWVdPVwidGFibGVEYXRhXCIgKG9uRmlsdGVyKT1cInByaW50RmlsdGVyZWRVc2VycygkZXZlbnQpXCIgW3BhZ2luYXRvcl09XCJwYWdpbmF0b3JcIiBbcm93c109XCJyb3dcIiBbc2hvd0N1cnJlbnRQYWdlUmVwb3J0XT1cInNob3dDdXJyZW50UGFnZVJlcG9ydFwiXHJcbiAgICBjdXJyZW50UGFnZVJlcG9ydFRlbXBsYXRlPVwiU2hvd2luZyB7Zmlyc3R9IHRvIHtsYXN0fSBvZiB7dG90YWxSZWNvcmRzfSBlbnRyaWVzXCIgW3Jvd3NQZXJQYWdlT3B0aW9uc109XCJyb3dzUGVyUGFnZU9wdGlvbnNcIlxyXG4gICAgW3Njcm9sbGFibGVdPVwic2Nyb2xsYWJsZVwiIFtzY3JvbGxIZWlnaHRdPVwic2Nyb2xsSGVpZ2h0XCIgW3N0eWxlQ2xhc3NdPVwic3R5bGVDbGFzc1wiIFtzb3J0RmllbGRdPVwic29ydEZpZWxkXCIgW3NvcnRPcmRlcl09XCJzb3J0T3JkZXJcIiBcclxuICAgIFtnbG9iYWxGaWx0ZXJGaWVsZHNdPVwiZ2xvYmFsRmlsdGVyRmllbGRzXCI+XHJcbiAgICAgIDxuZy10ZW1wbGF0ZSBwVGVtcGxhdGU9XCJjYXB0aW9uXCI+XHJcbiAgICAgICAgPGRpdiBjbGFzcz1cInAtZC1mbGV4IHAtYWktY2VudGVyIHAtamMtYmV0d2VlblwiPlxyXG4gICAgICAgICAgICA8aDQgY2xhc3M9XCJwLW0tMFwiPnt7aGVhZGVydGV4dH19PGJyPjxoNSBzdHlsZT1cIm1hcmdpbjogM3B4O1wiPnt7c3ViSGVhZGVydGV4dH19PC9oNT48L2g0PiBcclxuICAgICAgICAgICAgPHNwYW4gY2xhc3M9XCJwLWlucHV0LWljb24tbGVmdFwiPlxyXG4gICAgICAgICAgICAgICAgPGkgY2xhc3M9XCJwaSBwaS1zZWFyY2hcIj48L2k+XHJcbiAgICAgICAgICAgICAgICA8aW5wdXQgcElucHV0VGV4dCB0eXBlPVwidGV4dFwiIChpbnB1dCk9XCJkdC5maWx0ZXJHbG9iYWwoJGV2ZW50LnRhcmdldC52YWx1ZSwgJ2NvbnRhaW5zJylcIiBbcGxhY2Vob2xkZXJdPVwic2VhcmNoUGxhY2VIb2xkZXJcIiAvPlxyXG4gICAgICAgICAgICA8L3NwYW4+XHJcbiAgICAgICAgPC9kaXY+XHJcbiAgICA8L25nLXRlbXBsYXRlPlxyXG4gICAgPG5nLXRlbXBsYXRlIHBUZW1wbGF0ZT1cImhlYWRlclwiIGxldC1jb2x1bW5zPlxyXG4gICAgICAgIDx0ciBzdHlsZT1cImZvbnQtc2l6ZTogMTNweDtcIj5cclxuICAgICAgICAgICAgPHRoICpuZ0Zvcj1cImxldCBjb2wgb2YgY29sdW1uc1wiIHBTb3J0YWJsZUNvbHVtbj1cInt7Y29sLnNvcnRPcHQgPyBjb2wuZmllbGQgOiBudWxsfX1cIj5cclxuICAgICAgICAgICAgICAgIHt7Y29sLmhlYWRlcn19IDxwLXNvcnRJY29uICpuZ0lmPVwiY29sLnNvcnRPcHRcIiBbZmllbGRdPVwiY29sLmZpZWxkXCI+PC9wLXNvcnRJY29uPlxyXG4gICAgICAgICAgICA8L3RoPlxyXG4gICAgICAgICAgICA8dGggc3R5bGU9XCJ3aWR0aDo1cmVtXCIgKm5nSWY9XCJhY3Rpb25idG5cIj48L3RoPlxyXG4gICAgICAgIDwvdHI+XHJcbiAgICA8L25nLXRlbXBsYXRlPlxyXG4gICAgPG5nLXRlbXBsYXRlIHBUZW1wbGF0ZT1cImJvZHlcIiBsZXQtcm93RGF0YSBsZXQtcmk9XCJyb3dJbmRleFwiIGxldC1jb2x1bW5zPVwiY29sdW1uc1wiPlxyXG4gICAgICAgIFxyXG4gICAgICAgIDx0ciBzdHlsZT1cImZvbnQtc2l6ZTogMTJweDtcIj5cclxuICAgICAgICAgICAgPHRkICpuZ0Zvcj1cImxldCBjb2wgb2YgY29sdW1uc1wiID5cclxuICAgICAgICAgICAgICAgICAgICB7e2NvbC50eXBlICE9ICdkYXRlJyA/IHJvd0RhdGFbY29sLmZpZWxkXSA6IHJvd0RhdGFbY29sLmZpZWxkXSAqIDEwMDB8ICBkYXRlOidzaG9ydCd9fVxyXG4gICAgICAgICAgICA8L3RkPlxyXG4gICAgICAgICAgICA8dGQgc3R5bGU9XCJ3aWR0aDo1cmVtXCIgKm5nSWY9XCJhY3Rpb25idG5cIj5cclxuICAgICAgICAgICAgICA8YnV0dG9uIHN0eWxlPVwicG9zaXRpb246IHJlbGF0aXZlO1wiIHBCdXR0b24gcFJpcHBsZSBpY29uPVwicGkgcGktZWxsaXBzaXMtdlwiIGNsYXNzPVwicC1idXR0b24tcm91bmRlZCBwLWJ1dHRvbi1zdWNjZXNzIHAtbXItMlwiIChjbGljayk9XCJzaG93V3JhcHBlZE1lbnUocm93RGF0YSxyaSlcIj48L2J1dHRvbj5cclxuICAgICAgICAgICAgICA8ZGl2IGNsYXNzPVwibWVudWNcIiBzdHlsZT1cInBvc2l0aW9uOiBhYnNvbHV0ZVwiPlxyXG4gICAgICAgICAgICAgICAgPGRpdiBpZD1cIm15RHJvcGRvd25cIiBjbGFzcz1cImRyb3Bkb3duLWNvbnRlbnRcIiAqbmdJZj1cInJpID09IHJzXCI+XHJcbiAgICAgICAgICAgICAgICAgIDxhICpuZ0lmPVwic2hvd1ZpZXdBdG5CdG5cIiBvbk1vdXNlT3Zlcj1cInRoaXMuc3R5bGUuY29sb3I9JyMzMmNhZmMnXCJcclxuICAgICAgICAgICAgICAgICAgb25Nb3VzZU91dD1cInRoaXMuc3R5bGUuY29sb3I9J2JsYWNrJ1wiIChjbGljayk9XCJ2aWV3Um93KHJvd0RhdGEpXCI+PGkgY2xhc3M9XCJmYSBmYS1leWVcIiBhcmlhLWhpZGRlbj1cInRydWVcIj48L2k+Jm5ic3A7Jm5ic3A7VmlldzwvYT5cclxuICAgICAgICAgICAgICAgICAgPGEgKm5nSWY9XCJzaG93RHVwbGljYXRlQXRuQnRuXCIgb25Nb3VzZU92ZXI9XCJ0aGlzLnN0eWxlLmNvbG9yPScjMzJjYWZjJ1wiXHJcbiAgICAgICAgICAgICAgICAgIG9uTW91c2VPdXQ9XCJ0aGlzLnN0eWxlLmNvbG9yPSdibGFjaydcIiAoY2xpY2spPVwiZHVwbGljYXRlUm93KHJvd0RhdGEpXCI+PGkgY2xhc3M9XCJmYSBmYS1maWxlcy1vXCIgYXJpYS1oaWRkZW49XCJ0cnVlXCI+PC9pPiZuYnNwOyZuYnNwO0R1cGxpY2F0ZTwvYT5cclxuICAgICAgICAgICAgICAgICAgPGEgKm5nSWY9XCJzaG93RGVsZXRlQXRuQnRuXCIgb25Nb3VzZU92ZXI9XCJ0aGlzLnN0eWxlLmNvbG9yPSdyZWQnXCJcclxuICAgICAgICAgICAgICAgICAgb25Nb3VzZU91dD1cInRoaXMuc3R5bGUuY29sb3I9J2JsYWNrJ1wiIChjbGljayk9XCJkZWxldGVSb3cocm93RGF0YSlcIj48aSBjbGFzcz1cImZhIGZhLXRyYXNoLW9cIiBhcmlhLWhpZGRlbj1cInRydWVcIj48L2k+Jm5ic3A7Jm5ic3A7RGVsZXRlPC9hPlxyXG4gICAgICAgICAgICAgICAgICA8YSAqbmdJZj1cInNob3dEb3dubG9hZEF0bkJ0blwiIG9uTW91c2VPdmVyPVwidGhpcy5zdHlsZS5jb2xvcj0nI2ZjYmEwMydcIlxyXG4gICAgICAgICAgICAgICAgICBvbk1vdXNlT3V0PVwidGhpcy5zdHlsZS5jb2xvcj0nYmxhY2snXCIgKGNsaWNrKT1cImRvd25sb2FkUm93KHJvd0RhdGEpXCI+PGkgY2xhc3M9XCJmYSBmYS1kb3dubG9hZFwiIGFyaWEtaGlkZGVuPVwidHJ1ZVwiPjwvaT4mbmJzcDsmbmJzcDtEb3dubG9hZDwvYT5cclxuICAgICAgICAgICAgICAgICAgPGEgKm5nSWY9XCJzaG93U2hhcmVBdG5CdG5cIiBvbk1vdXNlT3Zlcj1cInRoaXMuc3R5bGUuY29sb3I9JyNmY2JhMDMnXCJcclxuICAgICAgICAgICAgICAgICAgb25Nb3VzZU91dD1cInRoaXMuc3R5bGUuY29sb3I9J2JsYWNrJ1wiIChjbGljayk9XCJzaGFyZVJvdyhyb3dEYXRhKVwiPjxpIGNsYXNzPVwiZmEgZmEtc2hhcmUtYWx0XCIgYXJpYS1oaWRkZW49XCJ0cnVlXCI+PC9pPiZuYnNwOyZuYnNwO1NoYXJlPC9hPlxyXG4gICAgICAgICAgICAgICAgPC9kaXY+XHJcbiAgICAgICAgICAgICAgPC9kaXY+ICBcclxuICAgICAgICAgICAgPC90ZD5cclxuICAgICAgICA8L3RyPlxyXG4gICAgPC9uZy10ZW1wbGF0ZT5cclxuPC9wLXRhYmxlPlxyXG48cC1jb25maXJtRGlhbG9nIFtwb3NpdGlvbl09XCIndG9wJ1wiIFtiYXNlWkluZGV4XT1cIjEwMDAwMDAwXCIgZGVmYXVsdEZvY3VzPVwibm9uZVwiPjwvcC1jb25maXJtRGlhbG9nPlxyXG5cclxuIl19