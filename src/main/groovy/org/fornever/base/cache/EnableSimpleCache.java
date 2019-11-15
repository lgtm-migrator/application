package org.fornever.base.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

@EnableCaching
@Import(SimpleCache.class)
public @interface EnableSimpleCache {

}
