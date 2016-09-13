package com.packt.cookbook.domain;

import java.util.HashMap;
import java.util.Map;

public class Filter {
	
	private String name;

	private int page;
	
	private int size;
	
	private Map<String , Object> listOfFilters = new HashMap<>();
	
	private boolean fuzzy;
	
	private SortType sort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
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

	public Map<String , Object> getListOfFilters() {
		return listOfFilters;
	}

	public void setListOfFilters(Map<String , Object> listOfFilters) {
		this.listOfFilters = listOfFilters;
	}

	public boolean getFuzzy() {
		return fuzzy;
	}

	public void setFuzzy(boolean fuzzy) {
		this.fuzzy = fuzzy;
	}

	public SortType getSort() {
		return sort;
	}

	public void setSort(SortType sort) {
		this.sort = sort;
	}	
	
}
