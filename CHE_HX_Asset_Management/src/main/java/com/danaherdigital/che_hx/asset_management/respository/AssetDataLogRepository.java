package com.danaherdigital.che_hx.asset_management.respository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danaherdigital.che_hx.asset_management.dto.AssetIngestionLogDTO;
import com.danaherdigital.che_hx.asset_management.model.AssetDataLog;

@Repository
public interface AssetDataLogRepository extends JpaRepository<AssetDataLog, Long> {

	

	@Query("select c from AssetDataLog c where c.assetName=:assetId order by c.id desc")
	List<AssetDataLog>	 findByAssetName(@Param("assetId")String assetName);

	@Query("select new com.danaherdigital.che_hx.asset_management.dto.AssetIngestionLogDTO(c.timestamp,c.assetName,c.workFlowStatus,c.fileName,c.rowCount,c.ingestionEndTime,c.calcStartTime,c.calcFailedTime,c.calcEndTime) from AssetDataLog c where c.tenantId=:tenantId order by c.id desc")
	List<AssetIngestionLogDTO> findAllByTenantId(@NotBlank @Param("tenantId") String tenantId);

	

}
