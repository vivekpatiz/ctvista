package com.danaherdigital.che_hx.asset_management.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danaherdigital.che_hx.asset_management.dto.AssetTypeModelDTO;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.asset_management.model.AssetType;
import com.danaherdigital.che_hx.asset_management.respository.AssetTypeRepository;
import com.danaherdigital.che_hx.asset_management.service.IAssetTypeService;
import com.danaherdigital.che_hx.asset_management.utils.ApplicationConstants;

/**
 * The Service Class AssetTypeServiceImpl.
 */
@Service
public class AssetTypeServiceImpl implements IAssetTypeService {

	/** The asset type repository. */
	@Autowired
	private AssetTypeRepository assetTypeRepository;

	/** The Constant LOGGER. */
	static	final  Logger LOGGER = LoggerFactory.getLogger(AssetTypeServiceImpl.class);


	/**
	 * Save asset type.
	 *
	 * @param assetType the asset type
	 * @return the string
	 * @throws ChemtreatException the chemtreat exception
	 */
	@Override
	public String saveAssetType(AssetTypeModelDTO assetType) throws ChemtreatException {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "saveAssetType");
		LOGGER.info("Checking if AssetType is exists");
		Optional<AssetType> assetTypeDetails = assetTypeRepository.findByName(assetType.getName());
		if (assetTypeDetails.isPresent()) {
			LOGGER.info("AssetType is already exists");

			throw new ChemtreatException(ApplicationConstants.ASSET_TYPE_ALREADY_EXISTS);
		}
		AssetType assetTypeData = new ModelMapper().map(assetType, AssetType.class);
		 assetTypeData = assetTypeRepository.save(assetTypeData);
		LOGGER.info("AssetType is inserted successfully.");
		return assetTypeData.getId();
	}

	/**
	 * Gets the asset type by id.
	 *
	 * @param assetTypeId the asset type id
	 * @return the asset type by id
	 */
	@Override
	public AssetType getAssetTypeById(String assetTypeId) {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAssetTypeById");
		LOGGER.info("Get AssetTypeById fething started");
		AssetType assetTypeData=assetTypeRepository.findById(assetTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("AssetType not found"));
		LOGGER.info("Get AssetTypeById fething started");
		return assetTypeData;

	}

	/**
	 * Delete asset type.
	 *
	 * @param assetTypeId the asset type id
	 */
	@Override
	public void deleteAssetType(String assetTypeId) {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "deleteAssetType");
		LOGGER.info("Fetching AssetType By Id to delete");
		AssetType assetTypeData=assetTypeRepository.findById(assetTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("AssetType not found"));
		assetTypeRepository.delete(assetTypeData);
		LOGGER.info("Asset Type deleted successfully");



	}

	/**
	 * Gets the all asset types.
	 *
	 * @return the all asset types
	 */
	@Override
	public List<AssetType> getAllAssetTypes() {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAllAssetTypes");
		LOGGER.info("Get all AssetTypes fetching started");
		return assetTypeRepository.findAll();

	}

	/**
	 * Update asset type.
	 *
	 * @param assetTypeId the asset type id
	 * @param assetType   the asset type
	 * @return the string
	 */
	@Override
	public String updateAssetType(String assetTypeId, AssetTypeModelDTO assetType) {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "updateAssetType");
		return assetTypeRepository.findById(assetTypeId).map(assetTypeData -> {
			LOGGER.info("AssetType found to update");
			assetTypeData.setName(assetType.getName());
			LOGGER.info("AssetType updated successfully.");
			return assetTypeRepository.save(assetTypeData).getId();
		}).orElseThrow(() -> new ResourceNotFoundException("AssetTypeId " + assetTypeId + " not found"));

	}

}
