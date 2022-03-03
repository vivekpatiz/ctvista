package com.danaherdigital.che_hx.schedular.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetDataUOMDTO {
	private String id;

	private Long dataTimeStamp;

	private Object floatValues;

	private String name;

	private Long uom;
}
