package org.fornever.base.security.rbac.services

import javax.transaction.Transactional

import org.fornever.base.security.rbac.dao.AuthUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Transactional
class RBACUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AuthUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
	}
}
