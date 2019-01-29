package com.in28minutos.microservices.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutos.microservices.dao.UserDaoService;
import com.in28minutos.microservices.model.Post;
import com.in28minutos.microservices.model.User;
import com.in28minutos.microservices.service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserDaoService daoService;
	
	@GetMapping("/users")
	public MappingJacksonValue retrieveAll() {
		List<User> users = daoService.findAll();
		for (User user : users) {
			user.add(linkTo(methodOn(this.getClass()).retrieveAll()).withSelfRel());
			user.add(linkTo(methodOn(this.getClass()).retrieveUser(user.getUserId())).withRel("user-details"));
		}
		
		User[] users_ = new User[users.size()];
		users_ = users.toArray(users_);
		
		// Adding dynamic filtering - do not want to return posts information on this response
		return service.mapUserDataPresentation(users_);
	}
	
	@GetMapping("/users/{id}")
	public MappingJacksonValue retrieveUser(@PathVariable Long id) {
		User user = daoService.findUser(id);
		
		user.add(linkTo(methodOn(this.getClass()).retrieveAll()).withRel("all-users"));
		user.add(linkTo(methodOn(this.getClass()).retrieveUserPosts(id)).withRel("user-posts"));
		
		// Adding dynamic filtering - do not want to return posts information on this response
		return service.mapUserDataPresentation(user);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User newUser = daoService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		
		return ResponseEntity.created(location).body(newUser);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> removeUser(@PathVariable Long id) {
		daoService.removeUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Post> createUserPost(@PathVariable Long id, @RequestBody Post post) {
		
		Post postCreated = daoService.createPost(id, post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{post_id}").buildAndExpand(postCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).body(postCreated);
	}
	
	@GetMapping("/users/{id}/posts/{post_id}")
	public Post retrievePostDetails(@PathVariable Long id, @PathVariable(value="post_id") Long postId) {
		Post post = daoService.getPostDetails(id, postId);
		
		post.add(linkTo(methodOn(this.getClass()).retrieveUserPosts(id)).withRel("user-posts"));
		return post;
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable Long id) {
		return daoService.getUserPosts(id);
	}
}
