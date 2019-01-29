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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.in28minutos.microservices.dao.PostDaoService;
import com.in28minutos.microservices.dao.UserDaoService;
import com.in28minutos.microservices.model.Post;
import com.in28minutos.microservices.model.User;
import com.in28minutos.microservices.service.CustomUserSerializer;
import com.in28minutos.microservices.service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired
	private PostDaoService postDaoService;
	
	
	@GetMapping("/users")
	public MappingJacksonValue retrieveAll() throws JsonProcessingException {
		List<User> users = userDaoService.findAll();
		for (User user : users) {
			user.add(linkTo(methodOn(this.getClass()).retrieveAll()).withSelfRel());
			user.add(linkTo(methodOn(this.getClass()).retrieveUser(user.getUserId())).withRel("user-details"));
		}
		
		User[] users_ = new User[users.size()];
		users_ = users.toArray(users_);
		
		// Adding dynamic filtering - do not want to return posts information on this response
		return service.mapUserDataPresentation(users_);
	}
	
	@GetMapping(path="/users/{id}", produces="application/json")
	public String retrieveUser(@PathVariable Long id) throws JsonProcessingException {
		User user = userDaoService.findUser(id);
		
		user.add(linkTo(methodOn(this.getClass()).retrieveAll()).withRel("all-users"));
		user.add(linkTo(methodOn(this.getClass()).retrieveUserPosts(id)).withRel("user-posts"));
		
		// TODO - DECLARAÇÃO DE CUSTOM SERIALIZER NÃO DEVE REPETIR PARA TODOS OS MÉTODOS
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new SimpleModule().addSerializer(User.class, new CustomUserSerializer()));
		return objMapper.writeValueAsString(user);
		
		
		// Adding dynamic filtering - do not want to return posts information on this response
		//return service.mapUserDataPresentation(user);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User newUser = userDaoService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		
		return ResponseEntity.created(location).body(newUser);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> removeUser(@PathVariable Long id) {
		userDaoService.removeUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Post> createUserPost(@PathVariable Long id, @RequestBody Post post) {
		
		Post postCreated = postDaoService.createPost(id, post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{post_id}").buildAndExpand(postCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).body(postCreated);
	}
	
	@GetMapping("/users/{id}/posts/{post_id}")
	public Post retrievePostDetails(@PathVariable Long id, @PathVariable(value="post_id") Long postId) {
		Post post = postDaoService.getPostDetails(id, postId);
		
		post.add(linkTo(methodOn(this.getClass()).retrieveUserPosts(id)).withRel("user-posts"));
		return post;
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable Long id) {
		return postDaoService.getUserPosts(id);
	}
}
