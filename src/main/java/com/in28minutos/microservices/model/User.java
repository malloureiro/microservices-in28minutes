package com.in28minutos.microservices.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {

	private Long id;
	
	@Size(min=2)
	private String name;
	
	@Past
	private Date birthdayDate;
	
	private List<Post> posts = new ArrayList<>();
	
	// Empty constructor is mandatory for deserialization (json -> Java object)
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthdayDate=" + birthdayDate + "]";
	}
	
}
