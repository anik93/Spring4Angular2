package com.packt.cookbook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.cookbook.domain.User;
import com.packt.cookbook.repository.UserRepository;
import com.packt.cookbook.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User login(User login) {
		return userRepository.login(login);
	}

	@Override
	public boolean register(User register) {
		return userRepository.register(register);
	}

	@Override
	public boolean logout(User logout) {
		return userRepository.logout(logout);
	}

	@Override
	public User getUser(User userToFind) {
		return userRepository.getUser(userToFind);
	}

	@Override
	public boolean updateUser(User test) {
		return userRepository.updateUser(test);
	}

	@Override
	public boolean validToken(User userToken) {
		return userRepository.validToken(userToken);
	}

	@Override
	public boolean restartPassword(User user) {
		return userRepository.restartPassword(user);
	}
}
