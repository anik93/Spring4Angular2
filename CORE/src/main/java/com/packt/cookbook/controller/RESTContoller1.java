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
import org.springframework.web.bind.annotation.ResponseBody;

import com.packt.cookbook.domain.User;
import com.packt.cookbook.service.UserService;

@Controller
@RequestMapping(value = "rest1")
public class RESTContoller1 {
	
	private static final Logger log = LoggerFactory.getLogger(RESTContoller1.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> test(@RequestBody User user){
		Map<String, Object> mapForReponse = new HashMap<>();
		boolean exist = userService.login(user);
		mapForReponse.put("success", exist);
		
		if(exist)
			mapForReponse.put("user", userService.getUser(user));
		return new ResponseEntity<Map<String, Object>>(mapForReponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User test1(){
		return new User("name","pass","token");
	}
}
