package org.fornever.base.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

/**
 * simple in-memory cache
 * 
 * @author Theo Sun
 *
 */
@EnableCaching
@Import(SimpleCache.class)
public @interface EnableSimpleCache {

}
