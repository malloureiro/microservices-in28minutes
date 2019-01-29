package com.in28minutos.microservices.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutos.microservices.model.HelloWorld;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Endpoint that exemplifies Locale configuration an message bundle.
	 * 
	 * @param locale
	 * @return
	 */
	@GetMapping("/hello-world-header")
	public String sayHello(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("hello.world.message", null, locale);
	}
	
	
	/**
	 * Endpoint that exemplifies Locale configuration an message bundle.
	 * 
	 * @param locale
	 * @return
	 */
	@GetMapping("/hello-world")
	public String sayhelloNoHeader() {
		return messageSource.getMessage("hello.world.message", null, LocaleContextHolder.getLocale());
	}
	
	/**
	 * Endpoint that exemplifies static filtering: filter were added to some properties directly at the bean.
	 * @return
	 */
	@GetMapping("/hello-world-filter")
	public HelloWorld sayHelloFiltered() {
		return new HelloWorld("Hello", "Olá", "Ciao");
	}
	
	/**
	 * Endpoint that exemplifies static filtering on different types of response: filter were added to some properties directly at the bean.
	 * @return
	 */
	@GetMapping("/hello-world-filter-list")
	public List<HelloWorld> sayHelloFilteredList() {
		List<HelloWorld> list = new ArrayList<>();
		list.add(new HelloWorld("Hello", "Olá", "Ciao"));
		list.add(new HelloWorld("Oi", "Hi", "Ciao"));
		return list;
				
	}
	
	
}
