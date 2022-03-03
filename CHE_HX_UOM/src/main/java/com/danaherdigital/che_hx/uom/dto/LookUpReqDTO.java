package com.danaherdigital.che_hx.uom.dto;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class LookUpReqDTO.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"input"
})

/**
 * Gets the input.
 *
 * @return the input
 */
@Getter

/**
 * Sets the input.
 *
 * @param input the new input
 */
@Setter
public class LookUpReqDTO {

/** The input. */
@JsonProperty("input")
public List<RequestDTO> input = null;

}
