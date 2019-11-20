package org.fornever.base.action

import org.fornever.base.model.BaseBusinessObject

abstract class BaseBusinessAction<B extends BaseBusinessObject, S> {

	Integer getOrder() {
		return 0;
	};


	abstract Boolean acceptBusinessObject(Class<?> c);
	/**
	 * this action name could be used in the model as a method
	 * 
	 * 
	 * @return
	 */
	abstract String getActionName();
	/**
	 * action supported states
	 * 
	 * @param s
	 * @return
	 */
	abstract Boolean acceptFromState(S s);
	/**
	 * state after action processed
	 * 
	 * @return
	 */
	abstract S getToState();
	abstract void process(B instance, params) throws BaseBusinessActionException;
}
