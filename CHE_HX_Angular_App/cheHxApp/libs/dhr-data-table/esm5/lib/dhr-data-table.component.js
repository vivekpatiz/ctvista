import { Component, Input, Output, EventEmitter } from '@angular/core';
import * as i0 from "@angular/core";
import * as i1 from "primeng/api";
import * as i2 from "primeng/table";
import * as i3 from "primeng/confirmdialog";
import * as i4 from "primeng/inputtext";
import * as i5 from "@angular/common";
import * as i6 from "primeng/button";
function DhrDataTableComponent_ng_template_2_Template(rf, ctx) { if (rf & 1) {
    var _r5 = i0.ɵɵgetCurrentView();
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
    i0.ɵɵlistener("input", function DhrDataTableComponent_ng_template_2_Template_input_input_8_listener($event) { i0.ɵɵrestoreView(_r5); i0.ɵɵnextContext(); var _r0 = i0.ɵɵreference(1); return _r0.filterGlobal($event.target.value, "contains"); });
    i0.ɵɵelementEnd();
    i0.ɵɵelementEnd();
    i0.ɵɵelementEnd();
} if (rf & 2) {
    var ctx_r1 = i0.ɵɵnextContext();
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
    var col_r9 = i0.ɵɵnextContext().$implicit;
    i0.ɵɵproperty("field", col_r9.field);
} }
function DhrDataTableComponent_ng_template_3_th_1_Template(rf, ctx) { if (rf & 1) {
    i0.ɵɵelementStart(0, "th", 15);
    i0.ɵɵtext(1);
    i0.ɵɵtemplate(2, DhrDataTableComponent_ng_template_3_th_1_p_sortIcon_2_Template, 1, 1, "p-sortIcon", 16);
    i0.ɵɵelementEnd();
} if (rf & 2) {
    var col_r9 = ctx.$implicit;
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
    var columns_r6 = ctx.$implicit;
    var ctx_r2 = i0.ɵɵnextContext();
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
    var col_r17 = ctx.$implicit;
    var rowData_r12 = i0.ɵɵnextContext().$implicit;
    i0.ɵɵadvance(1);
    i0.ɵɵtextInterpolate1(" ", col_r17.type != "date" ? rowData_r12[col_r17.field] : i0.ɵɵpipeBind2(2, 1, rowData_r12[col_r17.field] * 1000, "short"), " ");
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template(rf, ctx) { if (rf & 1) {
    var _r27 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "a", 28);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template_a_click_0_listener() { i0.ɵɵrestoreView(_r27); var rowData_r12 = i0.ɵɵnextContext(3).$implicit; var ctx_r25 = i0.ɵɵnextContext(); return ctx_r25.viewRow(rowData_r12); });
    i0.ɵɵelement(1, "i", 29);
    i0.ɵɵtext(2, "\u00A0\u00A0View");
    i0.ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template(rf, ctx) { if (rf & 1) {
    var _r30 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "a", 28);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template_a_click_0_listener() { i0.ɵɵrestoreView(_r30); var rowData_r12 = i0.ɵɵnextContext(3).$implicit; var ctx_r28 = i0.ɵɵnextContext(); return ctx_r28.duplicateRow(rowData_r12); });
    i0.ɵɵelement(1, "i", 30);
    i0.ɵɵtext(2, "\u00A0\u00A0Duplicate");
    i0.ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template(rf, ctx) { if (rf & 1) {
    var _r33 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "a", 31);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template_a_click_0_listener() { i0.ɵɵrestoreView(_r33); var rowData_r12 = i0.ɵɵnextContext(3).$implicit; var ctx_r31 = i0.ɵɵnextContext(); return ctx_r31.deleteRow(rowData_r12); });
    i0.ɵɵelement(1, "i", 32);
    i0.ɵɵtext(2, "\u00A0\u00A0Delete");
    i0.ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template(rf, ctx) { if (rf & 1) {
    var _r36 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "a", 33);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template_a_click_0_listener() { i0.ɵɵrestoreView(_r36); var rowData_r12 = i0.ɵɵnextContext(3).$implicit; var ctx_r34 = i0.ɵɵnextContext(); return ctx_r34.downloadRow(rowData_r12); });
    i0.ɵɵelement(1, "i", 34);
    i0.ɵɵtext(2, "\u00A0\u00A0Download");
    i0.ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template(rf, ctx) { if (rf & 1) {
    var _r39 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "a", 33);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template_a_click_0_listener() { i0.ɵɵrestoreView(_r39); var rowData_r12 = i0.ɵɵnextContext(3).$implicit; var ctx_r37 = i0.ɵɵnextContext(); return ctx_r37.shareRow(rowData_r12); });
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
    var ctx_r19 = i0.ɵɵnextContext(3);
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
    var _r42 = i0.ɵɵgetCurrentView();
    i0.ɵɵelementStart(0, "td", 18);
    i0.ɵɵelementStart(1, "button", 21);
    i0.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_Template_button_click_1_listener() { i0.ɵɵrestoreView(_r42); var ctx_r41 = i0.ɵɵnextContext(); var rowData_r12 = ctx_r41.$implicit; var ri_r13 = ctx_r41.rowIndex; var ctx_r40 = i0.ɵɵnextContext(); return ctx_r40.showWrappedMenu(rowData_r12, ri_r13); });
    i0.ɵɵelementEnd();
    i0.ɵɵelementStart(2, "div", 22);
    i0.ɵɵtemplate(3, DhrDataTableComponent_ng_template_4_td_2_div_3_Template, 6, 5, "div", 23);
    i0.ɵɵelementEnd();
    i0.ɵɵelementEnd();
} if (rf & 2) {
    var ri_r13 = i0.ɵɵnextContext().rowIndex;
    var ctx_r16 = i0.ɵɵnextContext();
    i0.ɵɵadvance(3);
    i0.ɵɵproperty("ngIf", ri_r13 == ctx_r16.rs);
} }
function DhrDataTableComponent_ng_template_4_Template(rf, ctx) { if (rf & 1) {
    i0.ɵɵelementStart(0, "tr", 19);
    i0.ɵɵtemplate(1, DhrDataTableComponent_ng_template_4_td_1_Template, 3, 4, "td", 20);
    i0.ɵɵtemplate(2, DhrDataTableComponent_ng_template_4_td_2_Template, 4, 1, "td", 14);
    i0.ɵɵelementEnd();
} if (rf & 2) {
    var columns_r14 = ctx.columns;
    var ctx_r3 = i0.ɵɵnextContext();
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngForOf", columns_r14);
    i0.ɵɵadvance(1);
    i0.ɵɵproperty("ngIf", ctx_r3.actionbtn);
} }
var DhrDataTableComponent = /** @class */ (function () {
    function DhrDataTableComponent(confirmDialog) {
        this.confirmDialog = confirmDialog;
        this.deleteEvent = new EventEmitter();
        this.duplicateEvent = new EventEmitter();
        this.viewEvent = new EventEmitter();
        this.downloadEvent = new EventEmitter();
        this.shareEvent = new EventEmitter();
    }
    DhrDataTableComponent.prototype.ngOnInit = function () {
    };
    DhrDataTableComponent.prototype.deleteRow = function (data) {
        var _this = this;
        this.rs = null;
        if (document.getElementById('myDropdown')) {
            document.getElementById('myDropdown').remove();
        }
        this.confirmDialog.confirm({
            message: "This will delete " + this.contentText + " '<span class=\"confrm-name\">" + data.name + "</span>' from your records. You can't undo this.",
            header: "Delete the " + this.contentText,
            acceptLabel: 'Delete',
            rejectLabel: 'Cancel',
            acceptIcon: null,
            rejectIcon: null,
            accept: function () {
                _this.deleteEvent.emit(data);
            },
            reject: function () {
            }
        });
    };
    DhrDataTableComponent.prototype.duplicateRow = function (data) {
        var _this = this;
        this.rs = null;
        if (document.getElementById('myDropdown')) {
            document.getElementById('myDropdown').remove();
        }
        this.confirmDialog.confirm({
            message: "This will create a copy of condenser '<span class=\"confrm-name\">" + data.name + "</span>'. You will be able to edit it later.",
            header: 'Duplicate the Condenser',
            acceptLabel: 'Duplicate',
            rejectLabel: 'Cancel',
            acceptIcon: null,
            rejectIcon: null,
            accept: function () {
                _this.duplicateEvent.emit(data);
            },
            reject: function () {
            }
        });
    };
    DhrDataTableComponent.prototype.viewRow = function (data) {
        this.viewEvent.emit(data);
        this.rs = null;
        if (document.getElementById('myDropdown')) {
            document.getElementById('myDropdown').remove();
        }
    };
    DhrDataTableComponent.prototype.downloadRow = function (data) {
        this.downloadEvent.emit(data);
        this.rs = null;
        if (document.getElementById('myDropdown')) {
            document.getElementById('myDropdown').remove();
        }
    };
    DhrDataTableComponent.prototype.shareRow = function (data) {
        this.shareEvent.emit(data);
        this.rs = null;
        if (document.getElementById('myDropdown')) {
            document.getElementById('myDropdown').remove();
        }
    };
    DhrDataTableComponent.prototype.showWrappedMenu = function (r, s) {
        if (document.querySelector('.dropdown-content') == undefined || null) {
            this.rs = s;
        }
        else {
            this.rs = null;
            document.getElementById('myDropdown').remove();
        }
    };
    DhrDataTableComponent.prototype.printFilteredUsers = function (e) {
        var data = { load: e };
        var event = new CustomEvent('dhr_table_filter_data', { detail: data });
        window.dispatchEvent(event);
    };
    // custom sorting for different field
    DhrDataTableComponent.prototype.customSort = function (event) {
        console.log(event);
        event.data.sort(function (data1, data2) {
            var value1 = data1[event.field];
            var value2 = data2[event.field];
            var result = null;
            console.log(value1, value2);
            if (value1 == null && value2 != null)
                result = -1;
            else if (value1 != null && value2 == null)
                result = 1;
            else if (value1 == null && value2 == null)
                result = 0;
            else if (event.field.includes('updatedAt')) {
                var v1 = new Date(value1);
                var v2 = new Date(value2);
                result = (v1 < v2) ? -1 : (v1 > v2) ? 1 : 0;
            }
            else if (typeof value1 === 'string' && typeof value2 === 'string')
                result = value1.localeCompare(value2);
            else
                result = (value1 < value2) ? -1 : (value1 > value2) ? 1 : 0;
            return (event.order * result);
        });
    };
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
    return DhrDataTableComponent;
}());
export { DhrDataTableComponent };
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
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZGhyLWRhdGEtdGFibGUuY29tcG9uZW50LmpzIiwic291cmNlUm9vdCI6Im5nOi8vZGhyLWRhdGEtdGFibGUvIiwic291cmNlcyI6WyJsaWIvZGhyLWRhdGEtdGFibGUuY29tcG9uZW50LnRzIiwibGliL2Roci1kYXRhLXRhYmxlLmNvbXBvbmVudC5odG1sIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBLE9BQU8sRUFBRSxTQUFTLEVBQVUsS0FBSyxFQUFFLE1BQU0sRUFBRSxZQUFZLEVBQUUsTUFBTSxlQUFlLENBQUM7Ozs7Ozs7Ozs7SUNLdkUsOEJBQ0k7SUFBQSw2QkFBa0I7SUFBQSxZQUFjO0lBQUEscUJBQUk7SUFBQSw2QkFBeUI7SUFBQSxZQUFpQjtJQUFBLGlCQUFLO0lBQUEsaUJBQUs7SUFDeEYsK0JBQ0k7SUFBQSx3QkFBNEI7SUFDNUIsaUNBQ0o7SUFEa0MsNkxBQVMsc0NBQXFDLFVBQVUsQ0FBQyxJQUFDO0lBQXhGLGlCQUNKO0lBQUEsaUJBQU87SUFDWCxpQkFBTTs7O0lBTGdCLGVBQWM7SUFBZCx1Q0FBYztJQUE2QixlQUFpQjtJQUFqQiwwQ0FBaUI7SUFHZSxlQUFpQztJQUFqQyxzREFBaUM7OztJQU8zRyxpQ0FBaUU7OztJQUFqQyxvQ0FBbUI7OztJQUR0RSw4QkFDSTtJQUFBLFlBQWU7SUFBQSx3R0FBb0Q7SUFDdkUsaUJBQUs7OztJQUYyQixpRkFBb0Q7SUFDaEYsZUFBZTtJQUFmLDhDQUFlO0lBQVksZUFBbUI7SUFBbkIscUNBQW1COzs7SUFFbEQseUJBQThDOzs7SUFKbEQsOEJBQ0k7SUFBQSxtRkFDSTtJQUVKLG1GQUF5QztJQUM3QyxpQkFBSzs7OztJQUpHLGVBQTJCO0lBQTNCLG9DQUEyQjtJQUdSLGVBQWlCO0lBQWpCLHVDQUFpQjs7O0lBTXhDLDBCQUNRO0lBQUEsWUFDUjs7SUFBQSxpQkFBSzs7OztJQURHLGVBQ1I7SUFEUSx1SkFDUjs7OztJQUtNLDZCQUNpRTtJQUEzQixxUUFBMEI7SUFBQyx3QkFBNEM7SUFBQSxnQ0FBZ0I7SUFBQSxpQkFBSTs7OztJQUNqSSw2QkFDc0U7SUFBaEMsMFFBQStCO0lBQUMsd0JBQWdEO0lBQUEscUNBQXFCO0lBQUEsaUJBQUk7Ozs7SUFDL0ksNkJBQ21FO0lBQTdCLHVRQUE0QjtJQUFDLHdCQUFnRDtJQUFBLGtDQUFrQjtJQUFBLGlCQUFJOzs7O0lBQ3pJLDZCQUNxRTtJQUEvQix5UUFBOEI7SUFBQyx3QkFBaUQ7SUFBQSxvQ0FBb0I7SUFBQSxpQkFBSTs7OztJQUM5SSw2QkFDa0U7SUFBNUIsc1FBQTJCO0lBQUMsd0JBQWtEO0lBQUEsaUNBQWlCO0lBQUEsaUJBQUk7OztJQVYzSSwrQkFDRTtJQUFBLDRGQUNpRTtJQUNqRSw0RkFDc0U7SUFDdEUsNEZBQ21FO0lBQ25FLDRGQUNxRTtJQUNyRSw0RkFDa0U7SUFDcEUsaUJBQU07OztJQVZELGVBQXNCO0lBQXRCLDZDQUFzQjtJQUV0QixlQUEyQjtJQUEzQixrREFBMkI7SUFFM0IsZUFBd0I7SUFBeEIsK0NBQXdCO0lBRXhCLGVBQTBCO0lBQTFCLGlEQUEwQjtJQUUxQixlQUF1QjtJQUF2Qiw4Q0FBdUI7Ozs7SUFaaEMsOEJBQ0U7SUFBQSxrQ0FBNEs7SUFBL0MscVVBQXFDO0lBQUMsaUJBQVM7SUFDNUssK0JBQ0U7SUFBQSwwRkFDRTtJQVdKLGlCQUFNO0lBQ1IsaUJBQUs7Ozs7SUFiNkMsZUFBZ0I7SUFBaEIsMkNBQWdCOzs7SUFQdEUsOEJBQ0k7SUFBQSxtRkFDUTtJQUVSLG1GQUNFO0lBZ0JOLGlCQUFLOzs7O0lBcEJHLGVBQTJCO0lBQTNCLHFDQUEyQjtJQUdSLGVBQWlCO0lBQWpCLHVDQUFpQjs7QUR4QnBEO0lBc0NFLCtCQUFtQixhQUFrQztRQUFsQyxrQkFBYSxHQUFiLGFBQWEsQ0FBcUI7UUFQM0MsZ0JBQVcsR0FBRyxJQUFJLFlBQVksRUFBRSxDQUFDO1FBQ2pDLG1CQUFjLEdBQUcsSUFBSSxZQUFZLEVBQUUsQ0FBQztRQUNwQyxjQUFTLEdBQUcsSUFBSSxZQUFZLEVBQUUsQ0FBQztRQUMvQixrQkFBYSxHQUFHLElBQUksWUFBWSxFQUFFLENBQUM7UUFDbkMsZUFBVSxHQUFHLElBQUksWUFBWSxFQUFFLENBQUM7SUFHZSxDQUFDO0lBRTFELHdDQUFRLEdBQVI7SUFDQSxDQUFDO0lBQ0QseUNBQVMsR0FBVCxVQUFVLElBQUk7UUFBZCxpQkFrQkM7UUFqQkMsSUFBSSxDQUFDLEVBQUUsR0FBRyxJQUFJLENBQUM7UUFDZixJQUFJLFFBQVEsQ0FBQyxjQUFjLENBQUMsWUFBWSxDQUFDLEVBQUU7WUFDekMsUUFBUSxDQUFDLGNBQWMsQ0FBQyxZQUFZLENBQUMsQ0FBQyxNQUFNLEVBQUUsQ0FBQztTQUNoRDtRQUNELElBQUksQ0FBQyxhQUFhLENBQUMsT0FBTyxDQUFDO1lBQ3pCLE9BQU8sRUFBRSxzQkFBb0IsSUFBSSxDQUFDLFdBQVcsc0NBQStCLElBQUksQ0FBQyxJQUFJLHFEQUFrRDtZQUN2SSxNQUFNLEVBQUUsZ0JBQWMsSUFBSSxDQUFDLFdBQWE7WUFDeEMsV0FBVyxFQUFFLFFBQVE7WUFDckIsV0FBVyxFQUFFLFFBQVE7WUFDckIsVUFBVSxFQUFFLElBQUk7WUFDaEIsVUFBVSxFQUFFLElBQUk7WUFDaEIsTUFBTSxFQUFFO2dCQUNOLEtBQUksQ0FBQyxXQUFXLENBQUMsSUFBSSxDQUFDLElBQUksQ0FBQyxDQUFDO1lBQzlCLENBQUM7WUFDRCxNQUFNLEVBQUU7WUFDUixDQUFDO1NBQ0YsQ0FBQyxDQUFDO0lBQ0wsQ0FBQztJQUVELDRDQUFZLEdBQVosVUFBYSxJQUFJO1FBQWpCLGlCQWtCQztRQWpCQyxJQUFJLENBQUMsRUFBRSxHQUFHLElBQUksQ0FBQztRQUNmLElBQUksUUFBUSxDQUFDLGNBQWMsQ0FBQyxZQUFZLENBQUMsRUFBRTtZQUN6QyxRQUFRLENBQUMsY0FBYyxDQUFDLFlBQVksQ0FBQyxDQUFDLE1BQU0sRUFBRSxDQUFDO1NBQ2hEO1FBQ0QsSUFBSSxDQUFDLGFBQWEsQ0FBQyxPQUFPLENBQUM7WUFDekIsT0FBTyxFQUFFLHVFQUFtRSxJQUFJLENBQUMsSUFBSSxpREFBOEM7WUFDbkksTUFBTSxFQUFFLHlCQUF5QjtZQUNqQyxXQUFXLEVBQUUsV0FBVztZQUN4QixXQUFXLEVBQUUsUUFBUTtZQUNyQixVQUFVLEVBQUUsSUFBSTtZQUNoQixVQUFVLEVBQUUsSUFBSTtZQUNoQixNQUFNLEVBQUU7Z0JBQ04sS0FBSSxDQUFDLGNBQWMsQ0FBQyxJQUFJLENBQUMsSUFBSSxDQUFDLENBQUM7WUFDakMsQ0FBQztZQUNELE1BQU0sRUFBRTtZQUNSLENBQUM7U0FDRixDQUFDLENBQUM7SUFDTCxDQUFDO0lBRUQsdUNBQU8sR0FBUCxVQUFRLElBQUk7UUFDVixJQUFJLENBQUMsU0FBUyxDQUFDLElBQUksQ0FBQyxJQUFJLENBQUMsQ0FBQztRQUMxQixJQUFJLENBQUMsRUFBRSxHQUFHLElBQUksQ0FBQztRQUNmLElBQUksUUFBUSxDQUFDLGNBQWMsQ0FBQyxZQUFZLENBQUMsRUFBRTtZQUN6QyxRQUFRLENBQUMsY0FBYyxDQUFDLFlBQVksQ0FBQyxDQUFDLE1BQU0sRUFBRSxDQUFDO1NBQ2hEO0lBQ0gsQ0FBQztJQUVELDJDQUFXLEdBQVgsVUFBWSxJQUFJO1FBQ2QsSUFBSSxDQUFDLGFBQWEsQ0FBQyxJQUFJLENBQUMsSUFBSSxDQUFDLENBQUM7UUFDOUIsSUFBSSxDQUFDLEVBQUUsR0FBRyxJQUFJLENBQUM7UUFDZixJQUFJLFFBQVEsQ0FBQyxjQUFjLENBQUMsWUFBWSxDQUFDLEVBQUU7WUFDekMsUUFBUSxDQUFDLGNBQWMsQ0FBQyxZQUFZLENBQUMsQ0FBQyxNQUFNLEVBQUUsQ0FBQztTQUNoRDtJQUNILENBQUM7SUFFRCx3Q0FBUSxHQUFSLFVBQVMsSUFBSTtRQUNYLElBQUksQ0FBQyxVQUFVLENBQUMsSUFBSSxDQUFDLElBQUksQ0FBQyxDQUFDO1FBQzNCLElBQUksQ0FBQyxFQUFFLEdBQUcsSUFBSSxDQUFDO1FBQ2YsSUFBSSxRQUFRLENBQUMsY0FBYyxDQUFDLFlBQVksQ0FBQyxFQUFFO1lBQ3pDLFFBQVEsQ0FBQyxjQUFjLENBQUMsWUFBWSxDQUFDLENBQUMsTUFBTSxFQUFFLENBQUM7U0FDaEQ7SUFDSCxDQUFDO0lBRUQsK0NBQWUsR0FBZixVQUFnQixDQUFDLEVBQUUsQ0FBQztRQUNsQixJQUFJLFFBQVEsQ0FBQyxhQUFhLENBQUMsbUJBQW1CLENBQUMsSUFBSSxTQUFTLElBQUksSUFBSSxFQUFFO1lBQ3BFLElBQUksQ0FBQyxFQUFFLEdBQUcsQ0FBQyxDQUFDO1NBQ2I7YUFDSTtZQUNILElBQUksQ0FBQyxFQUFFLEdBQUcsSUFBSSxDQUFDO1lBQ2YsUUFBUSxDQUFDLGNBQWMsQ0FBQyxZQUFZLENBQUMsQ0FBQyxNQUFNLEVBQUUsQ0FBQztTQUNoRDtJQUNILENBQUM7SUFDRCxrREFBa0IsR0FBbEIsVUFBbUIsQ0FBQztRQUNsQixJQUFNLElBQUksR0FBRyxFQUFFLElBQUksRUFBRSxDQUFDLEVBQUUsQ0FBQztRQUN6QixJQUFNLEtBQUssR0FBRyxJQUFJLFdBQVcsQ0FBQyx1QkFBdUIsRUFBRSxFQUFFLE1BQU0sRUFBRSxJQUFJLEVBQUUsQ0FBQyxDQUFDO1FBQ3pFLE1BQU0sQ0FBQyxhQUFhLENBQUMsS0FBSyxDQUFDLENBQUM7SUFDOUIsQ0FBQztJQUVELHFDQUFxQztJQUNyQywwQ0FBVSxHQUFWLFVBQVcsS0FBZ0I7UUFDekIsT0FBTyxDQUFDLEdBQUcsQ0FBQyxLQUFLLENBQUMsQ0FBQTtRQUNsQixLQUFLLENBQUMsSUFBSSxDQUFDLElBQUksQ0FBQyxVQUFDLEtBQUssRUFBRSxLQUFLO1lBQzNCLElBQUksTUFBTSxHQUFHLEtBQUssQ0FBQyxLQUFLLENBQUMsS0FBSyxDQUFDLENBQUM7WUFDaEMsSUFBSSxNQUFNLEdBQUcsS0FBSyxDQUFDLEtBQUssQ0FBQyxLQUFLLENBQUMsQ0FBQztZQUNoQyxJQUFJLE1BQU0sR0FBRyxJQUFJLENBQUM7WUFDbEIsT0FBTyxDQUFDLEdBQUcsQ0FBQyxNQUFNLEVBQUUsTUFBTSxDQUFDLENBQUE7WUFDM0IsSUFBSSxNQUFNLElBQUksSUFBSSxJQUFJLE1BQU0sSUFBSSxJQUFJO2dCQUNsQyxNQUFNLEdBQUcsQ0FBQyxDQUFDLENBQUM7aUJBQ1QsSUFBSSxNQUFNLElBQUksSUFBSSxJQUFJLE1BQU0sSUFBSSxJQUFJO2dCQUN2QyxNQUFNLEdBQUcsQ0FBQyxDQUFDO2lCQUNSLElBQUksTUFBTSxJQUFJLElBQUksSUFBSSxNQUFNLElBQUksSUFBSTtnQkFDdkMsTUFBTSxHQUFHLENBQUMsQ0FBQztpQkFDUixJQUFJLEtBQUssQ0FBQyxLQUFLLENBQUMsUUFBUSxDQUFDLFdBQVcsQ0FBQyxFQUFFO2dCQUMxQyxJQUFJLEVBQUUsR0FBRyxJQUFJLElBQUksQ0FBQyxNQUFNLENBQUMsQ0FBQztnQkFDMUIsSUFBSSxFQUFFLEdBQUcsSUFBSSxJQUFJLENBQUMsTUFBTSxDQUFDLENBQUM7Z0JBQzFCLE1BQU0sR0FBRyxDQUFDLEVBQUUsR0FBRyxFQUFFLENBQUMsQ0FBQyxDQUFDLENBQUMsQ0FBQyxDQUFDLENBQUMsQ0FBQyxDQUFDLENBQUMsRUFBRSxHQUFHLEVBQUUsQ0FBQyxDQUFDLENBQUMsQ0FBQyxDQUFDLENBQUMsQ0FBQyxDQUFDLENBQUMsQ0FBQzthQUM3QztpQkFDSSxJQUFJLE9BQU8sTUFBTSxLQUFLLFFBQVEsSUFBSSxPQUFPLE1BQU0sS0FBSyxRQUFRO2dCQUMvRCxNQUFNLEdBQUcsTUFBTSxDQUFDLGFBQWEsQ0FBQyxNQUFNLENBQUMsQ0FBQzs7Z0JBRXRDLE1BQU0sR0FBRyxDQUFDLE1BQU0sR0FBRyxNQUFNLENBQUMsQ0FBQyxDQUFDLENBQUMsQ0FBQyxDQUFDLENBQUMsQ0FBQyxDQUFDLENBQUMsTUFBTSxHQUFHLE1BQU0sQ0FBQyxDQUFDLENBQUMsQ0FBQyxDQUFDLENBQUMsQ0FBQyxDQUFDLENBQUMsQ0FBQztZQUU5RCxPQUFPLENBQUMsS0FBSyxDQUFDLEtBQUssR0FBRyxNQUFNLENBQUMsQ0FBQztRQUNoQyxDQUFDLENBQUMsQ0FBQztJQUNMLENBQUM7OEZBOUlVLHFCQUFxQjs4REFBckIscUJBQXFCO1lDUmxDLHFDQUlNO1lBSndCLHVIQUFnQixzQkFBa0IsSUFBQyxrR0FBcUQsOEJBQTBCLElBQS9FO1lBSTNELHNGQUNFO1lBUUosc0ZBQ0k7WUFPSixzRkFFSTtZQXVCUixpQkFBVTtZQUNWLHFDQUFrRzs7WUEvQ3JGLGtDQUFnQixvQkFBQSx3QkFBQSw0QkFBQSxpQkFBQSxvREFBQSw4Q0FBQSw4QkFBQSxrQ0FBQSw4QkFBQSw0QkFBQSw0QkFBQSw4Q0FBQTtZQStDWixlQUFrQjtZQUFsQixnQ0FBa0Isd0JBQUE7O2dDRC9DbkM7Q0F3SkMsQUFySkQsSUFxSkM7U0FoSlkscUJBQXFCO2tEQUFyQixxQkFBcUI7Y0FMakMsU0FBUztlQUFDO2dCQUNULFFBQVEsRUFBRSxnQkFBZ0I7Z0JBQzFCLFdBQVcsRUFBRSxpQ0FBaUM7Z0JBQzlDLFNBQVMsRUFBRSxDQUFDLGdDQUFnQyxDQUFDO2FBQzlDOztrQkFHRSxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFFTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFDTCxLQUFLOztrQkFFTCxNQUFNOztrQkFDTixNQUFNOztrQkFDTixNQUFNOztrQkFDTixNQUFNOztrQkFDTixNQUFNIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgQ29tcG9uZW50LCBPbkluaXQsIElucHV0LCBPdXRwdXQsIEV2ZW50RW1pdHRlciB9IGZyb20gJ0Bhbmd1bGFyL2NvcmUnO1xyXG5pbXBvcnQgeyBDb25maXJtYXRpb25TZXJ2aWNlLCBTb3J0RXZlbnQgfSBmcm9tICdwcmltZW5nL2FwaSc7XHJcblxyXG5AQ29tcG9uZW50KHtcclxuICBzZWxlY3RvcjogJ2Roci1kYXRhLXRhYmxlJyxcclxuICB0ZW1wbGF0ZVVybDogJy4vZGhyLWRhdGEtdGFibGUuY29tcG9uZW50Lmh0bWwnLFxyXG4gIHN0eWxlVXJsczogWycuL2Roci1kYXRhLXRhYmxlLmNvbXBvbmVudC5jc3MnXVxyXG59KVxyXG5leHBvcnQgY2xhc3MgRGhyRGF0YVRhYmxlQ29tcG9uZW50IGltcGxlbWVudHMgT25Jbml0IHtcclxuXHJcbiAgQElucHV0KCkgY29sczogc3RyaW5nW107XHJcbiAgQElucHV0KCkgdGFibGVEYXRhOiBhbnlbXTtcclxuICBASW5wdXQoKSBwYWdpbmF0b3I6IGJvb2xlYW47XHJcbiAgQElucHV0KCkgc2VhcmNoUGxhY2VIb2xkZXI6IHN0cmluZztcclxuICBASW5wdXQoKSByb3c6IG51bWJlcjtcclxuICBASW5wdXQoKSBzaG93Q3VycmVudFBhZ2VSZXBvcnQ6IGJvb2xlYW47XHJcbiAgQElucHV0KCkgcm93c1BlclBhZ2VPcHRpb25zOiBhbnlbXTtcclxuICBASW5wdXQoKSBzY3JvbGxhYmxlOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNjcm9sbEhlaWdodDogc3RyaW5nO1xyXG4gIEBJbnB1dCgpIHN0eWxlQ2xhc3M6IHN0cmluZztcclxuICBASW5wdXQoKSBzb3J0RmllbGQ6IHN0cmluZztcclxuICBASW5wdXQoKSBzb3J0T3JkZXI6IG51bWJlcjtcclxuICBASW5wdXQoKSBnbG9iYWxGaWx0ZXJGaWVsZHM6IGFueVtdO1xyXG4gIEBJbnB1dCgpIGhlYWRlcnRleHQ6IHN0cmluZztcclxuICBASW5wdXQoKSBzdWJIZWFkZXJ0ZXh0OiBzdHJpbmc7XHJcbiAgQElucHV0KCkgY29udGVudFRleHQ6IHN0cmluZztcclxuICBASW5wdXQoKSBhY3Rpb25idG46IGJvb2xlYW47XHJcblxyXG4gIEBJbnB1dCgpIHNob3dEZWxldGVBdG5CdG46IGJvb2xlYW47XHJcbiAgQElucHV0KCkgc2hvd0R1cGxpY2F0ZUF0bkJ0bjogYm9vbGVhbjtcclxuICBASW5wdXQoKSBzaG93Vmlld0F0bkJ0bjogYm9vbGVhbjtcclxuICBASW5wdXQoKSBzaG93RG93bmxvYWRBdG5CdG46IGJvb2xlYW47XHJcbiAgQElucHV0KCkgc2hvd1NoYXJlQXRuQnRuOiBib29sZWFuO1xyXG5cclxuICBAT3V0cHV0KCkgZGVsZXRlRXZlbnQgPSBuZXcgRXZlbnRFbWl0dGVyKCk7XHJcbiAgQE91dHB1dCgpIGR1cGxpY2F0ZUV2ZW50ID0gbmV3IEV2ZW50RW1pdHRlcigpO1xyXG4gIEBPdXRwdXQoKSB2aWV3RXZlbnQgPSBuZXcgRXZlbnRFbWl0dGVyKCk7XHJcbiAgQE91dHB1dCgpIGRvd25sb2FkRXZlbnQgPSBuZXcgRXZlbnRFbWl0dGVyKCk7XHJcbiAgQE91dHB1dCgpIHNoYXJlRXZlbnQgPSBuZXcgRXZlbnRFbWl0dGVyKCk7XHJcbiAgcnM6IGFueTtcclxuXHJcbiAgY29uc3RydWN0b3IocHVibGljIGNvbmZpcm1EaWFsb2c6IENvbmZpcm1hdGlvblNlcnZpY2UpIHsgfVxyXG5cclxuICBuZ09uSW5pdCgpOiB2b2lkIHtcclxuICB9XHJcbiAgZGVsZXRlUm93KGRhdGEpIHtcclxuICAgIHRoaXMucnMgPSBudWxsO1xyXG4gICAgaWYgKGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdteURyb3Bkb3duJykpIHtcclxuICAgICAgZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoJ215RHJvcGRvd24nKS5yZW1vdmUoKTtcclxuICAgIH1cclxuICAgIHRoaXMuY29uZmlybURpYWxvZy5jb25maXJtKHtcclxuICAgICAgbWVzc2FnZTogYFRoaXMgd2lsbCBkZWxldGUgJHt0aGlzLmNvbnRlbnRUZXh0fSAnPHNwYW4gY2xhc3M9XCJjb25mcm0tbmFtZVwiPiR7ZGF0YS5uYW1lfTwvc3Bhbj4nIGZyb20geW91ciByZWNvcmRzLiBZb3UgY2FuJ3QgdW5kbyB0aGlzLmAsXHJcbiAgICAgIGhlYWRlcjogYERlbGV0ZSB0aGUgJHt0aGlzLmNvbnRlbnRUZXh0fWAsXHJcbiAgICAgIGFjY2VwdExhYmVsOiAnRGVsZXRlJyxcclxuICAgICAgcmVqZWN0TGFiZWw6ICdDYW5jZWwnLFxyXG4gICAgICBhY2NlcHRJY29uOiBudWxsLFxyXG4gICAgICByZWplY3RJY29uOiBudWxsLFxyXG4gICAgICBhY2NlcHQ6ICgpID0+IHtcclxuICAgICAgICB0aGlzLmRlbGV0ZUV2ZW50LmVtaXQoZGF0YSk7XHJcbiAgICAgIH0sXHJcbiAgICAgIHJlamVjdDogKCkgPT4ge1xyXG4gICAgICB9XHJcbiAgICB9KTtcclxuICB9XHJcblxyXG4gIGR1cGxpY2F0ZVJvdyhkYXRhKSB7XHJcbiAgICB0aGlzLnJzID0gbnVsbDtcclxuICAgIGlmIChkb2N1bWVudC5nZXRFbGVtZW50QnlJZCgnbXlEcm9wZG93bicpKSB7XHJcbiAgICAgIGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdteURyb3Bkb3duJykucmVtb3ZlKCk7XHJcbiAgICB9XHJcbiAgICB0aGlzLmNvbmZpcm1EaWFsb2cuY29uZmlybSh7XHJcbiAgICAgIG1lc3NhZ2U6IGBUaGlzIHdpbGwgY3JlYXRlIGEgY29weSBvZiBjb25kZW5zZXIgJzxzcGFuIGNsYXNzPVwiY29uZnJtLW5hbWVcIj4ke2RhdGEubmFtZX08L3NwYW4+Jy4gWW91IHdpbGwgYmUgYWJsZSB0byBlZGl0IGl0IGxhdGVyLmAsXHJcbiAgICAgIGhlYWRlcjogJ0R1cGxpY2F0ZSB0aGUgQ29uZGVuc2VyJyxcclxuICAgICAgYWNjZXB0TGFiZWw6ICdEdXBsaWNhdGUnLFxyXG4gICAgICByZWplY3RMYWJlbDogJ0NhbmNlbCcsXHJcbiAgICAgIGFjY2VwdEljb246IG51bGwsXHJcbiAgICAgIHJlamVjdEljb246IG51bGwsXHJcbiAgICAgIGFjY2VwdDogKCkgPT4ge1xyXG4gICAgICAgIHRoaXMuZHVwbGljYXRlRXZlbnQuZW1pdChkYXRhKTtcclxuICAgICAgfSxcclxuICAgICAgcmVqZWN0OiAoKSA9PiB7XHJcbiAgICAgIH1cclxuICAgIH0pO1xyXG4gIH1cclxuXHJcbiAgdmlld1JvdyhkYXRhKSB7XHJcbiAgICB0aGlzLnZpZXdFdmVudC5lbWl0KGRhdGEpO1xyXG4gICAgdGhpcy5ycyA9IG51bGw7XHJcbiAgICBpZiAoZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoJ215RHJvcGRvd24nKSkge1xyXG4gICAgICBkb2N1bWVudC5nZXRFbGVtZW50QnlJZCgnbXlEcm9wZG93bicpLnJlbW92ZSgpO1xyXG4gICAgfVxyXG4gIH1cclxuXHJcbiAgZG93bmxvYWRSb3coZGF0YSkge1xyXG4gICAgdGhpcy5kb3dubG9hZEV2ZW50LmVtaXQoZGF0YSk7XHJcbiAgICB0aGlzLnJzID0gbnVsbDtcclxuICAgIGlmIChkb2N1bWVudC5nZXRFbGVtZW50QnlJZCgnbXlEcm9wZG93bicpKSB7XHJcbiAgICAgIGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdteURyb3Bkb3duJykucmVtb3ZlKCk7XHJcbiAgICB9XHJcbiAgfVxyXG5cclxuICBzaGFyZVJvdyhkYXRhKSB7XHJcbiAgICB0aGlzLnNoYXJlRXZlbnQuZW1pdChkYXRhKTtcclxuICAgIHRoaXMucnMgPSBudWxsO1xyXG4gICAgaWYgKGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdteURyb3Bkb3duJykpIHtcclxuICAgICAgZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoJ215RHJvcGRvd24nKS5yZW1vdmUoKTtcclxuICAgIH1cclxuICB9XHJcblxyXG4gIHNob3dXcmFwcGVkTWVudShyLCBzKSB7XHJcbiAgICBpZiAoZG9jdW1lbnQucXVlcnlTZWxlY3RvcignLmRyb3Bkb3duLWNvbnRlbnQnKSA9PSB1bmRlZmluZWQgfHwgbnVsbCkge1xyXG4gICAgICB0aGlzLnJzID0gcztcclxuICAgIH1cclxuICAgIGVsc2Uge1xyXG4gICAgICB0aGlzLnJzID0gbnVsbDtcclxuICAgICAgZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoJ215RHJvcGRvd24nKS5yZW1vdmUoKTtcclxuICAgIH1cclxuICB9XHJcbiAgcHJpbnRGaWx0ZXJlZFVzZXJzKGUpIHtcclxuICAgIGNvbnN0IGRhdGEgPSB7IGxvYWQ6IGUgfTtcclxuICAgIGNvbnN0IGV2ZW50ID0gbmV3IEN1c3RvbUV2ZW50KCdkaHJfdGFibGVfZmlsdGVyX2RhdGEnLCB7IGRldGFpbDogZGF0YSB9KTtcclxuICAgIHdpbmRvdy5kaXNwYXRjaEV2ZW50KGV2ZW50KTtcclxuICB9XHJcblxyXG4gIC8vIGN1c3RvbSBzb3J0aW5nIGZvciBkaWZmZXJlbnQgZmllbGRcclxuICBjdXN0b21Tb3J0KGV2ZW50OiBTb3J0RXZlbnQpIHtcclxuICAgIGNvbnNvbGUubG9nKGV2ZW50KVxyXG4gICAgZXZlbnQuZGF0YS5zb3J0KChkYXRhMSwgZGF0YTIpID0+IHtcclxuICAgICAgbGV0IHZhbHVlMSA9IGRhdGExW2V2ZW50LmZpZWxkXTtcclxuICAgICAgbGV0IHZhbHVlMiA9IGRhdGEyW2V2ZW50LmZpZWxkXTtcclxuICAgICAgbGV0IHJlc3VsdCA9IG51bGw7XHJcbiAgICAgIGNvbnNvbGUubG9nKHZhbHVlMSwgdmFsdWUyKVxyXG4gICAgICBpZiAodmFsdWUxID09IG51bGwgJiYgdmFsdWUyICE9IG51bGwpXHJcbiAgICAgICAgcmVzdWx0ID0gLTE7XHJcbiAgICAgIGVsc2UgaWYgKHZhbHVlMSAhPSBudWxsICYmIHZhbHVlMiA9PSBudWxsKVxyXG4gICAgICAgIHJlc3VsdCA9IDE7XHJcbiAgICAgIGVsc2UgaWYgKHZhbHVlMSA9PSBudWxsICYmIHZhbHVlMiA9PSBudWxsKVxyXG4gICAgICAgIHJlc3VsdCA9IDA7XHJcbiAgICAgIGVsc2UgaWYgKGV2ZW50LmZpZWxkLmluY2x1ZGVzKCd1cGRhdGVkQXQnKSkge1xyXG4gICAgICAgIGxldCB2MSA9IG5ldyBEYXRlKHZhbHVlMSk7XHJcbiAgICAgICAgbGV0IHYyID0gbmV3IERhdGUodmFsdWUyKTtcclxuICAgICAgICByZXN1bHQgPSAodjEgPCB2MikgPyAtMSA6ICh2MSA+IHYyKSA/IDEgOiAwO1xyXG4gICAgICB9XHJcbiAgICAgIGVsc2UgaWYgKHR5cGVvZiB2YWx1ZTEgPT09ICdzdHJpbmcnICYmIHR5cGVvZiB2YWx1ZTIgPT09ICdzdHJpbmcnKVxyXG4gICAgICAgIHJlc3VsdCA9IHZhbHVlMS5sb2NhbGVDb21wYXJlKHZhbHVlMik7XHJcbiAgICAgIGVsc2VcclxuICAgICAgICByZXN1bHQgPSAodmFsdWUxIDwgdmFsdWUyKSA/IC0xIDogKHZhbHVlMSA+IHZhbHVlMikgPyAxIDogMDtcclxuXHJcbiAgICAgIHJldHVybiAoZXZlbnQub3JkZXIgKiByZXN1bHQpO1xyXG4gICAgfSk7XHJcbiAgfVxyXG5cclxufSIsIjxwLXRhYmxlICNkdCBbY29sdW1uc109XCJjb2xzXCIgKHNvcnRGdW5jdGlvbik9XCJjdXN0b21Tb3J0KCRldmVudClcIiBbY3VzdG9tU29ydF09XCJ0cnVlXCIgW3ZhbHVlXT1cInRhYmxlRGF0YVwiIChvbkZpbHRlcik9XCJwcmludEZpbHRlcmVkVXNlcnMoJGV2ZW50KVwiIFtwYWdpbmF0b3JdPVwicGFnaW5hdG9yXCIgW3Jvd3NdPVwicm93XCIgW3Nob3dDdXJyZW50UGFnZVJlcG9ydF09XCJzaG93Q3VycmVudFBhZ2VSZXBvcnRcIlxyXG4gICAgY3VycmVudFBhZ2VSZXBvcnRUZW1wbGF0ZT1cIlNob3dpbmcge2ZpcnN0fSB0byB7bGFzdH0gb2Yge3RvdGFsUmVjb3Jkc30gZW50cmllc1wiIFtyb3dzUGVyUGFnZU9wdGlvbnNdPVwicm93c1BlclBhZ2VPcHRpb25zXCJcclxuICAgIFtzY3JvbGxhYmxlXT1cInNjcm9sbGFibGVcIiBbc2Nyb2xsSGVpZ2h0XT1cInNjcm9sbEhlaWdodFwiIFtzdHlsZUNsYXNzXT1cInN0eWxlQ2xhc3NcIiBbc29ydEZpZWxkXT1cInNvcnRGaWVsZFwiIFtzb3J0T3JkZXJdPVwic29ydE9yZGVyXCIgXHJcbiAgICBbZ2xvYmFsRmlsdGVyRmllbGRzXT1cImdsb2JhbEZpbHRlckZpZWxkc1wiPlxyXG4gICAgICA8bmctdGVtcGxhdGUgcFRlbXBsYXRlPVwiY2FwdGlvblwiPlxyXG4gICAgICAgIDxkaXYgY2xhc3M9XCJwLWQtZmxleCBwLWFpLWNlbnRlciBwLWpjLWJldHdlZW5cIj5cclxuICAgICAgICAgICAgPGg0IGNsYXNzPVwicC1tLTBcIj57e2hlYWRlcnRleHR9fTxicj48aDUgc3R5bGU9XCJtYXJnaW46IDNweDtcIj57e3N1YkhlYWRlcnRleHR9fTwvaDU+PC9oND4gXHJcbiAgICAgICAgICAgIDxzcGFuIGNsYXNzPVwicC1pbnB1dC1pY29uLWxlZnRcIj5cclxuICAgICAgICAgICAgICAgIDxpIGNsYXNzPVwicGkgcGktc2VhcmNoXCI+PC9pPlxyXG4gICAgICAgICAgICAgICAgPGlucHV0IHBJbnB1dFRleHQgdHlwZT1cInRleHRcIiAoaW5wdXQpPVwiZHQuZmlsdGVyR2xvYmFsKCRldmVudC50YXJnZXQudmFsdWUsICdjb250YWlucycpXCIgW3BsYWNlaG9sZGVyXT1cInNlYXJjaFBsYWNlSG9sZGVyXCIgLz5cclxuICAgICAgICAgICAgPC9zcGFuPlxyXG4gICAgICAgIDwvZGl2PlxyXG4gICAgPC9uZy10ZW1wbGF0ZT5cclxuICAgIDxuZy10ZW1wbGF0ZSBwVGVtcGxhdGU9XCJoZWFkZXJcIiBsZXQtY29sdW1ucz5cclxuICAgICAgICA8dHIgc3R5bGU9XCJmb250LXNpemU6IDEzcHg7XCI+XHJcbiAgICAgICAgICAgIDx0aCAqbmdGb3I9XCJsZXQgY29sIG9mIGNvbHVtbnNcIiBwU29ydGFibGVDb2x1bW49XCJ7e2NvbC5zb3J0T3B0ID8gY29sLmZpZWxkIDogbnVsbH19XCI+XHJcbiAgICAgICAgICAgICAgICB7e2NvbC5oZWFkZXJ9fSA8cC1zb3J0SWNvbiAqbmdJZj1cImNvbC5zb3J0T3B0XCIgW2ZpZWxkXT1cImNvbC5maWVsZFwiPjwvcC1zb3J0SWNvbj5cclxuICAgICAgICAgICAgPC90aD5cclxuICAgICAgICAgICAgPHRoIHN0eWxlPVwid2lkdGg6NXJlbVwiICpuZ0lmPVwiYWN0aW9uYnRuXCI+PC90aD5cclxuICAgICAgICA8L3RyPlxyXG4gICAgPC9uZy10ZW1wbGF0ZT5cclxuICAgIDxuZy10ZW1wbGF0ZSBwVGVtcGxhdGU9XCJib2R5XCIgbGV0LXJvd0RhdGEgbGV0LXJpPVwicm93SW5kZXhcIiBsZXQtY29sdW1ucz1cImNvbHVtbnNcIj5cclxuICAgICAgICBcclxuICAgICAgICA8dHIgc3R5bGU9XCJmb250LXNpemU6IDEycHg7XCI+XHJcbiAgICAgICAgICAgIDx0ZCAqbmdGb3I9XCJsZXQgY29sIG9mIGNvbHVtbnNcIiA+XHJcbiAgICAgICAgICAgICAgICAgICAge3tjb2wudHlwZSAhPSAnZGF0ZScgPyByb3dEYXRhW2NvbC5maWVsZF0gOiByb3dEYXRhW2NvbC5maWVsZF0gKiAxMDAwfCAgZGF0ZTonc2hvcnQnfX1cclxuICAgICAgICAgICAgPC90ZD5cclxuICAgICAgICAgICAgPHRkIHN0eWxlPVwid2lkdGg6NXJlbVwiICpuZ0lmPVwiYWN0aW9uYnRuXCI+XHJcbiAgICAgICAgICAgICAgPGJ1dHRvbiBzdHlsZT1cInBvc2l0aW9uOiByZWxhdGl2ZTtcIiBwQnV0dG9uIHBSaXBwbGUgaWNvbj1cInBpIHBpLWVsbGlwc2lzLXZcIiBjbGFzcz1cInAtYnV0dG9uLXJvdW5kZWQgcC1idXR0b24tc3VjY2VzcyBwLW1yLTJcIiAoY2xpY2spPVwic2hvd1dyYXBwZWRNZW51KHJvd0RhdGEscmkpXCI+PC9idXR0b24+XHJcbiAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cIm1lbnVjXCIgc3R5bGU9XCJwb3NpdGlvbjogYWJzb2x1dGVcIj5cclxuICAgICAgICAgICAgICAgIDxkaXYgaWQ9XCJteURyb3Bkb3duXCIgY2xhc3M9XCJkcm9wZG93bi1jb250ZW50XCIgKm5nSWY9XCJyaSA9PSByc1wiPlxyXG4gICAgICAgICAgICAgICAgICA8YSAqbmdJZj1cInNob3dWaWV3QXRuQnRuXCIgb25Nb3VzZU92ZXI9XCJ0aGlzLnN0eWxlLmNvbG9yPScjMzJjYWZjJ1wiXHJcbiAgICAgICAgICAgICAgICAgIG9uTW91c2VPdXQ9XCJ0aGlzLnN0eWxlLmNvbG9yPSdibGFjaydcIiAoY2xpY2spPVwidmlld1Jvdyhyb3dEYXRhKVwiPjxpIGNsYXNzPVwiZmEgZmEtZXllXCIgYXJpYS1oaWRkZW49XCJ0cnVlXCI+PC9pPiZuYnNwOyZuYnNwO1ZpZXc8L2E+XHJcbiAgICAgICAgICAgICAgICAgIDxhICpuZ0lmPVwic2hvd0R1cGxpY2F0ZUF0bkJ0blwiIG9uTW91c2VPdmVyPVwidGhpcy5zdHlsZS5jb2xvcj0nIzMyY2FmYydcIlxyXG4gICAgICAgICAgICAgICAgICBvbk1vdXNlT3V0PVwidGhpcy5zdHlsZS5jb2xvcj0nYmxhY2snXCIgKGNsaWNrKT1cImR1cGxpY2F0ZVJvdyhyb3dEYXRhKVwiPjxpIGNsYXNzPVwiZmEgZmEtZmlsZXMtb1wiIGFyaWEtaGlkZGVuPVwidHJ1ZVwiPjwvaT4mbmJzcDsmbmJzcDtEdXBsaWNhdGU8L2E+XHJcbiAgICAgICAgICAgICAgICAgIDxhICpuZ0lmPVwic2hvd0RlbGV0ZUF0bkJ0blwiIG9uTW91c2VPdmVyPVwidGhpcy5zdHlsZS5jb2xvcj0ncmVkJ1wiXHJcbiAgICAgICAgICAgICAgICAgIG9uTW91c2VPdXQ9XCJ0aGlzLnN0eWxlLmNvbG9yPSdibGFjaydcIiAoY2xpY2spPVwiZGVsZXRlUm93KHJvd0RhdGEpXCI+PGkgY2xhc3M9XCJmYSBmYS10cmFzaC1vXCIgYXJpYS1oaWRkZW49XCJ0cnVlXCI+PC9pPiZuYnNwOyZuYnNwO0RlbGV0ZTwvYT5cclxuICAgICAgICAgICAgICAgICAgPGEgKm5nSWY9XCJzaG93RG93bmxvYWRBdG5CdG5cIiBvbk1vdXNlT3Zlcj1cInRoaXMuc3R5bGUuY29sb3I9JyNmY2JhMDMnXCJcclxuICAgICAgICAgICAgICAgICAgb25Nb3VzZU91dD1cInRoaXMuc3R5bGUuY29sb3I9J2JsYWNrJ1wiIChjbGljayk9XCJkb3dubG9hZFJvdyhyb3dEYXRhKVwiPjxpIGNsYXNzPVwiZmEgZmEtZG93bmxvYWRcIiBhcmlhLWhpZGRlbj1cInRydWVcIj48L2k+Jm5ic3A7Jm5ic3A7RG93bmxvYWQ8L2E+XHJcbiAgICAgICAgICAgICAgICAgIDxhICpuZ0lmPVwic2hvd1NoYXJlQXRuQnRuXCIgb25Nb3VzZU92ZXI9XCJ0aGlzLnN0eWxlLmNvbG9yPScjZmNiYTAzJ1wiXHJcbiAgICAgICAgICAgICAgICAgIG9uTW91c2VPdXQ9XCJ0aGlzLnN0eWxlLmNvbG9yPSdibGFjaydcIiAoY2xpY2spPVwic2hhcmVSb3cocm93RGF0YSlcIj48aSBjbGFzcz1cImZhIGZhLXNoYXJlLWFsdFwiIGFyaWEtaGlkZGVuPVwidHJ1ZVwiPjwvaT4mbmJzcDsmbmJzcDtTaGFyZTwvYT5cclxuICAgICAgICAgICAgICAgIDwvZGl2PlxyXG4gICAgICAgICAgICAgIDwvZGl2PiAgXHJcbiAgICAgICAgICAgIDwvdGQ+XHJcbiAgICAgICAgPC90cj5cclxuICAgIDwvbmctdGVtcGxhdGU+XHJcbjwvcC10YWJsZT5cclxuPHAtY29uZmlybURpYWxvZyBbcG9zaXRpb25dPVwiJ3RvcCdcIiBbYmFzZVpJbmRleF09XCIxMDAwMDAwMFwiIGRlZmF1bHRGb2N1cz1cIm5vbmVcIj48L3AtY29uZmlybURpYWxvZz5cclxuXHJcbiJdfQ==