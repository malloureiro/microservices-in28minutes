package com.in28minutos.microservices.service;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.in28minutos.microservices.model.User;

@Component
public class UserService {

	/**
	 * Customize json presentation. 
	 * 
	 * Given the @param users, serialize all information EXCEPT {@Post} related data (which should only be presented on specific endpoint).
	 * 
	 * @param users
	 * @return User object without Post information.
	 */
	public MappingJacksonValue mapUserDataPresentation(User...users) {
		SimpleFilterProvider filters = new SimpleFilterProvider();
		filters.addFilter("UserPostsFilter", SimpleBeanPropertyFilter.serializeAllExcept("posts"));
		
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
}
