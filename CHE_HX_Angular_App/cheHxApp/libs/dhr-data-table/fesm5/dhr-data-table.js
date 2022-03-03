import { ɵɵdefineInjectable, ɵsetClassMetadata, Injectable, ɵɵgetCurrentView, ɵɵelementStart, ɵɵtext, ɵɵelement, ɵɵelementEnd, ɵɵlistener, ɵɵrestoreView, ɵɵnextContext, ɵɵreference, ɵɵadvance, ɵɵtextInterpolate, ɵɵproperty, ɵɵtemplate, ɵɵpropertyInterpolate, ɵɵtextInterpolate1, ɵɵpipe, ɵɵpipeBind2, EventEmitter, ɵɵdirectiveInject, ɵɵdefineComponent, Component, Input, Output, ɵɵdefineNgModule, ɵɵdefineInjector, ɵɵsetNgModuleScope, NgModule } from '@angular/core';
import { ConfirmationService, PrimeTemplate } from 'primeng/api';
import { Table, SortableColumn, SortIcon, TableModule } from 'primeng/table';
import { ConfirmDialog, ConfirmDialogModule } from 'primeng/confirmdialog';
import { InputText, InputTextModule } from 'primeng/inputtext';
import { NgForOf, NgIf, DatePipe, CommonModule } from '@angular/common';
import { ButtonDirective, ButtonModule } from 'primeng/button';
import { HttpClientModule } from '@angular/common/http';
import { SplitButtonModule } from 'primeng/splitbutton';
import { FormsModule } from '@angular/forms';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

var DhrDataTableService = /** @class */ (function () {
    function DhrDataTableService() {
    }
    DhrDataTableService.ɵfac = function DhrDataTableService_Factory(t) { return new (t || DhrDataTableService)(); };
    DhrDataTableService.ɵprov = ɵɵdefineInjectable({ token: DhrDataTableService, factory: DhrDataTableService.ɵfac, providedIn: 'root' });
    return DhrDataTableService;
}());
/*@__PURE__*/ (function () { ɵsetClassMetadata(DhrDataTableService, [{
        type: Injectable,
        args: [{
                providedIn: 'root'
            }]
    }], function () { return []; }, null); })();

