package com.danaherdigital.che_hx.schedular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.schedular.model.AssetParam;

public interface AssetParamRepository extends JpaRepository<AssetParam, String> {

	@Query("select assetParam from AssetParam assetParam where assetParam.id=:assetParamId and assetParam.assetId=:assetId")
	AssetParam findByAssetAndParamId(@Param("assetParamId")String id,@Param("assetId")String assetId);

	@Query(value="SELECT * FROM `dhrd_asset_params` WHERE asset_id in(:unitId,:plantId)",nativeQuery = true)
	List<AssetParam> findByUnitAndPlantId(@Param("unitId") String unitId,@Param("plantId")String plantId);

	@Query(value="SELECT assetParam from AssetParam assetParam where assetParam.assetId=:unitId")
	List<AssetParam> findByUnit(@Param("unitId") String unitId);

}
