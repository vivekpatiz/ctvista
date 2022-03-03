package com.danaherdigital.che_hx.uom.service;

import java.util.List;

import com.danaherdigital.che_hx.uom.dto.UOMReqDTO;
import com.danaherdigital.che_hx.uom.dto.UOMRespDTO;
import com.danaherdigital.che_hx.uom.dto.UOMdataDTO;
import com.danaherdigital.che_hx.uom.exceptionhandler.ChemtreatException;

/**
 * The Interface IUnitOfMeasureService.
 */
public interface IUnitOfMeasureService {

	/**
	 * Gets the UOM conversion data.
	 *
	 * @param req the req
	 * @return the UOM conversion data
	 * @throws ChemtreatException the chemtreat exception
	 */
	UOMRespDTO getUOMConversionData(UOMReqDTO req) throws ChemtreatException;

	List<UOMdataDTO> getUOMList();

}
