package com.danaherdigital.che_hx.lookup.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * Sets the calculated values.
 *
 * @param calculatedValues the calculated values
 */
@Setter

/**
 * Gets the calculated values.
 *
 * @return the calculated values
 */
@Getter
public class ResponseDTO {

	/** The req. */
	private RequestDTO req;

	/** The calculated values. */
	private Map<String, Double> calculatedValues;
}
