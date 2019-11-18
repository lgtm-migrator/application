package org.fornever.base.model

import javax.persistence.EntityListeners
import javax.persistence.Id
import javax.persistence.MappedSuperclass

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
class BaseBussinessObject extends BaseTable {
	
	void destroy() {
	}

	void archive() {
	}
}
