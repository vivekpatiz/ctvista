package com.danaherdigital.che_hx.time_series.dto;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"name",
"display_name",
"assetParamName",
"min",
"max",
"data"
})
@Getter
@Setter
public class ChartDataArray {

@JsonProperty("name")
public String name;
@JsonProperty("display_name")
public String displayName;
@JsonProperty("assetParamName")
public String assetParamName;
@JsonProperty("data")
public List<Data> data = null;
@JsonProperty("min")
public Double min;
@JsonProperty("max")
public Double max;

}