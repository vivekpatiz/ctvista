package com.danaherdigital.che_hx.schedular.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * The persistent class for the dhrd_job database table.
 *
 */
@Entity
@Table(name="dhrd_job")
@Getter
@Setter
public class CalcJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;

	@Column(name="inflow_log_id")
	private Long inflowId;


	private String engine;

	@Column(name="asset_name")
	private String assetName;

	@Column(name="from_date")
	private Long fromDate;

	private String method;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="job_date")
	private Date jobDate;

	private String status;

	@Column(name="to_date")
	private Long toDate;

	private Long tenantId;


	public CalcJob()
	{
		this.jobDate=new Date();
	}


}