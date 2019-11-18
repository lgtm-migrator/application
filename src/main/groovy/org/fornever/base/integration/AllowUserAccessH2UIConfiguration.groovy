package org.fornever.base.integration

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@ConditionalOnProperty(
	value="spring.h2.console.enabled",
	havingValue = "true",
	matchIfMissing = false
)
class AllowUserAccessH2UIConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/private/h2_console/**").permitAll();
		http.csrf().ignoringAntMatchers("/private/h2-console/**");
		http.headers().frameOptions().sameOrigin();
	}
	
}
