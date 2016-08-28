package com.packt.cookbook.controller;

import java.time.LocalDateTime;
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
import com.packt.cookbook.service.MailService;
import com.packt.cookbook.service.UserService;

@Controller
@RequestMapping(value = "user")
public class UserController {
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> login(@RequestBody User login){
		Map<String, Object> mapForReponse = new HashMap<>();
		if(login.getName() != null && login.getPassword() !=null){
			User user = userService.login(login);
			
			if(user != null){
				user.setPassword(null);
				user.setTimeToken(null);
				mapForReponse.put("success", true);
				mapForReponse.put("user", user);
			} else {
				mapForReponse.put("success", false);
				mapForReponse.put("error", "wrong data");
			}
		} else {
			mapForReponse.put("success", false);
			mapForReponse.put("error", "empty");
		}
		
		return new ResponseEntity<Map<String, Object>>(mapForReponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> register(@RequestBody User register){
		Map<String, Object> mapForReponse = new HashMap<>();
		HttpStatus httpStatus = HttpStatus.OK;
		if(register.getName()==null){
			mapForReponse.put("success", false);
			mapForReponse.put("error", "empty name");
		} else if(register.getPassword()==null){
			mapForReponse.put("success", false);
			mapForReponse.put("error", "empty password");
		} else if(register.getEmail()==null){
			mapForReponse.put("success", false);
			mapForReponse.put("error", "empty email");			
		} else {
			boolean success = userService.register(register);
			if(success){
				mapForReponse.put("success", success);
				httpStatus = HttpStatus.CREATED;
				Runnable task = () -> {
					mailService.registration(register.getEmail(), register.getName());
				};
				Thread thread = new Thread(task);
				thread.start();
			} else {
				mapForReponse.put("success", success);
				mapForReponse.put("error", "user exist");
			}
		}
		
		return new ResponseEntity<Map<String, Object>>(mapForReponse, httpStatus);
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> logout(@RequestBody User logout){
		Map<String, Object> mapForReponse = new HashMap<>();
		if(logout.getName() != null && logout.getToken() != null)
			mapForReponse.put("success", userService.logout(logout));
		else
			mapForReponse.put("success", false);
		
		return new ResponseEntity<Map<String, Object>>(mapForReponse, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getUser(@RequestBody User user){
		Map<String, Object> mapForReponse = new HashMap<>();
		User user1 = userService.getUser(user);
		if(user1 != null && user1.getToken().equals(user.getToken()) && user1.getLogin() && userService.validToken(user1)){
			mapForReponse.put("success", true);
			user1.setPassword(null);
			user1.setTimeToken(null);
			mapForReponse.put("user", user1);
			mapForReponse.put("access", true);			
		} else {
			mapForReponse.put("success", true);
			mapForReponse.put("access", false);
		}
		
		return new ResponseEntity<Map<String, Object>>(mapForReponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody User update){
		Map<String, Object> mapForReponse = new HashMap<>();
		if(update != null && update.getName()==null){
			mapForReponse.put("success", false);
			mapForReponse.put("error", "empty name");
		} else if(update.getEmail()==null){
			mapForReponse.put("success", false);
			mapForReponse.put("error", "empty email");
		} else if(update.getToken()==null){
			mapForReponse.put("success", false);
			mapForReponse.put("error", "empty token");
		} else {
			User user = new User();
			user.setToken(update.getToken());
			user = userService.getUser(user);
			boolean token = userService.validToken(user);
			if(user != null && token){
				update.setTimeToken(LocalDateTime.now());
				if(update.getPassword()==null)
					update.setPassword(user.getPassword());
				if(update.getListOfRole().size()==0)
					update.setListOfRole(user.getListOfRole());
				update.setId_u(user.getId_u());
				update.setLogin(user.getLogin());
				boolean success = userService.updateUser(update);
				if(success)
					mapForReponse.put("success", success);
				else {
					mapForReponse.put("access", false);
					mapForReponse.put("success", success);
					mapForReponse.put("error", "not save");
				}
			} else {
				if(!token){
					mapForReponse.put("success", false);
					mapForReponse.put("access", false);
					mapForReponse.put("error", "token expired");
				} else {
					mapForReponse.put("success", true);
					mapForReponse.put("access", false);
					mapForReponse.put("error", "user not exist");
				}
			}
		}
		
		return new ResponseEntity<Map<String, Object>>(mapForReponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> restartPassword(@RequestBody User password){
		Map<String, Object> mapForReponse = new HashMap<>();
		if(password != null && password.getEmail()!=null){
			mapForReponse.put("access", true);
			User user = userService.getUser(password);
			if(user != null && userService.restartPassword(user)){
				Runnable task = () -> {
					mailService.restartPassword(user);
				};
				Thread thread = new Thread(task);
				thread.start();
				mapForReponse.put("success", true);
			} else {
				mapForReponse.put("success", false);
				mapForReponse.put("error", "email not exist");
			}
		} else {
			mapForReponse.put("access", false);
			mapForReponse.put("success", false);
			mapForReponse.put("error", "empty emial");
		}
		
		return new ResponseEntity<Map<String, Object>>(mapForReponse, HttpStatus.OK);
	}
}
