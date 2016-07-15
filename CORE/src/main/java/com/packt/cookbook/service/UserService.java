package com.packt.cookbook.service;

import com.packt.cookbook.domain.User;

public interface UserService {
	User login(User login);

	Boolean register(User register);
	
	Boolean logout(User logout);
}
