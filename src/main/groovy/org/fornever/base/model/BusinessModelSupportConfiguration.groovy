package org.fornever.base.model;

import org.fornever.base.annotations.BusinessAction
import org.fornever.base.model.internal.ActionNotFound
import org.fornever.base.model.internal.DuplicateActionFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener
import org.springframework.core.annotation.AnnotationUtils

@Configuration
@ComponentScan(basePackageClasses = BusinessModelSupportConfiguration.class)
public class BusinessModelSupportConfiguration {

	@Autowired
	ApplicationContext ctx;

	@EventListener(ApplicationReadyEvent.class)
	def afterStartup() {
		/**
		 * make business object could access application context
		 */
		BaseBusinessObject.metaClass._getApplicationContext = { ctx }
		
	}
}
