package org.fornever.base.security.rbac.dao

import org.fornever.base.security.rbac.models.AuthPrivilege
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthPrivilegeRepository extends PagingAndSortingRepository<AuthPrivilege, Long> {
}
