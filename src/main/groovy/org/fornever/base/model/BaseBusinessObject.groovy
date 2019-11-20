package org.fornever.base.model

import javax.persistence.Column
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.MappedSuperclass
import javax.persistence.Transient

import org.fornever.base.action.BaseBusinessAction
import org.fornever.base.model.internal.ActionNotFound
import org.fornever.base.model.internal.DuplicateActionFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

@MappedSuperclass
abstract class BaseBusinessObject<S> extends BaseTable {

	private static final String S_STATE = "state"

	@Column
	@Enumerated(EnumType.STRING)
	S state;

	@Transient
	S beforeState;

	@Autowired
	@Transient
	private ApplicationContext ctx;

	@Override
	def void setProperty(String propertyName, Object value) {
		if(propertyName.equals(S_STATE)) {
			this.beforeState = this.state;
		}
		super.setProperty(propertyName, value);
	}

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

		def actions = ctx.getBeansOfType(BaseBusinessAction.class).values()
		actions = actions.findAll { it.acceptBusinessObject(this.class) && it.acceptFromState(this.state) && it.getActionName().equals(methodName) }
		switch(actions.size()) {
			case 0: throw new ActionNotFound(methodName);
			case 1:

				def bAction = actions.getAt(0)
				def action = { args ->

					def params = new HashMap()

					if (args instanceof List && args.size() > 0) {
						params = args[0]
					} else if (args instanceof Map) {
						params = args
					}

					// getDelegate means 'get current invoke instance'
					bAction.process(getDelegate(), params)
				}

			// cache this action to class
				this.class.getMetaClass()[methodName] = action

				return action(methodArgs)


			default: throw new DuplicateActionFound(methodName);
		}
	}
}
