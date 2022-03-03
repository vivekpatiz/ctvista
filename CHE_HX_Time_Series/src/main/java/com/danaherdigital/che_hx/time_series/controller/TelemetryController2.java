package com.danaherdigital.che_hx.time_series.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.time_series.ApiSuccess;
import com.danaherdigital.che_hx.time_series.dto.ResDTO;
import com.danaherdigital.che_hx.time_series.service.TelemetryService;
import com.danaherdigital.che_hx.time_series.utils.ApplicationConstants;
import com.danaherdigital.che_hx.time_series.utils.AssetUtils;

@RestController
@RequestMapping("/v2/TimeSeries")
public class TelemetryController2 {

	static final Logger LOGGER = LoggerFactory.getLogger(TelemetryController2.class);

	@Autowired
	TelemetryService telemetryService;

	@GetMapping("/charts")
	public ResponseEntity<Object> getChartsData(@RequestParam Date fromDate, @RequestParam Date toDate,
			@RequestParam Integer fromLoad, @RequestParam Integer toLoad, @RequestParam Integer tenantId,
			@RequestParam String assetId, @RequestParam(required = false) List<String> assetParamName,
			@RequestParam Boolean isKpi) {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getChartsData");

		ResDTO resultObj = telemetryService.getChartsData2(fromDate, toDate, fromLoad, toLoad, tenantId, assetId,
				assetParamName, isKpi);
		return AssetUtils.buildResponseEntity(new ApiSuccess((!Objects.isNull(resultObj))?resultObj:""));
	}

}