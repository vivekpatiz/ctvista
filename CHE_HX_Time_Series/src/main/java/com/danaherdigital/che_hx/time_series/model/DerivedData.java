package com.danaherdigital.che_hx.time_series.model;

import java.util.List;
import java.util.Map;

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
public class DerivedData {

@JsonProperty("input")
public List<Map<String,Object>> input = null;
@JsonProperty("assetId")
public String assetId;
@JsonProperty("tenantId")
private Integer tenantId;

}
