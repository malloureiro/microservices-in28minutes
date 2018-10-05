package com.in28minutos.microservices.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties({"hreflang","media","title","type","deprecation"})
@JsonFilter("UserPostsFilter")
public class User extends ResourceSupport {

	private Long userId;
	
	@Size(min=2)
	private String name;
	
	@Past
	private Date birthdayDate;
	
	private List<Post> posts = new ArrayList<>();
	
	// Empty constructor is mandatory for deserialization (json -> Java object)
	public User() {}
	
	public User(Long id, String name, Date birthdayDate) {
		super();
		this.userId = id;
		this.name = name;
		this.birthdayDate = birthdayDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long id) {
		this.userId = id;
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
		return "User [id=" + userId + ", name=" + name + ", birthdayDate=" + birthdayDate + "]";
	}
	
}
