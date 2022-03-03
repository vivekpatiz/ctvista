package com.danaherdigital.che_hx.lookup.service;

import java.util.List;

import com.danaherdigital.che_hx.lookup.dto.LookUpReqDTO;
import com.danaherdigital.che_hx.lookup.dto.ResponseDTO;
import com.danaherdigital.che_hx.lookup.exceptionhandler.ChemtreatException;

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
