package com.danaherdigital.che_hx.time_series.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	 public AssetDTOLoadInt(int minVal,int maxVal) {
			super();
			this.minVal = minVal;		
			this.maxVal = maxVal;
		
		}
	 
}