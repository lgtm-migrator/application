package org.fornever.base.security.rbac

import javax.persistence.EntityManager

import org.fornever.base.annotations.API
import org.fornever.base.security.rbac.dao.AuthPrivilegeRepository
import org.fornever.base.security.rbac.dao.AuthRoleRepository
import org.fornever.base.security.rbac.dao.AuthUserRepository
import org.fornever.base.security.rbac.models.AuthPrivilege
import org.fornever.base.security.rbac.models.AuthRole
import org.fornever.base.security.rbac.models.AuthUser
import org.fornever.base.security.rbac.services.RBACUserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Configuration
@EnableWebSecurity
@EntityScan(basePackageClasses=RBACSecurityConfiguration.class)
@EnableJpaRepositories(basePackageClasses=RBACSecurityConfiguration.class)
@ComponentScan(basePackageClasses=RBACSecurityConfiguration.class)
@EnableGlobalMethodSecurity(prePostEnabled=true)
class RBACSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthUserRepository userRepository;

	@Autowired
	AuthRoleRepository roleRepository;

	@Autowired
	AuthPrivilegeRepository privilegeRepository;

	@Autowired
	RBACUserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	ApplicationContext ctx;

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@Value('${spring.security.user.name}')
	private String defaultUsername;

	@Value('${spring.security.user.password}')
	private String defaultPassword;

	@EventListener(ApplicationReadyEvent.class)
	def afterStartup() {
		if(userRepository.count() == 0 && this.defaultUsername) {
			// without user
			def defaultUser = new AuthUser(
					username:this.defaultUsername,
					password:passwordEncoder().encode(this.defaultPassword),
					roles:[
						new AuthRole(
						roleName: "ADMIN",
						privileges: [
							new AuthPrivilege(privilegeName: "ROLE_ADMIN")]
						)]
					)

			userRepository.save(defaultUser)
		}

		def apiPrivileges = requestMappingHandlerMapping.getHandlerMethods().entrySet().collect { new AuthPrivilege(privilegeName: "API_${it.key.toString()}") }

		apiPrivileges.add(new AuthPrivilege(privilegeName: "ROLE_API"))

		def roleAPI = new AuthRole(roleName: "ROLE_API", privileges: apiPrivileges.toSet())

		roleRepository.save(roleAPI)
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.authenticationProvider(authenticationProvider())
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable(); // enable embeded iframe
		http.csrf().disable().httpBasic().and().authorizeRequests().anyRequest().authenticated();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		String idForEncode = "bcrypt";
		Map encoders = new HashMap<>();
		encoders.put(idForEncode, new BCryptPasswordEncoder());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());
		return new DelegatingPasswordEncoder(idForEncode, encoders);
	}
}
