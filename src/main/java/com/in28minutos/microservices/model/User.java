package com.in28minutos.microservices.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.in28minutos.microservices.mapping.CustomDateDeserializer;
import com.in28minutos.microservices.mapping.CustomUserSerializer;

/*
 *  JsonFilter is setting dinamic filter. It's used when a service modifies the json returned (MappingJacksonValue)
 *  and excludes some information on the response.
 *  
 *  This approach is best used (against static filters) when there are certain services that wants to modify the data
 *  being sent to the request but do not want it all the time - in all services. In static filter, the value that is annotated with @JsonIgnore will always be ignored.
 *   
 *  The problem using this annotation is that it expects that every service that interacts with this entity, is supposed to resolve the filter,
 *  but this is not true. In this project, we are using this specific filter to exclude "posts" information when some user endpoints gets called, 
 *  but this is also affecting negatively on some other endpoints that do not need to filter information, as it is the case of user creation endpoint.
 *   
 *  
 */
//@JsonFilter("UserPostsFilter")

@JsonSerialize(using = CustomUserSerializer.class)
public class User extends ResourceSupport {

	private Long userId;

	@Size(min = 2)
	private String name;

	@JsonDeserialize(using = CustomDateDeserializer.class)
	@Past
	private Date birthdayDate;

	private List<Post> posts = new ArrayList<>();

	// Empty constructor is mandatory for deserialization (json -> Java object)
	public User() {
	}

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
