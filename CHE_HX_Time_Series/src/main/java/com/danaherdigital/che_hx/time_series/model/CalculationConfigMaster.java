package com.danaherdigital.che_hx.time_series.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the dhrd_calculation_config_master database table.
 *
 */
@Entity
@Table(name="dhrd_calculation_config_master")
@Getter
@Setter
public class CalculationConfigMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="calculation_name")
	private String calculationName;

	@Column(name="asset_type_id")
	private String assetTypeId;

	private String engine;

	@Column(name="input_params")
	private String inputParams;

	@Column(name="output_params")
	private String outputParams;

	private byte status;

	private String type;


}