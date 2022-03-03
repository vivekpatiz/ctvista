package com.danaherdigital.che_hx.asset_management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.danaherdigital.che_hx.asset_management.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the asset_params database table.
 * 
 */
@Entity(name = "AssetParam")
@Table(name="dhrd_asset_params")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIgnoreProperties( value= {})
public class AssetParam extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

	@Column(name="name",length = 200)
	private String name;
	
	@Column(name="display_name",length = 200)
	private String displayName;
	

	@ManyToOne
	@JoinColumn(name="asset_id")
	private Asset asset;
	
	@Column(name="data_type",length = 100)
	private String dataType;
	
	
	@Column(name="status", columnDefinition = "TINYINT(1)")
	private Boolean status;	
	
	@Column(name="calculation_master_id")
	private String calculationMasterId;

	@Column(name="customer_tag_name",length = 100)
	private String customerTagName;

	@Column(name="descriptor",length = 400)
	private String descriptor;
	
	@Column(name="type",length = 45)
	private String type;

	@Column(name="unit_of_measure_id")
	private String unitOfMeasureId;

	private String value;
	
	@Column(name="computed", columnDefinition = "TINYINT(1)")
	private Boolean computed;	
	

}