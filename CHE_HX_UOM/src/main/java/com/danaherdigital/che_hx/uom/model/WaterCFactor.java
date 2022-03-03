package com.danaherdigital.che_hx.uom.model;

import javax.persistence.*;


import lombok.Getter;


/**
 * The persistent class for the dhrd_water_correction_factor_master database table.
 *
 */
@Entity
@Table(name = "dhrd_water_correction_factor_master")
@Getter
public class WaterCFactor {

	@Id
	@Column(name = "inlet_water_temp")
	private int inletWaterTemp;

	@Column(name = "f_w")
	private double fW;

}