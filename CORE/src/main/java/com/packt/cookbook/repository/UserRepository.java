package com.packt.cookbook.repository;

import com.packt.cookbook.domain.User;

public interface UserRepository {
	boolean login(User user);

	User getUser(User user);
}
