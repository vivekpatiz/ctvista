package com.danaherdigital.che_hx.uom.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UOMRespDTO {

@JsonProperty("result")
private List<UOMResult> list;

}
