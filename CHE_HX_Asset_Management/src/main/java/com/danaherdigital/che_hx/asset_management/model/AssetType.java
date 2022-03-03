package com.danaherdigital.che_hx.asset_management.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.GenericGenerator;

import com.danaherdigital.che_hx.asset_management.service.OnCreate;
import com.danaherdigital.che_hx.asset_management.service.OnUpdate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the asset_type database table.
 *
 */
@Entity(name = "AssetType")
@Table(name="dhrd_asset_type")

/**
 * Gets the name.
 *
 * @return the name
 */
@Getter

/**
 * Sets the name.
 *
 * @param name the new name
 */
@Setter

/**
 * Instantiates a new asset type.
 */
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AssetType implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Null(groups = OnCreate.class)
	@NotNull(groups = OnUpdate.class)
    private String id;

	/** The name. */
	@NotBlank
	private String name;

	@NotBlank(groups = OnCreate.class)
	@Column(name = "parent_asset_type_id")
	private String parentId;

	/*
	 * //bi-directional many-to-one association to AssetTypeParam
	 *
	 * @OneToMany(mappedBy="assetType") private List<AssetTypeParam>
	 * assetTypeParams;
	 *
	 * //bi-directional many-to-one association to Asset
	 *
	 * @OneToMany(mappedBy="assetType") private List<Asset> assets;
	 */



}
