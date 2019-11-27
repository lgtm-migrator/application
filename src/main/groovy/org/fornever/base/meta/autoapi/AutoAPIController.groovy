package org.fornever.base.meta.autoapi;

import java.lang.reflect.Method

import javax.transaction.Transactional

import org.fornever.base.annotations.BusinessObject
import org.fornever.base.meta.autoapi.annotations.ExposeAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException

import com.fasterxml.jackson.databind.ObjectMapper

import groovy.transform.Memoized

@RestController
public class AutoAPIController {

	@Autowired
	ApplicationContext ctx;

	@Memoized
	ObjectMapper getMapper() {
		return new ObjectMapper()
	}

	@Memoized
	Method getClassMethod(Class<?> clazz, String methodName) {
		for(m in clazz.getMethods()) {
			if(m.getName().equals(methodName)) {
				return m
			}
		}
		return null;
	}

	@RequestMapping(path="/api/generated/{topic}/{api}",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	Object dispatch(@RequestBody String body, @PathVariable("topic") String topic, @PathVariable("api") String api) {
		def rt
		def s = ctx.getBeansWithAnnotation(ExposeAPI.class).find { this.getAPITopicForClass(it.value.class).equals(topic) }
		if(s) {
			def i = s.value;
			def iClazz = i.class

			def m = this.getClassMethod(iClazz, api)

			if(m!=null) {
				def rtType = m.getReturnType();
				// if return type is not 'void', means null is not accepted
				def returnValueRequire = !rtType.equals(Void.TYPE)

				def params = m.getParameters();
				switch(params.length) {
					case 0:
						return m.invoke(i)
						break;
					case 1:
						def p0 = getMapper().readValue(body, params[0].type)
						return m.invoke(i, p0)
						break;
					default:
						def ps = []
						// do some thing here
						return m.invoke(i, )
						break;
				}
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API '${api}' of '${iClazz.getName()}' Not Found")
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic '${topic}' Not Found")
		}
		return rt
	}

	@GetMapping("/api/generated")
	Object apiList() {
		def rt = []

		def exposed = ctx.getBeansWithAnnotation(ExposeAPI.class)
		rt.addAll(exposed.collect {	this.getAPITopicForClass(it.value.class) })

		return rt
	}

	String getAPITopicForClass(Class<?> clazz) {
		def a = AnnotationUtils.findAnnotation(clazz, ExposeAPI.class)
		if(a && a.value()) {
			return a.value()
		} else {
			return clazz.getName()
		}
	}
}
