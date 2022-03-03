package com.danaherdigital.che_hx.time_series.dto;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"data",
"fromdate",
"todate",
"minLoadRange",
"maxLoadRange",
"tenantid",
"assetId"
})
@Getter
@Setter
public class YAxisReqDTO {

@NotEmpty
@JsonProperty("data")
public List<@Valid YAxisData> data = Collections.emptyList();
//@JsonFormat(pattern="yyyy-MM-dd")
@NotBlank
@JsonProperty("fromdate")
public String fromdate;

//@JsonFormat(pattern="yyyy-MM-dd")
@NotBlank
@JsonProperty("todate")
public String todate;
@NotNull
@JsonProperty("minLoadRange")
public Integer minLoadRange;
@NotNull
@JsonProperty("maxLoadRange")
public Integer maxLoadRange;
@NotNull
@JsonProperty("tenantid")
public Integer tenantid;
@NotBlank
@JsonProperty("assetId")
public String assetId;

}