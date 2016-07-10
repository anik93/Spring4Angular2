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
	public boolean login(User user) {
		return userRepository.login(user);
	}

	@Override
	public User getUser(User user) {
		return userRepository.getUser(user);
	}
	
}
