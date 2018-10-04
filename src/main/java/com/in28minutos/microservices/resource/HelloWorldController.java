package com.in28minutos.microservices.resource;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/hello-world-header")
	public String sayHello(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("hello.world.message", null, locale);
	}
	
	@GetMapping("/hello-world")
	public String sayhelloNoHeader() {
		return messageSource.getMessage("hello.world.message", null, LocaleContextHolder.getLocale());
	}
}
