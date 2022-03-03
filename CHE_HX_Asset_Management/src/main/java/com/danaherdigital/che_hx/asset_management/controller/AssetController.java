package com.danaherdigital.che_hx.asset_management.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.asset_management.ApiSuccess;
import com.danaherdigital.che_hx.asset_management.dto.AssetDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetDTOLoadInt;
import com.danaherdigital.che_hx.asset_management.dto.AssetIngestionLogDTO;
import com.danaherdigital.che_hx.asset_management.dto.ChartsConfigDTO;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.asset_management.service.IAssetService;
import com.danaherdigital.che_hx.asset_management.utils.ApplicationConstants;
import com.danaherdigital.che_hx.asset_management.utils.AssetUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/v1/AssetManagement")
public class AssetController {
	static final  Logger LOGGER = LoggerFactory.getLogger(AssetController.class);

	@Autowired
	IAssetService assetService;

	@GetMapping("/condenser-asset/{assetId}")
	public ResponseEntity<Object> getAssetById(@PathVariable String assetId) throws ChemtreatException {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAssetById");

		JSONObject condenserAssetData = assetService.getAssetById(assetId);
		return AssetUtils.buildResponseEntity(new ApiSuccess(condenserAssetData.toMap()));
	}

	@GetMapping("/condenser-load/{assetId}")
	public ResponseEntity<String> getCondenserLoadById( @PathVariable String assetId) throws JsonProcessingException  {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getCondenserLoadById");

		AssetDTOLoadInt condenserAssetData = assetService.getCondenserLoadById(assetId);
		return new
				ResponseEntity<>
		(new ObjectMapper().writeValueAsString(condenserAssetData),HttpStatus.OK);
	}


	@GetMapping("/condenser-chart-key/{assetId}")
	public ResponseEntity<List<ChartsConfigDTO>> getCondenserChartValues(@PathVariable String assetId,@RequestParam Boolean isKpi){

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getCondenserChartValues");

		List<ChartsConfigDTO> condenserAssetData = assetService.getCondenserChartValues(assetId,isKpi);
		return new ResponseEntity<>(condenserAssetData,HttpStatus.OK);
	}


	@GetMapping("/condenser-chart-kpi/{assetId}")
	public ResponseEntity<List<String>> getKPIChartValues(@PathVariable String assetId){

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getKPIChartValues");

		List<String> kpiId = assetService.getKPIChartValues(assetId);
		return new ResponseEntity<>(kpiId,HttpStatus.OK);
	}


	@PostMapping("/asset")
	public ResponseEntity<Object> createAsset(@RequestBody String requestData) throws ChemtreatException {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "createAsset");

		String assetId = assetService.saveAsset(requestData);
		return new ResponseEntity<>(new ApiSuccess(assetId), HttpStatus.CREATED);
	}

	@PutMapping("/asset/{assetId}")
	public ResponseEntity<Object> updateAsset(@Valid @RequestBody String requestData,@PathVariable @Min(37) final String assetId) throws ChemtreatException {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "updateAsset");
		assetService.updateCondenser(requestData,assetId);
		return new ResponseEntity<>(new ApiSuccess(null), HttpStatus.OK);
	}

	@GetMapping("/asset-data")
	public ResponseEntity<Object> getAssetInfo(@RequestParam String name,@RequestParam String type,@RequestParam Integer tenantId){

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAssetInfo");
		JSONObject condenserAssetData = assetService.getAssetInfo(name,type,tenantId);
		return AssetUtils.buildResponseEntity(new ApiSuccess(condenserAssetData.toMap()));
	}


	// delete asset by id
	@DeleteMapping("/asset/{assetId}")
	public ResponseEntity<Object> deleteAsset(@PathVariable String assetId) {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "deleteAssetByID");

		assetService.deleteAsset(assetId);

		return AssetUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK));
	}



	/**
	 * Get the Asset on tenant ID and type
	 *
	 * @param assetType the asset type
	 * @return the response entity
	 * @throws ChemtreatException the chemtreat exception
	 */
	@GetMapping("/assets/{tenantId}/{assetType}")
	public ResponseEntity<Object> getAllAssetByTenantAndType(@PathVariable(name = "tenantId",required = true) @NotBlank Integer tenantId, @PathVariable(name = "assetType",required = true) @NotBlank String assetType) throws ChemtreatException {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAllAssetByTenantAndType");
		List<AssetDTO> assetList = assetService.getAllAssetByTenantAndType(tenantId,assetType);
		return AssetUtils.buildResponseEntity(new ApiSuccess(assetList));
	}


	@GetMapping(value="/inflow-log/{tenantId}", produces = { "application/json" })
	public ResponseEntity<Object> getAllInflowLog(@PathVariable(name = "tenantId",required = true) @NotBlank Integer tenantId,
			@RequestParam Integer size) throws ChemtreatException {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAllInflowLog");
		List<AssetIngestionLogDTO> assetInflowList = assetService.getAllInflowLog(tenantId,size);
		return AssetUtils.buildResponseEntity(new ApiSuccess(assetInflowList));
	}




}
