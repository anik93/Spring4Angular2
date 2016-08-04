package com.packt.cookbook.service;

import com.packt.cookbook.domain.User;

public interface UserService {
	User login(User login);

	boolean register(User register);
	
	boolean logout(User logout);
	
	User getUser(User userToFind);
	
	boolean updateUser(User test);
	
	boolean validToken(User userToken);

	boolean restartPassword(String email);
}
