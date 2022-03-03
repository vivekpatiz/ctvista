package com.danaherdigital.che_hx.asset_management.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.asset_management.dto.ChartsConfigDTO;
import com.danaherdigital.che_hx.asset_management.dto.HistorianDataDTO;
import com.danaherdigital.che_hx.asset_management.dto.PlantDataDTO;
import com.danaherdigital.che_hx.asset_management.model.AssetParam;

public interface AssetParamRepository extends JpaRepository<AssetParam, String> {

	@Modifying
	@Query("DELETE FROM AssetParam assetParam where assetParam.asset.id=:assetId")
	void deleteAssetParamOnAssetId(@Param("assetId") String assetId);

	
	@Query("SELECT assetParam from AssetParam assetParam WHERE  assetParam.asset.id =:assetId AND assetParam.name=:name")
	Optional<AssetParam> getAssetByNameId(@Param("name") String name,@Param("assetId")  String assetId);
	
	
	@Query("SELECT assetParam from AssetParam assetParam WHERE  assetParam.asset.id =:assetId AND assetParam.name=:name AND assetParam.computed=false")
	Optional<AssetParam> getAssetByNameIdF(@Param("name") String name,@Param("assetId")  String assetId);
	
	@Query("SELECT new com.danaherdigital.che_hx.asset_management.dto.PlantDataDTO(assetParam.name,assetParam.value) from AssetParam assetParam WHERE assetParam.asset.id =:assetId")
	List<PlantDataDTO> getAssetParamById(@Param("assetId")  String assetId);
	
	
	@Query("SELECT new com.danaherdigital.che_hx.asset_management.dto.HistorianDataDTO(assetParam.name,assetParam.unitOfMeasureId,assetParam.customerTagName,assetParam.descriptor,assetParam.unitOfMeasureId) from AssetParam assetParam WHERE assetParam.asset.id =:assetId AND assetParam.computed=false")
	List<HistorianDataDTO> getHistAssetParamById(@Param("assetId")  String assetId);

	@Query("SELECT new com.danaherdigital.che_hx.asset_management.dto.ChartsConfigDTO(assetParam.name,assetParam.id,assetParam.unitOfMeasureId,assetParam.displayName,assetParam.type,assetParam.computed,'KPI') from AssetParam assetParam WHERE assetParam.asset.id =:assetId AND assetParam.name IN (:kpiAttr)")
	List<ChartsConfigDTO> getAssetParamChartsKPI(@Param("assetId") String assetId,@Param("kpiAttr") List<String> kpiAttr);

	
	@Query("SELECT new com.danaherdigital.che_hx.asset_management.dto.ChartsConfigDTO(assetParam.name,assetParam.id,assetParam.unitOfMeasureId,assetParam.displayName,assetParam.type,assetParam.computed,'CUSTOM') from AssetParam assetParam WHERE assetParam.asset.id =:assetId AND assetParam.type =:type ")
	List<ChartsConfigDTO> getAssetParamChartsNonKPI(@Param("assetId") String assetId,@Param("type") String type);
	
	@Query("SELECT assetParam.id FROM AssetParam assetParam where assetParam.asset.id=:assetId AND assetParam.name IN (:kpiAttr) ")
	List<String> getAssetParamIdbyName(@Param("assetId") String assetId,@Param("kpiAttr") List<String> kpiAttr);
	
}
