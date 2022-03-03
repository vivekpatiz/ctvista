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
@Entity(name = "UserPreferences")
@Table(name = "dhrd_user_preferences")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIgnoreProperties(value = {})
public class UserPreferences extends AuditModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;

	@Column(name = "minimum_load")
	private Double minimumLoad;

	@Column(name = "maximum_load")
	private Double maximumLoad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	public Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	public Date endDate;

	@Column(name = "asset_id", length = 200)
	private String assetId;

	@Column(name = "uasset_name", length = 200)
	private String uassetName;

	@Lob
	@Column(name = "preference_json")
	@Convert(converter = HashMapConverter.class)
    private  Map<String, Serializable> preferenceJson;

	@Column(name = "utenant_id")
	private Integer utenantId;

	@Column(name = "user_name", length = 200)
	private String userName;



}