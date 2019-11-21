package org.fornever.base.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.stereotype.Service

@Service
class BusinessObjectFactoryService {

	@Autowired
	AutowireCapableBeanFactory beanFactory;


	def <T extends BaseBusinessObject> T createNewInstance(Class<T> clazz) {
		return beanFactory.getBean(clazz)
	}

	def <T extends BaseBusinessObject> T createNewInstance(Class<T> clazz, data) {
		return beanFactory.getBean(clazz).copyFrom(data)
	}
}
