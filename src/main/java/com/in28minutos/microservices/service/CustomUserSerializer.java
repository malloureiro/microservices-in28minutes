package com.in28minutos.microservices.service;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.in28minutos.microservices.model.User;

public class CustomUserSerializer extends JsonSerializer<User> {
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void serialize(User user, JsonGenerator generator, SerializerProvider serializers) throws IOException {
		generator.writeStartObject();
		generator.writeNumberField("userId", user.getUserId());
		generator.writeStringField("name", user.getName());
		generator.writeStringField("birthdayDate", formatter.format(user.getBirthdayDate()));
		generator.writeEndObject();
	}

}
