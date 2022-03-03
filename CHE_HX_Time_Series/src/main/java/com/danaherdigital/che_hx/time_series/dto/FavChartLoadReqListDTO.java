package com.danaherdigital.che_hx.time_series.dto;

import java.util.Collections;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"ChartData"
})
@Getter
@Setter
public class FavChartLoadReqListDTO {
	@JsonProperty("ChartData")
	private List<FavoriteChartsReqDto> chartData = null;
}
