package com.danaherdigital.che_hx.uom.model;

import javax.persistence.*;


import lombok.Getter;


/**
 * The persistent class for the dhrd_hei_standards_ht_tx_coef database table.
 *
 */
@Entity
@Table(name = "dhrd_hei_standards_ht_tx_coef")
@Getter
public class HEIStdCoefficients {

	@Id
	private double dia;

	@Column(name = "ft_per_sec")
	private double ftPerSec;

	@Column(name = "ht_tx_coef")
	private double htTxCoef;

}