package com.packt.cookbook.domain;

import java.io.Serializable;

public class Section implements Serializable{

	private static final long serialVersionUID = 208165341586699318L;
	
	private int id_s;

	private String text;

	public int getId_s() {
		return id_s;
	}

	public void setId_s(int id_s) {
		this.id_s = id_s;
	}

	public String getText() {
		return text;
	}
 
	public void setText(String text) {
		this.text = text;
	}
}
