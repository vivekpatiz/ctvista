package com.danaherdigital.che_hx.time_series.dto;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"paramName",
"assetParamId"
})
@Getter
@Setter
public class FavChartData {

@NotBlank
@JsonProperty("paramName")
public String paramName;

@NotBlank
@JsonProperty("assetParamId")
public String assetParamId;

}