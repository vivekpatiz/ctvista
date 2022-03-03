package com.danaherdigital.che_hx.report_management.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.service.HistorianMappingService;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/HistorianMapping")
@Slf4j
public class HistorianMappingController {

	@Autowired
	HistorianMappingService historianMappingService;

	@PostMapping("/parse_excel")
	public ResponseEntity<String> parseExcelToJson(@RequestParam("file") MultipartFile file) throws IOException, ChemtreatException {
		log.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "parseExcelToJson");
		return new ResponseEntity<>(historianMappingService.convertExcelToJsonString(file.getInputStream()),
				HttpStatus.OK);
	}


}
