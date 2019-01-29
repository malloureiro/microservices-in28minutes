package com.in28minutos.microservices.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutos.microservices.exception.PostNotFoundException;
import com.in28minutos.microservices.model.Post;
import com.in28minutos.microservices.model.User;

@Component
public class PostDaoService {
	
	@Autowired
	private UserDaoService userService;
	
	private static Long idCounter = 0L;

	public Post addPost(User user, Post post) {
		List<Post> userPosts = user.getPosts();
		
		post.setPostId(++idCounter);
		userPosts.add(post);
		
		return post;
	}
	
	public Post createPost(Long userId, Post post) {
		User user = userService.findUser(userId);
		post = addPost(user, post);
		return post;
	}
	
	public Post getPostDetails(Long userId, Long postId) {
		User user = userService.findUser(userId);
		
		Post postFound = null;
		
		List<Post> userPosts = user.getPosts();
		for (Post post : userPosts) {
			if (post.getPostId() == postId) {
				postFound = post;
				break;
			}
		}
		if (postFound == null) {
			throw new PostNotFoundException("Post not found for this specific user");
		}
		
		return postFound;
	}
	
	public List<Post> getUserPosts(Long userId) {
		User user = userService.findUser(userId);
		return user.getPosts();
	}
}
