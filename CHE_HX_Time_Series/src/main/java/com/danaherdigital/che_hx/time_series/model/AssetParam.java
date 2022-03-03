package com.danaherdigital.che_hx.time_series.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the dhrd_asset_params database table.
 *
 */
@Entity
@Table(name="dhrd_asset_params")
@Getter
@Setter

public class AssetParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="asset_id")
	private String assetId;

	@Column(name="calculation_master_id")
	private String calculationMasterId;

	private byte computed;
	@Column(name="customer_tag_name")
	private String customerTagName;

	@Column(name="data_type")
	private String dataType;

	private String descriptor;

	@Column(name="display_name")
	private String displayName;

	private String name;

	private byte status;

	private String type;

	@Column(name="unit_of_measure_id")
	private String unitOfMeasureId;


	private String value;


}