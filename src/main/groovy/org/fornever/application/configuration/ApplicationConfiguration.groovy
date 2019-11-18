package org.fornever.application.configuration

import org.fornever.base.cache.SimpleCacheConfiguration
import org.fornever.base.integration.AllowUserAccessH2UIConfiguration
import org.fornever.base.integration.SwaggerIntegrationConfiguration
import org.fornever.base.security.JPAAuditConfiguration
import org.fornever.example.ExampleConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import([
	SimpleCacheConfiguration.class, 		// in-memory cache
	JPAAuditConfiguration.class, 			// JPA audit
	ExampleConfiguration.class,				// import example code
	AllowUserAccessH2UIConfiguration.class, // H2 UI Console
	SwaggerIntegrationConfiguration.class 	// swagger 
])
class ApplicationConfiguration {
}
