package com.danaherdigital.che_hx.schedular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danaherdigital.che_hx.schedular.dto.AssetDataDTO;
import com.danaherdigital.che_hx.schedular.dto.AssetDataParamDTO;
import com.danaherdigital.che_hx.schedular.dto.SliceDTO;
import com.danaherdigital.che_hx.schedular.model.AssetData;

@Repository
public interface AssetDataRespository extends JpaRepository<AssetData, String> {

	@Query(value = "select distinct(assetData.dataTimestamp)  from AssetData assetData where assetData.assetName=:assetName and assetData.tenantId=:tenantId  and "
			+ "assetData.dataTimestamp BETWEEN :from AND :to GROUP BY assetData.dataTimestamp ORDER BY assetData.dataTimestamp")
	List<Long> findAllByDataTimestampBetween(@Param("from") Long long1, @Param("to") Long long2,
			@Param("assetName") String assetName, @Param("tenantId") Long tenantId);

	@Query(value = "SELECT a.asset_name as assetName ,min(a.data_timestamp)AS fromDate,max(a.data_timestamp)AS toDate FROM dhrd_asset_data a where a.asset_name=:assetName", nativeQuery = true)
	AssetDataDTO findDateRange(@Param("assetName") String assetName);

	@Query(value = "SELECT asset_name as assetName,min(data_timestamp) AS fromDate ,max(data_timestamp) AS toDate FROM(\n"
			+ " SELECT * from dhrd_asset_data where asset_name=:assetName order by data_timestamp limit :limit offset :offset)as dt", nativeQuery = true)
	AssetDataDTO findDateRangeByLimit(@Param("assetName") String assetName, @Param("limit") long limit,
			@Param("offset") long offset);

	@Query(value = "select distinct min(data_timestamp) as minTime,max(data_timestamp) as maxTime from dhrd_asset_data  assetData where assetData.asset_name=:assetName and assetData.tenant_id=:tenantId  "
			+ "and  assetData.data_timestamp BETWEEN unix_timestamp(:from) AND unix_timestamp(:to)", nativeQuery = true)
	SliceDTO getDataTimeStamp(@Param("from") String string, @Param("to") String string2,
			@Param("assetName") String assetId, @Param("tenantId") Long tenantId);

	@Query(value = "SELECT ad.id,ad.data_timestamp as dataTimeStamp,ad.float_values as floatValues,ad.string_values as stringValues,am.name,am.unit_of_measure_id as uom  FROM dhrd_asset_data ad \n"
			+ "join dhrd_asset_params am on ad.asset_name=am.asset_id and ad.asset_params_name=am.id where ad.data_timestamp=:dataTimestamp and ad.asset_name=:assetName and ad.tenant_id=:tenantId", nativeQuery = true)
	List<AssetDataParamDTO> findAllByAssetNameTimestamp(@Param("assetName") String assetName,
			@Param("dataTimestamp") long dataTimestamp, @Param("tenantId") long tenantId);

}
