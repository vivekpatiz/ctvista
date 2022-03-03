package com.danaherdigital.che_hx.calcengine.dto;

import java.util.Map;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomCalcReqDTO {

	@NotNull
	private Map<String,Object> input;
}
