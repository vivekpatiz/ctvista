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
"tenantid",
"assetId",
"source",
"group_name"
})
@Getter
@Setter
public class FavoriteChartsReqDto {
	@NotEmpty
	@JsonProperty("data")
	public List<@Valid FavChartData> data = Collections.emptyList();

	@NotNull
	@JsonProperty("tenantid")
	public Integer tenantid;
	@NotBlank
	@JsonProperty("assetId")
	public String assetId;
	@NotBlank
	@JsonProperty("source")
	public String source;
	@NotBlank
	@JsonProperty("group_name")
	public String group_name;
}
