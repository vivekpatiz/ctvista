package com.danaherdigital.che_hx.lookup.model;

import javax.persistence.*;


import lombok.Getter;


/**
 * The persistent class for the dhrd_hei_standard_mat_gauge_cor database table.
 *
 */
@Entity
@Table(name = "dhrd_hei_standard_mat_gauge_cor")

@Getter
public class HEIStdCFactor {



	/** The bwg. */
	@Id
	private int bwg;

	/** The correction factor. */
	@Column(name = "correction_factor")
	private double correctionFactor;

	/** The material name. */
	@Column(name = "material_name")
	private String materialName;

}