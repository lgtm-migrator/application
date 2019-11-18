package org.fornever.base.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class JPAAuditConfiguration {
	
	@Bean
	public AuditorAware<String> auditorProvider() {

		return { -> SecurityContextHolder.getContext().getAuthentication().getName() }
	}
}
