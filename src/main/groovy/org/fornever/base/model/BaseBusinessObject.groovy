package org.fornever.base.model

import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Transient

import org.fornever.base.action.BaseBusinessAction
import org.fornever.base.action.BaseBusinessActionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.stereotype.Component

@MappedSuperclass
abstract class BaseBusinessObject<S> extends BaseTable {

	@Column
	@Enumerated(EnumType.STRING)
	S state;

}
