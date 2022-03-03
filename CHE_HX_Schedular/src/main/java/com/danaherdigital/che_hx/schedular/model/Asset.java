package com.danaherdigital.che_hx.schedular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.danaherdigital.che_hx.schedular.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Entity(name = "Asset")
@Table(name = "dhrd_asset")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIgnoreProperties( value= {})
public class Asset extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

	@Column(name="calculation_master_id")
	private String calculationMasterId;

	private String name;

	@Column(name="status", columnDefinition = "TINYINT(1)")
	private Boolean status;

	@Column(name="asset_type_id")
	private String assetTypeId;

	@ManyToOne
	@JoinColumn(name="parent_asset_id")
	@JsonBackReference
	private Asset parentAssetId;



}