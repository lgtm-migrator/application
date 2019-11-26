package org.fornever.base.security.rbac.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

import org.fornever.base.model.BaseTable
import org.springframework.security.core.GrantedAuthority

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@Entity
@AutoClone
@ToString
@EqualsAndHashCode
class AuthPrivilege extends BaseTable implements GrantedAuthority {

	@Column(unique=true, nullable=false, length=255)
	String privilegeName;

	@Column(length=10240)
	String description;
	
	String type;

	@Override
	public String getAuthority() {
		return this.privilegeName;
	}
}
