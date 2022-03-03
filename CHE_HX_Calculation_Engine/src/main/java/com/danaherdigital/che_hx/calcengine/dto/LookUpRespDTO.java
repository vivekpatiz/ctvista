package com.danaherdigital.che_hx.calcengine.dto;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"req",
"calculatedValues"
})
@Getter
@Setter
public class LookUpRespDTO {



/** The req. */
@JsonProperty("req")
private RequestDTO req;

/** The calculated values. */
@JsonProperty("calculatedValues")
private Map<String, Double> calculatedValues;

}