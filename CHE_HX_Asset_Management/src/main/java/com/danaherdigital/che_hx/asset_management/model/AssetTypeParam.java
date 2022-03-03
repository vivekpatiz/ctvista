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
 * The persistent class for the asset_type_params database table.
 * 
 */
@Entity(name = "AssetTypeParam")
@Table(name="dhrd_asset_type_params")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIgnoreProperties( value= {})
public class AssetTypeParam extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;


	@Column(name="calculation_master_id")
	private String calculationMasterId;

	

	@Column(name="name",length = 200)
	private String name;
	
	@Column(name="display_name",length = 200)
	private String displayName;

	@Column(name="data_type",length = 100)
	private String dataType;
	
	
	@Column(name="status", columnDefinition = "TINYINT(1)")
	private Boolean status;	
	


	@Column(name="unit_of_measure_id")
	private String unitOfMeasureId;

	private String value;

	//bi-directional many-to-one association to AssetType
	@ManyToOne
	@JoinColumn(name="asset_type_id")
	private AssetType assetType;
	
	@Column(name="computed", columnDefinition = "TINYINT(1)")
	private Boolean computed;	

	

}