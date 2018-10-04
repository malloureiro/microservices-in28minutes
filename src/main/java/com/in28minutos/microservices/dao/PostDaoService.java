package com.in28minutos.microservices.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutos.microservices.model.Post;
import com.in28minutos.microservices.model.User;

@Component
public class PostDaoService {
	
	private static Long idCounter = 0L;

	public Post addPost(User user, Post post) {
		List<Post> userPosts = user.getPosts();
		
		post.setPostId(++idCounter);
		userPosts.add(post);
		
		return post;
	}
}
