package com.packt.cookbook.controller;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	private String name;

	private List<Integer> ListOfInteger = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getListOfInteger() {
		return ListOfInteger;
	}

	public void setListOfInteger(List<Integer> listOfInteger) {
		ListOfInteger = listOfInteger;
	}
}
