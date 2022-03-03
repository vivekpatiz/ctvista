(function (global, factory) {
    typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('@angular/core'), require('@angular/common'), require('ngx-echarts')) :
    typeof define === 'function' && define.amd ? define('dhr-chart', ['exports', '@angular/core', '@angular/common', 'ngx-echarts'], factory) :
    (global = global || self, factory(global['dhr-chart'] = {}, global.ng.core, global.ng.common, global.ngxEcharts));
}(this, (function (exports, core, common, ngxEcharts) { 'use strict';

    var DhrChartService = /** @class */ (function () {
        function DhrChartService() {
        }
        DhrChartService.ɵfac = function DhrChartService_Factory(t) { return new (t || DhrChartService)(); };
        DhrChartService.ɵprov = core.ɵɵdefineInjectable({ token: DhrChartService, factory: DhrChartService.ɵfac, providedIn: 'root' });
        return DhrChartService;
    }());
    /*@__PURE__*/ (function () { core.ɵsetClassMetadata(DhrChartService, [{
            type: core.Injectable,
            args: [{
                    providedIn: 'root'
                }]
        }], function () { return []; }, null); })();

    var DhrChartComponent = /** @class */ (function () {
        function DhrChartComponent() {
        }
        DhrChartComponent.prototype.ngOnInit = function () {
            this.loadChart(this.chartData, this.xAxisLabel, this.yAxislabel, this.chartLabel, this.chartTitle, this.showyAxisLabel, this.showToolbox, this.showToolTip, this.showxAxisLabel, this.isKpiOverView, this.color);
        };
        DhrChartComponent.prototype.ngOnChanges = function (changes) {
            // Called before any other lifecycle hook. Use it to inject dependencies, but avoid any serious work here.
            // Add '${implements OnChanges}' to the class.
            this.loadChart(this.chartData, this.xAxisLabel, this.yAxislabel, this.chartLabel, this.chartTitle, this.showyAxisLabel, this.showToolbox, this.showToolTip, this.showxAxisLabel, this.isKpiOverView, this.color);
        };
        DhrChartComponent.prototype.ngAfterViewInit = function () {
            // Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
            // Add 'implements AfterViewInit' to the class.
            this.loadChart(this.chartData, this.xAxisLabel, this.yAxislabel, this.chartLabel, this.chartTitle, this.showyAxisLabel, this.showToolbox, this.showToolTip, this.showxAxisLabel, this.isKpiOverView, this.color);
        };
        DhrChartComponent.prototype.loadChart = function (d, xl, yl, cl, ct, yAxisLabelFlag, toolboxFlag, tooltipFlag, xAxisLabelFlag, kpiOverviewflag, colorValues) {
            if (this.isMultiSeries) {
                this.getMultiSeriesChartOptions(this.xAxisLabel, this.yAxislabel, this.chartData);
            }
            else {
                this.chartOption = this.getChartOptions(d, xl, yl, cl, ct, yAxisLabelFlag, toolboxFlag, tooltipFlag, xAxisLabelFlag, kpiOverviewflag, colorValues);
            }
        };
        DhrChartComponent.prototype.getChartOptions = function (d, xl, yl, cl, ct, yAxisLabelFlag, toolboxFlag, tooltipFlag, xAxisLabelFlag, kpiOverviewflag, colorValues) {
            var showMarkLine = {};
            var chartColorProperty = '#009DDC';
            if (!kpiOverviewflag) {
                showMarkLine = {
                    data: [
                        {
                            type: 'average',
                            label: {
                                position: 'End',
                                distance: 5,
                                formatter: function (params) {
                                    return "" + params.value;
                                },
                            },
                            lineStyle: { color: '#707070', width: 2 },
                        },
                    ],
                    symbol: 'none',
                };
            }
            if (kpiOverviewflag) {
                chartColorProperty = colorValues;
            }
            return {
                title: {
                    text: ct,
                    left: 'left',
                    top: 'left',
                    triggerEvent: true,
                    textStyle: {
                        fontSize: 13,
                    },
                    padding: [
                        15,
                        10,
                        5,
                        10,
                    ],
                    show: false,
                },
                toolbox: {
                    show: toolboxFlag,
                    orient: 'vertical',
                    showTitle: false,
                    feature: {
                        saveAsImage: {
                            title: 'Save Image',
                        },
                    },
                    tooltip: {
                        show: true,
                        formatter: function (param) {
                            return '<div>' + param.title + '</div>';
                        },
                        backgroundColor: '#222',
                        textStyle: {
                            fontSize: 8,
                        },
                        extraCssText: 'box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);',
                    },
                },
                tooltip: {
                    show: tooltipFlag,
                    trigger: 'axis',
                    backgroundColor: '#FFFFFF',
                    axisPointer: {
                        type: 'line',
                        lable: {
                            precision: 1,
                        },
                    },
                    formatter: function (params) {
                        return "<div style=\" padding: 10px 10px 20px 10px;\n          box-shadow: 2px 2px 4px 2px #aaaaaa;\n          background-color: white;\">\n          <span style=\"color: #808080;font-size: 12px;\">" + (new Date(params[0].name).toString().split(' ', 4).join(' ') +
                            ' ' +
                            ('0' + new Date(params[0].name).getHours()).slice(-2) +
                            ':' +
                            ('0' + new Date(params[0].name).getMinutes()).slice(-2)) + "</span><br>\n                  <span style=\"color: #333333; font-size: 12px\">" + yl + "</span>\n                  &nbsp;&nbsp;&nbsp;\n                  <span style=\"color: #333333; font-size: 14px; text-align:right; font-weight:700\">" + params[0].value[1] + "&nbsp;" + xl + "</span></div>";
                    },
                },
                grid: {
                    containLabel: true,
                },
                xAxis: {
                    type: 'category',
                    show: xAxisLabelFlag,
                    axisLabel: {
                        color: '#808080',
                    },
                    axisTick: {
                        alignWithLabel: true,
                    },
                    splitNumber: 1,
                },
                yAxis: {
                    type: 'value',
                    name: yl,
                    nameLocation: 'middle',
                    show: yAxisLabelFlag,
                    axisLabel: {
                        color: '#808080',
                    },
                    nameTextStyle: {
                        align: 'center',
                        verticalAlign: 'bottom',
                        lineHeight: 150,
                        fontSize: 12,
                        fontFamily: "Segoe UI",
                        color: '#808080',
                        fontWeight: 'normal',
                    },
                },
                legend: {
                    left: 'right',
                    textStyle: {
                        color: '#808080',
                        fontSize: '10',
                        fontWeight: 'bold',
                        align: 'left',
                    },
                    padding: [
                        10,
                        70,
                        0,
                        0,
                    ],
                },
                series: [
                    {
                        name: cl,
                        smooth: false,
                        symbol: 'circle',
                        color: chartColorProperty,
                        symbolSize: 2,
                        data: d,
                        type: 'line',
                        lineStyle: {
                            color: colorValues,
                            width: 2,
                        },
                        markLine: showMarkLine,
                    },
                ],
            };
        };
        DhrChartComponent.prototype.getMultiSeriesChartOptions = function (xaxis, yaxis, data) {
            var _this = this;
            var legends = [];
            var yAxisOptions = [];
            // const colors = [
            //   '#4F95D4',
            //   '#D53B8D',
            //   '#03B711'
            // ];
            var seriseData = [];
            yaxis.forEach(function (y, index) {
                legends.push(y.name);
                legends.sort();
                yAxisOptions.push(_this.generateYAxisOption(index, y));
                seriseData.push(_this.generateSeriseData(index, y, data));
            });
            this.chartOption = this.getMultiChartOptions(legends, xaxis, yAxisOptions, seriseData);
        };
        DhrChartComponent.prototype.generateYAxisOption = function (index, y) {
            var yAxisPosition = index % 2 !== 0 ? 'right' : 'left';
            //const yAxisColor = colors[index];
            // const nameTextPadding = yAxisPosition === 'left' ? [0, 2, 0, 0] : [0, 0, 0, 2]; // [top, right, bottom, left]
            var yOffset = Math.floor(index / 2) * 40;
            var yaxisoption = {
                type: 'value',
                //name: y.name,
                nameLocation: 'middle',
                nameTextStyle: {
                    align: 'center',
                    verticalAlign: 'bottom',
                    fontSize: 12,
                    fontFamily: "Segoe UI",
                    color: '#808080',
                    fontWeight: 'normal',
                },
                min: y.min,
                max: y.max,
                nameGap: 25,
                padding: [3, 0, 0, 0],
                // position: yAxisPosition,
                axisLine: {
                    onZero: true,
                    lineStyle: {
                        // color: yAxisColor,
                        width: 2
                    }
                },
                offset: yOffset,
                axisLabel: {
                    show: true,
                    formatter: '{value}'
                },
            };
            return yaxisoption;
        };
        DhrChartComponent.prototype.generateSeriseData = function (index, y, data) {
            var seriseObj = {
                name: y.name,
                type: 'line',
                lineStyle: {
                    //color: colors[index],
                    width: 2
                },
                markLine: {
                    symbol: 'none'
                },
                data: data[index]
            };
            return seriseObj;
        };
        DhrChartComponent.prototype.getMultiChartOptions = function (legends, xaxis, yaxis, seriseData) {
            return {
                //colors: colors,
                tooltip: {
                    trigger: 'axis',
                    backgroundColor: '#FFFFFF',
                    textStyle: {
                        color: '#808080',
                        fontSize: '10',
                        fontWeight: 'bold',
                        align: 'left',
                    },
                    axisPointer: {
                        type: 'cross',
                        lable: {
                            precision: 1,
                        },
                    }
                },
                toolbox: {
                    feature: {
                        dataZoom: {
                            yAxisIndex: 'none',
                            title: {
                                zoom: 'Zoom',
                                back: 'Zoom Reset'
                            }
                        },
                        dataView: {
                            readOnly: true,
                            title: 'Data View',
                            lang: ['Data View', 'Close']
                        },
                        magicType: { show: false, type: ['line'] },
                        restore: {
                            title: 'Restore'
                        },
                        saveAsImage: {
                            title: 'Save Image',
                        },
                    }
                },
                legend: {
                    left: 'right',
                    textStyle: {
                        color: '#808080',
                        fontSize: '10',
                        fontWeight: 'bold',
                    },
                    padding: [10, 140, 0, 0],
                    data: legends.sort(),
                    icon: 'circle',
                },
                xAxis: [
                    {
                        type: 'category',
                        data: xaxis
                    }
                ],
                yAxis: yaxis,
                series: seriseData
            };
        };
        DhrChartComponent.prototype.formatTime = function (value) {
            var datePipe = new common.DatePipe('en-US');
            var tempDate = datePipe.transform(new Date(value), 'dd/MM/yyyy') +
                '\n' +
                datePipe.transform(new Date(value), 'shortTime');
            return tempDate;
        };
        DhrChartComponent.ɵfac = function DhrChartComponent_Factory(t) { return new (t || DhrChartComponent)(); };
        DhrChartComponent.ɵcmp = core.ɵɵdefineComponent({ type: DhrChartComponent, selectors: [["dhr-chart"]], inputs: { chartData: "chartData", xAxisLabel: "xAxisLabel", yAxislabel: "yAxislabel", chartLabel: "chartLabel", chartTitle: "chartTitle", magicType: "magicType", saveAsImage: "saveAsImage", isMultiSeries: "isMultiSeries", showyAxisLabel: "showyAxisLabel", showToolbox: "showToolbox", showToolTip: "showToolTip", showxAxisLabel: "showxAxisLabel", isKpiOverView: "isKpiOverView", color: "color" }, features: [core.ɵɵNgOnChangesFeature], decls: 1, vars: 2, consts: [["echarts", "", 3, "ngClass", "options"]], template: function DhrChartComponent_Template(rf, ctx) { if (rf & 1) {
                core.ɵɵelement(0, "div", 0);
            } if (rf & 2) {
                core.ɵɵproperty("ngClass", ctx.isKpiOverView ? "kpiOverview" : "commonChart")("options", ctx.chartOption);
            } }, directives: [ngxEcharts.NgxEchartsDirective, common.NgClass], styles: [".kpiOverview[_ngcontent-%COMP%]{height:70px!important;width:315px!important}.commonChart[_ngcontent-%COMP%]{height:auto;width:auto}"] });
        return DhrChartComponent;
    }());
    /*@__PURE__*/ (function () { core.ɵsetClassMetadata(DhrChartComponent, [{
            type: core.Component,
            args: [{
                    selector: 'dhr-chart',
                    templateUrl: './dhr-chart.component.html',
                    styleUrls: ['./dhr-chart.component.css']
                }]
        }], function () { return []; }, { chartData: [{
                type: core.Input
            }], xAxisLabel: [{
                type: core.Input
            }], yAxislabel: [{
                type: core.Input
            }], chartLabel: [{
                type: core.Input
            }], chartTitle: [{
                type: core.Input
            }], magicType: [{
                type: core.Input
            }], saveAsImage: [{
                type: core.Input
            }], isMultiSeries: [{
                type: core.Input
            }], showyAxisLabel: [{
                type: core.Input
            }], showToolbox: [{
                type: core.Input
            }], showToolTip: [{
                type: core.Input
            }], showxAxisLabel: [{
                type: core.Input
            }], isKpiOverView: [{
                type: core.Input
            }], color: [{
                type: core.Input
            }] }); })();

    var DhrChartModule = /** @class */ (function () {
        function DhrChartModule() {
        }
        DhrChartModule.ɵmod = core.ɵɵdefineNgModule({ type: DhrChartModule });
        DhrChartModule.ɵinj = core.ɵɵdefineInjector({ factory: function DhrChartModule_Factory(t) { return new (t || DhrChartModule)(); }, imports: [[
                    common.CommonModule,
                    ngxEcharts.NgxEchartsModule.forRoot({
                        echarts: function () { return import('echarts'); },
                    }),
                ]] });
        return DhrChartModule;
    }());
    (function () { (typeof ngJitMode === "undefined" || ngJitMode) && core.ɵɵsetNgModuleScope(DhrChartModule, { declarations: [DhrChartComponent], imports: [common.CommonModule, ngxEcharts.NgxEchartsModule], exports: [DhrChartComponent] }); })();
    /*@__PURE__*/ (function () { core.ɵsetClassMetadata(DhrChartModule, [{
            type: core.NgModule,
            args: [{
                    declarations: [DhrChartComponent],
                    imports: [
                        common.CommonModule,
                        ngxEcharts.NgxEchartsModule.forRoot({
                            echarts: function () { return import('echarts'); },
                        }),
                    ],
                    exports: [DhrChartComponent]
                }]
        }], null, null); })();

    exports.DhrChartComponent = DhrChartComponent;
    exports.DhrChartModule = DhrChartModule;
    exports.DhrChartService = DhrChartService;

    Object.defineProperty(exports, '__esModule', { value: true });

})));
//# sourceMappingURL=dhr-chart.umd.js.map
