package org.fornever.base.action.internal

import groovy.transform.InheritConstructors

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
