package com.danaherdigital.che_hx.schedular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danaherdigital.che_hx.schedular.model.CalcJob;

@Repository
public interface CalcJobRepository extends JpaRepository<CalcJob, String> {

	List<CalcJob> findByStatus(String statusReady);

	@Query("select  count(c)>0 from CalcJob c where c.assetName=:assetName and c.tenantId=:tenantId and c.status=:status  and c.fromDate=:start and  c.toDate=:end")
	boolean findByStatusAndAssetName(@Param("assetName")String assetName, @Param("status")String statusReady,@Param("tenantId") Long tenantId,@Param("start") Long long1,@Param("end") Long long2);

}
