package com.danaherdigital.che_hx.report_management.serviceimpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.danaherdigital.che_hx.report_management.dto.ReportDTO;
import com.danaherdigital.che_hx.report_management.dto.ReportListDTO;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ConflictException;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.report_management.model.Report;
import com.danaherdigital.che_hx.report_management.repository.ReportRepository;
import com.danaherdigital.che_hx.report_management.service.ReportService;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;
import com.danaherdigital.che_hx.report_management.utils.CommonUtils;

@Service
public class ReportServiceImpl implements ReportService {

	 @Autowired
	 private ReportRepository reportRepository;


	@Autowired
	private ModelMapper modelMapper;

	 static final  Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Override
	public String saveorUpdate(ReportDTO reportReq) throws ConflictException {
LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),
				"saveorUpdate");
		LOGGER.info("Saving Report");
		Optional<Report> optReportN = reportRepository.findByReportName(reportReq.getReportName());

		if(!optReportN.isPresent()) {
			Report report = modelMapper.map(reportReq, Report.class);

		Report reportNew = reportRepository.save(report);
		LOGGER.info("Saved Report");
		return reportNew.getId();
		}
		else {
			throw new ConflictException("Report name already exists!");

		}



	}

	@Override
	public void updateReport(ReportDTO reportRequest, String reportId) throws ChemtreatException, ConflictException {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "updateReport");
		LOGGER.info("updateReport Start");

		Optional<Report> optReport = reportRepository.findById(reportId);
		if(optReport.isPresent()) {
			Optional<Report> optReportN = reportRepository.findByReportName(reportRequest.getReportName());
			if((optReportN.isPresent() && optReportN.get().getId().equals(optReport.get().getId()))||(!optReportN.isPresent()))
			{
			reportRequest.setId(reportId);
			Report report = modelMapper.map(reportRequest, Report.class);
			report.setCreatedBy(optReportN.isPresent()?optReportN.get().createdBy:"");
			reportRepository.save(report);
			}
			else {
				throw new ConflictException("Report name already exists!");
			}
		}
		else {
			throw new ResourceNotFoundException("Report not found");
		}
		LOGGER.info("updateReport done");

	}

	@Override
	public void deleteReport(String reportId) throws ConflictException {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),"deleteReport");
		LOGGER.info("deleteReport Start");

		Optional<Report> optReport = reportRepository.findById(reportId);
		if(optReport.isPresent()) {
			reportRepository.deleteById(optReport.get().getId());
		}
		else {
			throw new ResourceNotFoundException(ApplicationConstants.REPORT_NOT_PRESENT);
		}
		LOGGER.info("deleteReport done");

	}

	@Override
	public Page<ReportListDTO> getAllReport(Integer tenantId, Integer page, Integer size, String sort, String order, String facilityName, String systemName) {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),"getAllReport");
		LOGGER.info("getAllReport Start");
		Page<ReportListDTO> reportPage = null;

		Pageable sortedPage = CommonUtils.getPageable(page, size, sort, order);

		reportPage =  reportRepository.getAllReports(sortedPage,tenantId,facilityName,systemName);
		LOGGER.info("getAllReport done");
		return reportPage;

	}

	@Override
	public Report getReportById(String reportId) {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),"getReportById");
		LOGGER.info("getReportById Start");

		Optional<Report> optReport = reportRepository.findById(reportId);
		if(optReport.isPresent()) {
			LOGGER.info("getReportById done");
			return optReport.get();
		}
		else {
			LOGGER.info("getReportById error");
			throw new ResourceNotFoundException("Report not found");
		}




	}

}
