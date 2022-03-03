package com.danaherdigital.che_hx.uom.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the dhrd_asset database table.
 *
 */
@Entity
@Table(name="dhrd_unit_of_measure")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UnitOfMeasure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="display_name")
	private String displayName;

	@Column(name="name")
	private String name;

	@Column(name="symbol")
	private String symbol;

	@Column(name="group")
	private String group;

	@Column(name="dimension")
	private String dimension;
}