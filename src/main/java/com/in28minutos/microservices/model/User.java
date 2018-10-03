package com.in28minutos.microservices.model;

import java.util.Date;

public class User {

	private Long id;
	
	private String name;
	
	private Date birthdayDate;
	
	public User() {}
	
	public User(Long id, String name, Date birthdayDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdayDate = birthdayDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthdayDate=" + birthdayDate + "]";
	}
	
}
