(function (global, factory) {
    typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('@angular/core'), require('primeng/api'), require('primeng/table'), require('primeng/confirmdialog'), require('primeng/inputtext'), require('@angular/common'), require('primeng/button'), require('@angular/common/http'), require('primeng/splitbutton'), require('@angular/forms'), require('primeng/paginator'), require('primeng/progressspinner')) :
    typeof define === 'function' && define.amd ? define('dhr-data-table', ['exports', '@angular/core', 'primeng/api', 'primeng/table', 'primeng/confirmdialog', 'primeng/inputtext', '@angular/common', 'primeng/button', '@angular/common/http', 'primeng/splitbutton', '@angular/forms', 'primeng/paginator', 'primeng/progressspinner'], factory) :
    (global = global || self, factory(global['dhr-data-table'] = {}, global.ng.core, global.api, global.table, global.confirmdialog, global.inputtext, global.ng.common, global.button, global.ng.common.http, global.splitbutton, global.ng.forms, global.paginator, global.progressspinner));
}(this, (function (exports, core, api, table, confirmdialog, inputtext, common, button, http, splitbutton, forms, paginator, progressspinner) { 'use strict';

    var DhrDataTableService = /** @class */ (function () {
        function DhrDataTableService() {
        }
        DhrDataTableService.ɵfac = function DhrDataTableService_Factory(t) { return new (t || DhrDataTableService)(); };
        DhrDataTableService.ɵprov = core.ɵɵdefineInjectable({ token: DhrDataTableService, factory: DhrDataTableService.ɵfac, providedIn: 'root' });
        return DhrDataTableService;
    }());
    /*@__PURE__*/ (function () { core.ɵsetClassMetadata(DhrDataTableService, [{
            type: core.Injectable,
            args: [{
                    providedIn: 'root'
                }]
        }], function () { return []; }, null); })();

    function DhrDataTableComponent_ng_template_2_Template(rf, ctx) { if (rf & 1) {
        var _r5 = core.ɵɵgetCurrentView();
        core.ɵɵelementStart(0, "div", 6);
        core.ɵɵelementStart(1, "h4", 7);
        core.ɵɵtext(2);
        core.ɵɵelement(3, "br");
        core.ɵɵelementStart(4, "h5", 8);
        core.ɵɵtext(5);
        core.ɵɵelementEnd();
        core.ɵɵelementEnd();
        core.ɵɵelementStart(6, "span", 9);
        core.ɵɵelement(7, "i", 10);
        core.ɵɵelementStart(8, "input", 11);
        core.ɵɵlistener("input", function DhrDataTableComponent_ng_template_2_Template_input_input_8_listener($event) { core.ɵɵrestoreView(_r5); core.ɵɵnextContext(); var _r0 = core.ɵɵreference(1); return _r0.filterGlobal($event.target.value, "contains"); });
        core.ɵɵelementEnd();
        core.ɵɵelementEnd();
        core.ɵɵelementEnd();
    } if (rf & 2) {
        var ctx_r1 = core.ɵɵnextContext();
        core.ɵɵadvance(2);
        core.ɵɵtextInterpolate(ctx_r1.headertext);
        core.ɵɵadvance(3);
        core.ɵɵtextInterpolate(ctx_r1.subHeadertext);
        core.ɵɵadvance(3);
        core.ɵɵproperty("placeholder", ctx_r1.searchPlaceHolder);
    } }
    function DhrDataTableComponent_ng_template_3_th_1_p_sortIcon_2_Template(rf, ctx) { if (rf & 1) {
        core.ɵɵelement(0, "p-sortIcon", 17);
    } if (rf & 2) {
        var col_r9 = core.ɵɵnextContext().$implicit;
        core.ɵɵproperty("field", col_r9.field);
    } }
    function DhrDataTableComponent_ng_template_3_th_1_Template(rf, ctx) { if (rf & 1) {
        core.ɵɵelementStart(0, "th", 15);
        core.ɵɵtext(1);
        core.ɵɵtemplate(2, DhrDataTableComponent_ng_template_3_th_1_p_sortIcon_2_Template, 1, 1, "p-sortIcon", 16);
        core.ɵɵelementEnd();
    } if (rf & 2) {
        var col_r9 = ctx.$implicit;
        core.ɵɵpropertyInterpolate("pSortableColumn", col_r9.sortOpt ? col_r9.field : null);
        core.ɵɵadvance(1);
        core.ɵɵtextInterpolate1(" ", col_r9.header, " ");
        core.ɵɵadvance(1);
        core.ɵɵproperty("ngIf", col_r9.sortOpt);
    } }
    function DhrDataTableComponent_ng_template_3_th_2_Template(rf, ctx) { if (rf & 1) {
        core.ɵɵelement(0, "th", 18);
    } }
    function DhrDataTableComponent_ng_template_3_Template(rf, ctx) { if (rf & 1) {
        core.ɵɵelementStart(0, "tr", 12);
        core.ɵɵtemplate(1, DhrDataTableComponent_ng_template_3_th_1_Template, 3, 3, "th", 13);
        core.ɵɵtemplate(2, DhrDataTableComponent_ng_template_3_th_2_Template, 1, 0, "th", 14);
        core.ɵɵelementEnd();
    } if (rf & 2) {
        var columns_r6 = ctx.$implicit;
        var ctx_r2 = core.ɵɵnextContext();
        core.ɵɵadvance(1);
        core.ɵɵproperty("ngForOf", columns_r6);
        core.ɵɵadvance(1);
        core.ɵɵproperty("ngIf", ctx_r2.actionbtn);
    } }
    function DhrDataTableComponent_ng_template_4_td_1_Template(rf, ctx) { if (rf & 1) {
        core.ɵɵelementStart(0, "td");
        core.ɵɵtext(1);
        core.ɵɵpipe(2, "date");
        core.ɵɵelementEnd();
    } if (rf & 2) {
        var col_r17 = ctx.$implicit;
        var rowData_r12 = core.ɵɵnextContext().$implicit;
        core.ɵɵadvance(1);
        core.ɵɵtextInterpolate1(" ", col_r17.type != "date" ? rowData_r12[col_r17.field] : core.ɵɵpipeBind2(2, 1, rowData_r12[col_r17.field] * 1000, "short"), " ");
    } }
    function DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template(rf, ctx) { if (rf & 1) {
        var _r27 = core.ɵɵgetCurrentView();
        core.ɵɵelementStart(0, "a", 28);
        core.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template_a_click_0_listener() { core.ɵɵrestoreView(_r27); var rowData_r12 = core.ɵɵnextContext(3).$implicit; var ctx_r25 = core.ɵɵnextContext(); return ctx_r25.viewRow(rowData_r12); });
        core.ɵɵelement(1, "i", 29);
        core.ɵɵtext(2, "\u00A0\u00A0View");
        core.ɵɵelementEnd();
    } }
    function DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template(rf, ctx) { if (rf & 1) {
        var _r30 = core.ɵɵgetCurrentView();
        core.ɵɵelementStart(0, "a", 28);
        core.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template_a_click_0_listener() { core.ɵɵrestoreView(_r30); var rowData_r12 = core.ɵɵnextContext(3).$implicit; var ctx_r28 = core.ɵɵnextContext(); return ctx_r28.duplicateRow(rowData_r12); });
        core.ɵɵelement(1, "i", 30);
        core.ɵɵtext(2, "\u00A0\u00A0Duplicate");
        core.ɵɵelementEnd();
    } }
    function DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template(rf, ctx) { if (rf & 1) {
        var _r33 = core.ɵɵgetCurrentView();
        core.ɵɵelementStart(0, "a", 31);
        core.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template_a_click_0_listener() { core.ɵɵrestoreView(_r33); var rowData_r12 = core.ɵɵnextContext(3).$implicit; var ctx_r31 = core.ɵɵnextContext(); return ctx_r31.deleteRow(rowData_r12); });
        core.ɵɵelement(1, "i", 32);
        core.ɵɵtext(2, "\u00A0\u00A0Delete");
        core.ɵɵelementEnd();
    } }
    function DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template(rf, ctx) { if (rf & 1) {
        var _r36 = core.ɵɵgetCurrentView();
        core.ɵɵelementStart(0, "a", 33);
        core.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template_a_click_0_listener() { core.ɵɵrestoreView(_r36); var rowData_r12 = core.ɵɵnextContext(3).$implicit; var ctx_r34 = core.ɵɵnextContext(); return ctx_r34.downloadRow(rowData_r12); });
        core.ɵɵelement(1, "i", 34);
        core.ɵɵtext(2, "\u00A0\u00A0Download");
        core.ɵɵelementEnd();
    } }
    function DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template(rf, ctx) { if (rf & 1) {
        var _r39 = core.ɵɵgetCurrentView();
        core.ɵɵelementStart(0, "a", 33);
        core.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template_a_click_0_listener() { core.ɵɵrestoreView(_r39); var rowData_r12 = core.ɵɵnextContext(3).$implicit; var ctx_r37 = core.ɵɵnextContext(); return ctx_r37.shareRow(rowData_r12); });
        core.ɵɵelement(1, "i", 35);
        core.ɵɵtext(2, "\u00A0\u00A0Share");
        core.ɵɵelementEnd();
    } }
    function DhrDataTableComponent_ng_template_4_td_2_div_3_Template(rf, ctx) { if (rf & 1) {
        core.ɵɵelementStart(0, "div", 24);
        core.ɵɵtemplate(1, DhrDataTableComponent_ng_template_4_td_2_div_3_a_1_Template, 3, 0, "a", 25);
        core.ɵɵtemplate(2, DhrDataTableComponent_ng_template_4_td_2_div_3_a_2_Template, 3, 0, "a", 25);
        core.ɵɵtemplate(3, DhrDataTableComponent_ng_template_4_td_2_div_3_a_3_Template, 3, 0, "a", 26);
        core.ɵɵtemplate(4, DhrDataTableComponent_ng_template_4_td_2_div_3_a_4_Template, 3, 0, "a", 27);
        core.ɵɵtemplate(5, DhrDataTableComponent_ng_template_4_td_2_div_3_a_5_Template, 3, 0, "a", 27);
        core.ɵɵelementEnd();
    } if (rf & 2) {
        var ctx_r19 = core.ɵɵnextContext(3);
        core.ɵɵadvance(1);
        core.ɵɵproperty("ngIf", ctx_r19.showViewAtnBtn);
        core.ɵɵadvance(1);
        core.ɵɵproperty("ngIf", ctx_r19.showDuplicateAtnBtn);
        core.ɵɵadvance(1);
        core.ɵɵproperty("ngIf", ctx_r19.showDeleteAtnBtn);
        core.ɵɵadvance(1);
        core.ɵɵproperty("ngIf", ctx_r19.showDownloadAtnBtn);
        core.ɵɵadvance(1);
        core.ɵɵproperty("ngIf", ctx_r19.showShareAtnBtn);
    } }
    function DhrDataTableComponent_ng_template_4_td_2_Template(rf, ctx) { if (rf & 1) {
        var _r42 = core.ɵɵgetCurrentView();
        core.ɵɵelementStart(0, "td", 18);
        core.ɵɵelementStart(1, "button", 21);
        core.ɵɵlistener("click", function DhrDataTableComponent_ng_template_4_td_2_Template_button_click_1_listener() { core.ɵɵrestoreView(_r42); var ctx_r41 = core.ɵɵnextContext(); var rowData_r12 = ctx_r41.$implicit; var ri_r13 = ctx_r41.rowIndex; var ctx_r40 = core.ɵɵnextContext(); return ctx_r40.showWrappedMenu(rowData_r12, ri_r13); });
        core.ɵɵelementEnd();
        core.ɵɵelementStart(2, "div", 22);
        core.ɵɵtemplate(3, DhrDataTableComponent_ng_template_4_td_2_div_3_Template, 6, 5, "div", 23);
        core.ɵɵelementEnd();
        core.ɵɵelementEnd();
    } if (rf & 2) {
        var ri_r13 = core.ɵɵnextContext().rowIndex;
        var ctx_r16 = core.ɵɵnextContext();
        core.ɵɵadvance(3);
        core.ɵɵproperty("ngIf", ri_r13 == ctx_r16.rs);
    } }
    function DhrDataTableComponent_ng_template_4_Template(rf, ctx) { if (rf & 1) {
        core.ɵɵelementStart(0, "tr", 19);
        core.ɵɵtemplate(1, DhrDataTableComponent_ng_template_4_td_1_Template, 3, 4, "td", 20);
        core.ɵɵtemplate(2, DhrDataTableComponent_ng_template_4_td_2_Template, 4, 1, "td", 14);
        core.ɵɵelementEnd();
    } if (rf & 2) {
        var columns_r14 = ctx.columns;
        var ctx_r3 = core.ɵɵnextContext();
        core.ɵɵadvance(1);
        core.ɵɵproperty("ngForOf", columns_r14);
        core.ɵɵadvance(1);
        core.ɵɵproperty("ngIf", ctx_r3.actionbtn);
    } }
    var DhrDataTableComponent = /** @class */ (function () {
        function DhrDataTableComponent(confirmDialog) {
            this.confirmDialog = confirmDialog;
            this.deleteEvent = new core.EventEmitter();
            this.duplicateEvent = new core.EventEmitter();
            this.viewEvent = new core.EventEmitter();
            this.downloadEvent = new core.EventEmitter();
            this.shareEvent = new core.EventEmitter();
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
        DhrDataTableComponent.ɵfac = function DhrDataTableComponent_Factory(t) { return new (t || DhrDataTableComponent)(core.ɵɵdirectiveInject(api.ConfirmationService)); };
        DhrDataTableComponent.ɵcmp = core.ɵɵdefineComponent({ type: DhrDataTableComponent, selectors: [["dhr-data-table"]], inputs: { cols: "cols", tableData: "tableData", paginator: "paginator", searchPlaceHolder: "searchPlaceHolder", row: "row", showCurrentPageReport: "showCurrentPageReport", rowsPerPageOptions: "rowsPerPageOptions", scrollable: "scrollable", scrollHeight: "scrollHeight", styleClass: "styleClass", sortField: "sortField", sortOrder: "sortOrder", globalFilterFields: "globalFilterFields", headertext: "headertext", subHeadertext: "subHeadertext", contentText: "contentText", actionbtn: "actionbtn", showDeleteAtnBtn: "showDeleteAtnBtn", showDuplicateAtnBtn: "showDuplicateAtnBtn", showViewAtnBtn: "showViewAtnBtn", showDownloadAtnBtn: "showDownloadAtnBtn", showShareAtnBtn: "showShareAtnBtn" }, outputs: { deleteEvent: "deleteEvent", duplicateEvent: "duplicateEvent", viewEvent: "viewEvent", downloadEvent: "downloadEvent", shareEvent: "shareEvent" }, decls: 6, vars: 15, consts: [["currentPageReportTemplate", "Showing {first} to {last} of {totalRecords} entries", 3, "columns", "customSort", "value", "paginator", "rows", "showCurrentPageReport", "rowsPerPageOptions", "scrollable", "scrollHeight", "styleClass", "sortField", "sortOrder", "globalFilterFields", "sortFunction", "onFilter"], ["dt", ""], ["pTemplate", "caption"], ["pTemplate", "header"], ["pTemplate", "body"], ["defaultFocus", "none", 3, "position", "baseZIndex"], [1, "p-d-flex", "p-ai-center", "p-jc-between"], [1, "p-m-0"], [2, "margin", "3px"], [1, "p-input-icon-left"], [1, "pi", "pi-search"], ["pInputText", "", "type", "text", 3, "placeholder", "input"], [2, "font-size", "13px"], [3, "pSortableColumn", 4, "ngFor", "ngForOf"], ["style", "width:5rem", 4, "ngIf"], [3, "pSortableColumn"], [3, "field", 4, "ngIf"], [3, "field"], [2, "width", "5rem"], [2, "font-size", "12px"], [4, "ngFor", "ngForOf"], ["pButton", "", "pRipple", "", "icon", "pi pi-ellipsis-v", 1, "p-button-rounded", "p-button-success", "p-mr-2", 2, "position", "relative", 3, "click"], [1, "menuc", 2, "position", "absolute"], ["id", "myDropdown", "class", "dropdown-content", 4, "ngIf"], ["id", "myDropdown", 1, "dropdown-content"], ["onMouseOver", "this.style.color='#32cafc'", "onMouseOut", "this.style.color='black'", 3, "click", 4, "ngIf"], ["onMouseOver", "this.style.color='red'", "onMouseOut", "this.style.color='black'", 3, "click", 4, "ngIf"], ["onMouseOver", "this.style.color='#fcba03'", "onMouseOut", "this.style.color='black'", 3, "click", 4, "ngIf"], ["onMouseOver", "this.style.color='#32cafc'", "onMouseOut", "this.style.color='black'", 3, "click"], ["aria-hidden", "true", 1, "fa", "fa-eye"], ["aria-hidden", "true", 1, "fa", "fa-files-o"], ["onMouseOver", "this.style.color='red'", "onMouseOut", "this.style.color='black'", 3, "click"], ["aria-hidden", "true", 1, "fa", "fa-trash-o"], ["onMouseOver", "this.style.color='#fcba03'", "onMouseOut", "this.style.color='black'", 3, "click"], ["aria-hidden", "true", 1, "fa", "fa-download"], ["aria-hidden", "true", 1, "fa", "fa-share-alt"]], template: function DhrDataTableComponent_Template(rf, ctx) { if (rf & 1) {
                core.ɵɵelementStart(0, "p-table", 0, 1);
                core.ɵɵlistener("sortFunction", function DhrDataTableComponent_Template_p_table_sortFunction_0_listener($event) { return ctx.customSort($event); })("onFilter", function DhrDataTableComponent_Template_p_table_onFilter_0_listener($event) { return ctx.printFilteredUsers($event); });
                core.ɵɵtemplate(2, DhrDataTableComponent_ng_template_2_Template, 9, 3, "ng-template", 2);
                core.ɵɵtemplate(3, DhrDataTableComponent_ng_template_3_Template, 3, 2, "ng-template", 3);
                core.ɵɵtemplate(4, DhrDataTableComponent_ng_template_4_Template, 3, 2, "ng-template", 4);
                core.ɵɵelementEnd();
                core.ɵɵelement(5, "p-confirmDialog", 5);
            } if (rf & 2) {
                core.ɵɵproperty("columns", ctx.cols)("customSort", true)("value", ctx.tableData)("paginator", ctx.paginator)("rows", ctx.row)("showCurrentPageReport", ctx.showCurrentPageReport)("rowsPerPageOptions", ctx.rowsPerPageOptions)("scrollable", ctx.scrollable)("scrollHeight", ctx.scrollHeight)("styleClass", ctx.styleClass)("sortField", ctx.sortField)("sortOrder", ctx.sortOrder)("globalFilterFields", ctx.globalFilterFields);
                core.ɵɵadvance(5);
                core.ɵɵproperty("position", "top")("baseZIndex", 10000000);
            } }, directives: [table.Table, api.PrimeTemplate, confirmdialog.ConfirmDialog, inputtext.InputText, common.NgForOf, common.NgIf, table.SortableColumn, table.SortIcon, button.ButtonDirective], pipes: [common.DatePipe], styles: [".p-button.p-button-success[_ngcontent-%COMP%]{color:#000;background-color:Transparent;background-repeat:no-repeat;border:none;cursor:pointer;overflow:hidden;outline:0}.p-button[_ngcontent-%COMP%]:active, .p-button[_ngcontent-%COMP%]:hover{color:#ccc!important;background:no-repeat Transparent!important;border:none!important;cursor:pointer!important;overflow:hidden!important;outline:0!important}[_nghost-%COMP%]     .confrm-name{font-weight:700;font-size:14px}[_nghost-%COMP%]     .p-dialog-top .p-dialog{width:29%}[_nghost-%COMP%]     .p-dialog.p-confirm-dialog .p-confirm-dialog-message{margin:0;font-size:13px;font-weight:500}[_nghost-%COMP%]     .p-dialog .p-dialog-header{padding:.5em .5em .5em 1.5em;background:#dbe1e5}[_nghost-%COMP%]     .p-confirm-dialog .p-dialog-content{width:100%;height:6em}[_nghost-%COMP%]     .p-dialog .p-dialog-header .p-dialog-title{font-size:1em!important}[_nghost-%COMP%]     .p-dialog .p-dialog-footer .p-button .p-button-icon-left{margin-right:.5rem;display:none}[_nghost-%COMP%]     .p-dialog .p-dialog-footer{direction:rtl}[_nghost-%COMP%]     .p-confirm-dialog-accept{background:#5cb85c;color:#ceeace;border:1px solid #5cb85c}[_nghost-%COMP%]     .p-confirm-dialog-accept:hover{background:#4e994e;color:#ceeace;border:1px solid #4e994e}[_nghost-%COMP%]     .p-confirm-dialog-reject{background:#fff;color:#a1d6a1;border:1px solid #e2e2e2}[_nghost-%COMP%]     .p-confirm-dialog-reject:hover{background:#f1f1f1;color:#a1d6a1;border:1px solid #e2e2e2}[_nghost-%COMP%]    .p-button:focus{color:#ccc!important;outline:0!important;box-shadow:0 0 0 0 transparent!important}[_nghost-%COMP%]     .p-paginator{background:#f8f9fa!important;justify-content:flex-end;align-items:flex-end}[_nghost-%COMP%]     p-dropdown{z-index:1;margin-right:88%;margin-top:-2.75em}[_nghost-%COMP%]     .p-datatable .p-datatable-tbody>tr>td{text-align:left;border:1px solid #e9ecef;border-width:0 0 1px;word-break:break-all;padding:0 0 0 1.5em}[_nghost-%COMP%]     .p-paginator-current{font-size:13px;height:1.8rem}[_nghost-%COMP%]     .p-paginator .p-paginator-pages .p-paginator-page{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-first{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-last{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-prev{min-width:1.357rem;height:1.357rem}[_nghost-%COMP%]     .p-paginator .p-paginator-next{min-width:1.357rem;height:1.357rem}.dropdown-content[_ngcontent-%COMP%]{display:flex;flex-direction:column;justify-content:space-around;text-align:center;position:fixed;background-color:#fff;width:10em;height:8em;margin-top:-2em;margin-left:-10.3em;box-shadow:0 8px 16px 0 rgba(0,0,0,.2);transition:opacity .5s;z-index:1}.dropdown-content[_ngcontent-%COMP%]   a[_ngcontent-%COMP%]{color:#000;text-decoration:none;display:block;padding:8px;border-bottom:1px solid #ccc;text-align:left;cursor:pointer}"] });
        return DhrDataTableComponent;
    }());
    /*@__PURE__*/ (function () { core.ɵsetClassMetadata(DhrDataTableComponent, [{
            type: core.Component,
            args: [{
                    selector: 'dhr-data-table',
                    templateUrl: './dhr-data-table.component.html',
                    styleUrls: ['./dhr-data-table.component.css']
                }]
        }], function () { return [{ type: api.ConfirmationService }]; }, { cols: [{
                type: core.Input
            }], tableData: [{
                type: core.Input
            }], paginator: [{
                type: core.Input
            }], searchPlaceHolder: [{
                type: core.Input
            }], row: [{
                type: core.Input
            }], showCurrentPageReport: [{
                type: core.Input
            }], rowsPerPageOptions: [{
                type: core.Input
            }], scrollable: [{
                type: core.Input
            }], scrollHeight: [{
                type: core.Input
            }], styleClass: [{
                type: core.Input
            }], sortField: [{
                type: core.Input
            }], sortOrder: [{
                type: core.Input
            }], globalFilterFields: [{
                type: core.Input
            }], headertext: [{
                type: core.Input
            }], subHeadertext: [{
                type: core.Input
            }], contentText: [{
                type: core.Input
            }], actionbtn: [{
                type: core.Input
            }], showDeleteAtnBtn: [{
                type: core.Input
            }], showDuplicateAtnBtn: [{
                type: core.Input
            }], showViewAtnBtn: [{
                type: core.Input
            }], showDownloadAtnBtn: [{
                type: core.Input
            }], showShareAtnBtn: [{
                type: core.Input
            }], deleteEvent: [{
                type: core.Output
            }], duplicateEvent: [{
                type: core.Output
            }], viewEvent: [{
                type: core.Output
            }], downloadEvent: [{
                type: core.Output
            }], shareEvent: [{
                type: core.Output
            }] }); })();

    var DhrDataTableModule = /** @class */ (function () {
        function DhrDataTableModule() {
        }
        DhrDataTableModule.ɵmod = core.ɵɵdefineNgModule({ type: DhrDataTableModule });
        DhrDataTableModule.ɵinj = core.ɵɵdefineInjector({ factory: function DhrDataTableModule_Factory(t) { return new (t || DhrDataTableModule)(); }, providers: [api.ConfirmationService], imports: [[
                    common.CommonModule,
                    table.TableModule,
                    button.ButtonModule,
                    http.HttpClientModule,
                    splitbutton.SplitButtonModule,
                    forms.FormsModule,
                    confirmdialog.ConfirmDialogModule,
                    paginator.PaginatorModule,
                    inputtext.InputTextModule,
                    progressspinner.ProgressSpinnerModule
                ]] });
        return DhrDataTableModule;
    }());
    (function () { (typeof ngJitMode === "undefined" || ngJitMode) && core.ɵɵsetNgModuleScope(DhrDataTableModule, { declarations: [DhrDataTableComponent], imports: [common.CommonModule,
            table.TableModule,
            button.ButtonModule,
            http.HttpClientModule,
            splitbutton.SplitButtonModule,
            forms.FormsModule,
            confirmdialog.ConfirmDialogModule,
            paginator.PaginatorModule,
            inputtext.InputTextModule,
            progressspinner.ProgressSpinnerModule], exports: [DhrDataTableComponent] }); })();
    /*@__PURE__*/ (function () { core.ɵsetClassMetadata(DhrDataTableModule, [{
            type: core.NgModule,
            args: [{
                    declarations: [DhrDataTableComponent],
                    imports: [
                        common.CommonModule,
                        table.TableModule,
                        button.ButtonModule,
                        http.HttpClientModule,
                        splitbutton.SplitButtonModule,
                        forms.FormsModule,
                        confirmdialog.ConfirmDialogModule,
                        paginator.PaginatorModule,
                        inputtext.InputTextModule,
                        progressspinner.ProgressSpinnerModule
                    ],
                    providers: [api.ConfirmationService],
                    exports: [DhrDataTableComponent]
                }]
        }], null, null); })();

    exports.DhrDataTableComponent = DhrDataTableComponent;
    exports.DhrDataTableModule = DhrDataTableModule;
    exports.DhrDataTableService = DhrDataTableService;

    Object.defineProperty(exports, '__esModule', { value: true });

})));
//# sourceMappingURL=dhr-data-table.umd.js.map
