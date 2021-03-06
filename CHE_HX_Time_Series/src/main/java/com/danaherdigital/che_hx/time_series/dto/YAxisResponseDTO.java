package com.danaherdigital.che_hx.time_series.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"timestamp",
"data"
})
@Getter
@Setter
public class YAxisResponseDTO {

@JsonProperty("chart_data")
public List<ChartDataArray> chartData = null;


}