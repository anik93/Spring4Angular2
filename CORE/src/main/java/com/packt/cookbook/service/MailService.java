package com.packt.cookbook.service;

import com.packt.cookbook.domain.User;

public interface MailService {
	void restartPassword(User user);
	void registration(String toAddress, String name);
}
