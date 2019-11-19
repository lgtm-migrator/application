package org.fornever.example.action;

import java.util.Map;

import org.fornever.base.action.BaseBusinessAction;
import org.fornever.base.action.BaseBusinessActionException;
import org.fornever.base.annotations.BusinessAction;
import org.fornever.example.model.ExampleHouse;
import org.fornever.example.model.ExampleHouseState;

@BusinessAction("HouseNewCreatedAction")
public class ExampleHouseNewAction extends BaseBusinessAction<ExampleHouse, ExampleHouseState> {

	@Override
	public Boolean acceptBusinessObject(Class<?> c) {
		return c.equals(ExampleHouse.class);
	}

	@Override
	public Boolean acceptState(ExampleHouseState s) {
		return true;
	}

	@Override
	public ExampleHouseState targetState() {
		return ExampleHouseState.NEW;
	}

	@Override
	public void process(ExampleHouse instance, Map<String, Object> params) throws BaseBusinessActionException {

		if (instance.getArea() == null) {
			instance.setArea(new Double(99));
		}

		instance.setState(ExampleHouseState.NEW);

	}

}
