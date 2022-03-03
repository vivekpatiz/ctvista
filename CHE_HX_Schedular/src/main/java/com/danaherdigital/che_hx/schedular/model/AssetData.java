package com.danaherdigital.che_hx.schedular.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;



/**
 * The persistent class for the dhrd_asset_data database table.
 *
 */
@Entity
@Table(name="dhrd_asset_data")
@Getter
@Setter

public class AssetData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

	@Column(name="dataTimestamp")
	private Long dataTimestamp;

	@Column(name="string_values")
	private String stringValues;

	@Column(name="float_values")
	private Double floatValues;

	@Column(name="asset_params_name")
	private String assetParamsName;

	@Column(name="asset_name")
	private String assetName;

	@Column(name="tenant_id")
	private Long tenantId;

	private byte computed;

	@Column(name="tag_name",length = 400)
	private String tagName;


}