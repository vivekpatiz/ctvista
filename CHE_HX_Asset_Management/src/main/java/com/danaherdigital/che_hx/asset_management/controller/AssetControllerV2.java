package com.danaherdigital.che_hx.asset_management.controller;


import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.asset_management.ApiSuccess;
import com.danaherdigital.che_hx.asset_management.dto.AssetDTO;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.asset_management.service.IAssetService;
import com.danaherdigital.che_hx.asset_management.utils.ApplicationConstants;
import com.danaherdigital.che_hx.asset_management.utils.AssetUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v2/AssetManagement")
@Slf4j
public class AssetControllerV2 {

	@Autowired
	IAssetService assetService;

	/**
	 * Get the Asset on tenant ID and type
	 *
	 * @param assetType the asset type
	 * @return the response entity
	 * @throws ChemtreatException the chemtreat exception
	 */
	@GetMapping("/assets/{tenantId}/{assetType}")
	public ResponseEntity<Object> getAllAssetByTenantAndType(@PathVariable(name = "tenantId",required = true) @NotBlank Integer tenantId, @PathVariable(name = "assetType",required = true) @NotBlank String assetType,@RequestParam(value = "page",required=false,defaultValue = "0") Integer page,
    		@RequestParam(value = "size",required=false,defaultValue = Integer.MAX_VALUE+"") Integer size,
    		@RequestParam(value = "sort",required=false) String  sort,
    		@RequestParam(value = "order",required=false) String order,
    		@RequestParam(value = "facilityName",required=false) String facilityName,
    		@RequestParam(value = "systemName",required=false) String systemName) throws ChemtreatException {

		log.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAllAssetByTenantAndType");
		Page<AssetDTO> assetList = assetService.getAllAssetByTenantAndTypes(tenantId,assetType,page,size,sort,order,facilityName,systemName);
		return AssetUtils.buildResponseEntity(new ApiSuccess(assetList));
	}

}