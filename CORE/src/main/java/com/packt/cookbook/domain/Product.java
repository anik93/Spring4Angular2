package com.packt.cookbook.domain;

import java.io.Serializable;

public class Product implements Serializable{

	private static final long serialVersionUID = 6350610688774296147L;

	private int id_p;
	
	private String name;

	public int getId_p() {
		return id_p;
	}

	public void setId_p(int id_p) {
		this.id_p = id_p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
