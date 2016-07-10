package com.packt.cookbook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class RESTController {
	
	private static final Logger log = LoggerFactory.getLogger(RESTController.class);
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, List<Object>>> test(HttpServletRequest request){
		Map<String, List<Object>> mapForJSON = new HashMap<>();
		List<Object> listOfTest = new ArrayList<>();
		List<Integer> listOfInteger = new ArrayList<>();
		List<Integer> listOfInteger1 = new ArrayList<>();
		Test test = new Test();
		listOfInteger.add(2);
		listOfInteger.add(2);
		listOfInteger.add(2);
		
		test.setName("tak");
		test.setListOfInteger(listOfInteger);
		listOfTest.add(test);
		Test test1 = new Test();
		listOfInteger1.add(3);
		listOfInteger1.add(3);
		listOfInteger1.add(3);
		test1.setName("test1");
		test1.setListOfInteger(listOfInteger1);
		listOfTest.add(test1);
		List<Object> listOfString = new ArrayList<>();
		listOfString.add("string1");
		listOfString.add("string2");
		List<Object> listObject = new ArrayList<>();
		listObject.addAll(listOfInteger);
		
		mapForJSON.put("object", listOfTest);
		mapForJSON.put("integer", listObject);
		mapForJSON.put("string", listOfString);

		return new ResponseEntity<Map<String, List<Object>>>(mapForJSON, HttpStatus.OK);
	}
}
