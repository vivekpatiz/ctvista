package com.danaherdigital.che_hx.time_series.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
public class AssetDataDTO {


	private String id;

	private Date epochDate;

	private Double floatValue;

	private String tagName;

	private Long dataTimestamp;

	@JsonCreator
	 public AssetDataDTO(String id,@JsonProperty("date")  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "UTC") Date epochDate,
			 Double floatValue,String tagName,Long dataTimestamp) {
			super();
			this.id = id;
			this.epochDate = epochDate;
			this.floatValue = floatValue;
			this.tagName = tagName;
			this.dataTimestamp = dataTimestamp;
		}




}