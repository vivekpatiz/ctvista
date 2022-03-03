package com.danaherdigital.che_hx.calcengine.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.calcengine.dto.CalcReqDTO;
import com.danaherdigital.che_hx.calcengine.service.ICalcService;
import com.danaherdigital.che_hx.calcengine.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class CalcController.
 */
@RestController

/** The Constant log. */
@Slf4j
public class CalcController {

	/** The I calc service. */
	@Autowired
	ICalcService calcService;


	/**
	 * Gets the calc data
	 * @param req the req
	 * @return the calc data
	 */
	@PostMapping("/api/v1/runCalculation")
	public ResponseEntity<List<Object>> getCalcData(@RequestBody @Valid CalcReqDTO req) {
		log.info("Get getCalcData controller started");
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getCalcData");
		Date d1=new Date();
		log.info("calc started at :{}",System.currentTimeMillis());
		List<Object> calcData = calcService.getCalcData(req);
		Date d2=new Date();
		log.info("calc completed at :{}",d2.getTime()-d1.getTime());

		return new ResponseEntity<>(calcData,
				HttpStatus.OK);
	}

}
