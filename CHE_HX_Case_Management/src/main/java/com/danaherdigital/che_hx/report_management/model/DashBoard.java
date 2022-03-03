package com.danaherdigital.che_hx.report_management.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="DashBoard")
@Table(name="dhrd_dashboard_config")
@Getter
@Setter
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonStringType.class)

public class DashBoard {

	@Id
	private Long id;

	private String type;

	private String name;

	@Column(name = "asset_id")
	private String assetId;

	@Column(name = "asset_type")
	private String assetType;

	@Column(name = "config_json",columnDefinition = "jsonb")
	@Type(type = "jsonb")
    private Map<String ,Object> configJson;

	@Column(name = "tenant_id")
	private String tenantId;
}
