package com.danaherdigital.che_hx.asset_management.utils;


import com.danaherdigital.che_hx.asset_management.dto.AssetTypeModelDTO;
import com.danaherdigital.che_hx.asset_management.model.AssetType;


public class AssetTypeTestUtil {

	public static AssetTypeModelDTO getAssetTypeModelTemplate()
	{
		AssetTypeModelDTO assetType=new AssetTypeModelDTO();
		assetType.setName("Heat Exchanger");
		assetType.setParentId("bea96b40-dbb4-42de-8507-ff827f222926");
		return assetType;

	}

	public static AssetType getAssetTypeTemplate()
	{
		AssetType assetType=new AssetType();
		assetType.setName("Heat Exchanger");

		return assetType;

	}
	public static AssetTypeModelDTO getInvalidAssetTypeTemplate()
	{
		AssetTypeModelDTO assetType=new AssetTypeModelDTO();
		assetType.setName(null);

		return assetType;

	}

}
