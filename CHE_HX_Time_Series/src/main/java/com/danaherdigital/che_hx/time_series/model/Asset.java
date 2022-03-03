package com.danaherdigital.che_hx.time_series.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the dhrd_asset database table.
 *
 */
@Entity
@Table(name="dhrd_asset")
@Getter
@Setter
public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="asset_type_id")
	private String assetTypeId;

	@Column(name="calculation_master_id")
	private String calculationMasterId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private String createdBy;

	private String name;

	private byte status;

	@Column(name="tenant_id")
	private int tenantId;





}