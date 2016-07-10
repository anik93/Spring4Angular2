package com.packt.webstore.repository;

import com.packt.webstore.domain.User;

public interface UserRepository {
	boolean login(User user);

	User getUser(User user);
}
