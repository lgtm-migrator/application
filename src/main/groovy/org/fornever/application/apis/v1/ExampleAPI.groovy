package org.fornever.application.apis.v1

import org.fornever.base.annotations.API
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@API("/v1/example")
class ExampleAPI {


	@GetMapping("/hello")
	@Cacheable
	String hello(@RequestParam("words") String words){
		return words
	}
}
