package org.fornever.base.model

import javax.persistence.Column
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.MappedSuperclass
import javax.persistence.Transient

import org.fornever.base.action.BaseBusinessAction
import org.fornever.base.annotations.BusinessAction
import org.fornever.base.model.internal.ActionNotFound
import org.fornever.base.model.internal.DuplicateActionFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.AnnotationUtils

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@MappedSuperclass
@AutoClone
@ToString(includeNames=true)
@EqualsAndHashCode
abstract class BaseBusinessObject<S> extends BaseTable {

	private static final String S_STATE = "state"

	@Column
	@Enumerated(EnumType.STRING)
	S state;

	/**
	 * copy data from object
	 * 
	 * @param obj
	 * @return
	 */
	def BaseBusinessObject copyFrom(obj) {
		if(obj != null) {
			if(obj instanceof Map) {
				for(entry in obj.entrySet()) {
					this.setProperty(entry.getKey(), entry.getValue())
				}
			} else {
				for(f in obj.getProperties().entrySet()) {
					this.setProperty(f.getKey(), f.getValue())
				}
			}
		}
		return this
	}

	/**
	 * runtime will call this method when user call a method not existed
	 * 
	 * @param methodName
	 * @param methodArgs
	 * @return
	 */
	def methodMissing(String methodName, methodArgs) {

		// the class 'BusinessModelSupportConfiguration' defined 'this._getApplicationContext' method
		def actions = this._getApplicationContext().getBeansWithAnnotation(BusinessAction.class).values()

		actions = actions.findAll { it ->

			def annotation = AnnotationUtils.findAnnotation(it.class, BusinessAction.class)

			if(annotation!=null) {
				return annotation.model().equals(this.class) && it.acceptFromState(this.state) && annotation.name().equals(methodName)
			} else {
				return false
			}
		}

		switch(actions.size()) {
			case 0: throw new ActionNotFound(methodName);
			case 1:

				def bAction = actions.getAt(0)
				def action = { args ->

					def params = new HashMap()

					if ((args instanceof List || args.getClass().isArray()) && args.size() > 0) {
						params = args[0]
					} else if (args instanceof Map || args instanceof LinkedHashMap) {
						params = args
					}

					// getDelegate means 'get current invoke instance'
					bAction.run(getDelegate(), params)
				}

				this.class.getMetaClass()[methodName] = action // cache this action to class

				return action(methodArgs)

			default: throw new DuplicateActionFound(methodName);
		}
	}
}
