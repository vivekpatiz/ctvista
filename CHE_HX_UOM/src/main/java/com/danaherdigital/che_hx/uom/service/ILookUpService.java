package com.danaherdigital.che_hx.uom.service;

import java.util.List;

import com.danaherdigital.che_hx.uom.dto.LookUpReqDTO;
import com.danaherdigital.che_hx.uom.dto.ResponseDTO;
import com.danaherdigital.che_hx.uom.exceptionhandler.ChemtreatException;



/**
 * The Interface ILookUpService.
 */
public interface ILookUpService {

	/**
	 * Gets the look up data.
	 *
	 * @param req the req
	 * @return the look up data
	 * @throws ChemtreatException the chemtreat exception
	 */
	List<ResponseDTO> getLookUpData(LookUpReqDTO req) throws ChemtreatException;

}
