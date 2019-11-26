package org.fornever.base.security.rbac.models

import static javax.persistence.CascadeType.ALL
import static javax.persistence.CascadeType.PERSIST
import static javax.persistence.CascadeType.REFRESH
import static javax.persistence.FetchType.EAGER

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

import org.fornever.base.model.BaseTable
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import groovy.transform.AutoClone
import groovy.transform.Immutable

@Entity
@AutoClone
class AuthUser extends BaseTable implements UserDetails {

	@Column(unique=true, length=255, nullable=false)
	String username;

	@Column(length=255)
	String displayName;

	@Column(length=255, nullable=false)
	String password;

	/**
	 * name, address, age ....
	 */
	@OneToMany(fetch=EAGER,cascade=ALL)
	Set<Property> properties;

	@ManyToMany(fetch=EAGER, cascade=ALL)
	Set<AuthRole> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		def set = new HashSet<AuthPrivilege>()
		if(this.getRoles()) {
			for(role in this.getRoles()) {
				if(role.getPrivileges()) {
					set.addAll(role.getPrivileges())
				}
			}
		}
		return set;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	Boolean expired = false;

	Boolean locked = false;

	Boolean enabled = true;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !this.expired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
}
