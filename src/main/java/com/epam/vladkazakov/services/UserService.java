package com.epam.vladkazakov.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.vladkazakov.entities.User;
import com.epam.vladkazakov.repositories.UserRepository;

@Service("userService")
public class UserService {
	@Autowired
	public UserRepository userRepository;

	public UserService() {
	}

	@Transactional
	public User getById(int id) {
		User user = userRepository.findOne(id);
		return (user);
	}

	@Transactional
	public User add(User user) {
		return userRepository.save(user);
	}

	public User getByLoginAndPassword(String login, String password) {
		return userRepository.findByLoginAndPassword(login, password);
	}

	public User getByLogin(String login) {
		return userRepository.findByLogin(login);
	}

}
