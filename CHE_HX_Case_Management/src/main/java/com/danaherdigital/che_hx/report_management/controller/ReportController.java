package com.danaherdigital.che_hx.report_management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.report_management.ApiSuccess;
import com.danaherdigital.che_hx.report_management.dto.ReportDTO;
import com.danaherdigital.che_hx.report_management.dto.ReportListDTO;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ConflictException;
import com.danaherdigital.che_hx.report_management.model.Report;
import com.danaherdigital.che_hx.report_management.service.ReportService;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;
import com.danaherdigital.che_hx.report_management.utils.CommonUtils;


@RestController
@RequestMapping("/api/v1/ReportManagement")
public class ReportController {

	static final  Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;
   
    
    @GetMapping("/report-all/{tenantId}")
    public ResponseEntity<Object> getAllReport(@PathVariable Integer tenantId,
    		@RequestParam(value = "page",required=false,defaultValue = "0") Integer page,
    		@RequestParam(value = "size",required=false,defaultValue = Integer.MAX_VALUE+"") Integer size,
    		@RequestParam(value = "sort",required=false) String  sort,
    		@RequestParam(value = "order",required=false) String order,
    		@RequestParam(value = "facilityName",required=false) String facilityName,
    		@RequestParam(value = "systemName",required=false) String systemName) {
    	LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),
				 "getAllReport");
    	Page<ReportListDTO> reportPage = reportService.getAllReport(tenantId,page,size,sort,order,facilityName,systemName);
		return CommonUtils.buildResponseEntity(new ApiSuccess(reportPage));
    }


    @GetMapping("/report-id/{reportId}")
    public ResponseEntity<Object> getReportById(@PathVariable String reportId) {
    	LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),
				 "getReportById");
    	Report report = reportService.getReportById(reportId);
   		return new ResponseEntity<>(report,HttpStatus.OK);
    }

    @PostMapping("/report")
    public ResponseEntity<Object> createReport(@RequestBody ReportDTO reportReq) throws ConflictException {
    	LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),
				 "createReport");

    	String reportId = reportService.saveorUpdate(reportReq);
		return new ResponseEntity<>(new ApiSuccess(reportId), HttpStatus.CREATED);
    }

    @PutMapping("/report/{reportId}")
    public ResponseEntity<Object> updateReport(@PathVariable String reportId,@RequestBody ReportDTO reportReq) throws ChemtreatException, ConflictException {
    	LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),"updateReport");
    	
    	reportService.updateReport(reportReq, reportId);
    	   	
		return new ResponseEntity<>(new ApiSuccess(null), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/reports/{reportId}")
    public ResponseEntity<Object> deleteReport(@PathVariable String reportId) throws ConflictException {
    	reportService.deleteReport(reportId);
    	return new ResponseEntity<>(new ApiSuccess(null), HttpStatus.OK);

    }

}
