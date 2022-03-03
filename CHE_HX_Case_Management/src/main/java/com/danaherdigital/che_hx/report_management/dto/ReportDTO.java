package com.danaherdigital.che_hx.report_management.dto;

import java.util.Date;
import java.util.Map;

import javax.persistence.Convert;


import com.danaherdigital.che_hx.report_management.utils.HashMapConverter;
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
public class ReportDTO {

	private String id;

	private Double minLoad;

	private Double maxLoad;

	private Date fromDate;

	private Date toDate;

	private String assetId;

	private String assetName;

	private String reportName;

	@Convert(converter = HashMapConverter.class)
	private Map<String, Object> reportJson;

	private Integer tenantId;

	private Boolean isActive;

	private String facilityName;

	private String systemName;

	private String createdBy;

	private String updatedBy;

}