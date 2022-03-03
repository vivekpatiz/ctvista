package com.danaherdigital.che_hx.uom.model;

import javax.persistence.*;


import lombok.Getter;


/**
 * The persistent class for the dhrd_tube_data_master database table.
 *
 */
@Entity
@Table(name = "dhrd_tube_data_master")
@Getter
public class TubeData {

	@Id
	private int bwg;

	@Column(name = "tube_id_inches")
	private double tubeIdInches;

	@Column(name = "tube_od_inches")
	private double tubeOdInches;

	@Column(name = "wall_thickness_inches")
	private double wallThicknessInches;

}