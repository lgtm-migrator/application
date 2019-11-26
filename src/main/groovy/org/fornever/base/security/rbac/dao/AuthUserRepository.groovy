package org.fornever.base.security.rbac.dao

import org.fornever.base.security.rbac.models.AuthUser
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthUserRepository extends PagingAndSortingRepository<AuthUser, Long> {


	AuthUser findByUsername(String username);
	
}
