package com.danaherdigital.che_hx.schedular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;


import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.service.ICalcStatusService;
import com.danaherdigital.che_hx.schedular.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;



/**
 * The Class SchedularController.
 */
@RestController

/** The Constant log. */
@Slf4j
public class SchedularController {

	/** The ICalcStatusService . */
	@Autowired
	ICalcStatusService calcStatusService;


	@GetMapping("/api/v1/Schedular/getAssetLogStatus/{tenantId}")
	public ResponseEntity<List<AssetDataLog>> getAssetLogStatus(@PathVariable String tenantId) {
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getCalcDesignDataData");
		List<AssetDataLog> listAssetLog = calcStatusService.getList(tenantId);
		log.info("getAssetLogStatus completed->{}",listAssetLog);
		return new ResponseEntity<>(listAssetLog,
				HttpStatus.OK);
	}
}
