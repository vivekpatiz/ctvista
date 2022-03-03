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
public class AssetLoadDataDTO {


	private Long dataTimestamp;

	private Double floatValues;

	@JsonCreator
	 public AssetLoadDataDTO(Long dataTimestamp,Double floatValues) {
			super();
			this.dataTimestamp = dataTimestamp;
			this.floatValues = floatValues;

		}

}