package org.fornever.base.security.rbac.models

import static javax.persistence.CascadeType.ALL
import static javax.persistence.CascadeType.DETACH
import static javax.persistence.CascadeType.MERGE
import static javax.persistence.CascadeType.PERSIST
import static javax.persistence.CascadeType.REFRESH
import static javax.persistence.FetchType.EAGER

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

import org.fornever.base.model.BaseTable

import groovy.transform.AutoClone

@Entity
@AutoClone
class AuthRole extends BaseTable {

	@Column(unique=true, length=255, nullable=false)
	String roleName;

	@Column(length=10240, nullable=true)
	String description;

	@ManyToMany(fetch=EAGER, cascade=[MERGE, PERSIST, REFRESH, DETACH])
	Set<AuthPrivilege> privileges;
}
