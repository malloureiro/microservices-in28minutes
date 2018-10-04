package com.in28minutos.microservices.model;

import java.util.Date;

public class Post {

	private Long id;
	
	private String title;

	private String message;
	
	private Date timestamp;
	
	public Post() {}
	
	public Post(Long id, String title, Date timestamp) {
		super();
		this.id = id;
		this.title = title;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
