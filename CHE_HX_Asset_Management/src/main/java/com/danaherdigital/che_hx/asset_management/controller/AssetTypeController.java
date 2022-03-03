package com.danaherdigital.che_hx.asset_management.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.asset_management.ApiSuccess;
import com.danaherdigital.che_hx.asset_management.dto.AssetTypeDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetTypeModelDTO;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.asset_management.model.AssetType;
import com.danaherdigital.che_hx.asset_management.service.IAssetTypeService;
import com.danaherdigital.che_hx.asset_management.utils.ApplicationConstants;

/**
 * The Class AssetTypeController.
 *
 * @author mohammad.bagali
 * AssetType controller
 */

@RestController
@RequestMapping("/v1/AssetManagement")
public class AssetTypeController {

	/** The Constant LOGGER. */
	static final  Logger LOGGER = LoggerFactory.getLogger(AssetTypeController.class);

    /** The I asset type service. */
    @Autowired
    IAssetTypeService assetTypeService;

    /**
     * Gets the asset type by id.
     *
     * @param assetTypeId the asset type id
     * @return the asset type by id
     */
	@GetMapping("/AssetType/{assetTypeId}")
	public ResponseEntity<Object> getAssetTypeById(@PathVariable String assetTypeId) {

		LOGGER.info("Get AssetTypeById controller started");
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAssetTypeById");

		AssetType assetType = assetTypeService.getAssetTypeById(assetTypeId);
		return new ResponseEntity<>(new ApiSuccess(new AssetTypeDTO(assetType)),
				HttpStatus.OK);
	}

	/**
	 * Gets the all asset types.
	 *
	 * @return the all asset types
	 */
	@GetMapping("/AssetTypes")
	public ResponseEntity<Object> getAllAssetTypes() {

		LOGGER.info("Get all AssetTypes controller started");
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAllAssetTypes");

		List<AssetType> assetTypeList = assetTypeService.getAllAssetTypes();
		LOGGER.info("Get all AssetTypes fetching completed");

		return new ResponseEntity<>(new ApiSuccess(new AssetTypeDTO(assetTypeList)),
				HttpStatus.OK);
	}

	/**
	 * Creates the asset type.
	 *
	 * @param assetType the asset type
	 * @return the response entity
	 * @throws ChemtreatException the chemtreat exception
	 */
	@PostMapping("/AssetType")
	public ResponseEntity<Object> createAssetType(@RequestBody @Valid AssetTypeModelDTO assetType) throws ChemtreatException {

		LOGGER.info("CreateAssetType controller started");
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "createAssetType");
		assetTypeService.saveAssetType(assetType);
		return new ResponseEntity<>(new ApiSuccess(null), HttpStatus.CREATED);

	}


	/**
	 * Update asset type.
	 *
	 * @param assetTypeId the asset type id
	 * @param assetType the asset type
	 * @return the response entity
	 */
	@PutMapping("/AssetType/{assetTypeId}")
	public ResponseEntity<Object> updateAssetType(@PathVariable String assetTypeId,@RequestBody @Valid AssetTypeModelDTO assetType) {

		LOGGER.info("Update AssetType controller started");
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "updateAssetType");
		assetTypeService.updateAssetType(assetTypeId, assetType);
		return new ResponseEntity<>(new ApiSuccess(new AssetTypeDTO(ApplicationConstants.ASSET_TYPE_UPDATED)),
				HttpStatus.OK);
	}

	/**
	 * Delete asset type.
	 *
	 * @param assetTypeId the asset type id
	 * @return the response entity
	 */
	@DeleteMapping("/AssetType/{assetTypeId}")
	public ResponseEntity<Object> deleteAssetType(@PathVariable String assetTypeId){

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "deleteAssetType");
		assetTypeService.deleteAssetType(assetTypeId);

		return new ResponseEntity<>(new ApiSuccess(null),
				HttpStatus.OK);
	}

}
