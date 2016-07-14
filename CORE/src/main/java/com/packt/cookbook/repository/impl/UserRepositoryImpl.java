package com.packt.cookbook.repository.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.cookbook.domain.User;
import com.packt.cookbook.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public User login(User login) {
		User user = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) session.createQuery("from User").list();
		for(User x: users){
			if(x.getName().equals(login.getName()) && x.getPassword().equals(login.getPassword())){
				user = x;
			}
		}
		if(user != null && user.getName() != null){
			user.setToken(UUID.randomUUID().toString());
			setToken(user);
		}
        session.close();
		return user;
	}
	
	private void setToken(User test){
		User user = new User();
		user.setEmail(test.getEmail());
		user.setId_u(test.getId_u());
		user.setName(test.getName());
		user.setPassword(test.getPassword());
		user.setToken(test.getToken());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user); 
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Boolean register(User register) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(register); 
		session.getTransaction().commit();
		session.close();
		return true;
	}
}
