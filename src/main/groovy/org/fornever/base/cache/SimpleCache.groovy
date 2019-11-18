package org.fornever.base.cache

import org.springframework.cache.CacheManager
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * For single node deployment, in-memory cache is OK.
 * 
 * @author Theo Sun
 *
 */
@Configuration
class SimpleCache {

	@Bean
	CacheManager getCacheManager() {
		def cm = new SimpleCacheManager();
		return cm;
	}
}
