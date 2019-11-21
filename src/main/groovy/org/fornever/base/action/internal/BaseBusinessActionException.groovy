package org.fornever.base.action.internal

/**
 * 
 * Base Exception of Business Action
 * 
 * @author Theo Sun
 *
 */
class BaseBusinessActionException extends Exception {

	public BaseBusinessActionException() {
	}

	public BaseBusinessActionException(String msg){
		super(msg)
	}
}
