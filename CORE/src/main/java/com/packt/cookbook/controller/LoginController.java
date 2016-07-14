package com.packt.cookbook.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packt.cookbook.domain.User;
import com.packt.cookbook.service.UserService;

@Controller
@RequestMapping(value = "user")
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> login(@RequestBody User login){
		Map<String, Object> mapForReponse = new HashMap<>();
		User user = userService.login(login);

		if(user != null){
			user.setId_u(null);
			user.setPassword(null);
			mapForReponse.put("success", true);
			mapForReponse.put("user", user);
		} else
			mapForReponse.put("success", false);
		
		return new ResponseEntity<Map<String, Object>>(mapForReponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> register(@RequestBody User register){
		Map<String, Object> mapForReponse = new HashMap<>();
		mapForReponse.put("success", userService.register(register));

		return new ResponseEntity<Map<String, Object>>(mapForReponse, HttpStatus.OK);
	}
}
