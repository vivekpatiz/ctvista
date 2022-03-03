package com.danaherdigital.che_hx.time_series.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.danaherdigital.che_hx.time_series.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the asset database table.
 *
 */
@Entity(name = "AssetData")
@Table(name = "dhrd_asset_data")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIgnoreProperties( value= {})
public class AssetData extends AuditModel {
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
	private Integer tenantId;

	private byte computed;


	@Column(name="tag_name",length = 400)
	private String tagName;

}