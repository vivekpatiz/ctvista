package com.danaherdigital.che_hx.schedular.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"input",
"engine",
"function"
})

@Setter
public class CalcEngineDTO {

@JsonProperty("input")
public List<Map<String,Object>> input = null;
@JsonProperty("engine")
public String engine;
@JsonProperty("function")
public String function;

}