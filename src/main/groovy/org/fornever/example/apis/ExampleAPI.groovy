package org.fornever.example.apis

import javax.persistence.EntityManager
import javax.transaction.Transactional

import org.fornever.base.annotations.API
import org.fornever.base.model.BusinessObjectFactoryService
import org.fornever.example.jpa.ExampleHouseRepo
import org.fornever.example.model.ExampleHouse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@API("/v1/example")
class ExampleAPI {

	@Autowired
	BusinessObjectFactoryService factory;

	@Autowired
	ExampleHouseRepo repo;

	@GetMapping("/houses")
	List<ExampleHouse> getHouses() {
		return repo.findAll();
	}
	
	@GetMapping("/houses/{id}")
	Optional<ExampleHouse> getHouseById(@PathVariable("id") Long id) {
		return repo.findById(id)
	}
	
	@PostMapping("/house")
	@Transactional
	ExampleHouse createHouse(@RequestBody Map data) {

		// must create empty instance by this way
		// so that framework could inject context to model
		def newHouse = factory.createNewInstance(ExampleHouse.class, data)

		// invoke action dynamic
		newHouse.initHouse([price:999]);
		
		return repo.save(newHouse)
	}
}
