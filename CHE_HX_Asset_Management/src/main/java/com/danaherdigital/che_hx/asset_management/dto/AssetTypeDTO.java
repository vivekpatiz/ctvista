package com.danaherdigital.che_hx.asset_management.dto;

import java.util.List;

import com.danaherdigital.che_hx.asset_management.model.AssetType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


/**
 * The Class AssetTypeDTO.
 */
@Data
public class AssetTypeDTO {

	/** The message. */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;
	
	/** The asset type. */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private AssetType assetType;
	
	/** The asset type list. */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<AssetType> assetTypeList;
	
	/**
	 * Instantiates a new asset type DTO.
	 *
	 * @param message the message
	 */
	public AssetTypeDTO(String message) {
		
		this.message = message;
	}
	
	/**
	 * Instantiates a new asset type DTO.
	 *
	 * @param assetType the asset type
	 */
	public AssetTypeDTO(AssetType assetType) {
		
		this.assetType = assetType;
	}
	
	/**
	 * Instantiates a new asset type DTO.
	 *
	 * @param assetTypeList the asset type list
	 */
	public AssetTypeDTO(List<AssetType> assetTypeList) {
		
		this.assetTypeList = assetTypeList;
	}
}
