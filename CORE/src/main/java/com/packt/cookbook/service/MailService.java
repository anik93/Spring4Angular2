package com.packt.cookbook.service;

public interface MailService {
	Boolean restartPassword(String email);
	void registration(String toAddress, String name);
}
