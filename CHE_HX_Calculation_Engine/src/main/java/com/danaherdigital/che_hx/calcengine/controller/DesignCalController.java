package com.danaherdigital.che_hx.calcengine.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.calcengine.dto.CustomCalcReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.DesignCalReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.DesignCalRespDTO;
import com.danaherdigital.che_hx.calcengine.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.calcengine.service.IDesignCalService;
import com.danaherdigital.che_hx.calcengine.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class DesignCalController.
 */
@RestController

/** The Constant log. */
@Slf4j
public class DesignCalController {

	/** The I design cal service. */
	@Autowired
	IDesignCalService designCalService;


	/**
	 * Gets the calc design data data.
	 *
	 * @return the calc design data data
	 * @throws ChemtreatException
	 */
	@PostMapping("/api/v1/runDesignCalculation")
	public ResponseEntity<DesignCalRespDTO> getCalcDesignDataData(@RequestBody DesignCalReqDTO req) throws ChemtreatException {
		log.info("Get getCalcData getCalcDesignDataData started");
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getCalcDesignDataData");
		DesignCalRespDTO calcData = designCalService.getCalcDesignData(req);

		return new ResponseEntity<>(calcData,
				HttpStatus.OK);
	}


	/**
	 * Gets the calc design data data.
	 *
	 * @return the calc design data data
	 * @throws ChemtreatException
	 */
	@PostMapping("/api/v1/runCustomDesignCalculation")
	public ResponseEntity<CustomCalcReqDTO> getCustomCalcDesignDataData(@RequestBody CustomCalcReqDTO req) throws ChemtreatException {
		log.info("Get getCustomCalcDesignDataData started");
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getCustomCalcDesignDataData");
		CustomCalcReqDTO customResDTO = designCalService.getCustomCalcDesignDataData(req);

		return new ResponseEntity<>(customResDTO,
				HttpStatus.OK);
	}

}
