package org.fornever.application;

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Application Entry
 */
// only scan classes in this and sub packages
@SpringBootApplication(scanBasePackageClasses=ApplicationEntry.class) 
class ApplicationEntry {

	static void main(String[] args) {
		SpringApplication.run(ApplicationEntry, args)
	}
}
