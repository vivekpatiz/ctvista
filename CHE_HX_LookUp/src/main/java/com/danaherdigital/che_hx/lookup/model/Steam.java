package com.danaherdigital.che_hx.lookup.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Digits;

import lombok.Getter;


/**
 * The persistent class for the dhrd_steam_pressure_temp_master database table.
 *
 */
@Entity
@Table(name = "dhrd_steam_pressure_temp_master")
@Getter
public class Steam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pressure_in_hg")
	private double pressureInHg;

	@Column(name = "liquid_enthalpy_btu_lb")
	private double liquidEnthalpyBtuLb;

	@Column(name = "liquid_ht_enthalpy_btu_lb")
	private double enthalpy;

	@Column(name = "pressure_psia")
	private double pressurePsia;

	@Digits(integer = 3, fraction = 1)
	@Column(name = "sat_temp_deg_f")
	private double temp;

	@Column(name = "total_steam_btu_lb")
	private double totalSteam;

}