package com.danaherdigital.che_hx.asset_management.dto;

import java.util.Date;

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
public class AssetDTO {

	 
	private String id;

	private Date createdAt;
	 
	 private Date updatedAt;
	 
	 private String createdBy;
	 
	 private String updatedBy;
	 
	 private String name;
	 
	 private Boolean status;
	 
	 private String facilityName;
	 
	 private String accountName;
	 
	 private String systemName;
	
	
	 @JsonCreator
	 public AssetDTO(String id,@JsonProperty("createdAt")  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "UTC") Date createdAt,@JsonProperty("updatedAt")  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "UTC") Date updatedAt, String createdBy, String updatedBy, String name, Boolean status,
				String facilityName, String accountName,String systemName) {
			super();
			this.id = id;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.createdBy = createdBy;
			this.updatedBy = updatedBy;
			this.name = name;
			this.status = status;
			this.facilityName = facilityName;
			this.accountName = accountName;
			this.systemName = systemName;
			
			
		}
	 
	 

	 
}