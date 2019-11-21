package org.fornever.base.action.internal;

public class ObjectStateInconsistency extends BaseBusinessActionException {

	private static final long serialVersionUID = 3133422642279772488L;

	public ObjectStateInconsistency() {
	}

	public ObjectStateInconsistency(String msg) {
		super(msg);
	}

}
