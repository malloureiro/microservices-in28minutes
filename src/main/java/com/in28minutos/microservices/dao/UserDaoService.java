package com.in28minutos.microservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutos.microservices.exception.UserNotFoundException;
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
		if (user.getUserId() == null) {
			user.setUserId(++idCounter);
		}
		users.add(user);
		return user;
	}
	
	public User findUser(Long id) {
		User foundUser = null;
		
		for (User user : users) {
			if (user.getUserId().equals(id)) {
				foundUser = user;
				break;
			}
		}
		if (foundUser == null) {
			throw new UserNotFoundException("User not found");
		}
		return foundUser;
	}
	
	public void removeUser(Long userId) {
		User userFound = null;
		
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if (user.getUserId() == userId) {
				userFound = user;
				iterator.remove();
				break;
			}
		}
		
		if (userFound == null) {
			throw new UserNotFoundException("User not found");
		}
	}
	
}
