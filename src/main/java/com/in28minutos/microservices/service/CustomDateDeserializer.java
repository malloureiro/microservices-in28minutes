package com.in28minutos.microservices.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date> {
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String textDate = parser.getText();
		
		Date date = null;
		
		try {
			date = formatter.parse(textDate);
		} catch(ParseException ex) {
			throw new RuntimeException(ex);
		}
		
		return date;
	}

}
