package com.in28minutos.microservices.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Simple bean used to exemplify some concepts.
 * 
 * @author mloureiro
 *
 */
// Static filtering at class level
@JsonIgnoreProperties(value={"msg2", "msg3"})
public class HelloWorld {

	private String msg1;
	
	private String msg2;
	
	//Static filtering at field level
	//@JsonIgnore
	private String msg3;

	public HelloWorld(String msg1, String msg2, String msg3) {
		super();
		this.msg1 = msg1;
		this.msg2 = msg2;
		this.msg3 = msg3;
	}

	public String getMsg1() {
		return msg1;
	}

	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}

	public String getMsg2() {
		return msg2;
	}

	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}

	public String getMsg3() {
		return msg3;
	}

	public void setMsg3(String msg3) {
		this.msg3 = msg3;
	}
	
 }
