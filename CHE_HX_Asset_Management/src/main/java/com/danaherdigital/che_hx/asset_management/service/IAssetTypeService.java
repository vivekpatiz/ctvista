package com.danaherdigital.che_hx.asset_management.service;

import java.util.List;

import javax.validation.Valid;

import com.danaherdigital.che_hx.asset_management.dto.AssetTypeModelDTO;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.asset_management.model.AssetType;

/**
 * The Interface IAssetTypeService.
 */
public interface IAssetTypeService {

	/**
	 * Save asset type.
	 *
	 * @param assetType the asset type
	 * @return the string
	 * @throws ChemtreatException the chemtreat exception
	 */
	String saveAssetType(@Valid AssetTypeModelDTO assetType) throws ChemtreatException;

	/**
	 * Update asset type.
	 *
	 * @param assetTypeId the asset type id
	 * @param assetType the asset type
	 * @return the string
	 */
	String updateAssetType(String assetTypeId,@Valid AssetTypeModelDTO assetType);

	/**
	 * Gets the asset type by id.
	 *
	 * @param assetTypeId the asset type id
	 * @return the asset type by id
	 */
	AssetType getAssetTypeById(String assetTypeId);

	/**
	 * Delete asset type.
	 *
	 * @param asseTypetId the asse typet id
	 */
	void deleteAssetType(String asseTypetId);

	/**
	 * Gets the all asset types.
	 *
	 * @return the all asset types
	 */
	List<AssetType> getAllAssetTypes();
}
