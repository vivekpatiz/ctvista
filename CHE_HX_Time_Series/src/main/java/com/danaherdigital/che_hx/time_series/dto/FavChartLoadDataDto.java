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
public class FavChartLoadDataDto {

	
	
	private String inputParams;
	private String id;
	private int tenantId;
	private String assetTypeId;
	private String source;
	private String group_name;

	@JsonCreator
	 public FavChartLoadDataDto(String id,int tenantid,String assetId,String data,String source,String group_name) {
		super();
		this.inputParams = data;
		this.id=id;
		this.tenantId=tenantid;
		this.assetTypeId=assetId;
		this.source=source;
		this.group_name=group_name;

	}
}
