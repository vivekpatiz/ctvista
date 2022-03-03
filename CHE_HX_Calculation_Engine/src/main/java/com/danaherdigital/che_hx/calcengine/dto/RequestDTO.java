package com.danaherdigital.che_hx.calcengine.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class RequestDTO.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * Gets the value 2.
 *
 * @return the value 2
 */
@Getter

/**
 * Sets the value 2.
 *
 * @param value2 the new value 2
 */
@Setter
public class RequestDTO {

/** The table name. */
@JsonProperty("tableName")
public String tableName;

/** The from. */
@JsonProperty("from")
public String from;

/** The value. */
@JsonProperty("value")
public Object value;

/** The value 2. */
@JsonProperty("value2")
public Object value2;

}