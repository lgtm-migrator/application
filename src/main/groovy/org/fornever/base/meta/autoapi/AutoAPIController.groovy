package org.fornever.base.meta.autoapi;

import java.lang.reflect.Method

import javax.transaction.Transactional

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation
import org.fornever.base.annotations.BusinessObject
import org.fornever.base.meta.autoapi.annotations.ExposeAPI
import org.fornever.base.meta.autoapi.exceptions.RequestParamExtractException
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
import org.springframework.web.bind.annotation.RequestParam
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
	Object dispatch(
			@RequestBody String body,
			@RequestParam Map<String, String> urlParam,
			@PathVariable("topic") String topic,
			@PathVariable("api") String api
	) {
		def s = ctx.getBeansWithAnnotation(ExposeAPI.class).find { this.getAPITopicForClass(it.value.class).equals(topic) }
		if(s) {
			def i = s.value;
			def iClazz = i.class

			def m = this.getClassMethod(iClazz, api)

			if(m != null) {

				def rtType = m.getReturnType();

				// if return type is not 'void', means null is not accepted
				def returnValueRequire = !rtType.equals(Void.TYPE)

				def params = m.getParameters();
				def rValue = null
				def ps = []

				if(params.length > 0) {

					def bodyTree = getMapper().readTree(body)

					for(p in params) {

						def pName = p.getName();
						def pType = p.getType();
						def pAnnotations = p.getAnnotations();

						if( pAnnotations.any { it.annotationType().equals(RequestBody.class) }) {

							ps.add(getMapper().readValue(body, pType))

						} else if(pAnnotations.any { it.annotationType().equals(RequestParam.class) }) {

							def aReqParam = pAnnotations.find { it.annotationType().equals(RequestParam.class) }

							def sName = aReqParam.value();

							if (sName) {
								ps.add(DefaultTypeTransformation.castToType(urlParam.get(pName), pType))
							} else {
								ps.add(DefaultTypeTransformation.castToType(urlParam, pType))
							}

						} else if(urlParam.containsKey(pName)) {

							ps.add(DefaultTypeTransformation.castToType(urlParam.get(pName), pType))

						} else if (bodyTree.get(pName) != null) {

							ps.add(getMapper().treeToValue(bodyTree.get(pName), pType))

						} else {
							
							try {

								ps.add(getMapper().readValue(body, pType))

							} catch (Exception e) {

								throw new RequestParamExtractException("Can not process api ${api} param ${pName} with payload ${body}")

							}

						}

					}

				}

				rValue = m.invoke(i, *ps)

				if(returnValueRequire && rValue == null) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND);
				} else {
					return rValue
				}

			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API '${api}' of '${iClazz.getName()}' Not Found")
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic '${topic}' Not Found")
		}
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
