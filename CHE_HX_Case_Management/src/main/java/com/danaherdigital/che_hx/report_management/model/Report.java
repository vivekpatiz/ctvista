package com.danaherdigital.che_hx.report_management.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.danaherdigital.che_hx.report_management.utils.AuditModel;
import com.danaherdigital.che_hx.report_management.utils.HashMapConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the report database table.
 *
 */
@Entity(name = "Report")
@Table(name = "dhrd_reports")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIgnoreProperties(value = {})
public class Report  extends AuditModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;

	@Column(name = "min_load")
	private Double minLoad;

	@Column(name = "max_load")
	private Double maxLoad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "from_date")
	public Date fromDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "to_date")
	public Date toDate;

	@Column(name = "asset_id", length = 200)
	private String assetId;

	@Column(name = "asset_name", length = 200)
	private String assetName;

	@Column(name = "report_name", length = 200)
	private String reportName;





	 @Lob
	 @Column(name = "report_json")
	 @Convert(converter = HashMapConverter.class)
     private Map<String, Serializable> reportJson;

	@Column(name = "tenant_id")
	private Integer tenantId;

	@Column(name = "is_active", columnDefinition = "TINYINT(1)")
	private Boolean isActive;

	@Column(name = "facility_name", length = 200)
	private String facilityName;

	@Column(name = "system_name", length = 200)
	private String systemName;

}