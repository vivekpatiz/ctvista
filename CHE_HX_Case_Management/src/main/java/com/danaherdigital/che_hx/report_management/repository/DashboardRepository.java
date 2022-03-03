package com.danaherdigital.che_hx.report_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.report_management.model.DashBoard;

public interface DashboardRepository extends JpaRepository<DashBoard, Long> {

	@Query("select d from DashBoard d where d.assetId=:assetId and d.type=:type and d.assetType=:assetType")
	Optional<DashBoard> findDesign( @Param("type") String type,@Param("assetId") String assetId,@Param("assetType") String assetType);


	@Query("select d.assetType from DashBoard d where d.type='s3bucket' and d.name=:name")
	String findByNameandType(@Param("name")String docName);


}
