/**
 * 
 */
package org.fornever.base.meta.autoapi.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Internal class/method/package, will be exclude from generated API
 * @author Theo Sun
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Internal {

}
