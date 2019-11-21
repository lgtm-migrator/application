package org.fornever.base.model.internal;

import org.fornever.base.action.internal.BaseBusinessActionException;

public class ActionNotFound extends BaseBusinessActionException {

	private static final long serialVersionUID = 6877892271265549306L;

	
	public ActionNotFound() {
		super();
	}

	public ActionNotFound(String msg) {
		super(msg);
	}

	
}
