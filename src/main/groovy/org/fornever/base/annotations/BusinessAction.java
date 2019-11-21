package org.fornever.base.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * This is a business action
 * 
 * @author Theo Sun
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface BusinessAction {

	/**
	 * component alias
	 * 
	 * @return
	 */
	@AliasFor(annotation = Component.class)
	String value() default "";

	/**
	 * action name, developer could use this name on model to invoke this action
	 * 
	 * @return
	 */
	String name();

	/**
	 * the business action is for
	 * 
	 * @return
	 */
	Class<?> model();

	/**
	 * true  means this action is for the object, 
	 * false means this action is for the object instance
	 * 
	 * @return
	 */
	boolean objectAction() default false;

}
