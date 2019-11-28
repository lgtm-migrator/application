package org.fornever.application.configuration;

import org.fornever.base.cache.SimpleCacheConfiguration;
import org.fornever.base.integration.SwaggerIntegrationConfiguration;
import org.fornever.base.meta.autoapi.AutoAPIConfiguration;
import org.fornever.base.model.BusinessModelSupportConfiguration;
import org.fornever.base.security.JPAAuditConfiguration;
import org.fornever.base.security.rbac.RBACSecurityConfiguration;
import org.fornever.example.ExampleConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
	SimpleCacheConfiguration.class, 			// in-memory cache
	ExampleConfiguration.class,					// import example code
	JPAAuditConfiguration.class, 				// create/update user name
	SwaggerIntegrationConfiguration.class, 		// swagger doc
	RBACSecurityConfiguration.class,
	AutoAPIConfiguration.class,
	BusinessModelSupportConfiguration.class
})
public class ApplicationConfiguration {
	
}
