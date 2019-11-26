package org.fornever.application.configuration;

import org.fornever.base.cache.SimpleCacheConfiguration;
import org.fornever.base.integration.SwaggerIntegrationConfiguration;
import org.fornever.base.model.BusinessObjectFactoryService;
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
	BusinessObjectFactoryService.class,
	RBACSecurityConfiguration.class

})
public class ApplicationConfiguration {
	
}
