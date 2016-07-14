package com.packt.cookbook.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id_u;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String token;
	
	private Set<Role> listOfRole = new HashSet<>();
	
	public User(){
		
	}
	
	public Integer getId_u() {
		return id_u;
	}

	public void setId_u(Integer id_u) {
		this.id_u = id_u;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public Set<Role> getListOfRole() {
		return listOfRole;
	}

	public void setListOfRole(Set<Role> listOfRole) {
		this.listOfRole = listOfRole;
	}
	
}
