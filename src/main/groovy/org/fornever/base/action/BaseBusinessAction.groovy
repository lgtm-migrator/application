package org.fornever.base.action

import org.fornever.base.model.BaseBusinessObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

abstract class BaseBusinessAction<B extends BaseBusinessObject, S> {

	Integer getOrder() {
		return 0;
	};


	abstract Boolean acceptBusinessObject(Class<?> c);
	abstract Boolean acceptState(S s);
	abstract S targetState();

	abstract void process(B instance, Map<String, Object> params) throws BaseBusinessActionException;

	void processMulti(List<B> instances) throws BaseBusinessActionException {
		for(instance in instances) {
			this.process(instance)
		}
	}
}
