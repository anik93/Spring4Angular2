package com.packt.cookbook.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class User implements Serializable{

	private static final long serialVersionUID = -1572102918105879025L;

	private Integer id_u;
	
	private String name;
	
	@SuppressWarnings("deprecation")
	@Length(min=8)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String password;
	
	private String email;
	
	private String token;
	
	private Boolean login;
	
	@SuppressWarnings("deprecation")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private LocalDateTime timeToken;
	
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

	public Boolean getLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}

	public LocalDateTime getTimeToken() {
		return timeToken;
	}

	public void setTimeToken(LocalDateTime timeToken) {
		this.timeToken = timeToken;
	}
	
}
