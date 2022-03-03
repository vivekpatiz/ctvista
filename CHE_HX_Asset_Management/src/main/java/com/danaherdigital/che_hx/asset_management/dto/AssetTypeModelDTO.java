package com.danaherdigital.che_hx.asset_management.dto;

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
public class AssetTypeModelDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    private String id;

	/** The name. */

    @NotBlank
	private String name;

	@Column(name = "parent_asset_type_id")
	private String parentId;



}
