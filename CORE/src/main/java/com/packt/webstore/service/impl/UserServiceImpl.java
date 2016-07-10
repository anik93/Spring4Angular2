package com.packt.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.User;
import com.packt.webstore.repository.UserRepository;
import com.packt.webstore.service.UserService;

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
