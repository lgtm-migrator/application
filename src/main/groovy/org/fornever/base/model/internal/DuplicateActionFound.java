package org.fornever.base.model.internal;

import org.fornever.base.action.BaseBusinessActionException;

public class DuplicateActionFound extends BaseBusinessActionException {

	private static final long serialVersionUID = -8230863492169209424L;

	public DuplicateActionFound() {
		super();
	}

	public DuplicateActionFound(String msg) {
		super(msg);
	}

}
