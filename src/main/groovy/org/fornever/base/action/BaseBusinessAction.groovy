package org.fornever.base.action

import org.fornever.base.action.internal.ActionNotAcceptState
import org.fornever.base.action.internal.BaseBusinessActionException
import org.fornever.base.model.BaseBusinessObject

/**
 * Base Business Object Type
 * 
 * @author Theo Sun
 *
 * @param <B> business object type
 * @param <S> state type
 * @param <P> action parameter type
 */
abstract class BaseBusinessAction<B extends BaseBusinessObject, S, P> {

	/**
	 * action supported states
	 * 
	 * @param s
	 * @return
	 */
	Boolean acceptFromState(S s) {
		return true;
	}

	/**
	 * state after action processed, system will force set the state after process
	 * 
	 * @return
	 */
	S targetState() {
		return null;
	}

	/**
	 * the main process logic on model, but not save
	 * 
	 * @param instance
	 * @param params
	 * @throws BaseBusinessActionException
	 */
	abstract void process(B instance,P params) throws BaseBusinessActionException;

	/**
	 * execute check & process instance & set state
	 * 
	 * @param instance
	 * @param params
	 * @return
	 * @throws BaseBusinessActionException
	 */
	def run(B instance, P params) throws BaseBusinessActionException {

		if(!this.acceptFromState(instance.getState())) {
			throw new ActionNotAcceptState("action ${this.getClass().getName()} not accept state ${instance.getState()}")
		}

		this.process(instance, params)

		if(this.targetState() != null && !this.targetState().equals(instance.getState())) {
			instance.setState(this.targetState())
		}
	}
}
