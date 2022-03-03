package com.danaherdigital.che_hx.time_series.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the dhrd_calculation_config_master database table.
 *
 */
@Entity
@Table(name="dhrd_favourite_charts")
@Getter
@Setter
public class FavoriteChartsModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;

	@Column(name="tenant_id")
	private int tenantId;

	@Column(name="asset_type_id")
	private String assetTypeId;


	@Column(name="input_params")
	private String inputParams;

	@Column(name="source")
	private String source;

	@Column(name="group_name")
	private String group_name;

	//@Column(name="min_load_range")
	//private int min_load_range;
	
	//@Column(name="max_load_range")
	//private int max_load_range;

}