package com.in28minutos.microservices.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in28minutos.microservices.model.User;

public class JsonUtils {

	public static ObjectMapper objectMapper = null;
	
	static {
		objectMapper = new ObjectMapper();
		objectMapper.addMixIn(User.class, UserMixIn.class);
	}
	
	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
}
