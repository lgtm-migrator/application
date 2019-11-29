package org.fornever.example.services

import org.fornever.base.meta.autoapi.annotations.ExposeAPI
import org.fornever.base.model.BusinessObjectFactoryService
import org.fornever.example.jpa.ExampleHouseRepo
import org.fornever.example.model.ExampleHouse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@ExposeAPI("house")
@Service
class ExampleHouseService {

	@Autowired
	BusinessObjectFactoryService factory;

	@Autowired
	ExampleHouseRepo repo;

	ExampleHouse createHouse(ExampleHouse newHouse, Integer id) {

		// invoke action dynamic
		newHouse.initHouse([price:999]);

		return repo.save(newHouse)
	}
}
