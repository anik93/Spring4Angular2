package com.packt.cookbook.domain;

import java.io.Serializable;

public class Role implements Serializable{

	private static final long serialVersionUID = -1606966327904797675L;

	private Integer id_r;
	
	private String name;
	
	public Role(){
		
	}
	
	public Integer getId_r() {
		return id_r;
	}

	public void setId_r(Integer id_r) {
		this.id_r = id_r;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
