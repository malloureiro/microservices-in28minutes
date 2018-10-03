package com.in28minutos.microservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutos.microservices.model.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	private static Long idCounter = 3L;
	
	static {
		users.add(new User(1L, "Maria", new Date()));
		users.add(new User(2L, "Joaquim", new Date()));
		users.add(new User(3L, "Paulo", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++idCounter);
		}
		users.add(user);
		return user;
	}
	
	public User findUser(Long id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}
}
