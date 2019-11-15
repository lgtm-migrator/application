package org.fornever.application;

import org.fornever.application.ApplicationEntry
import org.fornever.base.cache.EnableSimpleCache
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching

/**
 * Application Entry
 */
@SpringBootApplication
@EnableSimpleCache
class ApplicationEntry {

	static void main(String[] args) {
		SpringApplication.run(ApplicationEntry, args)
	}
}
