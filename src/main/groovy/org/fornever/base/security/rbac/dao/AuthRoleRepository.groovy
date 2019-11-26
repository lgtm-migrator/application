package org.fornever.base.security.rbac.dao

import org.fornever.base.security.rbac.models.AuthRole
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthRoleRepository extends PagingAndSortingRepository<AuthRole, Long> {
}
