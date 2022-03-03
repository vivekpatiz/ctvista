package com.danaherdigital.che_hx.schedular.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danaherdigital.che_hx.schedular.model.AssetDataLog;

@Repository
public interface AssetDataLogRepository extends JpaRepository<AssetDataLog, Long> {

	@Query("select assetDataLog from AssetDataLog assetDataLog where assetDataLog.workFlowStatus=:status and assetDataLog.rowCount>0 order by assetDataLog.timestamp")
	List<AssetDataLog> findByworkFlowStatus(@Param("status")String status,Pageable pageRequest);
	@Query("select count(*) from AssetDataLog assetDataLog where assetDataLog.rowCount >0 and assetDataLog.workFlowStatus=:status")
	Long countByworkFlowStatusAndrowCount(@Param("status")String status);

	@Query("select c from AssetDataLog c where c.id=:id and c.assetName=:assetId and c.tenantId=:tenantId and c.workFlowStatus=:status")
	List<AssetDataLog>	 findByAssetName(@Param("id")Long id,@Param("assetId")String assetName,@Param("status")String status,@Param("tenantId") Long tenantId);

	List<AssetDataLog> findByTenantId(String id);

	@Query("select count(*) from AssetDataLog assetDataLog where assetDataLog.workFlowStatus=:status")
	Long countInProgressStatus(@Param("status")String statusCalcInProgress);

	@Query("select assetDataLog from AssetDataLog assetDataLog where assetDataLog.id=:id and  assetDataLog.assetName=:assetName")
	AssetDataLog findByExistStatus( @Param("id")Long inflowId,@Param("assetName") String assetName);

}
