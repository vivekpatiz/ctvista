package com.danaherdigital.che_hx.lookup.model;

import javax.persistence.*;


import lombok.Getter;


/**
 * The persistent class for the dhrd_lookup_co2 database table.
 *
 */
@Entity
@Table(name = "dhrd_lookup_co2")
@Getter
public class LookUpFuel {



	@Column(name = "co2_lb_per_10_pw_6_btu")
	private int co2LbPer10Pw6Btu;

	@Id
	private String fuel;

	@Column(name = "fuel_cost")
	private double fuelCost;

	@Column(name = "fuel_cost_per_10_pw_6_btu")
	private double fuelCostPer10Pw6Btu;


}