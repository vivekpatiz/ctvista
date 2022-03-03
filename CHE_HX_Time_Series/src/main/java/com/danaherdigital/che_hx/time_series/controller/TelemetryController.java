package com.danaherdigital.che_hx.time_series.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.time_series.ApiSuccess;
import com.danaherdigital.che_hx.time_series.dto.AssetDTOLoadInt;
import com.danaherdigital.che_hx.time_series.dto.FavChartLoadDataDto;
import com.danaherdigital.che_hx.time_series.dto.FavChartLoadReqListDTO;
import com.danaherdigital.che_hx.time_series.dto.FavoriteChartsReqDto;
import com.danaherdigital.che_hx.time_series.dto.PersistResDTO;
import com.danaherdigital.che_hx.time_series.dto.YAxisReqDTO;
import com.danaherdigital.che_hx.time_series.dto.YAxisResponseDTO;
import com.danaherdigital.che_hx.time_series.model.DerivedData;
import com.danaherdigital.che_hx.time_series.service.FavoriteChartService;
import com.danaherdigital.che_hx.time_series.service.TelemetryService;
import com.danaherdigital.che_hx.time_series.utils.ApplicationConstants;
import com.danaherdigital.che_hx.time_series.utils.AssetUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/v1/TimeSeries")

public class TelemetryController {

	static final Logger LOGGER = LoggerFactory.getLogger(TelemetryController.class);

	@Autowired
	TelemetryService telemetryService;
	@Autowired
	FavoriteChartService favoriteChartService;

	@GetMapping("/charts")
	public ResponseEntity<Object> getChartsData(@RequestParam Date fromDate, @RequestParam Date toDate,
			@RequestParam Integer fromLoad, @RequestParam Integer toLoad, @RequestParam Integer tenantId,
			@RequestParam String assetId, @RequestParam(required = false) List<String> assetParamName,
			@RequestParam Boolean isKpi) {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getChartsData");

		JSONObject resultObj = telemetryService.getChartsData(fromDate, toDate, fromLoad, toLoad, tenantId, assetId,
				assetParamName, isKpi);
		if (!Objects.isNull(resultObj)) {
			return AssetUtils.buildResponseEntity(new ApiSuccess(resultObj.toMap()));
		}
		return AssetUtils.buildResponseEntity(new ApiSuccess(""), HttpStatus.NOT_FOUND);
	}

	@PostMapping("/charts/yaxis")
	public ResponseEntity<Object> getYaxisChartsData(@Valid @RequestBody YAxisReqDTO req) {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getChartsData");

		YAxisResponseDTO resultObj = telemetryService.getYaxisChartsData(req);
		if (!Objects.isNull(resultObj) && !CollectionUtils.isEmpty(resultObj.getChartData())) {
			return AssetUtils.buildResponseEntity(new ApiSuccess(resultObj));

		}
		return AssetUtils.buildResponseEntity(new ApiSuccess(Collections.emptyList()), HttpStatus.NOT_FOUND);
	}

	@GetMapping("/load")
	public ResponseEntity<AssetDTOLoadInt> getMinMaxLoad(@RequestParam String assetId,
			@RequestParam String assetParamName) {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getMinMaxLoad");

		AssetDTOLoadInt resultObj = telemetryService.getMinMaxLoad(assetId, assetParamName);
		return new ResponseEntity<>(resultObj, HttpStatus.OK);
	}

	@PostMapping("/persist")
	public ResponseEntity<PersistResDTO> persistData(@RequestBody DerivedData calcReqData) {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "persistData");

		long start = System.currentTimeMillis();
		PersistResDTO b=telemetryService.persistCalculatedData(calcReqData);
		long end = System.currentTimeMillis();

		LOGGER.info("persist total time:{}", end - start);

		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	@PostMapping("/favchart/adding")
	public ResponseEntity<Object> addFavouriteChartsData(@Valid @RequestBody FavChartLoadReqListDTO reqlist) {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "addFavouriteChartsData");
		String response="";
		if (!Objects.isNull(reqlist) && !CollectionUtils.isEmpty(reqlist.getChartData())) {
			for (FavoriteChartsReqDto req:reqlist.getChartData())
				response= favoriteChartService.addFavouriteChartsData(req);
		}
		return new ResponseEntity<>(new ApiSuccess(response), HttpStatus.OK);
	}
	@GetMapping("/allfavcharts/{tenantId}/{assetId}")
	public ResponseEntity<Object> getAllFavouriteCharts(@PathVariable(name = "tenantId", required = true) @NotBlank Integer tenantId, @PathVariable(name = "assetId",required = true) @NotBlank String assetId)  {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAllFavouriteCharts");
		List<FavChartLoadDataDto> resultObj = favoriteChartService.getAllFavouriteCharts(tenantId,assetId);
		String jsonData="";
		if (!Objects.isNull(resultObj) && !CollectionUtils.isEmpty(resultObj)) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				jsonData = mapper.writeValueAsString(resultObj);
				jsonData=jsonData.replaceAll("\\\\", ""); 
				jsonData=jsonData.replaceAll("\"\\[", "[");
				jsonData=jsonData.replaceAll("\\]\"", "]");
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} finally {
				resultObj = null;
			}
			return new ResponseEntity<>(jsonData, HttpStatus.OK);
		} else {
			return AssetUtils.buildResponseEntity(new ApiSuccess(Collections.emptyList()), HttpStatus.NOT_FOUND);	
		}
	}
	
	@DeleteMapping("/deleteFavchart/{id}")
	public ResponseEntity<Object> deleteFavChart(@PathVariable(name = "id", required = true) @NotBlank String id)  {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "updateAsset");
		int response = favoriteChartService.deleteFavouriteChart(id);
		if (response > 0) {
			return new ResponseEntity<>(new ApiSuccess("success"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiSuccess(null), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/deleteFavchartGrp/{tenantId}/{assetId}/{groupName}")
	public ResponseEntity<Object> deleteFavChartGroup(@PathVariable(name = "tenantId",required = true) @NotBlank Integer tenantId, @PathVariable(name = "assetId",required = true) @NotBlank String assetId, @PathVariable(name = "groupName",required = true) @NotBlank String groupName)  {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "updateAsset");
		int response = favoriteChartService.deleteFavouriteChartGrp(tenantId, assetId, groupName);
		if (response > 0) {
			return new ResponseEntity<>(new ApiSuccess("success"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiSuccess(null), HttpStatus.OK);
		}
	}

}
