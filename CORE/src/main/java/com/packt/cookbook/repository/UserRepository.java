package com.packt.cookbook.repository;

import com.packt.cookbook.domain.User;

public interface UserRepository {
	User login(User login);

	Boolean register(User register);
}
