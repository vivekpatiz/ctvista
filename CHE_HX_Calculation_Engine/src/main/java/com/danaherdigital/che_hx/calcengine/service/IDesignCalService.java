package com.danaherdigital.che_hx.calcengine.service;


import com.danaherdigital.che_hx.calcengine.dto.CustomCalcReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.DesignCalReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.DesignCalRespDTO;
import com.danaherdigital.che_hx.calcengine.exceptionhandler.ChemtreatException;

/**
 * The Interface IDesignCalService.
 */
public interface IDesignCalService {


	/**
	 * Gets the calc design data.
	 *
	 * @param req the req
	 * @return the calc design data
	 * @throws ChemtreatException
	 */
	DesignCalRespDTO getCalcDesignData(DesignCalReqDTO req) throws ChemtreatException;

	CustomCalcReqDTO getCustomCalcDesignDataData(CustomCalcReqDTO req) throws ChemtreatException;

}
