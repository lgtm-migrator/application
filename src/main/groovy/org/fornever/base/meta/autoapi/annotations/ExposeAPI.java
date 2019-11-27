package org.fornever.base.meta.autoapi.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * expose class/method as API
 * 
 * @author Theo Sun
 *
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD, PACKAGE })
@Component
public @interface ExposeAPI {

	/**
	 * the name of API topic
	 * 
	 * @return
	 */
	@AliasFor(annotation = Component.class)
	String value() default "";

}
