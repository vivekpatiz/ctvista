package com.danaherdigital.che_hx.time_series.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.danaherdigital.che_hx.time_series.dto.AssetDTOLoadInt;
import com.danaherdigital.che_hx.time_series.dto.AssetParamDTO2;
import com.danaherdigital.che_hx.time_series.dto.ChartData;
import com.danaherdigital.che_hx.time_series.dto.PersistResDTO;
import com.danaherdigital.che_hx.time_series.dto.ResDTO;
import com.danaherdigital.che_hx.time_series.dto.YAxisReqDTO;
import com.danaherdigital.che_hx.time_series.dto.YAxisResponseDTO;
import com.danaherdigital.che_hx.time_series.model.DerivedData;


public interface TelemetryService {

	public JSONObject getChartsData(Date fromDate, Date toDate, Integer fromLoad, Integer toLoad, Integer tenantId,
			String assetId, List<String> assetName, Boolean isKpi);

	public ResDTO getChartsData2(Date fromDate, Date toDate, Integer fromLoad, Integer toLoad, Integer tenantId,
			String assetId, List<String> assetName, Boolean isKpi);

	public PersistResDTO persistCalculatedData(DerivedData calcReqData);

	public AssetDTOLoadInt getMinMaxLoad(String assetId, String assetParamName);
	public List<ChartData> getOutput(Date fromDate, Date toDate, Integer fromLoad, Integer toLoad, Integer tenantId,
			String assetId,String assetParamId,Map<Long,Double> loadMap, List<AssetParamDTO2> apList);

	public YAxisResponseDTO getYaxisChartsData(YAxisReqDTO req);

}
