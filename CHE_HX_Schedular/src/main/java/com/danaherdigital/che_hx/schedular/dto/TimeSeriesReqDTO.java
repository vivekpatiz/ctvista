package com.danaherdigital.che_hx.schedular.dto;

import java.util.List;

import com.danaherdigital.che_hx.schedular.model.Input;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"input",
"assetId",
"tenantId"
})
public class TimeSeriesReqDTO {

@JsonProperty("input")
public List<Input> input = null;
@JsonProperty("assetId")
private String assetId;
@JsonProperty("tenantId")
private Long tenantId;



}