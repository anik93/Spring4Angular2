package com.packt.cookbook.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter {

	private int page;
	
	private int size;
	
	private Map<String , List<String>> listOfFilters = new HashMap<>();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Map<String , List<String>> getListOfFilters() {
		return listOfFilters;
	}

	public void setListOfFilters(Map<String , List<String>> listOfFilters) {
		this.listOfFilters = listOfFilters;
	}
	
	
}
