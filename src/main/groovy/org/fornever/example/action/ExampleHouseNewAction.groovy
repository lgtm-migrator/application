package org.fornever.example.action;

import org.fornever.base.action.BaseBusinessAction;
import org.fornever.base.action.BaseBusinessActionException;
import org.fornever.base.annotations.BusinessAction;
import org.fornever.example.model.ExampleHouse;
import org.fornever.example.model.ExampleHouseState;

@BusinessAction
public class ExampleHouseNewAction extends BaseBusinessAction<ExampleHouse, ExampleHouseState> {

	@Override
	public Boolean acceptBusinessObject(Class<?> c) {
		return c.equals(ExampleHouse.class);
	}

	@Override
	public Boolean acceptFromState(ExampleHouseState s) {
		return true;
	}

	@Override
	public ExampleHouseState getToState() {
		return ExampleHouseState.NEW;
	}
	
	@Override
	public String getActionName() {
		return "initHouse";
	}

	@Override
	public void process(ExampleHouse instance, Object params) throws BaseBusinessActionException {

		if (instance.getArea() == null) {
			instance.setArea(new Double(99));
		}
		
		if (params.price) {
			instance.setPrice(params.price);
		}

		instance.setState(ExampleHouseState.NEW)
		
	}


}
