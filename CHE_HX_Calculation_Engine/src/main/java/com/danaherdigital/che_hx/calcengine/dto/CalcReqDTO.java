package com.danaherdigital.che_hx.calcengine.dto;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.Setter;

/**
 * The Class CalcReqDTO.
 */

/**
 * Instantiates a new calc req DTO.
 */
@Getter
@Setter
public class CalcReqDTO {

/** The input. */
@NotNull
private List<Map<String,Object>> input;

/** The engine. */
private String engine;

/** The function. */
@NotNull
private String function;

}