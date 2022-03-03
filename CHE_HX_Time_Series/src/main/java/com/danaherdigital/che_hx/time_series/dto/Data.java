package com.danaherdigital.che_hx.time_series.dto;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"date",
"avgValue",
"load",
"value"
})
@Getter
@Setter
public class Data{

@JsonProperty("date")
public Date date;

@JsonProperty("load")
public Double load;
@JsonProperty("value")
public Double value;

}