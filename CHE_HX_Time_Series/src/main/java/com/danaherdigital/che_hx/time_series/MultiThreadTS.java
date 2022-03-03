package com.danaherdigital.che_hx.time_series;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.danaherdigital.che_hx.time_series.dto.AssetParamDTO2;
import com.danaherdigital.che_hx.time_series.dto.ChartData;
import com.danaherdigital.che_hx.time_series.service.TelemetryService;

public class MultiThreadTS implements Callable<List<ChartData>> {
	private Date fromDate;
	private Date toDate;
	private Integer fromLoad;
	private Integer toLoad;
	private Integer tenantId;
	private String assetId;
	private String assetParamId;
	private Map<Long, Double> loadMap;
	private List<AssetParamDTO2> apList;

	private TelemetryService telemetryService;

	public MultiThreadTS(Date fromDate, Date toDate, Integer fromLoad, Integer toLoad, Integer tenantId, String assetId,
			String assetParamId, Map<Long, Double> loadMap, List<AssetParamDTO2> apList,
			TelemetryService telemetryService) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromLoad = fromLoad;
		this.toLoad = toLoad;
		this.apList = apList;
		this.tenantId = tenantId;
		this.assetId = assetId;
		this.assetParamId = assetParamId;
		this.loadMap = loadMap;
		this.telemetryService = telemetryService;

	}

	public List<ChartData> call() throws Exception {

		return telemetryService.getOutput(fromDate, toDate, fromLoad, toLoad, tenantId, assetId, assetParamId, loadMap,
				apList);
	}

}