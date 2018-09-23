/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TapojitBhattacharya
 *
 */
@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	/*
	 * @RequestMapping(method = RequestMethod.GET, path = "/hello-world") public
	 * String getHelloWorld() { return "Hello World";
	 * 
	 * }
	 */
	@GetMapping(path = "/hello-world")
	public String getHelloWorld() {
		return "Hello World";

	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World");

	}

	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public HelloWorldBean getHelloWorldBeanWithPathVariable(@PathVariable String name) {
		return new HelloWorldBean(name);

	}

	/*** Messages using Internationalization without using Request Header ***/
	@GetMapping(path = "/hello-world-internationalized")
	public String getHelloWorldInternationalized() {
		System.out.println("Locale:::::::::" + LocaleContextHolder.getLocale());
		return messageSource.getMessage("greeting", null, LocaleContextHolder.getLocale());

	}

}