function DhrDataTableComponent_ng_template_2_Template(rf, ctx) { if (rf & 1) {
    var _r5 = ɵɵgetCurrentView();
    ɵɵelementStart(0, "div", 6);
    ɵɵelementStart(1, "h4", 7);
    ɵɵtext(2);
    ɵɵelement(3, "br");
    ɵɵelementStart(4, "h5", 8);
    ɵɵtext(5);
    ɵɵelementEnd();
    ɵɵelementEnd();
    ɵɵelementStart(6, "span", 9);
    ɵɵelement(7, "i", 10);
    ɵɵelementStart(8, "input", 11);
    ɵɵlistener("input", function DhrDataTableComponent_ng_template_2_Template_input_input_8_listener($event) { ɵɵrestoreView(_r5); ɵɵnextContext(); var _r0 = ɵɵreference(1); return _r0.filterGlobal($event.target.value, "contains"); });
    ɵɵelementEnd();
    ɵɵelementEnd();
    ɵɵelementEnd();
} if (rf & 2) {
    var ctx_r1 = ɵɵnextContext();
    ɵɵadvance(2);
    ɵɵtextInterpolate(ctx_r1.headertext);
    ɵɵadvance(3);
    ɵɵtextInterpolate(ctx_r1.subHeadertext);
    ɵɵadvance(3);
    ɵɵproperty("placeholder", ctx_r1.searchPlaceHolder);
} }
function DhrDataTableComponent_ng_template_3_th_1_p_sortIcon_2_Template(rf, ctx) { if (rf & 1) {
    ɵɵelement(0, "p-sortIcon", 17);
} if (rf & 2) {
    var col_r9 = ɵɵnextContext().$implicit;
    ɵɵproperty("field", col_r9.field);
} }
function DhrDataTableComponent_ng_template_3_th_1_Template(rf, ctx) { if (rf & 1) {
    ɵɵelementStart(0, "th", 15);
    ɵɵtext(1);
    ɵɵtemplate(2, DhrDataTableComponent_ng_template_3_th_1_p_sortIcon_2_Template, 1, 1, "p-sortIcon", 16);
    ɵɵelementEnd();
} if (rf & 2) {
    var col_r9 = ctx.$implicit;
    ɵɵpropertyInterpolate("pSortableColumn", col_r9.sortOpt ? col_r9.field : null);
    ɵɵadvance(1);
    ɵɵtextInterpolate1(" ", col_r9.header, " ");
    ɵɵadvance(1);
    ɵɵproperty("ngIf", col_r9.sortOpt);
} }
function DhrDataTableComponent_ng_template_3_th_2_Template(rf, ctx) { if (rf & 1) {
    ɵɵelement(0, "th", 18);
} }
function DhrDataTableComponent_ng_template_3_Template(rf, ctx) { if (rf & 1) {
    ɵɵelementStart(0, "tr", 12);
    ɵɵtemplate(1, DhrDataTableComponent_ng_template_3_th_1_Template, 3, 3, "th", 13);
    ɵɵtemplate(2, DhrDataTableComponent_ng_template_3_th_2_Template, 1, 0, "th", 14);
    ɵɵelementEnd();
} if (rf & 2) {
    var columns_r6 = ctx.$implicit;
    var ctx_r2 = ɵɵnextContext();
    ɵɵadvance(1);
    ɵɵproperty("ngForOf", columns_r6);
    ɵɵadvance(1);
    ɵɵproperty("ngIf", ctx_r2.actionbtn);
} }
function DhrDataTableComponent_ng_template_4_td_1_Template(rf, ctx) { if (rf & 1) {
    ɵɵelementStart(0, "td");
    ɵɵtext(1);
    ɵɵpipe(2, "date");
    ɵɵelementEnd();
} if (rf & 2) {
    var col_r17 = ctx.$implicit;
    var rowData_r12 = ɵɵnextContext().$implicit;
    ɵɵadvance(1);
    ɵɵtextInterpolate1(" ", col_r17.type != "date" ? rowData_r12[col_r17.field] : ɵɵpipeBind2(2, 1, rowData_r12[col_r17.field] * 1000, "short"), " ");
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template(rf, ctx) { if (rf & 1) {
    var _r27 = ɵɵgetCurrentView();
    ɵɵelementStart(0, "a", 28);
    ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template_a_click_0_listener() { ɵɵrestoreView(_r27); var rowData_r12 = ɵɵnextContext(3).$implicit; var ctx_r25 = ɵɵnextContext(); return ctx_r25.viewRow(rowData_r12); });
    ɵɵelement(1, "i", 29);
    ɵɵtext(2, "\u00A0\u00A0View");
    ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template(rf, ctx) { if (rf & 1) {
    var _r30 = ɵɵgetCurrentView();
    ɵɵelementStart(0, "a", 28);
    ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template_a_click_0_listener() { ɵɵrestoreView(_r30); var rowData_r12 = ɵɵnextContext(3).$implicit; var ctx_r28 = ɵɵnextContext(); return ctx_r28.duplicateRow(rowData_r12); });
    ɵɵelement(1, "i", 30);
    ɵɵtext(2, "\u00A0\u00A0Duplicate");
    ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template(rf, ctx) { if (rf & 1) {
    var _r33 = ɵɵgetCurrentView();
    ɵɵelementStart(0, "a", 31);
    ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template_a_click_0_listener() { ɵɵrestoreView(_r33); var rowData_r12 = ɵɵnextContext(3).$implicit; var ctx_r31 = ɵɵnextContext(); return ctx_r31.deleteRow(rowData_r12); });
    ɵɵelement(1, "i", 32);
    ɵɵtext(2, "\u00A0\u00A0Delete");
    ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template(rf, ctx) { if (rf & 1) {
    var _r36 = ɵɵgetCurrentView();
    ɵɵelementStart(0, "a", 33);
    ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template_a_click_0_listener() { ɵɵrestoreView(_r36); var rowData_r12 = ɵɵnextContext(3).$implicit; var ctx_r34 = ɵɵnextContext(); return ctx_r34.downloadRow(rowData_r12); });
    ɵɵelement(1, "i", 34);
    ɵɵtext(2, "\u00A0\u00A0Download");
    ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template(rf, ctx) { if (rf & 1) {
    var _r39 = ɵɵgetCurrentView();
    ɵɵelementStart(0, "a", 33);
    ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template_a_click_0_listener() { ɵɵrestoreView(_r39); var rowData_r12 = ɵɵnextContext(3).$implicit; var ctx_r37 = ɵɵnextContext(); return ctx_r37.shareRow(rowData_r12); });
    ɵɵelement(1, "i", 35);
    ɵɵtext(2, "\u00A0\u00A0Share");
    ɵɵelementEnd();
} }
function DhrDataTableComponent_ng_template_4_td_2_div_3_Template(rf, ctx) { if (rf & 1) {
    ɵɵelementStart(0, "div", 24);
    ɵɵtemplate(1, DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template, 3, 0, "a", 25);
    ɵɵtemplate(2, DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template, 3, 0, "a", 25);
    ɵɵtemplate(3, DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template, 3, 0, "a", 26);
    ɵɵtemplate(4, DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template, 3, 0, "a", 27);
    ɵɵtemplate(5, DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template, 3, 0, "a", 27);
    ɵɵelementEnd();
} if (rf & 2) {
    var ctx_r19 = ɵɵnextContext(3);
    ɵɵadvance(1);
    ɵɵproperty("ngIf", ctx_r19.showViewAtnBtn);
    ɵɵadvance(1);
    ɵɵproperty("ngIf", ctx_r19.showDuplicateAtnBtn);
    ɵɵadvance(1);
    ɵɵproperty("ngIf", ctx_r19.showDeleteAtnBtn);
    ɵɵadvance(1);
    ɵɵproperty("ngIf", ctx_r19.showDownloadAtnBtn);
    ɵɵadvance(1);
    ɵɵproperty("ngIf", ctx_r19.showShareAtnBtn);
} }
function DhrDataTableComponent_ng_template_4_td_2_Template(rf, ctx) { if (rf & 1) {
    var _r42 = ɵɵgetCurrentView();
    ɵɵelementStart(0, "td", 18);
    ɵɵelementStart(1, "button", 21);
    ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_Template_button_click_1_listener() { ɵɵrestoreView(_r42); var ctx_r41 = ɵɵnextContext(); var rowData_r12 = ctx_r41.$implicit; var ri_r13 = ctx_r41.rowIndex; var ctx_r40 = ɵɵnextContext(); return ctx_r40.showWrappedMenu(rowData_r12, ri_r13); });
    ɵɵelementEnd();
    ɵɵelementStart(2, "div", 22);
    ɵɵtemplate(3, DhrDataTableComponent_ng_template_4_td_2_div_3_Template, 6, 5, "div", 23);
    ɵɵelementEnd();
    ɵɵelementEnd();
} if (rf & 2) {
    var ri_r13 = ɵɵnextContext().rowIndex;
    var ctx_r16 = ɵɵnextContext();
    ɵɵadvance(3);
    ɵɵproperty("ngIf", ri_r13 == ctx_r16.rs);
} }
function DhrDataTableComponent_ng_template_4_Template(rf, ctx) { if (rf & 1) {
    ɵɵelementStart(0, "tr", 19);
    ɵɵtemplate(1, DhrDataTableComponent_ng_template_4_td_1_Template, 3, 4, "td", 20);
    ɵɵtemplate(2, DhrDataTableComponent_ng_template_4_td_2_Template, 4, 1, "td", 14);
    ɵɵelementEnd();
} if (rf & 2) {
    var columns_r14 = ctx.columns;
    var ctx_r3 = ɵɵnextContext();
    ɵɵadvance(1);
    ɵɵproperty("ngForOf", columns_r14);
    ɵɵadvance(1);
    ɵɵproperty("ngIf", ctx_r3.actionbtn);
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
    DhrDataTableComponent.ɵfac = function DhrDataTableComponent_Factory(t) { return new (t || DhrDataTableComponent)(ɵɵdirectiveInject(ConfirmationService)); };
    DhrDataTableComponent.ɵcmp = ɵɵdefineComponent({ type: DhrDataTableComponent, selectors: [["dhr-data-table"]], inputs: { cols: "cols", tableData: "tableData", paginator: "paginator", searchPlaceHolder: "searchPlaceHolder", row: "row", showCurrentPageReport: "showCurrentPageReport", rowsPerPageOptions: "rowsPerPageOptions", scrollable: "scrollable", scrollHeight: "scrollHeight", styleClass: "styleClass", sortField: "sortField", sortOrder: "sortOrder", globalFilterFields: "globalFilterFields", headertext: "headertext", subHeadertext: "subHeadertext", contentText: "contentText", actionbtn: "actionbtn", showDeleteAtnBtn: "showDeleteAtnBtn", showDuplicateAtnBtn: "showDuplicateAtnBtn", showViewAtnBtn: "showViewAtnBtn", showDownloadAtnBtn: "showDownloadAtnBtn", showShareAtnBtn: "showShareAtnBtn" }, outputs: { deleteEvent: "deleteEvent", duplicateEvent: "duplicateEvent", viewEvent: "viewEvent", downloadEvent: "downloadEvent", shareEvent: "shareEvent" }, decls: 6, vars: 15, consts: [["currentPageReportTemplate", "Showing {first} to {last} of {totalRecords} entries", 3, "columns", "customSort", "value", "paginator", "rows", "showCurrentPageReport", "rowsPerPageOptions", "scrollable", "scrollHeight", "styleClass", "sortField", "sortOrder", "globalFilterFields", "sortFunction", "onFilter"], ["dt", ""], ["pTemplate", "caption"], ["pTemplate", "header"], ["pTemplate", "body"], ["defaultFocus", "none", 3, "position", "baseZIndex"], [1, "p-d-flex", "p-ai-center", "p-jc-between"], [1, "p-m-0"], [2, "margin", "3px"], [1, "p-input-icon-left"], [1, "pi", "pi-search"], ["pInputText", "", "type", "text", 3, "placeholder", "input"], [2, "font-size", "13px"], [3, "pSortableColumn", 4, "ngFor", "ngForOf"], ["style", "width:5rem", 4, "ngIf"], [3, "pSortableColumn"], [3, "field", 4, "ngIf"], [3, "field"], [2, "width", "5rem"], [2, "font-size", "12px"], [4, "ngFor", "ngForOf"], ["pButton", "", "pRipple", "", "icon", "pi pi-ellipsis-v", 1, "p-button-rounded", "p-button-success", "p-mr-2", 2, "position", "relative", 3, "click"], [1, "menuc", 2, "position", "absolute"], ["id", "myDropdown", "class", "dropdown-content", 4, "ngIf"], ["id", "myDropdown", 1, "dropdown-content"], ["onMouseOver", "this.style.color='#32cafc'", "onMouseOut", "this.style.color='black'", 3, "click", 4, "ngIf"], ["onMouseOver", "this.style.color='red'", "onMouseOut", "this.style.color='black'", 3, "click", 4, "ngIf"], ["onMouseOver", "this.style.color='#fcba03'", "onMouseOut", "this.style.color='black'", 3, "click", 4, "ngIf"], ["onMouseOver", "this.style.color='#32cafc'", "onMouseOut", "this.style.color='black'", 3, "click"], ["aria-hidden", "true", 1, "fa", "fa-eye"], ["aria-hidden", "true", 1, "fa", "fa-files-o"], ["onMouseOver", "this.style.color='red'", "onMouseOut", "this.style.color='black'", 3, "click"], ["aria-hidden", "true", 1, "fa", "fa-trash-o"], ["onMouseOver", "this.style.color='#fcba03'", "onMouseOut", "this.style.color='black'", 3, "click"], ["aria-hidden", "true", 1, "fa", "fa-download"], ["aria-hidden", "true", 1, "fa", "fa-share-alt"]], template: function DhrDataTableComponent_Template(rf, ctx) { if (rf & 1) {
            ɵɵelementStart(0, "p-table", 0, 1);
            ɵɵlistener("sortFunction", function DhrDataTableComponent_Template_p_table_sortFunction_0_listener($event) { return ctx.customSort($event); })("onFilter", function DhrDataTableComponent_Template_p_table_onFilter_0_listener($event) { return ctx.printFilteredUsers($event); });
            ɵɵtemplate(2, DhrDataTableComponent_ng_template_2_Template, 9, 3, "ng-template", 2);
            ɵɵtemplate(3, DhrDataTableComponent_ng_template_3_Template, 3, 2, "ng-template", 3);
            ɵɵtemplate(4, DhrDataTableComponent_ng_template_4_Template, 3, 2, "ng-template", 4);
            ɵɵelementEnd();
            ɵɵelement(5, "p-confirmDialog", 5);
        } if (rf & 2) {
            ɵɵproperty("columns", ctx.cols)("customSort", true)("value", ctx.tableData)("paginator", ctx.paginator)("rows", ctx.row)("showCurrentPageReport", ctx.showCurrentPageReport)("rowsPerPageOptions", ctx.rowsPerPageOptions)("scrollable", ctx.scrollable)("scrollHeight", ctx.scrollHeight)("styleClass", ctx.styleClass)("sortField", ctx.sortField)("sortOrder", ctx.sortOrder)("globalFilterFields", ctx.globalFilterFields);
            ɵɵadvance(5);
            ɵɵproperty("position", "top")("baseZIndex", 10000000);
        } }, directives: [Table, PrimeTemplate, ConfirmDialog, InputText, NgForOf, NgIf, SortableColumn, SortIcon, ButtonDirective], pipes: [DatePipe], styles: [".p-button.p-button-success[_ngcontent-%COMP%]{color:#000;background-color:Transparent;background-repeat:no-repeat;border:none;cursor:pointer;overflow:hidden;outline:0}.p-button[_ngcontent-%COMP%]:active, .p-button[_ngcontent-%COMP%]:hover{color:#ccc!important;background:no-repeat Transparent!important;border:none!important;cursor:pointer!important;overflow:hidden!important;outline:0!important}[_nghost-%COMP%]     .confrm-name{font-weight:700;font-size:14px}[_nghost-%COMP%]     .p-dialog-top .p-dialog{width:29%}[_nghost-%COMP%]     .p-dialog.p-confirm-dialog .p-confirm-dialog-message{margin:0;font-size:13px;font-weight:500}[_nghost-%COMP%]     .p-dialog .p-dialog-header{padding:.5em .5em .5em 1.5em;background:#dbe1e5}[_nghost-%COMP%]     .p-confirm-dialog .p-dialog-content{width:100%;height:6em}[_nghost-%COMP%]     .p-dialog .p-dialog-header .p-dialog-title{font-size:1em!important}[_nghost-%COMP%]     .p-dialog .p-dialog-footer .p-button .p-button-icon-left{margin-right:.5rem;display:none}[_nghost-%COMP%]     .p-dialog .p-dialog-footer{direction:rtl}[_nghost-%COMP%]     .p-confirm-dialog-accept{background:#5cb85c;color:#ceeace;border:1px solid #5cb85c}[_nghost-%COMP%]     .p-confirm-dialog-accept:hover{background:#4e994e;color:#ceeace;border:1px solid #4e994e}[_nghost-%COMP%]     .p-confirm-dialog-reject{background:#fff;color:#a1d6a1;border:1px solid #e2e2e2}[_nghost-%COMP%]     .p-confirm-dialog-reject:hover{background:#f1f1f1;color:#a1d6a1;border:1px solid #e2e2e2}[_nghost-%COMP%]    .p-button:focus{color:#ccc!important;outline:0!important;box-shadow:0 0 0 0 transparent!important}[_nghost-%COMP%]     .p-paginator{background:#f8f9fa!important;justify-content:flex-end;align-items:flex-end}[_nghost-%COMP%]     p-dropdown{z-index:1;margin-right:88%;margin-top:-2.75em}[_nghost-%COMP%]     .p-datatable .p-datatable-tbody>tr>td{text-align:left;border:1px solid #e9ecef;border-width:0 0 1px;word-break:break-all;padding:0 0 0 1.5em}[_nghost-%COMP%]     .p-paginator-current{font-size:13px;height:1.8rem}[_nghost-%COMP%]     .p-paginator .p-paginator-pages .p-paginator-page{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-first{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-last{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-prev{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-next{min-width:1.357rem;height:1.357rem}.dropdown-content[_ngcontent-%COMP%]{display:flex;flex-direction:column;justify-content:space-around;text-align:center;position:fixed;background-color:#fff;width:10em;height:8em;margin-top:-2em;margin-left:-10.3em;box-shadow:0 8px 16px 0 rgba(0,0,0,.2);transition:opacity .5s;z-index:1}.dropdown-content[_ngcontent-%COMP%]   a[_ngcontent-%COMP%]{color:#000;text-decoration:none;display:block;padding:8px;border-bottom:1px solid #ccc;text-align:left;cursor:pointer}"] });
    return DhrDataTableComponent;
}());
/*@__PURE__*/ (function () { ɵsetClassMetadata(DhrDataTableComponent, [{
        type: Component,
        args: [{
                selector: 'dhr-data-table',
                templateUrl: './dhr-data-table.component.html',
                styleUrls: ['./dhr-data-table.component.css']
            }]
    }], function () { return [{ type: ConfirmationService }]; }, { cols: [{
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

var DhrDataTableModule = /** @class */ (function () {
    function DhrDataTableModule() {
    }
    DhrDataTableModule.ɵmod = ɵɵdefineNgModule({ type: DhrDataTableModule });
    DhrDataTableModule.ɵinj = ɵɵdefineInjector({ factory: function DhrDataTableModule_Factory(t) { return new (t || DhrDataTableModule)(); }, providers: [ConfirmationService], imports: [[
                CommonModule,
                TableModule,
                ButtonModule,
                HttpClientModule,
                SplitButtonModule,
                FormsModule,
                ConfirmDialogModule,
                PaginatorModule,
                InputTextModule,
                ProgressSpinnerModule
            ]] });
    return DhrDataTableModule;
}());
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && ɵɵsetNgModuleScope(DhrDataTableModule, { declarations: [DhrDataTableComponent], imports: [CommonModule,
        TableModule,
        ButtonModule,
        HttpClientModule,
        SplitButtonModule,
        FormsModule,
        ConfirmDialogModule,
        PaginatorModule,
        InputTextModule,
        ProgressSpinnerModule], exports: [DhrDataTableComponent] }); })();
/*@__PURE__*/ (function () { ɵsetClassMetadata(DhrDataTableModule, [{
        type: NgModule,
        args: [{
                declarations: [DhrDataTableComponent],
                imports: [
                    CommonModule,
                    TableModule,
                    ButtonModule,
                    HttpClientModule,
                    SplitButtonModule,
                    FormsModule,
                    ConfirmDialogModule,
                    PaginatorModule,
                    InputTextModule,
                    ProgressSpinnerModule
                ],
                providers: [ConfirmationService],
                exports: [DhrDataTableComponent]
            }]
    }], null, null); })();

/*
 * Public API Surface of dhr-data-table
 */

/**
 * Generated bundle index. Do not edit.
 */

export { DhrDataTableComponent, DhrDataTableModule, DhrDataTableService };
//# sourceMappingURL=dhr-data-table.js.map
