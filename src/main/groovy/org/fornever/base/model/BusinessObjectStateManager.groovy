package org.fornever.base.model

import org.fornever.base.action.BaseBusinessAction
import org.fornever.base.action.BaseBusinessActionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
class BusinessObjectStateManager {
	
	@Autowired
	ApplicationContext ctx;
	
	public toState(BaseBusinessObject instance, next, Map<String, Object> params = null) throws BaseBusinessActionException {
		def actions = this.getActions(instance, next);
		for(action in actions) {
			action.process(instance, params)
		}
	}
	
	List<BaseBusinessAction> getActions(BaseBusinessObject instance, nextState) {
		
		def actions = ctx.getBeansOfType(BaseBusinessAction.class).values()
				
		actions = actions.findAll { it.acceptBusinessObject(instance.getClass()) && it.acceptState(instance.getState()) && it.targetState() == nextState }
		actions = actions.sort { a,b -> a.getOrder() <=> b.getOrder() }

		return actions;
	}
	
	
}
