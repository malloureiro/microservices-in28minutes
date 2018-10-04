package com.in28minutos.microservices.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
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

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAll() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable Long id) {
		User user = service.findUser(id);
		
		Resource<User> resource = new Resource<User>(user);
		
		Link linkToSelf = linkTo(methodOn(this.getClass()).retrieveUser(id)).withSelfRel();
		resource.add(linkToSelf);
		
		ControllerLinkBuilder linkAllUsers = linkTo(methodOn(this.getClass()).retrieveAll());
		resource.add(linkAllUsers.withRel("all-users"));
		
		ControllerLinkBuilder linkToUserPosts = linkTo(methodOn(this.getClass()).retrieveUserPosts(id));
		resource.add(linkToUserPosts.withRel("user-posts"));
		
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User newUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		
		return ResponseEntity.created(location).body(newUser);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> removeUser(@PathVariable Long id) {
		service.removeUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Post> createUserPost(@PathVariable Long id, @RequestBody Post post) {
		
		Post postCreated = service.createPost(id, post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{post_id}").buildAndExpand(postCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).body(postCreated);
	}
	
	@GetMapping("/users/{id}/posts/{post_id}")
	public Post retrievePostDetails(@PathVariable Long id, @PathVariable(value="post_id") Long postId) {
		Post post = service.getPostDetails(id, postId);
		return post;
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable Long id) {
		return service.getUserPosts(id);
	}
}
