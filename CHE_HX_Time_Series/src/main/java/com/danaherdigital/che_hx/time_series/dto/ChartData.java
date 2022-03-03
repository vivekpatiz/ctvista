package com.danaherdigital.che_hx.time_series.dto;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"data",
"name",
"avgKpi",
"min",
"max"
})
@Getter
@Setter
@ToString
public class ChartData {

@JsonProperty("data")
public List<Data> data = null;
@JsonProperty("name")
public String name;
@JsonProperty("avgKpi")
public Double avgKpi;
@JsonProperty("min")
public Double min;
@JsonProperty("max")
public Double max;

}