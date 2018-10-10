package com.in28minutos.microservices.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class used to configure fields to be excluded from User json on serialization
 * 
 * @author mloureiro
 *
 */
public abstract class UserMixIn {

	@JsonIgnore
	public String hreflang;
	
	@JsonIgnore
	public String media;
	
	@JsonIgnore
	public String title;
	
	@JsonIgnore
	public String type;
	
	@JsonIgnore
	public String deprecation;
	
}
