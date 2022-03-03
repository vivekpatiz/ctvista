package com.danaherdigital.che_hx.asset_management.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(value = {})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AssetIngestionLogDTO {

	private Date timestamp;

	private String condenserName;

	private String ingestionStatus;
	
	private String fileName;

	private Double rowCount;
	
	private Date ingestionEndTime;

	private Date calcStartTime;

	private Date calcFailedTime;

	private Date calcEndTime;

	@JsonCreator
	public AssetIngestionLogDTO(
			@JsonProperty("timestamp") @JsonFormat(pattern = "dd,MMM yyyy HH:mm:ss", timezone = "UTC") Date timestamp,
			String condenserName, String ingestionStatus,String fileName, Double rowCount,@JsonProperty("ingestionEndTime")
			@JsonFormat(pattern = "dd,MMM yyyy HH:mm:ss", timezone = "UTC")Date ingestionEndTime,
			@JsonProperty("calcStartTime") @JsonFormat(pattern = "dd,MMM yyyy HH:mm:ss", timezone = "UTC") Date calcStartTime,
			@JsonProperty("calcFailedTime") @JsonFormat(pattern = "dd,MMM yyyy HH:mm:ss", timezone = "UTC")Date calcFailedTime,
			@JsonProperty("calcEndTime") @JsonFormat(pattern = "dd,MMM yyyy HH:mm:ss", timezone = "UTC")Date calcEndTime) {
		super();

		this.timestamp = timestamp;
		this.condenserName = condenserName;
		this.ingestionStatus = ingestionStatus;
		this.fileName = fileName;
		this.rowCount = rowCount;
		this.ingestionEndTime = ingestionEndTime;
		this.calcStartTime = calcStartTime;
		this.calcFailedTime = calcFailedTime;
		this.calcEndTime = calcEndTime;

	}

}