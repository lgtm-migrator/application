package org.fornever.example.action;

import org.fornever.base.action.BaseBusinessAction;
import org.fornever.base.action.internal.BaseBusinessActionException
import org.fornever.base.annotations.BusinessAction;
import org.fornever.example.model.ExampleHouse;
import org.fornever.example.model.ExampleHouseState;

@BusinessAction(name="initHouse", model=ExampleHouse.class)
public class ExampleHouseNewAction extends BaseBusinessAction<ExampleHouse, ExampleHouseState, Object> {

	@Override
	public ExampleHouseState targetState() {
		return ExampleHouseState.NEW;
	}

	@Override
	public void process(ExampleHouse house, params) throws BaseBusinessActionException {
		
		if (house.getArea() == null) {
			house.setArea(new Double(99));
		}

		if (params.price) {
			house.setPrice(new Double(params.price));
		}
		
	}
	
}
