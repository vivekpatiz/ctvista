package com.danaherdigital.che_hx.asset_management.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@JsonIgnoreProperties(value = {})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
public class AssetDTOLoadInt {

	 
	private int minVal;	
	
	private int maxVal;	 
	
	@JsonCreator
	 public AssetDTOLoadInt(@JsonProperty("minVal") final int minVal,@JsonProperty("maxVal") final int maxVal) {
			super();
			this.minVal = minVal;		
			this.maxVal = maxVal;
		
		}
	 
}