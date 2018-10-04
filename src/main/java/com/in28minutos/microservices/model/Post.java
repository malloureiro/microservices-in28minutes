package com.in28minutos.microservices.model;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

public class Post extends ResourceSupport {

	private Long postId;
	
	private String title;

	private String message;
	
	private Date timestamp;
	
	public Post() {}
	
	public Post(Long id, String title, Date timestamp) {
		super();
		this.postId = id;
		this.title = title;
		this.timestamp = timestamp;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long id) {
		this.postId = id;
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
