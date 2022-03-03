package com.danaherdigital.che_hx.schedular.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * The persistent class for the dhrd_asset_data_inflow_log database table.
 *
 */
@Entity
@Table(name="dhrd_asset_data_inflow_log")
@Getter
@Setter
public class AssetDataLog implements Serializable {
	private static final long serialVersionUID = 1L;





	@Id
	private Long id;

	@Column(name="asset_name")
	private String assetName;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private String endDate;

	@Column(name="file_name")
	private String fileName;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private String startDate;



	@Column(name="tenant_id")
	private Long tenantId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Column(name="workflow_status")
	private String workFlowStatus;

	@Column(name="row_count")
	private Long rowCount;

	@Column(name="ingestion_end_time")
	private Date ingestionEndTime;

	@Column(name="calc_start_time")
	private Date calcStartTime;

	@Column(name="calc_fail_time")
	private Date calcFailedTime;

	@Column(name="calc_end_time")
	private Date calcEndTime;




}