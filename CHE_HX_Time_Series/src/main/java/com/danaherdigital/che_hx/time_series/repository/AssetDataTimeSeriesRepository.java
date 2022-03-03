package com.danaherdigital.che_hx.time_series.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.danaherdigital.che_hx.time_series.model.AssetDataTimeSeries;

public interface AssetDataTimeSeriesRepository extends CrudRepository<AssetDataTimeSeries, String> {

	long countByAssetName(String assetId);


	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Query("delete from AssetDataTimeSeries a where a.tenantId=:tenantId and a.assetName=:assetId and a.computed=1 and a.timeStamp between :minTimestamp and :maxTimestamp")
	int deleteOldComputed(@Param("tenantId")Integer tenantId,@Param("assetId") String assetId,@Param("minTimestamp") Long minTimestamp,@Param("maxTimestamp") Long maxTimestamp);

}
