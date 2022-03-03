package com.danaherdigital.che_hx.schedular.dto;

import java.util.List;

import com.danaherdigital.che_hx.schedular.model.Input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ApiResponseDTO {

	private List<Input> result;
	private String status;
	private String jobId;
	private String assetId;
	private String error;
	private Long id;
	private Long tenantId;




}
