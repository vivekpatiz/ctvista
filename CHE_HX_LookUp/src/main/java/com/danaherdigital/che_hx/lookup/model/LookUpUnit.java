package com.danaherdigital.che_hx.lookup.model;

import javax.persistence.*;


import lombok.Getter;


/**
 * The persistent class for the dhrd_lookup_ht_rt_penalty database table.
 *
 */
@Entity
@Table(name = "dhrd_lookup_ht_rt_penalty")
@Getter
public class LookUpUnit {

	@Column(name = "epri_hr_penalty")
	private double epriHrPenalty;

	@Column(name = "expected_heat_rate")
	private double expectedHeatRate;

	@Id
	@Column(name = "type_of_unit")
	private String typeOfUnit;

}