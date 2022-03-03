package com.danaherdigital.che_hx.uom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UOMReqData {

	private String id;
	private String fromUom;
	private String toUom;
	private double value;
}
