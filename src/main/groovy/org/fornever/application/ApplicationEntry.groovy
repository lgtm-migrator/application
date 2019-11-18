package org.fornever.application;

import org.fornever.application.ApplicationEntry
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.web.servlet.config.annotation.EnableWebMvc

import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Application Entry
 */
@SpringBootApplication(scanBasePackageClasses=ApplicationEntry.class) // not scan data from 
class ApplicationEntry {

	static void main(String[] args) {
		SpringApplication.run(ApplicationEntry, args)
	}
}
