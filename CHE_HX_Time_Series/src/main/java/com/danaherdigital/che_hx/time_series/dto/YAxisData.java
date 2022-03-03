package com.danaherdigital.che_hx.time_series.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"name",
"min",
"max",
"assetParamName"
})
@Getter
@Setter
public class YAxisData {

@NotBlank
@JsonProperty("name")
public String name;
@NotNull
@JsonProperty("min")
public Double min;
@NotNull
@JsonProperty("max")
public Double max;
@NotBlank
@JsonProperty("assetParamName")
public String paramId;

}