package com.in28minutos.microservices.service;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.in28minutos.microservices.model.User;

@Component
public class UserService {

	public MappingJacksonValue mapFilterValues(User...users) {
		SimpleFilterProvider filters = new SimpleFilterProvider();
		filters.addFilter("UserPostsFilter", SimpleBeanPropertyFilter.serializeAllExcept("posts"));
		
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
