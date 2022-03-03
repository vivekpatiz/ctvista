package com.danaherdigital.che_hx.report_management.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danaherdigital.che_hx.report_management.dto.ReportListDTO;
import com.danaherdigital.che_hx.report_management.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, String> {



	@Query("SELECT new com.danaherdigital.che_hx.report_management.dto.ReportListDTO(rep.id,rep.fromDate,rep.toDate,rep.reportName,rep.tenantId,rep.assetName,rep.isActive,rep.facilityName,rep.systemName,rep.createdBy,rep.updatedAt) FROM Report rep WHERE rep.tenantId = :tenantId AND (:facilityName IS NULL OR rep.facilityName = :facilityName) AND (:systemName IS NULL OR  rep.systemName  LIKE :systemName% ) ")
	Page<ReportListDTO> getAllReports(Pageable sortedPage,@Param("tenantId") Integer tenantId,@Param("facilityName") String facilityName,@Param("systemName") String systemName);

	Optional<Report> findByReportName(String reportName);

	@Override
	Optional<Report> findById(String id);

}
