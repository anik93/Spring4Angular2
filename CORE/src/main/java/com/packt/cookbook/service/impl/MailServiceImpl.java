package com.packt.cookbook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.packt.cookbook.domain.User;
import com.packt.cookbook.repository.UserRepository;
import com.packt.cookbook.service.MailService;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailSender mailSender;
	
	private SimpleMailMessage crunchifyMsg;
	
	@Override
	public Boolean restartPassword(String email) {
		User user = new User();
		user.setEmail(email);
		user = userRepository.getUser(user);
		if(user != null){
			crunchifyMsg = new SimpleMailMessage();
			crunchifyMsg.setFrom("mycookbook@gmail.com");
			crunchifyMsg.setTo(email);
			crunchifyMsg.setSubject("Restart Password");
			crunchifyMsg.setText("Restart Password");
			mailSender.send(crunchifyMsg);
			return true;
		} else 
			return false;
	}

	@Override
	public void registration(String toAddress, String name) {
		
	}

}
