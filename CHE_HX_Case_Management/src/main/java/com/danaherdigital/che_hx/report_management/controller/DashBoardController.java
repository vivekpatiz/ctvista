package com.danaherdigital.che_hx.report_management.controller;

import java.io.FileNotFoundException;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.service.DashBoardService;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(("/api/v1/dashboard"))
public class DashBoardController {

	@Autowired
	DashBoardService dashBoardService;

	@GetMapping("/design")
	public ResponseEntity<Map<String ,Object>> getKPIOverviewCards(@RequestParam("asset_id") @NotNull String assetId, @RequestParam("tenant_id") @NotBlank  String tenantId,
			@RequestParam("asset_type")@NotBlank  String assetType, @RequestParam @NotBlank  String type) throws FileNotFoundException, ChemtreatException {
		log.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getKPIOverviewCards");

		Map<String ,Object> design = dashBoardService.getKPIOverviewDesign(assetId, tenantId, assetType, type);
		return new ResponseEntity<>(design, HttpStatus.OK);
	}

}
