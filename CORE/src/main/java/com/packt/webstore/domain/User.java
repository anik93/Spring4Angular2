package com.packt.webstore.domain;

import java.util.HashSet;
import java.util.Set;

public class User {
	
	private String userName;
	private String password;
	private String token;
	private Set<Role> listOfRole = new HashSet<>();
	
	public User(){
		
	}
	
	public User(String userName, String password, String token) {
		this.userName=userName;
		this.password=password;
		this.token=token;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
