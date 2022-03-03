package com.danaherdigital.che_hx.uom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.uom.dto.UOMReqDTO;
import com.danaherdigital.che_hx.uom.dto.UOMRespDTO;
import com.danaherdigital.che_hx.uom.dto.UOMdataDTO;
import com.danaherdigital.che_hx.uom.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.uom.service.IUnitOfMeasureService;
import com.danaherdigital.che_hx.uom.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class UnitOfMeasureController.
 */
@RestController

/** The Constant log. */
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/UOM")
public class UnitOfMeasureController {

/** The I unit of measure service. */
@Autowired
IUnitOfMeasureService unitOfMeasureService;

	/**
	 * Gets the UOM conversion data.
	 *
	 * @param req the req
	 * @return the UOM conversion data
	 * @throws ChemtreatException the chemtreat exception
	 */
	@PostMapping("/api/v1/runUOMConversion")
	public ResponseEntity<UOMRespDTO> getUOMConversionData(@RequestBody UOMReqDTO req) throws ChemtreatException {
		log.info("Get getCalcData getUOMConversionData started");
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getUOMConversionData");
		UOMRespDTO calcData = unitOfMeasureService.getUOMConversionData(req);

		return new ResponseEntity<>(calcData,
				HttpStatus.OK);
	}



	/**
	 * Gets the UOM conversion data.
	 *
	 * @param req the req
	 * @return the UOM conversion data
	 * @throws ChemtreatException the chemtreat exception
	 */
	@GetMapping("/api/v1/uom")
	public ResponseEntity<List<UOMdataDTO>> getUOMList() throws ChemtreatException {
		log.info("Get getUOMList started");
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getUOMList");
		List<UOMdataDTO> uomDataList = unitOfMeasureService.getUOMList();

		return new ResponseEntity<>(uomDataList,HttpStatus.OK);
	}

}
