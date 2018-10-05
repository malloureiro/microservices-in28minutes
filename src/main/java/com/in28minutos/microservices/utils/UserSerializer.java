package com.in28minutos.microservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.in28minutos.microservices.model.User;

public class UserSerializer extends JsonSerializer<User>{

	@Override
	public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	}

}
