package com.danaherdigital.che_hx.schedular.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the dhrd_unit_of_measure database table.
 *
 */
@Entity
@Table(name="dhrd_unit_of_measure")
@Getter
@Setter
public class UnitOfMeasure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String dimension;

	@Column(name="display_name")
	private String displayName;

	private String group;

	private String name;

	private String symbol;


}