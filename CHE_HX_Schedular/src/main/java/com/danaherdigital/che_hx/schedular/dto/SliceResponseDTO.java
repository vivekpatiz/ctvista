package com.danaherdigital.che_hx.schedular.dto;

import java.util.List;

import com.danaherdigital.che_hx.schedular.model.CalcJob;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class SliceResponseDTO {
	private List<CalcJob> jobs;
	private String status;
	private Long id;
	private Long tenantId;
	private String assetId;
	private boolean isRecordSame;


}
