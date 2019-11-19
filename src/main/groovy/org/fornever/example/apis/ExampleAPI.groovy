package org.fornever.example.apis

import javax.transaction.Transactional

import org.fornever.base.annotations.API
import org.fornever.base.model.BusinessObjectStateManager
import org.fornever.example.jpa.ExampleHouseRepo
import org.fornever.example.model.ExampleHouse
import org.fornever.example.model.ExampleHouseState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@API("/v1/example")
class ExampleAPI {

	@Autowired
	BusinessObjectStateManager stateManager;

	@Autowired
	ExampleHouseRepo repo;

	@GetMapping("/houses")
	List<ExampleHouse> getHouses() {
		return repo.findAll();
	}
	
	

	@PostMapping("/house")
	@Transactional
	ExampleHouse createHouse(@RequestBody ExampleHouse house) {
		
		stateManager.toState(house, ExampleHouseState.NEW)
		
		return repo.save(house)
		
	}
}
