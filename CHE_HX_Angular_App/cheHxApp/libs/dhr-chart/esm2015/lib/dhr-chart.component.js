import { DatePipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import * as i0 from "@angular/core";
import * as i1 from "ngx-echarts";
import * as i2 from "@angular/common";
export class DhrChartComponent {
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
DhrChartComponent.ɵcmp = i0.ɵɵdefineComponent({ type: DhrChartComponent, selectors: [["dhr-chart"]], inputs: { chartData: "chartData", xAxisLabel: "xAxisLabel", yAxislabel: "yAxislabel", chartLabel: "chartLabel", chartTitle: "chartTitle", magicType: "magicType", saveAsImage: "saveAsImage", isMultiSeries: "isMultiSeries", showyAxisLabel: "showyAxisLabel", showToolbox: "showToolbox", showToolTip: "showToolTip", showxAxisLabel: "showxAxisLabel", isKpiOverView: "isKpiOverView", color: "color" }, features: [i0.ɵɵNgOnChangesFeature], decls: 1, vars: 2, consts: [["echarts", "", 3, "ngClass", "options"]], template: function DhrChartComponent_Template(rf, ctx) { if (rf & 1) {
        i0.ɵɵelement(0, "div", 0);
    } if (rf & 2) {
        i0.ɵɵproperty("ngClass", ctx.isKpiOverView ? "kpiOverview" : "commonChart")("options", ctx.chartOption);
    } }, directives: [i1.NgxEchartsDirective, i2.NgClass], styles: [".kpiOverview[_ngcontent-%COMP%]{height:70px!important;width:315px!important}.commonChart[_ngcontent-%COMP%]{height:auto;width:auto}"] });
/*@__PURE__*/ (function () { i0.ɵsetClassMetadata(DhrChartComponent, [{
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
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZGhyLWNoYXJ0LmNvbXBvbmVudC5qcyIsInNvdXJjZVJvb3QiOiJuZzovL2Roci1jaGFydC8iLCJzb3VyY2VzIjpbImxpYi9kaHItY2hhcnQuY29tcG9uZW50LnRzIiwibGliL2Roci1jaGFydC5jb21wb25lbnQuaHRtbCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQSxPQUFPLEVBQUUsUUFBUSxFQUFFLE1BQU0saUJBQWlCLENBQUM7QUFDM0MsT0FBTyxFQUFFLFNBQVMsRUFBVSxLQUFLLEVBQWlCLE1BQU0sZUFBZSxDQUFDOzs7O0FBU3hFLE1BQU0sT0FBTyxpQkFBaUI7SUFvQjVCLGdCQUFnQixDQUFDO0lBRWpCLFFBQVE7UUFDTixJQUFJLENBQUMsU0FBUyxDQUFDLElBQUksQ0FBQyxTQUFTLEVBQUUsSUFBSSxDQUFDLFVBQVUsRUFBRSxJQUFJLENBQUMsVUFBVSxFQUFFLElBQUksQ0FBQyxVQUFVLEVBQUUsSUFBSSxDQUFDLFVBQVUsRUFDOUYsSUFBSSxDQUFDLGNBQWMsRUFBRSxJQUFJLENBQUMsV0FBVyxFQUFFLElBQUksQ0FBQyxXQUFXLEVBQUUsSUFBSSxDQUFDLGNBQWMsRUFBRSxJQUFJLENBQUMsYUFBYSxFQUFFLElBQUksQ0FBQyxLQUFLLENBQUMsQ0FBQztJQUNuSCxDQUFDO0lBQ0QsV0FBVyxDQUFDLE9BQXNCO1FBQ2hDLDBHQUEwRztRQUMxRyw4Q0FBOEM7UUFDOUMsSUFBSSxDQUFDLFNBQVMsQ0FBQyxJQUFJLENBQUMsU0FBUyxFQUFFLElBQUksQ0FBQyxVQUFVLEVBQUUsSUFBSSxDQUFDLFVBQVUsRUFBRSxJQUFJLENBQUMsVUFBVSxFQUM5RSxJQUFJLENBQUMsVUFBVSxFQUFFLElBQUksQ0FBQyxjQUFjLEVBQUUsSUFBSSxDQUFDLFdBQVcsRUFBRSxJQUFJLENBQUMsV0FBVyxFQUFFLElBQUksQ0FBQyxjQUFjLEVBQUUsSUFBSSxDQUFDLGFBQWEsRUFBRSxJQUFJLENBQUMsS0FBSyxDQUFDLENBQUM7SUFDbkksQ0FBQztJQUNELGVB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LEVBQUUsQ0FBQyxDQUFDO2dCQUN4QixJQUFJLEVBQUUsT0FBTyxDQUFDLElBQUksRUFBRTtnQkFDcEIsSUFBSSxFQUFFLFFBQVE7YUFDZjtZQUNELEtBQUssRUFBRTtnQkFDTDtvQkFDRSxJQUFJLEVBQUUsVUFBVTtvQkFDaEIsSUFBSSxFQUFFLEtBQUs7aUJBQ1o7YUFDRjtZQUNELEtBQUssRUFBRSxLQUFLO1lBQ1osTUFBTSxFQUFFLFVBQVU7U0FDbkIsQ0FBQztJQUNKLENBQUM7SUFFRCxVQUFVLENBQUMsS0FBSztRQUNkLE1BQU0sUUFBUSxHQUFHLElBQUksUUFBUSxDQUFDLE9BQU8sQ0FBQyxDQUFDO1FBQ3ZDLE1BQU0sUUFBUSxHQUNaLFFBQVEsQ0FBQyxTQUFTLENBQUMsSUFBSSxJQUFJLENBQUMsS0FBSyxDQUFDLEVBQUUsWUFBWSxDQUFDO1lBQ2pELElBQUk7WUFDSixRQUFRLENBQUMsU0FBUyxDQUFDLElBQUksSUFBSSxDQUFDLEtBQUssQ0FBQyxFQUFFLFdBQVcsQ0FBQyxDQUFDO1FBQ25ELE9BQU8sUUFBUSxDQUFDO0lBQ2xCLENBQUM7O2tGQXJXVSxpQkFBaUI7c0RBQWpCLGlCQUFpQjtRQ1Y5Qix5QkFHTzs7UUFIRiwyRUFBeUQsNEJBQUE7O2tERFVqRCxpQkFBaUI7Y0FMN0IsU0FBUztlQUFDO2dCQUNULFFBQVEsRUFBRSxXQUFXO2dCQUNyQixXQUFXLEVBQUUsNEJBQTRCO2dCQUN6QyxTQUFTLEVBQUUsQ0FBQywyQkFBMkIsQ0FBQzthQUN6Qzs7a0JBR0UsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSzs7a0JBQ0wsS0FBSyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IERhdGVQaXBlIH0gZnJvbSAnQGFuZ3VsYXIvY29tbW9uJztcclxuaW1wb3J0IHsgQ29tcG9uZW50LCBPbkluaXQsIElucHV0LCBTaW1wbGVDaGFuZ2VzIH0gZnJvbSAnQGFuZ3VsYXIvY29yZSc7XHJcbmltcG9ydCB7IEVDaGFydE9wdGlvbiB9IGZyb20gJ2VjaGFydHMnO1xyXG5pbXBvcnQgKiBhcyBlY2hhcnRzIGZyb20gJ2VjaGFydHMnO1xyXG5cclxuQENvbXBvbmVudCh7XHJcbiAgc2VsZWN0b3I6ICdkaHItY2hhcnQnLFxyXG4gIHRlbXBsYXRlVXJsOiAnLi9kaHItY2hhcnQuY29tcG9uZW50Lmh0bWwnLFxyXG4gIHN0eWxlVXJsczogWycuL2Roci1jaGFydC5jb21wb25lbnQuY3NzJ11cclxufSlcclxuZXhwb3J0IGNsYXNzIERockNoYXJ0Q29tcG9uZW50IGltcGxlbWVudHMgT25Jbml0IHtcclxuXHJcbiAgQElucHV0KCkgY2hhcnREYXRhOiBhbnk7XHJcbiAgQElucHV0KCkgeEF4aXNMYWJlbDogYW55O1xyXG4gIEBJbnB1dCgpIHlBeGlzbGFiZWw6IGFueTtcclxuICBASW5wdXQoKSBjaGFydExhYmVsOiBhbnk7XHJcbiAgQElucHV0KCkgY2hhcnRUaXRsZTogYW55O1xyXG4gIEBJbnB1dCgpIG1hZ2ljVHlwZTogYm9vbGVhbjtcclxuICBASW5wdXQoKSBzYXZlQXNJbWFnZTogYm9vbGVhbjtcclxuICBASW5wdXQoKSBpc011bHRpU2VyaWVzOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNob3d5QXhpc0xhYmVsOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNob3dUb29sYm94OiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNob3dUb29sVGlwOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNob3d4QXhpc0xhYmVsOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIGlzS3BpT3ZlclZpZXc6IGJvb2xlYW47XHJcbiAgQElucHV0KCkgY29sb3I7XHJcblxyXG4gIGNoYXJ0T3B0aW9uOiBFQ2hhcnRPcHRpb247XHJcbiAgb3B0aW9uczogRUNoYXJ0T3B0aW9uO1xyXG5cclxuICBjb25zdHJ1Y3RvcigpIHsgfVxyXG5cclxuICBuZ09uSW5pdCgpOiB2b2lkIHtcclxuICAgIHRoaXMubG9hZENoYXJ0KHRoaXMuY2hhcnREYXRhLCB0aGlzLnhBeGlzTGFiZWwsIHRoaXMueUF4aXNsYWJlbCwgdGhpcy5jaGFydExhYmVsLCB0aGlzLmNoYXJ0VGl0bGUsXHJcbiAgICAgICB0aGlzLnNob3d5QXhpc0xhYmVsLCB0aGlzLnNob3dUb29sYm94LCB0aGlzLnNob3dUb29sVGlwLCB0aGlzLnNob3d4QXhpc0xhYmVsLCB0aGlzLmlzS3BpT3ZlclZpZXcsIHRoaXMuY29sb3IpO1xyXG4gIH1cclxuICBuZ09uQ2hhbmdlcyhjaGFuZ2VzOiBTaW1wbGVDaGFuZ2VzKTogdm9pZCB7XHJcbiAgICAvLyBDYWxsZWQgYmVmb3JlIGFueSBvdGhlciBsaWZlY3ljbGUgaG9vay4gVXNlIGl0IHRvIGluamVjdCBkZXBlbmRlbmNpZXMsIGJ1dCBhdm9pZCBhbnkgc2VyaW91cyB3b3JrIGhlcmUuXHJcbiAgICAvLyBBZGQgJyR7aW1wbGVtZW50cyBPbkNoYW5nZXN9JyB0byB0aGUgY2xhc3MuXHJcbiAgICB0aGlzLmxvYWRDaGFydCh0aGlzLmNoYXJ0RGF0YSwgdGhpcy54QXhpc0xhYmVsLCB0aGlzLnlBeGlzbGFiZWwsIHRoaXMuY2hhcnRMYWJlbCwgXHJcbiAgICAgIHRoaXMuY2hhcnRUaXRsZSwgdGhpcy5zaG93eUF4aXNMYWJlbCwgdGhpcy5zaG93VG9vbGJveCwgdGhpcy5zaG93VG9vbFRpcCwgdGhpcy5zaG93eEF4aXNMYWJlbCwgdGhpcy5pc0twaU92ZXJWaWV3LCB0aGlzLmNvbG9yKTtcclxuICB9XHJcbiAgbmdBZnRlclZpZXdJbml0KCk6IHZvaWQge1xyXG4gICAgLy8gQ2FsbGVkIGFmdGVyIG5nQWZ0ZXJDb250ZW50SW5pdCB3aGVuIHRoZSBjb21wb25lbnQncyB2aWV3IGhhcyBiZWVuIGluaXRpYWxpemVkLiBBcHBsaWVzIHRvIGNvbXBvbmVudHMgb25seS5cclxuICAgIC8vIEFkZCAnaW1wbGVtZW50cyBBZnRlclZpZXdJbml0JyB0byB0aGUgY2xhc3MuXHJcbiAgICB0aGlzLmxvYWRDaGFydCh0aGlzLmNoYXJ0RGF0YSwgdGhpcy54QXhpc0xhYmVsLCB0aGlzLnlBeGlzbGFiZWwsIFxyXG4gICAgdGhpcy5jaGFydExhYmVsLCB0aGlzLmNoYXJ0VGl0bGUsIHRoaXMuc2hvd3lBeGlzTGFiZWwsIHRoaXMuc2hvd1Rvb2xib3gsIHRoaXMuc2hvd1Rvb2xUaXAsXHJcbiAgICAgdGhpcy5zaG93eEF4aXNMYWJlbCwgdGhpcy5pc0twaU92ZXJWaWV3LCB0aGlzLmNvbG9yKTtcclxuICB9XHJcbiAgbG9hZENoYXJ0KGQsIHhsLCB5bCwgY2wsIGN0LCB5QXhpc0xhYmVsRmxhZywgdG9vbGJveEZsYWcsIHRvb2x0aXBGbGFnLCB4QXhpc0xhYmVsRmxhZywga3BpT3ZlcnZpZXdmbGFnLCBjb2xvclZhbHVlcykge1xyXG4gICAgaWYgKHRoaXMuaXNNdWx0aVNlcmllcykge1xyXG4gICAgICB0aGlzLmdldE11bHRpU2VyaWVzQ2hhcnRPcHRpb25zKHRoaXMueEF4aXNMYWJlbCwgdGhpcy55QXhpc2xhYmVsLCB0aGlzLmNoYXJ0RGF0YSk7XHJcbiAgICB9IGVsc2Uge1xyXG4gICAgICB0aGlzLmNoYXJ0T3B0aW9uID0gdGhpcy5nZXRDaGFydE9wdGlvbnMoZCwgeGwsIHlsLCBjbCwgY3QsXHJcbiAgICAgIHlBeGlzTGFiZWxGbGFnLCB0b29sYm94RmxhZywgdG9vbHRpcEZsYWcsIHhBeGlzTGFiZWxGbGFnLCBrcGlPdmVydmlld2ZsYWcsIGNvbG9yVmFsdWVzKTtcclxuICAgIH1cclxuICB9XHJcblxyXG4gIGdldENoYXJ0T3B0aW9ucyhkLCB4bCwgeWwsIGNsLCBjdCwgeUF4aXNMYWJlbEZsYWcsIHRvb2xib3hGbGFnLCB0b29sdGlwRmxhZywgeEF4aXNMYWJlbEZsYWcsIGtwaU92ZXJ2aWV3ZmxhZywgY29sb3JWYWx1ZXMpOiBhbnkge1xyXG4gICAgbGV0IHNob3dNYXJrTGluZSA9IHt9O1xyXG4gICAgbGV0IGNoYXJ0Q29sb3JQcm9wZXJ0eSA9ICcjMDA5RERDJztcclxuICAgIGlmICgha3BpT3ZlcnZpZXdmbGFnKXtcclxuICAgICAgc2hvd01hcmtMaW5lID0ge1xyXG4gICAgICAgIGRhdGE6IFtcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgdHlwZTogJ2F2ZXJhZ2UnLFxyXG4gICAgICAgICAgICBsYWJlbDoge1xyXG4gICAgICAgICAgICAgIHBvc2l0aW9uOiAnRW5kJyxcclxuICAgICAgICAgICAgICBkaXN0YW5jZTogNSxcclxuICAgICAgICAgICAgICBmb3JtYXR0ZXI6IChwYXJhbXMpID0+IHtcclxuICAgICAgICAgICAgICAgIHJldHVybiBgJHtwYXJhbXMudmFsdWV9YDtcclxuICAgICAgICAgICAgICB9LFxyXG4gICAgICAgICAgICB9LFxyXG4gICAgICAgICAgICBsaW5lU3R5bGU6IHsgY29sb3I6ICcjNzA3MDcwJywgd2lkdGg6IDIgfSxcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgXSxcclxuICAgICAgICBzeW1ib2w6ICdub25lJyxcclxuICAgICAgfVxyXG4gICAgfVxyXG4gICAgaWYgKGtwaU92ZXJ2aWV3ZmxhZykge1xyXG4gICAgICBjaGFydENvbG9yUHJvcGVydHkgPSBjb2xvclZhbHVlcztcclxuICAgIH1cclxuICAgIHJldHVybiB7XHJcbiAgICAgIHRpdGxlOiB7XHJcbiAgICAgICAgdGV4dDogY3QsXHJcbiAgICAgICAgbGVmdDogJ2xlZnQnLFxyXG4gICAgICAgIHRvcDogJ2xlZnQnLFxyXG4gICAgICAgIHRyaWdnZXJFdmVudDogdHJ1ZSxcclxuICAgICAgICB0ZXh0U3R5bGU6IHtcclxuICAgICAgICAgIGZvbnRTaXplOiAxMyxcclxuICAgICAgICB9LFxyXG4gICAgICAgIHBhZGRpbmc6IFtcclxuICAgICAgICAgIDE1LCAvLyB1cFxyXG4gICAgICAgICAgMTAsIC8vIHJpZ2h0XHJcbiAgICAgICAgICA1LCAvLyBkb3duXHJcbiAgICAgICAgICAxMCwgLy8gbGVmdFxyXG4gICAgICAgIF0sXHJcbiAgICAgICAgc2hvdzogZmFsc2UsXHJcbiAgICAgIH0sXHJcbiAgICAgIHRvb2xib3g6IHtcclxuICAgICAgICBzaG93OiB0b29sYm94RmxhZyxcclxuICAgICAgICBvcmllbnQ6ICd2ZXJ0aWNhbCcsXHJcbiAgICAgICAgc2hvd1RpdGxlOiBmYWxzZSxcclxuICAgICAgICBmZWF0dXJlOiB7XHJcbiAgICAgICAgICBzYXZlQXNJbWFnZToge1xyXG4gICAgICAgICAgICB0aXRsZTogJ1NhdmUgSW1hZ2UnLFxyXG4gICAgICAgICAgfSxcclxuICAgICAgICB9LFxyXG4gICAgICAgIHRvb2x0aXA6IHtcclxuICAgICAgICAgIHNob3c6IHRydWUsXHJcbiAgICAgICAgICBmb3JtYXR0ZXI6IChwYXJhbSkgPT4ge1xyXG4gICAgICAgICAgICByZXR1cm4gJzxkaXY+JyArIHBhcmFtLnRpdGxlICsgJzwvZGl2Pic7XHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAgYmFja2dyb3VuZENvbG9yOiAnIzIyMicsXHJcbiAgICAgICAgICB0ZXh0U3R5bGU6IHtcclxuICAgICAgICAgICAgZm9udFNpemU6IDgsXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAgZXh0cmFDc3NUZXh0OiAnYm94LXNoYWRvdzogMCAwIDFweCByZ2JhKDAsIDAsIDAsIDAuMyk7JywgLy8gdXNlci1kZWZpbmVkIENTUyBzdHlsZXNcclxuICAgICAgICB9LFxyXG4gICAgICB9LFxyXG4gICAgICB0b29sdGlwOiB7XHJcbiAgICAgICAgc2hvdzogdG9vbHRpcEZsYWcsXHJcbiAgICAgICAgdHJpZ2dlcjogJ2F4aXMnLFxyXG4gICAgICAgIGJhY2tncm91bmRDb2xvcjogJyNGRkZGRkYnLFxyXG4gICAgICAgIGF4aXNQb2ludGVyOiB7XHJcbiAgICAgICAgICB0eXBlOiAnbGluZScsXHJcbiAgICAgICAgICBsYWJsZToge1xyXG4gICAgICAgICAgICBwcmVjaXNpb246IDEsXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgZm9ybWF0dGVyOiAocGFyYW1zKSA9PiB7XHJcbiAgICAgICAgICByZXR1cm4gYDxkaXYgc3R5bGU9XCIgcGFkZGluZzogMTBweCAxMHB4IDIwcHggMTBweDtcclxuICAgICAgICAgIGJveC1zaGFkb3c6IDJweCAycHggNHB4IDJweCAjYWFhYWFhO1xyXG4gICAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XCI+XHJcbiAgICAgICAgICA8c3BhbiBzdHlsZT1cImNvbG9yOiAjODA4MDgwO2ZvbnQtc2l6ZTogMTJweDtcIj4ke1xyXG4gICAgICAgICAgICBuZXcgRGF0ZShwYXJhbXNbMF0ubmFtZSkudG9TdHJpbmcoKS5zcGxpdCgnICcsIDQpLmpvaW4oJyAnKSArXHJcbiAgICAgICAgICAgICcgJyArXHJcbiAgICAgICAgICAgICgnMCcgKyBuZXcgRGF0ZShwYXJhbXNbMF0ubmFtZSkuZ2V0SG91cnMoKSkuc2xpY2UoLTIpICtcclxuICAgICAgICAgICAgJzonICtcclxuICAgICAgICAgICAgKCcwJyArIG5ldyBEYXRlKHBhcmFtc1swXS5uYW1lKS5nZXRNaW51dGVzKCkpLnNsaWNlKC0yKVxyXG4gICAgICAgICAgICB9PC9zcGFuPjxicj5cclxuICAgICAgICAgICAgICAgICAgPHNwYW4gc3R5bGU9XCJjb2xvcjogIzMzMzMzMzsgZm9udC1zaXplOiAxMnB4XCI+JHt5bH08L3NwYW4+XHJcbiAgICAgICAgICAgICAgICAgICZuYnNwOyZuYnNwOyZuYnNwO1xyXG4gICAgICAgICAgICAgICAgICA8c3BhbiBzdHlsZT1cImNvbG9yOiAjMzMzMzMzOyBmb250LXNpemU6IDE0cHg7IHRleHQtYWxpZ246cmlnaHQ7IGZvbnQtd2VpZ2h0OjcwMFwiPiR7XHJcbiAgICAgICAgICAgIHBhcmFtc1swXS52YWx1ZVsxXVxyXG4gICAgICAgICAgICB9Jm5ic3A7JHt4bH08L3NwYW4+PC9kaXY+YDtcclxuICAgICAgICB9LFxyXG4gICAgICB9LFxyXG4gICAgICBncmlkOiB7XHJcbiAgICAgICAgY29udGFpbkxhYmVsOiB0cnVlLFxyXG4gICAgICB9LFxyXG4gICAgICB4QXhpczoge1xyXG4gICAgICAgIHR5cGU6ICdjYXRlZ29yeScsXHJcbiAgICAgICAgc2hvdzogeEF4aXNMYWJlbEZsYWcsXHJcbiAgICAgICAgYXhpc0xhYmVsOiB7XHJcbiAgICAgICAgICBjb2xvcjogJyM4MDgwODAnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgYXhpc1RpY2s6IHtcclxuICAgICAgICAgIGFsaWduV2l0aExhYmVsOiB0cnVlLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgc3BsaXROdW1iZXI6IDEsXHJcbiAgICAgIH0sXHJcbiAgICAgIHlBeGlzOiB7XHJcbiAgICAgICAgdHlwZTogJ3ZhbHVlJyxcclxuICAgICAgICBuYW1lOiB5bCxcclxuICAgICAgICBuYW1lTG9jYXRpb246ICdtaWRkbGUnLFxyXG4gICAgICAgIHNob3c6IHlBeGlzTGFiZWxGbGFnLFxyXG4gICAgICAgIGF4aXNMYWJlbDoge1xyXG4gICAgICAgICAgY29sb3I6ICcjODA4MDgwJyxcclxuICAgICAgICB9LFxyXG4gICAgICAgIG5hbWVUZXh0U3R5bGU6IHtcclxuICAgICAgICAgIGFsaWduOiAnY2VudGVyJyxcclxuICAgICAgICAgIHZlcnRpY2FsQWxpZ246ICdib3R0b20nLFxyXG4gICAgICAgICAgbGluZUhlaWdodDogMTUwLFxyXG4gICAgICAgICAgZm9udFNpemU6IDEyLFxyXG4gICAgICAgICAgZm9udEZhbWlseTogXCJTZWdvZSBVSVwiLFxyXG4gICAgICAgICAgY29sb3I6ICcjODA4MDgwJyxcclxuICAgICAgICAgIGZvbnRXZWlnaHQ6ICdub3JtYWwnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgIH0sXHJcbiAgICAgIGxlZ2VuZDoge1xyXG4gICAgICAgIGxlZnQ6ICdyaWdodCcsXHJcbiAgICAgICAgdGV4dFN0eWxlOiB7XHJcbiAgICAgICAgICBjb2xvcjogJyM4MDgwODAnLFxyXG4gICAgICAgICAgZm9udFNpemU6ICcxMCcsXHJcbiAgICAgICAgICBmb250V2VpZ2h0OiAnYm9sZCcsXHJcbiAgICAgICAgICBhbGlnbjogJ2xlZnQnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgcGFkZGluZzogW1xyXG4gICAgICAgICAgMTAsIC8vIHVwXHJcbiAgICAgICAgICA3MCwgLy8gcmlnaHRcclxuICAgICAgICAgIDAsIC8vIGRvd25cclxuICAgICAgICAgIDAsIC8vIGxlZnRcclxuICAgICAgICBdLFxyXG4gICAgICB9LFxyXG4gICAgICBzZXJpZXM6IFtcclxuICAgICAgICB7XHJcbiAgICAgICAgICBuYW1lOiBjbCxcclxuICAgICAgICAgIHNtb290aDogZmFsc2UsXHJcbiAgICAgICAgICBzeW1ib2w6ICdjaXJjbGUnLFxyXG4gICAgICAgICAgY29sb3I6IGNoYXJ0Q29sb3JQcm9wZXJ0eSxcclxuICAgICAgICAgIHN5bWJvbFNpemU6IDIsXHJcbiAgICAgICAgICBkYXRhOiBkLFxyXG4gICAgICAgICAgdHlwZTogJ2xpbmUnLFxyXG4gICAgICAgICAgbGluZVN0eWxlOiB7XHJcbiAgICAgICAgICAgIGNvbG9yOiBjb2xvclZhbHVlcyxcclxuICAgICAgICAgICAgd2lkdGg6IDIsXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAgbWFya0xpbmU6IHNob3dNYXJrTGluZSxcclxuICAgICAgICB9LFxyXG4gICAgICBdLFxyXG4gICAgfTtcclxuICB9XHJcblxyXG4gIGdldE11bHRpU2VyaWVzQ2hhcnRPcHRpb25zKHhheGlzLCB5YXhpcywgZGF0YSk6IHZvaWQge1xyXG4gICAgY29uc3QgbGVnZW5kcyA9IFtdO1xyXG4gICAgY29uc3QgeUF4aXNPcHRpb25zID0gW107XHJcbiAgICAvLyBjb25zdCBjb2xvcnMgPSBbXHJcbiAgICAvLyAgICcjNEY5NUQ0JyxcclxuICAgIC8vICAgJyNENTNCOEQnLFxyXG4gICAgLy8gICAnIzAzQjcxMSdcclxuICAgIC8vIF07XHJcbiAgICBjb25zdCBzZXJpc2VEYXRhID0gW107XHJcbiAgICB5YXhpcy5mb3JFYWNoKCh5LCBpbmRleCkgPT4ge1xyXG4gICAgICBsZWdlbmRzLnB1c2goeS5uYW1lKTtcclxuICAgICAgbGVnZW5kcy5zb3J0KCk7XHJcbiAgICAgIHlBeGlzT3B0aW9ucy5wdXNoKHRoaXMuZ2VuZXJhdGVZQXhpc09wdGlvbihpbmRleCwgeSkpO1xyXG4gICAgICBzZXJpc2VEYXRhLnB1c2goXHJcbiAgICAgICAgdGhpcy5nZW5lcmF0ZVNlcmlzZURhdGEoaW5kZXgsIHksIGRhdGEpXHJcbiAgICAgICk7XHJcbiAgICB9KTtcclxuICAgIHRoaXMuY2hhcnRPcHRpb24gPSB0aGlzLmdldE11bHRpQ2hhcnRPcHRpb25zKFxyXG4gICAgICBsZWdlbmRzLFxyXG4gICAgICB4YXhpcyxcclxuICAgICAgeUF4aXNPcHRpb25zLFxyXG4gICAgICBzZXJpc2VEYXRhLFxyXG4gICAgKTtcclxuICB9XHJcblxyXG4gIGdlbmVyYXRlWUF4aXNPcHRpb24oaW5kZXgsIHkpOiBhbnkge1xyXG4gICAgLy8gY29uc3QgeUF4aXNQb3NpdGlvbiA9IGluZGV4ICUgMiAhPT0gMCA/ICdyaWdodCcgOiAnbGVmdCc7XHJcbiAgICAvLyBjb25zdCB5QXhpc0NvbG9yID0gY29sb3JzW2luZGV4XTtcclxuICAgLy8gY29uc3QgbmFtZVRleHRQYWRkaW5nID0geUF4aXNQb3NpdGlvbiA9PT0gJ2xlZnQnID8gWzAsIDIsIDAsIDBdIDogWzAsIDAsIDAsIDJdOyAvLyBbdG9wLCByaWdodCwgYm90dG9tLCBsZWZ0XVxyXG4gICAgY29uc3QgeU9mZnNldCA9IE1hdGguZmxvb3IoaW5kZXggLyAyKSAqIDQwO1xyXG4gICAgY29uc3QgeWF4aXNvcHRpb24gPSB7XHJcbiAgICAgIHR5cGU6ICd2YWx1ZScsXHJcbiAgICAgIG5hbWU6IHkubmFtZSxcclxuICAgICAgbmFtZUxvY2F0aW9uOiAnY2VudGVyJyxcclxuICAgICAgbmFtZUdhcDogNjUsXHJcbiAgICAgIG5hbWVUZXh0U3R5bGU6IHtcclxuICAgICAgICBhbGlnbjogJ2VuZCcsXHJcbiAgICAgICAgLy8gdmVydGljYWxBbGlnbjogJ2JvdHRvbScsXHJcbiAgICAgICAgZm9udFNpemU6IDEyLFxyXG4gICAgICAgIGZvbnRGYW1pbHk6IFwiU2Vnb2UgVUlcIixcclxuICAgICAgICBjb2xvcjogJyM4MDgwODAnLFxyXG4gICAgICAgIGZvbnRXZWlnaHQ6ICdub3JtYWwnLFxyXG4gICAgICB9LFxyXG4gICAgICBtaW46IHkubWluLFxyXG4gICAgICBtYXg6IHkubWF4LFxyXG4gICAgICBwYWRkaW5nOiBbMywgMCwgMCwgMF0sXHJcbiAgICAgLy8gcG9zaXRpb246IHlBeGlzUG9zaXRpb24sXHJcbiAgICAgIGF4aXNMaW5lOiB7XHJcbiAgICAgICAgb25aZXJvOiB0cnVlLFxyXG4gICAgICAgIGxpbmVTdHlsZToge1xyXG4gICAgICAgICAgLy8gY29sb3I6IHlBeGlzQ29sb3IsXHJcbiAgICAgICAgICB3aWR0aDogMlxyXG4gICAgICAgIH1cclxuICAgICAgfSxcclxuICAgICAgb2Zmc2V0OiB5T2Zmc2V0LFxyXG4gICAgICBheGlzTGFiZWw6IHtcclxuICAgICAgICBzaG93OiB0cnVlLFxyXG4gICAgICAgIGZvcm1hdHRlcjogJ3t2YWx1ZX0nXHJcbiAgICAgIH0sXHJcbiAgICB9O1xyXG4gICAgcmV0dXJuIHlheGlzb3B0aW9uO1xyXG4gIH1cclxuXHJcbiAgZ2VuZXJhdGVTZXJpc2VEYXRhKGluZGV4LCB5LCBkYXRhKTogYW55IHtcclxuICAgIGNvbnN0IHNlcmlzZU9iaiA9IHtcclxuICAgICAgbmFtZTogeS5uYW1lLFxyXG4gICAgICB0eXBlOiAnbGluZScsXHJcbiAgICAgIHlBeGlzSW5kZXg6IGluZGV4LFxyXG4gICAgICBsaW5lU3R5bGU6IHtcclxuICAgICAgICAvL2NvbG9yOiBjb2xvcnNbaW5kZXhdLFxyXG4gICAgICAgIHdpZHRoOiAyXHJcbiAgICAgIH0sXHJcbiAgICAgIG1hcmtMaW5lOiB7XHJcbiAgICAgICAgc3ltYm9sOiAnbm9uZSdcclxuICAgICAgfSxcclxuICAgICAgZGF0YTogZGF0YVtpbmRleF1cclxuICAgIH07XHJcbiAgICByZXR1cm4gc2VyaXNlT2JqO1xyXG4gIH1cclxuXHJcbiAgZ2V0TXVsdGlDaGFydE9wdGlvbnMobGVnZW5kcywgeGF4aXMsIHlheGlzLCBzZXJpc2VEYXRhKTogYW55IHtcclxuICAgIHJldHVybiB7XHJcbiAgICAgIC8vY29sb3JzOiBjb2xvcnMsXHJcbiAgICAgIHRvb2x0aXA6IHtcclxuICAgICAgICB0cmlnZ2VyOiAnYXhpcycsXHJcbiAgICAgICAgYmFja2dyb3VuZENvbG9yOiAnI0ZGRkZGRicsXHJcbiAgICAgICAgdGV4dFN0eWxlOiB7XHJcbiAgICAgICAgICBjb2xvcjogJyM4MDgwODAnLFxyXG4gICAgICAgICAgZm9udFNpemU6ICcxMCcsXHJcbiAgICAgICAgICBmb250V2VpZ2h0OiAnYm9sZCcsXHJcbiAgICAgICAgICBhbGlnbjogJ2xlZnQnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgYXhpc1BvaW50ZXI6IHtcclxuICAgICAgICAgIHR5cGU6ICdjcm9zcycsXHJcbiAgICAgICAgICBsYWJsZToge1xyXG4gICAgICAgICAgICBwcmVjaXNpb246IDEsXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgIH1cclxuICAgICAgfSxcclxuICAgICAgdG9vbGJveDoge1xyXG4gICAgICAgIGZlYXR1cmU6IHtcclxuICAgICAgICAgIGRhdGFab29tOiB7XHJcbiAgICAgICAgICAgIHlBeGlzSW5kZXg6ICdub25lJyxcclxuICAgICAgICAgICAgdGl0bGUgOiB7XHJcbiAgICAgICAgICAgICAgem9vbSA6ICdab29tJyxcclxuICAgICAgICAgICAgICBiYWNrIDogJ1pvb20gUmVzZXQnXHJcbiAgICAgICAgICAgIH1cclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgICBkYXRhVmlldzoge1xyXG4gICAgICAgICAgICByZWFkT25seTogdHJ1ZSxcclxuICAgICAgICAgICAgdGl0bGU6ICdEYXRhIFZpZXcnLFxyXG4gICAgICAgICAgICBsYW5nIDogWydEYXRhIFZpZXcnLCAnQ2xvc2UnXVxyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIG1hZ2ljVHlwZTogeyBzaG93OiBmYWxzZSwgdHlwZTogWydsaW5lJ10gfSxcclxuICAgICAgICAgIHJlc3RvcmU6IHtcclxuICAgICAgICAgICAgdGl0bGU6ICdSZXN0b3JlJ1xyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHNhdmVBc0ltYWdlOiB7XHJcbiAgICAgICAgICAgIHRpdGxlOiAnU2F2ZSBJbWFnZScsXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgIH1cclxuICAgICAgfSxcclxuXHJcbiAgICAgIGxlZ2VuZDpcclxuICAgICAge1xyXG4gICAgICAgIGxlZnQ6ICdyaWdodCcsXHJcbiAgICAgICAgdGV4dFN0eWxlOiB7XHJcbiAgICAgICAgICBjb2xvcjogJyM4MDgwODAnLFxyXG4gICAgICAgICAgZm9udFNpemU6ICcxMCcsXHJcbiAgICAgICAgICBmb250V2VpZ2h0OiAnYm9sZCcsXHJcbiAgICAgICAgfSxcclxuICAgICAgICBwYWRkaW5nOiBbMTAsIDE0MCwgMCwgMF0sXHJcbiAgICAgICAgZGF0YTogbGVnZW5kcy5zb3J0KCksXHJcbiAgICAgICAgaWNvbjogJ2NpcmNsZScsXHJcbiAgICAgIH0sXHJcbiAgICAgIHhBeGlzOiBbXHJcbiAgICAgICAge1xyXG4gICAgICAgICAgdHlwZTogJ2NhdGVnb3J5JyxcclxuICAgICAgICAgIGRhdGE6IHhheGlzXHJcbiAgICAgICAgfVxyXG4gICAgICBdLFxyXG4gICAgICB5QXhpczogeWF4aXMsXHJcbiAgICAgIHNlcmllczogc2VyaXNlRGF0YVxyXG4gICAgfTtcclxuICB9XHJcblxyXG4gIGZvcm1hdFRpbWUodmFsdWUpOiBhbnkge1xyXG4gICAgY29uc3QgZGF0ZVBpcGUgPSBuZXcgRGF0ZVBpcGUoJ2VuLVVTJyk7XHJcbiAgICBjb25zdCB0ZW1wRGF0ZSA9XHJcbiAgICAgIGRhdGVQaXBlLnRyYW5zZm9ybShuZXcgRGF0ZSh2YWx1ZSksICdkZC9NTS95eXl5JykgK1xyXG4gICAgICAnXFxuJyArXHJcbiAgICAgIGRhdGVQaXBlLnRyYW5zZm9ybShuZXcgRGF0ZSh2YWx1ZSksICdzaG9ydFRpbWUnKTtcclxuICAgIHJldHVybiB0ZW1wRGF0ZTtcclxuICB9XHJcbn0iLCI8ZGl2IFtuZ0NsYXNzXT1cImlzS3BpT3ZlclZpZXcgPyAna3BpT3ZlcnZpZXcnIDogJ2NvbW1vbkNoYXJ0J1wiXHJcbiAgZWNoYXJ0c1xyXG4gIFtvcHRpb25zXT1cImNoYXJ0T3B0aW9uXCJcclxuPjwvZGl2PlxyXG4iXX0=