package org.fornever.base.model

import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
class BaseTable {

	@Id
	@GeneratedValue
	Long id;

	@CreatedBy
	@Column(updatable=false)
	String createdBy;

	@CreatedDate
	@Column(updatable=false)
	Date createdDate;

	@LastModifiedBy
	String lastModifiedBy;

	@LastModifiedDate
	Date lastModifiedDate;
}
