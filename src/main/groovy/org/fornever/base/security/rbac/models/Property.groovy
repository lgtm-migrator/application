package org.fornever.base.security.rbac.models

import javax.persistence.Column
import javax.persistence.Entity

import org.fornever.base.model.BaseTable

@Entity
class Property extends BaseTable {

	@Column(length=255)
	String key;
	
	@Column(length=255)
	String value;
	
	@Column(length=10240)
	String description;
	
}
