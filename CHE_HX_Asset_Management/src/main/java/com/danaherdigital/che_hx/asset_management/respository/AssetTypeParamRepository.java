package com.danaherdigital.che_hx.asset_management.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.asset_management.model.AssetTypeParam;

public interface AssetTypeParamRepository extends JpaRepository<AssetTypeParam, String> {
	
	@Query("SELECT assetTypeParam FROM AssetTypeParam assetTypeParam where assetTypeParam.assetType.name=:assetTypeName")
	List<AssetTypeParam> getAllAssetTypeParamOnName(@Param("assetTypeName") String assetTypeName);

}
