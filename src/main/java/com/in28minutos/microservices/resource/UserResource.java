package com.in28minutos.microservices.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutos.microservices.dao.UserDaoService;
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
	public User retrieveUser(@PathVariable Long id) {
		return service.findUser(id);
	}
}
