//Util package
package com.danaherdigital.che_hx.report_management.utils;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Setter;

@MappedSuperclass
@Setter
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties( value= {})
public abstract class AuditModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	public Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	public Date updatedAt;
	
    @Column(name = "created_by",nullable = true)
    public String createdBy;


    @LastModifiedBy
    @Column(name = "updated_by",nullable = true)
    public String updatedBy;
}
