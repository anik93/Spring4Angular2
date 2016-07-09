package com.packt.webstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "rest1")
public class RESTContoller1 {
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Test> test(HttpServletRequest request){
		List<Test> listOfTest = new ArrayList<>();
		List<Integer> listOfInteger = new ArrayList<>();
		List<Integer> listOfInteger1 = new ArrayList<>();
		Test test = new Test();
		
		listOfInteger.add(21);
		listOfInteger.add(21);
		listOfInteger.add(21);
		
		test.setName("tak");
		test.setListOfInteger(listOfInteger);
		listOfTest.add(test);
		Test test1 = new Test();
		listOfInteger1.add(31);
		listOfInteger1.add(31);
		listOfInteger1.add(31);
		test1.setName("test1");
		test1.setListOfInteger(listOfInteger1);
		listOfTest.add(test1);
		return listOfTest;
	}
}
