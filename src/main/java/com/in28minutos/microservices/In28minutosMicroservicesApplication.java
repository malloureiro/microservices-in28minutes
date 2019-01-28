package com.in28minutos.microservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.in28minutos.microservices.utils.UserMixIn;

@SpringBootApplication
public class In28minutosMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(In28minutosMicroservicesApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		//localeResolver.setDefaultLocale(Locale.US);
		localeResolver.setDefaultLocale(new Locale("pt", "BR"));
		return localeResolver;
	}
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder();
		mapperBuilder.mixIn(Link.class, UserMixIn.class);
		return mapperBuilder;
	}
	
	/*
	 * The bellow Bean configuration is no longer necessary since it can be replaced by the following declaration on application properties:
	 * spring.messages.basename
	 * */
	/*@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}*/
}
