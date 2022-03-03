import { ɵɵdefineInjectable, ɵsetClassMetadata, Injectable, ɵɵdefineComponent, ɵɵNgOnChangesFeature, ɵɵelement, ɵɵproperty, Component, Input, ɵɵdefineNgModule, ɵɵdefineInjector, ɵɵsetNgModuleScope, NgModule } from '@angular/core';
import { DatePipe, NgClass, CommonModule } from '@angular/common';
import { NgxEchartsDirective, NgxEchartsModule } from 'ngx-echarts';

class DhrChartService {
    constructor() { }
}
DhrChartService.ɵfac = function DhrChartService_Factory(t) { return new (t || DhrChartService)(); };
DhrChartService.ɵprov = ɵɵdefineInjectable({ token: DhrChartService, factory: DhrChartService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { ɵsetClassMetadata(DhrChartService, [{
        type: Injectable,
        args: [{
                providedIn: 'root'
            }]
    }], function () { return []; }, null); })();

class DhrChartComponent {
    constructor() { }
    ngOnInit() {
        this.loadChart(this.chartData, this.xAxisLabel, this.yAxislabel, this.chartLabel, this.chartTitle, this.showyAxisLabel, this.showToolbox, this.showToolTip, this.showxAxisLabel, this.isKpiOverView, this.color);
    }
    ngOnChanges(changes) {
        // Called before any other lifecycle hook. Use it to inject dependencies, but avoid any serious work here.
        // Add '${implements OnChanges}' to the class.
        this.loadChart(this.chartData, this.xAxisLabel, this.yAxislabel, this.chartLabel, this.chartTitle, this.showyAxisLabel, this.showToolbox, this.showToolTip, this.showxAxisLabel, this.isKpiOverView, this.color);
    }
    ngAfterViewInit() {
        // Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
        // Add 'implements AfterViewInit' to the class.
        this.loadChart(this.chartData, this.xAxisLabel, this.yAxislabel, this.chartLabel, this.chartTitle, this.showyAxisLabel, this.showToolbox, this.showToolTip, this.showxAxisLabel, this.isKpiOverView, this.color);
    }
    loadChart(d, xl, yl, cl, ct, yAxisLabelFlag, toolboxFlag, tooltipFlag, xAxisLabelFlag, kpiOverviewflag, colorValues) {
        if (this.isMultiSeries) {
            this.getMultiSeriesChartOptions(this.xAxisLabel, this.yAxislabel, this.chartData);
        }
        else {
            this.chartOption = this.getChartOptions(d, xl, yl, cl, ct, yAxisLabelFlag, toolboxFlag, tooltipFlag, xAxisLabelFlag, kpiOverviewflag, colorValues);
        }
    }
    getChartOptions(d, xl, yl, cl, ct, yAxisLabelFlag, toolboxFlag, tooltipFlag, xAxisLabelFlag, kpiOverviewflag, colorValues) {
        let showMarkLine = {};
        let chartColorProperty = '#009DDC';
        if (!kpiOverviewflag) {
            showMarkLine = {
                data: [
                    {
                        type: 'average',
                        label: {
                            position: 'End',
                            distance: 5,
                            formatter: (params) => {
                                return `${params.value}`;
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
                    formatter: (param) => {
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
                formatter: (params) => {
                    return `<div style=" padding: 10px 10px 20px 10px;
          box-shadow: 2px 2px 4px 2px #aaaaaa;
          background-color: white;">
          <span style="color: #808080;font-size: 12px;">${new Date(params[0].name).toString().split(' ', 4).join(' ') +
                        ' ' +
                        ('0' + new Date(params[0].name).getHours()).slice(-2) +
                        ':' +
                        ('0' + new Date(params[0].name).getMinutes()).slice(-2)}</span><br>
                  <span style="color: #333333; font-size: 12px">${yl}</span>
                  &nbsp;&nbsp;&nbsp;
                  <span style="color: #333333; font-size: 14px; text-align:right; font-weight:700">${params[0].value[1]}&nbsp;${xl}</span></div>`;
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
    }
    getMultiSeriesChartOptions(xaxis, yaxis, data) {
        const legends = [];
        const yAxisOptions = [];
        // const colors = [
        //   '#4F95D4',
        //   '#D53B8D',
        //   '#03B711'
        // ];
        const seriseData = [];
        yaxis.forEach((y, index) => {
            legends.push(y.name);
            legends.sort();
            yAxisOptions.push(this.generateYAxisOption(index, y));
            seriseData.push(this.generateSeriseData(index, y, data));
        });
        this.chartOption = this.getMultiChartOptions(legends, xaxis, yAxisOptions, seriseData);
    }
    generateYAxisOption(index, y) {
        // const yAxisPosition = index % 2 !== 0 ? 'right' : 'left';
        // const yAxisColor = colors[index];
        // const nameTextPadding = yAxisPosition === 'left' ? [0, 2, 0, 0] : [0, 0, 0, 2]; // [top, right, bottom, left]
        const yOffset = Math.floor(index / 2) * 40;
        const yaxisoption = {
            type: 'value',
            name: y.name,
            nameLocation: 'center',
            nameGap: 65,
            nameTextStyle: {
                align: 'end',
                // verticalAlign: 'bottom',
                fontSize: 12,
                fontFamily: "Segoe UI",
                color: '#808080',
                fontWeight: 'normal',
            },
            min: y.min,
            max: y.max,
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
    }
    generateSeriseData(index, y, data) {
        const seriseObj = {
            name: y.name,
            type: 'line',
            yAxisIndex: index,
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
    }
    getMultiChartOptions(legends, xaxis, yaxis, seriseData) {
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
    }
    formatTime(value) {
        const datePipe = new DatePipe('en-US');
        const tempDate = datePipe.transform(new Date(value), 'dd/MM/yyyy') +
            '\n' +
            datePipe.transform(new Date(value), 'shortTime');
        return tempDate;
    }
}
DhrChartComponent.ɵfac = function DhrChartComponent_Factory(t) { return new (t || DhrChartComponent)(); };
DhrChartComponent.ɵcmp = ɵɵdefineComponent({ type: DhrChartComponent, selectors: [["dhr-chart"]], inputs: { chartData: "chartData", xAxisLabel: "xAxisLabel", yAxislabel: "yAxislabel", chartLabel: "chartLabel", chartTitle: "chartTitle", magicType: "magicType", saveAsImage: "saveAsImage", isMultiSeries: "isMultiSeries", showyAxisLabel: "showyAxisLabel", showToolbox: "showToolbox", showToolTip: "showToolTip", showxAxisLabel: "showxAxisLabel", isKpiOverView: "isKpiOverView", color: "color" }, features: [ɵɵNgOnChangesFeature], decls: 1, vars: 2, consts: [["echarts", "", 3, "ngClass", "options"]], template: function DhrChartComponent_Template(rf, ctx) { if (rf & 1) {
        ɵɵelement(0, "div", 0);
    } if (rf & 2) {
        ɵɵproperty("ngClass", ctx.isKpiOverView ? "kpiOverview" : "commonChart")("options", ctx.chartOption);
    } }, directives: [NgxEchartsDirective, NgClass], styles: [".kpiOverview[_ngcontent-%COMP%]{height:70px!important;width:315px!important}.commonChart[_ngcontent-%COMP%]{height:auto;width:auto}"] });
/*@__PURE__*/ (function () { ɵsetClassMetadata(DhrChartComponent, [{
        type: Component,
        args: [{
                selector: 'dhr-chart',
                templateUrl: './dhr-chart.component.html',
                styleUrls: ['./dhr-chart.component.css']
            }]
    }], function () { return []; }, { chartData: [{
            type: Input
        }], xAxisLabel: [{
            type: Input
        }], yAxislabel: [{
            type: Input
        }], chartLabel: [{
            type: Input
        }], chartTitle: [{
            type: Input
        }], magicType: [{
            type: Input
        }], saveAsImage: [{
            type: Input
        }], isMultiSeries: [{
            type: Input
        }], showyAxisLabel: [{
            type: Input
        }], showToolbox: [{
            type: Input
        }], showToolTip: [{
            type: Input
        }], showxAxisLabel: [{
            type: Input
        }], isKpiOverView: [{
            type: Input
        }], color: [{
            type: Input
        }] }); })();

class DhrChartModule {
}
DhrChartModule.ɵmod = ɵɵdefineNgModule({ type: DhrChartModule });
DhrChartModule.ɵinj = ɵɵdefineInjector({ factory: function DhrChartModule_Factory(t) { return new (t || DhrChartModule)(); }, imports: [[
            CommonModule,
            NgxEchartsModule.forRoot({
                echarts: () => import('echarts'),
            }),
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && ɵɵsetNgModuleScope(DhrChartModule, { declarations: [DhrChartComponent], imports: [CommonModule, NgxEchartsModule], exports: [DhrChartComponent] }); })();
/*@__PURE__*/ (function () { ɵsetClassMetadata(DhrChartModule, [{
        type: NgModule,
        args: [{
                declarations: [DhrChartComponent],
                imports: [
                    CommonModule,
                    NgxEchartsModule.forRoot({
                        echarts: () => import('echarts'),
                    }),
                ],
                exports: [DhrChartComponent]
            }]
    }], null, null); })();

/*
 * Public API Surface of dhr-chart
 */

/**
 * Generated bundle index. Do not edit.
 */

export { DhrChartComponent, DhrChartModule, DhrChartService };
//# sourceMappingURL=dhr-chart.js.map
