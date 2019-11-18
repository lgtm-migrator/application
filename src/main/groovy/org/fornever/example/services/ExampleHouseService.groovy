package org.fornever.example.services

import org.fornever.example.jpa.ExampleHouseRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExampleHouseService {

	@Autowired
	ExampleHouseRepo exampleHouseRepo;
}
