package com.packt.webstore.service;

import com.packt.webstore.domain.User;

public interface UserService {
	boolean login(User user);

	User getUser(User user);
}
