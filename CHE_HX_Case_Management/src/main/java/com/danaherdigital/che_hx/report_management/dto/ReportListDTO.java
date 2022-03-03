package com.danaherdigital.che_hx.report_management.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIgnoreProperties(value = {})
public class ReportListDTO {

	private String id;	

	private Date fromDate;

	private Date toDate;

	private String reportName;
	
	private Integer tenantId;
	
	private String assetName;


	private Boolean isActive;

	private String facilityName;

	private String systemName;

	private String createdBy;

	private Date updatedAt;

}