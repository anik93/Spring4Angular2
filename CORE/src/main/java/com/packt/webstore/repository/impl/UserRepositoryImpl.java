package com.packt.webstore.repository.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Role;
import com.packt.webstore.domain.User;
import com.packt.webstore.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{

	private Set<User> listOfUser = new HashSet<>();
	
	public UserRepositoryImpl() {
		User user = new User("name","pass","token");
		Set<Role> listOfRole = new HashSet<>();
		listOfRole.add(new Role(2134,"testRole"));
		listOfRole.add(new Role(5466,"Roletest"));
		user.setListOfRole(listOfRole);
		listOfUser.add(user);
	}
	
	@Override
	public boolean login(User user) {
		boolean exist = false;
		for(User users: listOfUser){
			if(users.getUserName().equals(user.getUserName()) && users.getPassword().equals(user.getPassword()) && users.getToken().equals(user.getToken())){
				exist = true;
				break;
			}
		}
		return exist;
	}

	@Override
	public User getUser(User user) {
		for(User users: listOfUser){
			if(users.getUserName().equals(user.getUserName()) && users.getPassword().equals(user.getPassword()) && users.getToken().equals(user.getToken())){
				return users;
			}
		}
		return new User();
	}

}
