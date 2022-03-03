package com.danaherdigital.che_hx.uom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.uom.dto.LookUpReqDTO;
import com.danaherdigital.che_hx.uom.dto.ResponseDTO;
import com.danaherdigital.che_hx.uom.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.uom.service.ILookUpService;
import com.danaherdigital.che_hx.uom.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;



/**
 * The Class LookUpController.
 */
@RestController

/** The Constant log. */
@Slf4j
@RequestMapping("/UOM/Lookup")
public class LookUpController {

	/** The I look up service. */
	@Autowired
	ILookUpService lookUpService;

	/**
	 * Gets the calc design data data.
	 *
	 * @param req the req
	 * @return the calc design data data
	 * @throws ChemtreatException the chemtreat exception
	 */
	@PostMapping("/api/v1/runLookUp")
	public ResponseEntity<List<ResponseDTO>> getCalcDesignDataData(@RequestBody LookUpReqDTO req) throws ChemtreatException {
		log.info("getCalcDesignDataData controller started->{}",req);
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getCalcDesignDataData");
		List<ResponseDTO> calcData = lookUpService.getLookUpData(req);
		log.info("getCalcDesignDataDatacontroller completed->{}",calcData);
		return new ResponseEntity<>(calcData,
				HttpStatus.OK);
	}
}
