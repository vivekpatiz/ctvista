package com.danaherdigital.che_hx.report_management.service;

import org.springframework.data.domain.Page;

import com.danaherdigital.che_hx.report_management.dto.ReportDTO;
import com.danaherdigital.che_hx.report_management.dto.ReportListDTO;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ConflictException;
import com.danaherdigital.che_hx.report_management.model.Report;

public interface ReportService {

	String saveorUpdate(ReportDTO reportReq) throws ConflictException;

	void updateReport(ReportDTO reportRequest, String reportId) throws ChemtreatException, ConflictException;

	void deleteReport(String reportId) throws ConflictException;

	Page<ReportListDTO> getAllReport(Integer teanantId, Integer page, Integer size, String sort, String order, String facilityName, String systemName);

	Report getReportById(String reportId);

}
