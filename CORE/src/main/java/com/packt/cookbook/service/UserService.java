package com.packt.cookbook.service;

import com.packt.cookbook.domain.User;

public interface UserService {
	boolean login(User user);

	User getUser(User user);
}
