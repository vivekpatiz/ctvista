package com.danaherdigital.che_hx.time_series.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danaherdigital.che_hx.time_series.dto.AssetDataDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetLoadDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetLoadDataDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetParamDTO2;
import com.danaherdigital.che_hx.time_series.model.AssetData;

@Repository
public interface AssetDataRepository extends JpaRepository<AssetData, String> {


	@Query("SELECT new com.danaherdigital.che_hx.time_series.dto.AssetDataDTO(assetData.id,FROM_UNIXTIME(assetData.dataTimestamp), assetData.floatValues,assetData.tagName,assetData.dataTimestamp) from AssetData assetData where assetData.dataTimestamp BETWEEN :fromTime AND :toTime AND assetData.tenantId= :tenantId AND assetData.assetName=:assetId AND assetData.assetParamsName=:assetParamId ORDER BY assetData.dataTimestamp")
	List<AssetDataDTO> getChartsData(@Param("fromTime") Long fromTimeDbl,@Param("toTime") Long toTimeDbl,@Param("tenantId") Integer tenantId,@Param("assetId") String assetId,@Param("assetParamId") String assetParamId);


	@Query("SELECT new com.danaherdigital.che_hx.time_series.dto.AssetLoadDataDTO(assetData.dataTimestamp,assetData.floatValues) FROM AssetData assetData  WHERE assetData.dataTimestamp BETWEEN :fromTime AND :toTime AND assetData.tenantId= :tenantId AND assetData.assetName=:assetId AND assetData.tagName=:tagName AND assetData.floatValues BETWEEN :fromLoad AND :toLoad ORDER BY assetData.dataTimestamp")
	List<AssetLoadDataDTO> getLoadData(@Param("fromTime") Long fromTimeDbl,@Param("toTime") Long toTimeDbl,@Param("tenantId") Integer tenantId,@Param("assetId") String assetId,@Param("tagName") String tagName,@Param("fromLoad") Double fromLoad,@Param("toLoad") Double toLoad);

	@Query("SELECT new com.danaherdigital.che_hx.time_series.dto.AssetLoadDataDTO(assetData.dataTimestamp,assetData.floatValues) FROM AssetData assetData  WHERE assetData.dataTimestamp BETWEEN UNIX_TIMESTAMP(:fromTime) AND UNIX_TIMESTAMP(:toTime) AND assetData.tenantId= :tenantId AND assetData.assetName=:assetId AND assetData.tagName=:tagName AND assetData.floatValues BETWEEN :fromLoad AND :toLoad ORDER BY assetData.dataTimestamp")
	List<AssetLoadDataDTO> getLoadDataYaxis(@Param("fromTime") String fromTimeDbl,@Param("toTime") String toTimeDbl,@Param("tenantId") Integer tenantId,@Param("assetId") String assetId,@Param("tagName") String tagName,@Param("fromLoad") Double fromLoad,@Param("toLoad") Double toLoad);


	@Query(value="SELECT name,display_name as displayname FROM dhrd_asset_params WHERE id = ?1 ",nativeQuery = true)
	AssetParamDTO2 getAssetParamName(@Param("assetParamId") String assetParamId);

	@Query(value="SELECT id,name FROM dhrd_asset_params WHERE asset_id = :assetId ",nativeQuery = true)
	List<AssetParamDTO2> getAssetParam(@Param("assetId") String assetParamId);

	@Query("SELECT new com.danaherdigital.che_hx.time_series.dto.AssetLoadDTO(MIN(assetData.floatValues),MAX(assetData.floatValues)) FROM AssetData assetData WHERE assetData.assetName=:assetId AND assetData.assetParamsName=:assetParamId")
	AssetLoadDTO getMinMaxLoad(@Param("assetId") String assetId,@Param("assetParamId") String assetParamId);

	@Query("SELECT new com.danaherdigital.che_hx.time_series.dto.AssetDataDTO( assetData.id,FROM_UNIXTIME(assetData.dataTimestamp), assetData.floatValues,assetData.tagName,assetData.dataTimestamp) from AssetData assetData where assetData.dataTimestamp BETWEEN :from AND :to AND assetData.floatValues BETWEEN :min AND :max AND assetData.tenantId= :tenantId AND assetData.assetName=:assetId AND assetData.assetParamsName=:paramId ORDER BY assetData.dataTimestamp")
	List<AssetDataDTO> getYaxisChartsData(@Param("tenantId") Integer tenantId,@Param("assetId") String assetId,@Param("paramId")String paramId, @Param("min")Double min, @Param("max")Double max, @Param("from")Long fromTimeStamp,
			@Param("to")Long toTimeStamp);
			
	

	@Query("select count(*) from AssetData a where a.tenantId=:tenantId and a.assetName=:assetName and a.computed=1 and a.dataTimestamp between :min and :max")
	Long countByTimeStamp(@Param("tenantId")Integer tenantId,@Param("assetName") String assetId, @Param("min")Long minTimestamp, @Param("max")Long maxTimestamp);


}