import { DatePipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import * as i0 from "@angular/core";
import * as i1 from "ngx-echarts";
import * as i2 from "@angular/common";
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
        var datePipe = new DatePipe('en-US');
        var tempDate = datePipe.transform(new Date(value), 'dd/MM/yyyy') +
            '\n' +
            datePipe.transform(new Date(value), 'shortTime');
        return tempDate;
    };
    DhrChartComponent.ɵfac = function DhrChartComponent_Factory(t) { return new (t || DhrChartComponent)(); };
    DhrChartComponent.ɵcmp = i0.ɵɵdefineComponent({ type: DhrChartComponent, selectors: [["dhr-chart"]], inputs: { chartData: "chartData", xAxisLabel: "xAxisLabel", yAxislabel: "yAxislabel", chartLabel: "chartLabel", chartTitle: "chartTitle", magicType: "magicType", saveAsImage: "saveAsImage", isMultiSeries: "isMultiSeries", showyAxisLabel: "showyAxisLabel", showToolbox: "showToolbox", showToolTip: "showToolTip", showxAxisLabel: "showxAxisLabel", isKpiOverView: "isKpiOverView", color: "color" }, features: [i0.ɵɵNgOnChangesFeature], decls: 1, vars: 2, consts: [["echarts", "", 3, "ngClass", "options"]], template: function DhrChartComponent_Template(rf, ctx) { if (rf & 1) {
            i0.ɵɵelement(0, "div", 0);
        } if (rf & 2) {
            i0.ɵɵproperty("ngClass", ctx.isKpiOverView ? "kpiOverview" : "commonChart")("options", ctx.chartOption);
        } }, directives: [i1.NgxEchartsDirective, i2.NgClass], styles: [".kpiOverview[_ngcontent-%COMP%]{height:70px!important;width:315px!important}.commonChart[_ngcontent-%COMP%]{height:auto;width:auto}"] });
    return DhrChartComponent;
}());
export { DhrChartComponent };
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
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZGhyLWNoYXJ0LmNvbXBvbmVudC5qcyIsInNvdXJjZVJvb3QiOiJuZzovL2Roci1jaGFydC8iLCJzb3VyY2VzIjpbImxpYi9kaHItY2hhcnQuY29tcG9uZW50LnRzIiwibGliL2Roci1jaGFydC5jb21wb25lbnQuaHRtbCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQSxPQUFPLEVBQUUsUUFBUSxFQUFFLE1BQU0saUJBQWlCLENBQUM7QUFDM0MsT0FBTyxFQUFFLFNBQVMsRUFBVSxLQUFLLEVBQWlCLE1BQU0sZUFBZSxDQUFDOzs7O0FBSXhFO0lBeUJFO0lBQWdCLENBQUM7SUFFakIsb0NBQVEsR0FBUjtRQUNFLElBQUksQ0FBQyxTQUFTLENBQUMsSUFBSSxDQUFDLFNBQVMsRUFBRSxJQUFJLENBQUMsVUFBVSxFQUFFLElBQUksQ0FBQyxVQUFVLEVBQUUsSUFBSSxDQUFDLFVBQVUsRUFBRSxJQUFJLENBQUMsVUFBVSxFQUM5RixJQUFJLENBQUMsY0FBYyxFQUFFLElBQUksQ0FBQyxXQUFXLEVBQUUsSUFBSSxDQUFDLFdBQVcsRUFBRSxJQUFJLENBQUMsY0FBYyxFQUFFLElBQUksQ0FBQyxhQUFhLEVBQUUsSUFBSSxDQUFDLEtBQUssQ0FBQyxDQUFDO0lBQ25ILENBQUM7SUFDRCx1Q0FBVyxHQUFYLFVBQVksT0FBc0I7UUFDaEMsMEdBQTBHO1FBQzFHLDhDQUE4QztRQUM5QyxJQUFJLENBQUMsU0FBUyxDQUFDLElBQUksQ0FBQyxTQUFTLEVBQUUsSUFBSSxDQUFDLFVBQVUsRUFBRSxJQUFJLENBQUMsVUFBVSxFQUFFLElBQUksQ0FBQyxVQUFVLEVBQzlFLElBQUksQ0FBQyxVQUFVLEVBQUUsSUFBSSxDQUFDLGNBQWMsRUFBRSxJQUFJLENBQUMsV0FBVyxFQUFFLElBQUksQ0FBQyxXQUFXLEVBQUUsSUFBSSxDQUFDLGNBQWMsRUFBRSxJQUFJLENBQUMsYUFBYSxFQUFFLElBQUksQ0FBQyxLQUFLLENBQUMsQ0FBQztJQUNuSSxDQUFDO0lBQ0QsMkNBQWUsR0FB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Szs7a0JBQ0wsS0FBSyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IERhdGVQaXBlIH0gZnJvbSAnQGFuZ3VsYXIvY29tbW9uJztcclxuaW1wb3J0IHsgQ29tcG9uZW50LCBPbkluaXQsIElucHV0LCBTaW1wbGVDaGFuZ2VzIH0gZnJvbSAnQGFuZ3VsYXIvY29yZSc7XHJcbmltcG9ydCB7IEVDaGFydE9wdGlvbiB9IGZyb20gJ2VjaGFydHMnO1xyXG5pbXBvcnQgKiBhcyBlY2hhcnRzIGZyb20gJ2VjaGFydHMnO1xyXG5cclxuQENvbXBvbmVudCh7XHJcbiAgc2VsZWN0b3I6ICdkaHItY2hhcnQnLFxyXG4gIHRlbXBsYXRlVXJsOiAnLi9kaHItY2hhcnQuY29tcG9uZW50Lmh0bWwnLFxyXG4gIHN0eWxlVXJsczogWycuL2Roci1jaGFydC5jb21wb25lbnQuY3NzJ11cclxufSlcclxuZXhwb3J0IGNsYXNzIERockNoYXJ0Q29tcG9uZW50IGltcGxlbWVudHMgT25Jbml0IHtcclxuXHJcbiAgQElucHV0KCkgY2hhcnREYXRhOiBhbnk7XHJcbiAgQElucHV0KCkgeEF4aXNMYWJlbDogYW55O1xyXG4gIEBJbnB1dCgpIHlBeGlzbGFiZWw6IGFueTtcclxuICBASW5wdXQoKSBjaGFydExhYmVsOiBhbnk7XHJcbiAgQElucHV0KCkgY2hhcnRUaXRsZTogYW55O1xyXG4gIEBJbnB1dCgpIG1hZ2ljVHlwZTogYm9vbGVhbjtcclxuICBASW5wdXQoKSBzYXZlQXNJbWFnZTogYm9vbGVhbjtcclxuICBASW5wdXQoKSBpc011bHRpU2VyaWVzOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNob3d5QXhpc0xhYmVsOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNob3dUb29sYm94OiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNob3dUb29sVGlwOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIHNob3d4QXhpc0xhYmVsOiBib29sZWFuO1xyXG4gIEBJbnB1dCgpIGlzS3BpT3ZlclZpZXc6IGJvb2xlYW47XHJcbiAgQElucHV0KCkgY29sb3I7XHJcblxyXG4gIGNoYXJ0T3B0aW9uOiBFQ2hhcnRPcHRpb247XHJcbiAgb3B0aW9uczogRUNoYXJ0T3B0aW9uO1xyXG5cclxuICBjb25zdHJ1Y3RvcigpIHsgfVxyXG5cclxuICBuZ09uSW5pdCgpOiB2b2lkIHtcclxuICAgIHRoaXMubG9hZENoYXJ0KHRoaXMuY2hhcnREYXRhLCB0aGlzLnhBeGlzTGFiZWwsIHRoaXMueUF4aXNsYWJlbCwgdGhpcy5jaGFydExhYmVsLCB0aGlzLmNoYXJ0VGl0bGUsXHJcbiAgICAgICB0aGlzLnNob3d5QXhpc0xhYmVsLCB0aGlzLnNob3dUb29sYm94LCB0aGlzLnNob3dUb29sVGlwLCB0aGlzLnNob3d4QXhpc0xhYmVsLCB0aGlzLmlzS3BpT3ZlclZpZXcsIHRoaXMuY29sb3IpO1xyXG4gIH1cclxuICBuZ09uQ2hhbmdlcyhjaGFuZ2VzOiBTaW1wbGVDaGFuZ2VzKTogdm9pZCB7XHJcbiAgICAvLyBDYWxsZWQgYmVmb3JlIGFueSBvdGhlciBsaWZlY3ljbGUgaG9vay4gVXNlIGl0IHRvIGluamVjdCBkZXBlbmRlbmNpZXMsIGJ1dCBhdm9pZCBhbnkgc2VyaW91cyB3b3JrIGhlcmUuXHJcbiAgICAvLyBBZGQgJyR7aW1wbGVtZW50cyBPbkNoYW5nZXN9JyB0byB0aGUgY2xhc3MuXHJcbiAgICB0aGlzLmxvYWRDaGFydCh0aGlzLmNoYXJ0RGF0YSwgdGhpcy54QXhpc0xhYmVsLCB0aGlzLnlBeGlzbGFiZWwsIHRoaXMuY2hhcnRMYWJlbCwgXHJcbiAgICAgIHRoaXMuY2hhcnRUaXRsZSwgdGhpcy5zaG93eUF4aXNMYWJlbCwgdGhpcy5zaG93VG9vbGJveCwgdGhpcy5zaG93VG9vbFRpcCwgdGhpcy5zaG93eEF4aXNMYWJlbCwgdGhpcy5pc0twaU92ZXJWaWV3LCB0aGlzLmNvbG9yKTtcclxuICB9XHJcbiAgbmdBZnRlclZpZXdJbml0KCk6IHZvaWQge1xyXG4gICAgLy8gQ2FsbGVkIGFmdGVyIG5nQWZ0ZXJDb250ZW50SW5pdCB3aGVuIHRoZSBjb21wb25lbnQncyB2aWV3IGhhcyBiZWVuIGluaXRpYWxpemVkLiBBcHBsaWVzIHRvIGNvbXBvbmVudHMgb25seS5cclxuICAgIC8vIEFkZCAnaW1wbGVtZW50cyBBZnRlclZpZXdJbml0JyB0byB0aGUgY2xhc3MuXHJcbiAgICB0aGlzLmxvYWRDaGFydCh0aGlzLmNoYXJ0RGF0YSwgdGhpcy54QXhpc0xhYmVsLCB0aGlzLnlBeGlzbGFiZWwsIFxyXG4gICAgdGhpcy5jaGFydExhYmVsLCB0aGlzLmNoYXJ0VGl0bGUsIHRoaXMuc2hvd3lBeGlzTGFiZWwsIHRoaXMuc2hvd1Rvb2xib3gsIHRoaXMuc2hvd1Rvb2xUaXAsXHJcbiAgICAgdGhpcy5zaG93eEF4aXNMYWJlbCwgdGhpcy5pc0twaU92ZXJWaWV3LCB0aGlzLmNvbG9yKTtcclxuICB9XHJcbiAgbG9hZENoYXJ0KGQsIHhsLCB5bCwgY2wsIGN0LCB5QXhpc0xhYmVsRmxhZywgdG9vbGJveEZsYWcsIHRvb2x0aXBGbGFnLCB4QXhpc0xhYmVsRmxhZywga3BpT3ZlcnZpZXdmbGFnLCBjb2xvclZhbHVlcykge1xyXG4gICAgaWYgKHRoaXMuaXNNdWx0aVNlcmllcykge1xyXG4gICAgICB0aGlzLmdldE11bHRpU2VyaWVzQ2hhcnRPcHRpb25zKHRoaXMueEF4aXNMYWJlbCwgdGhpcy55QXhpc2xhYmVsLCB0aGlzLmNoYXJ0RGF0YSk7XHJcbiAgICB9IGVsc2Uge1xyXG4gICAgICB0aGlzLmNoYXJ0T3B0aW9uID0gdGhpcy5nZXRDaGFydE9wdGlvbnMoZCwgeGwsIHlsLCBjbCwgY3QsXHJcbiAgICAgIHlBeGlzTGFiZWxGbGFnLCB0b29sYm94RmxhZywgdG9vbHRpcEZsYWcsIHhBeGlzTGFiZWxGbGFnLCBrcGlPdmVydmlld2ZsYWcsIGNvbG9yVmFsdWVzKTtcclxuICAgIH1cclxuICB9XHJcblxyXG4gIGdldENoYXJ0T3B0aW9ucyhkLCB4bCwgeWwsIGNsLCBjdCwgeUF4aXNMYWJlbEZsYWcsIHRvb2xib3hGbGFnLCB0b29sdGlwRmxhZywgeEF4aXNMYWJlbEZsYWcsIGtwaU92ZXJ2aWV3ZmxhZywgY29sb3JWYWx1ZXMpOiBhbnkge1xyXG4gICAgbGV0IHNob3dNYXJrTGluZSA9IHt9O1xyXG4gICAgbGV0IGNoYXJ0Q29sb3JQcm9wZXJ0eSA9ICcjMDA5RERDJztcclxuICAgIGlmICgha3BpT3ZlcnZpZXdmbGFnKXtcclxuICAgICAgc2hvd01hcmtMaW5lID0ge1xyXG4gICAgICAgIGRhdGE6IFtcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgdHlwZTogJ2F2ZXJhZ2UnLFxyXG4gICAgICAgICAgICBsYWJlbDoge1xyXG4gICAgICAgICAgICAgIHBvc2l0aW9uOiAnRW5kJyxcclxuICAgICAgICAgICAgICBkaXN0YW5jZTogNSxcclxuICAgICAgICAgICAgICBmb3JtYXR0ZXI6IChwYXJhbXMpID0+IHtcclxuICAgICAgICAgICAgICAgIHJldHVybiBgJHtwYXJhbXMudmFsdWV9YDtcclxuICAgICAgICAgICAgICB9LFxyXG4gICAgICAgICAgICB9LFxyXG4gICAgICAgICAgICBsaW5lU3R5bGU6IHsgY29sb3I6ICcjNzA3MDcwJywgd2lkdGg6IDIgfSxcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgXSxcclxuICAgICAgICBzeW1ib2w6ICdub25lJyxcclxuICAgICAgfVxyXG4gICAgfVxyXG4gICAgaWYgKGtwaU92ZXJ2aWV3ZmxhZykge1xyXG4gICAgICBjaGFydENvbG9yUHJvcGVydHkgPSBjb2xvclZhbHVlcztcclxuICAgIH1cclxuICAgIHJldHVybiB7XHJcbiAgICAgIHRpdGxlOiB7XHJcbiAgICAgICAgdGV4dDogY3QsXHJcbiAgICAgICAgbGVmdDogJ2xlZnQnLFxyXG4gICAgICAgIHRvcDogJ2xlZnQnLFxyXG4gICAgICAgIHRyaWdnZXJFdmVudDogdHJ1ZSxcclxuICAgICAgICB0ZXh0U3R5bGU6IHtcclxuICAgICAgICAgIGZvbnRTaXplOiAxMyxcclxuICAgICAgICB9LFxyXG4gICAgICAgIHBhZGRpbmc6IFtcclxuICAgICAgICAgIDE1LCAvLyB1cFxyXG4gICAgICAgICAgMTAsIC8vIHJpZ2h0XHJcbiAgICAgICAgICA1LCAvLyBkb3duXHJcbiAgICAgICAgICAxMCwgLy8gbGVmdFxyXG4gICAgICAgIF0sXHJcbiAgICAgICAgc2hvdzogZmFsc2UsXHJcbiAgICAgIH0sXHJcbiAgICAgIHRvb2xib3g6IHtcclxuICAgICAgICBzaG93OiB0b29sYm94RmxhZyxcclxuICAgICAgICBvcmllbnQ6ICd2ZXJ0aWNhbCcsXHJcbiAgICAgICAgc2hvd1RpdGxlOiBmYWxzZSxcclxuICAgICAgICBmZWF0dXJlOiB7XHJcbiAgICAgICAgICBzYXZlQXNJbWFnZToge1xyXG4gICAgICAgICAgICB0aXRsZTogJ1NhdmUgSW1hZ2UnLFxyXG4gICAgICAgICAgfSxcclxuICAgICAgICB9LFxyXG4gICAgICAgIHRvb2x0aXA6IHtcclxuICAgICAgICAgIHNob3c6IHRydWUsXHJcbiAgICAgICAgICBmb3JtYXR0ZXI6IChwYXJhbSkgPT4ge1xyXG4gICAgICAgICAgICByZXR1cm4gJzxkaXY+JyArIHBhcmFtLnRpdGxlICsgJzwvZGl2Pic7XHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAgYmFja2dyb3VuZENvbG9yOiAnIzIyMicsXHJcbiAgICAgICAgICB0ZXh0U3R5bGU6IHtcclxuICAgICAgICAgICAgZm9udFNpemU6IDgsXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAgZXh0cmFDc3NUZXh0OiAnYm94LXNoYWRvdzogMCAwIDFweCByZ2JhKDAsIDAsIDAsIDAuMyk7JywgLy8gdXNlci1kZWZpbmVkIENTUyBzdHlsZXNcclxuICAgICAgICB9LFxyXG4gICAgICB9LFxyXG4gICAgICB0b29sdGlwOiB7XHJcbiAgICAgICAgc2hvdzogdG9vbHRpcEZsYWcsXHJcbiAgICAgICAgdHJpZ2dlcjogJ2F4aXMnLFxyXG4gICAgICAgIGJhY2tncm91bmRDb2xvcjogJyNGRkZGRkYnLFxyXG4gICAgICAgIGF4aXNQb2ludGVyOiB7XHJcbiAgICAgICAgICB0eXBlOiAnbGluZScsXHJcbiAgICAgICAgICBsYWJsZToge1xyXG4gICAgICAgICAgICBwcmVjaXNpb246IDEsXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgZm9ybWF0dGVyOiAocGFyYW1zKSA9PiB7XHJcbiAgICAgICAgICByZXR1cm4gYDxkaXYgc3R5bGU9XCIgcGFkZGluZzogMTBweCAxMHB4IDIwcHggMTBweDtcclxuICAgICAgICAgIGJveC1zaGFkb3c6IDJweCAycHggNHB4IDJweCAjYWFhYWFhO1xyXG4gICAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XCI+XHJcbiAgICAgICAgICA8c3BhbiBzdHlsZT1cImNvbG9yOiAjODA4MDgwO2ZvbnQtc2l6ZTogMTJweDtcIj4ke1xyXG4gICAgICAgICAgICBuZXcgRGF0ZShwYXJhbXNbMF0ubmFtZSkudG9TdHJpbmcoKS5zcGxpdCgnICcsIDQpLmpvaW4oJyAnKSArXHJcbiAgICAgICAgICAgICcgJyArXHJcbiAgICAgICAgICAgICgnMCcgKyBuZXcgRGF0ZShwYXJhbXNbMF0ubmFtZSkuZ2V0SG91cnMoKSkuc2xpY2UoLTIpICtcclxuICAgICAgICAgICAgJzonICtcclxuICAgICAgICAgICAgKCcwJyArIG5ldyBEYXRlKHBhcmFtc1swXS5uYW1lKS5nZXRNaW51dGVzKCkpLnNsaWNlKC0yKVxyXG4gICAgICAgICAgICB9PC9zcGFuPjxicj5cclxuICAgICAgICAgICAgICAgICAgPHNwYW4gc3R5bGU9XCJjb2xvcjogIzMzMzMzMzsgZm9udC1zaXplOiAxMnB4XCI+JHt5bH08L3NwYW4+XHJcbiAgICAgICAgICAgICAgICAgICZuYnNwOyZuYnNwOyZuYnNwO1xyXG4gICAgICAgICAgICAgICAgICA8c3BhbiBzdHlsZT1cImNvbG9yOiAjMzMzMzMzOyBmb250LXNpemU6IDE0cHg7IHRleHQtYWxpZ246cmlnaHQ7IGZvbnQtd2VpZ2h0OjcwMFwiPiR7XHJcbiAgICAgICAgICAgIHBhcmFtc1swXS52YWx1ZVsxXVxyXG4gICAgICAgICAgICB9Jm5ic3A7JHt4bH08L3NwYW4+PC9kaXY+YDtcclxuICAgICAgICB9LFxyXG4gICAgICB9LFxyXG4gICAgICBncmlkOiB7XHJcbiAgICAgICAgY29udGFpbkxhYmVsOiB0cnVlLFxyXG4gICAgICB9LFxyXG4gICAgICB4QXhpczoge1xyXG4gICAgICAgIHR5cGU6ICdjYXRlZ29yeScsXHJcbiAgICAgICAgc2hvdzogeEF4aXNMYWJlbEZsYWcsXHJcbiAgICAgICAgYXhpc0xhYmVsOiB7XHJcbiAgICAgICAgICBjb2xvcjogJyM4MDgwODAnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgYXhpc1RpY2s6IHtcclxuICAgICAgICAgIGFsaWduV2l0aExhYmVsOiB0cnVlLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgc3BsaXROdW1iZXI6IDEsXHJcbiAgICAgIH0sXHJcbiAgICAgIHlBeGlzOiB7XHJcbiAgICAgICAgdHlwZTogJ3ZhbHVlJyxcclxuICAgICAgICBuYW1lOiB5bCxcclxuICAgICAgICBuYW1lTG9jYXRpb246ICdtaWRkbGUnLFxyXG4gICAgICAgIHNob3c6IHlBeGlzTGFiZWxGbGFnLFxyXG4gICAgICAgIGF4aXNMYWJlbDoge1xyXG4gICAgICAgICAgY29sb3I6ICcjODA4MDgwJyxcclxuICAgICAgICB9LFxyXG4gICAgICAgIG5hbWVUZXh0U3R5bGU6IHtcclxuICAgICAgICAgIGFsaWduOiAnY2VudGVyJyxcclxuICAgICAgICAgIHZlcnRpY2FsQWxpZ246ICdib3R0b20nLFxyXG4gICAgICAgICAgbGluZUhlaWdodDogMTUwLFxyXG4gICAgICAgICAgZm9udFNpemU6IDEyLFxyXG4gICAgICAgICAgZm9udEZhbWlseTogXCJTZWdvZSBVSVwiLFxyXG4gICAgICAgICAgY29sb3I6ICcjODA4MDgwJyxcclxuICAgICAgICAgIGZvbnRXZWlnaHQ6ICdub3JtYWwnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgIH0sXHJcbiAgICAgIGxlZ2VuZDoge1xyXG4gICAgICAgIGxlZnQ6ICdyaWdodCcsXHJcbiAgICAgICAgdGV4dFN0eWxlOiB7XHJcbiAgICAgICAgICBjb2xvcjogJyM4MDgwODAnLFxyXG4gICAgICAgICAgZm9udFNpemU6ICcxMCcsXHJcbiAgICAgICAgICBmb250V2VpZ2h0OiAnYm9sZCcsXHJcbiAgICAgICAgICBhbGlnbjogJ2xlZnQnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgcGFkZGluZzogW1xyXG4gICAgICAgICAgMTAsIC8vIHVwXHJcbiAgICAgICAgICA3MCwgLy8gcmlnaHRcclxuICAgICAgICAgIDAsIC8vIGRvd25cclxuICAgICAgICAgIDAsIC8vIGxlZnRcclxuICAgICAgICBdLFxyXG4gICAgICB9LFxyXG4gICAgICBzZXJpZXM6IFtcclxuICAgICAgICB7XHJcbiAgICAgICAgICBuYW1lOiBjbCxcclxuICAgICAgICAgIHNtb290aDogZmFsc2UsXHJcbiAgICAgICAgICBzeW1ib2w6ICdjaXJjbGUnLFxyXG4gICAgICAgICAgY29sb3I6IGNoYXJ0Q29sb3JQcm9wZXJ0eSxcclxuICAgICAgICAgIHN5bWJvbFNpemU6IDIsXHJcbiAgICAgICAgICBkYXRhOiBkLFxyXG4gICAgICAgICAgdHlwZTogJ2xpbmUnLFxyXG4gICAgICAgICAgbGluZVN0eWxlOiB7XHJcbiAgICAgICAgICAgIGNvbG9yOiBjb2xvclZhbHVlcyxcclxuICAgICAgICAgICAgd2lkdGg6IDIsXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAgbWFya0xpbmU6IHNob3dNYXJrTGluZSxcclxuICAgICAgICB9LFxyXG4gICAgICBdLFxyXG4gICAgfTtcclxuICB9XHJcblxyXG4gIGdldE11bHRpU2VyaWVzQ2hhcnRPcHRpb25zKHhheGlzLCB5YXhpcywgZGF0YSk6IHZvaWQge1xyXG4gICAgY29uc3QgbGVnZW5kcyA9IFtdO1xyXG4gICAgY29uc3QgeUF4aXNPcHRpb25zID0gW107XHJcbiAgICAvLyBjb25zdCBjb2xvcnMgPSBbXHJcbiAgICAvLyAgICcjNEY5NUQ0JyxcclxuICAgIC8vICAgJyNENTNCOEQnLFxyXG4gICAgLy8gICAnIzAzQjcxMSdcclxuICAgIC8vIF07XHJcbiAgICBjb25zdCBzZXJpc2VEYXRhID0gW107XHJcbiAgICB5YXhpcy5mb3JFYWNoKCh5LCBpbmRleCkgPT4ge1xyXG4gICAgICBsZWdlbmRzLnB1c2goeS5uYW1lKTtcclxuICAgICAgbGVnZW5kcy5zb3J0KCk7XHJcbiAgICAgIHlBeGlzT3B0aW9ucy5wdXNoKHRoaXMuZ2VuZXJhdGVZQXhpc09wdGlvbihpbmRleCwgeSkpO1xyXG4gICAgICBzZXJpc2VEYXRhLnB1c2goXHJcbiAgICAgICAgdGhpcy5nZW5lcmF0ZVNlcmlzZURhdGEoaW5kZXgsIHksIGRhdGEpXHJcbiAgICAgICk7XHJcbiAgICB9KTtcclxuICAgIHRoaXMuY2hhcnRPcHRpb24gPSB0aGlzLmdldE11bHRpQ2hhcnRPcHRpb25zKFxyXG4gICAgICBsZWdlbmRzLFxyXG4gICAgICB4YXhpcyxcclxuICAgICAgeUF4aXNPcHRpb25zLFxyXG4gICAgICBzZXJpc2VEYXRhLFxyXG4gICAgKTtcclxuICB9XHJcblxyXG4gIGdlbmVyYXRlWUF4aXNPcHRpb24oaW5kZXgsIHkpOiBhbnkge1xyXG4gICAgY29uc3QgeUF4aXNQb3NpdGlvbiA9IGluZGV4ICUgMiAhPT0gMCA/ICdyaWdodCcgOiAnbGVmdCc7XHJcbiAgICAvL2NvbnN0IHlBeGlzQ29sb3IgPSBjb2xvcnNbaW5kZXhdO1xyXG4gICAvLyBjb25zdCBuYW1lVGV4dFBhZGRpbmcgPSB5QXhpc1Bvc2l0aW9uID09PSAnbGVmdCcgPyBbMCwgMiwgMCwgMF0gOiBbMCwgMCwgMCwgMl07IC8vIFt0b3AsIHJpZ2h0LCBib3R0b20sIGxlZnRdXHJcbiAgICBjb25zdCB5T2Zmc2V0ID0gTWF0aC5mbG9vcihpbmRleCAvIDIpICogNDA7XHJcbiAgICBjb25zdCB5YXhpc29wdGlvbiA9IHtcclxuICAgICAgdHlwZTogJ3ZhbHVlJyxcclxuICAgICAgLy9uYW1lOiB5Lm5hbWUsXHJcbiAgICAgIG5hbWVMb2NhdGlvbjogJ21pZGRsZScsXHJcbiAgICAgIG5hbWVUZXh0U3R5bGU6IHtcclxuICAgICAgICBhbGlnbjogJ2NlbnRlcicsXHJcbiAgICAgICAgdmVydGljYWxBbGlnbjogJ2JvdHRvbScsXHJcbiAgICAgICAgZm9udFNpemU6IDEyLFxyXG4gICAgICAgIGZvbnRGYW1pbHk6IFwiU2Vnb2UgVUlcIixcclxuICAgICAgICBjb2xvcjogJyM4MDgwODAnLFxyXG4gICAgICAgIGZvbnRXZWlnaHQ6ICdub3JtYWwnLFxyXG4gICAgICB9LFxyXG4gICAgICBtaW46IHkubWluLFxyXG4gICAgICBtYXg6IHkubWF4LFxyXG4gICAgICBuYW1lR2FwOiAyNSxcclxuICAgICAgcGFkZGluZzogWzMsIDAsIDAsIDBdLFxyXG4gICAgIC8vIHBvc2l0aW9uOiB5QXhpc1Bvc2l0aW9uLFxyXG4gICAgICBheGlzTGluZToge1xyXG4gICAgICAgIG9uWmVybzogdHJ1ZSxcclxuICAgICAgICBsaW5lU3R5bGU6IHtcclxuICAgICAgICAgIC8vIGNvbG9yOiB5QXhpc0NvbG9yLFxyXG4gICAgICAgICAgd2lkdGg6IDJcclxuICAgICAgICB9XHJcbiAgICAgIH0sXHJcbiAgICAgIG9mZnNldDogeU9mZnNldCxcclxuICAgICAgYXhpc0xhYmVsOiB7XHJcbiAgICAgICAgc2hvdzogdHJ1ZSxcclxuICAgICAgICBmb3JtYXR0ZXI6ICd7dmFsdWV9J1xyXG4gICAgICB9LFxyXG4gICAgfTtcclxuICAgIHJldHVybiB5YXhpc29wdGlvbjtcclxuICB9XHJcblxyXG4gIGdlbmVyYXRlU2VyaXNlRGF0YShpbmRleCwgeSwgZGF0YSk6IGFueSB7XHJcbiAgICBjb25zdCBzZXJpc2VPYmogPSB7XHJcbiAgICAgIG5hbWU6IHkubmFtZSxcclxuICAgICAgdHlwZTogJ2xpbmUnLFxyXG4gICAgICBsaW5lU3R5bGU6IHtcclxuICAgICAgICAvL2NvbG9yOiBjb2xvcnNbaW5kZXhdLFxyXG4gICAgICAgIHdpZHRoOiAyXHJcbiAgICAgIH0sXHJcbiAgICAgIG1hcmtMaW5lOiB7XHJcbiAgICAgICAgc3ltYm9sOiAnbm9uZSdcclxuICAgICAgfSxcclxuICAgICAgZGF0YTogZGF0YVtpbmRleF1cclxuICAgIH07XHJcbiAgICByZXR1cm4gc2VyaXNlT2JqO1xyXG4gIH1cclxuXHJcbiAgZ2V0TXVsdGlDaGFydE9wdGlvbnMobGVnZW5kcywgeGF4aXMsIHlheGlzLCBzZXJpc2VEYXRhKTogYW55IHtcclxuICAgIHJldHVybiB7XHJcbiAgICAgIC8vY29sb3JzOiBjb2xvcnMsXHJcbiAgICAgIHRvb2x0aXA6IHtcclxuICAgICAgICB0cmlnZ2VyOiAnYXhpcycsXHJcbiAgICAgICAgYmFja2dyb3VuZENvbG9yOiAnI0ZGRkZGRicsXHJcbiAgICAgICAgdGV4dFN0eWxlOiB7XHJcbiAgICAgICAgICBjb2xvcjogJyM4MDgwODAnLFxyXG4gICAgICAgICAgZm9udFNpemU6ICcxMCcsXHJcbiAgICAgICAgICBmb250V2VpZ2h0OiAnYm9sZCcsXHJcbiAgICAgICAgICBhbGlnbjogJ2xlZnQnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgYXhpc1BvaW50ZXI6IHtcclxuICAgICAgICAgIHR5cGU6ICdjcm9zcycsXHJcbiAgICAgICAgICBsYWJsZToge1xyXG4gICAgICAgICAgICBwcmVjaXNpb246IDEsXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgIH1cclxuICAgICAgfSxcclxuICAgICAgdG9vbGJveDoge1xyXG4gICAgICAgIGZlYXR1cmU6IHtcclxuICAgICAgICAgIGRhdGFab29tOiB7XHJcbiAgICAgICAgICAgIHlBeGlzSW5kZXg6ICdub25lJyxcclxuICAgICAgICAgICAgdGl0bGUgOiB7XHJcbiAgICAgICAgICAgICAgem9vbSA6ICdab29tJyxcclxuICAgICAgICAgICAgICBiYWNrIDogJ1pvb20gUmVzZXQnXHJcbiAgICAgICAgICAgIH1cclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgICBkYXRhVmlldzoge1xyXG4gICAgICAgICAgICByZWFkT25seTogdHJ1ZSxcclxuICAgICAgICAgICAgdGl0bGU6ICdEYXRhIFZpZXcnLFxyXG4gICAgICAgICAgICBsYW5nIDogWydEYXRhIFZpZXcnLCAnQ2xvc2UnXVxyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIG1hZ2ljVHlwZTogeyBzaG93OiBmYWxzZSwgdHlwZTogWydsaW5lJ10gfSxcclxuICAgICAgICAgIHJlc3RvcmU6IHtcclxuICAgICAgICAgICAgdGl0bGU6ICdSZXN0b3JlJ1xyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHNhdmVBc0ltYWdlOiB7XHJcbiAgICAgICAgICAgIHRpdGxlOiAnU2F2ZSBJbWFnZScsXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgIH1cclxuICAgICAgfSxcclxuXHJcbiAgICAgIGxlZ2VuZDpcclxuICAgICAge1xyXG4gICAgICAgIGxlZnQ6ICdyaWdodCcsXHJcbiAgICAgICAgdGV4dFN0eWxlOiB7XHJcbiAgICAgICAgICBjb2xvcjogJyM4MDgwODAnLFxyXG4gICAgICAgICAgZm9udFNpemU6ICcxMCcsXHJcbiAgICAgICAgICBmb250V2VpZ2h0OiAnYm9sZCcsXHJcbiAgICAgICAgfSxcclxuICAgICAgICBwYWRkaW5nOiBbMTAsIDE0MCwgMCwgMF0sXHJcbiAgICAgICAgZGF0YTogbGVnZW5kcy5zb3J0KCksXHJcbiAgICAgICAgaWNvbjogJ2NpcmNsZScsXHJcbiAgICAgIH0sXHJcbiAgICAgIHhBeGlzOiBbXHJcbiAgICAgICAge1xyXG4gICAgICAgICAgdHlwZTogJ2NhdGVnb3J5JyxcclxuICAgICAgICAgIGRhdGE6IHhheGlzXHJcbiAgICAgICAgfVxyXG4gICAgICBdLFxyXG4gICAgICB5QXhpczogeWF4aXMsXHJcbiAgICAgIHNlcmllczogc2VyaXNlRGF0YVxyXG4gICAgfTtcclxuICB9XHJcblxyXG4gIGZvcm1hdFRpbWUodmFsdWUpOiBhbnkge1xyXG4gICAgY29uc3QgZGF0ZVBpcGUgPSBuZXcgRGF0ZVBpcGUoJ2VuLVVTJyk7XHJcbiAgICBjb25zdCB0ZW1wRGF0ZSA9XHJcbiAgICAgIGRhdGVQaXBlLnRyYW5zZm9ybShuZXcgRGF0ZSh2YWx1ZSksICdkZC9NTS95eXl5JykgK1xyXG4gICAgICAnXFxuJyArXHJcbiAgICAgIGRhdGVQaXBlLnRyYW5zZm9ybShuZXcgRGF0ZSh2YWx1ZSksICdzaG9ydFRpbWUnKTtcclxuICAgIHJldHVybiB0ZW1wRGF0ZTtcclxuICB9XHJcbn0iLCI8ZGl2IFtuZ0NsYXNzXT1cImlzS3BpT3ZlclZpZXcgPyAna3BpT3ZlcnZpZXcnIDogJ2NvbW1vbkNoYXJ0J1wiXHJcbiAgZWNoYXJ0c1xyXG4gIFtvcHRpb25zXT1cImNoYXJ0T3B0aW9uXCJcclxuPjwvZGl2PlxyXG4iXX0=