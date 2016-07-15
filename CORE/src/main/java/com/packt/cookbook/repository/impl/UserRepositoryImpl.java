package com.packt.cookbook.repository.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.cookbook.controller.LoginController;
import com.packt.cookbook.domain.User;
import com.packt.cookbook.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
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
			saveToken(user);
		}
        session.close();
		return user;
	}
	
	private void saveToken(User test){
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
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(register); 
			session.getTransaction().commit();
			return true;
		} catch (HibernateError e){
			session.getTransaction().rollback();
			log.error("Error when save user {}",e);
		} finally {
			session.close();
		}
		return false;
	}
	
	private Boolean validToken(User userToken) {
		User user = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) session.createQuery("from User").list();
		session.close();
		for(User x: users){
			if(x.getName().equals(userToken.getName())){
				user = x;
			}
		}
		LocalDate now = LocalDate.now();
		if(userToken.getToken().equals(user.getToken()) && ChronoUnit.HOURS.between(user.getTimeToken(), now)<=2){
			user.setToken(UUID.randomUUID().toString());
			saveToken(user);
			return true;
		} else {
			return false;
		}
        
		
	}
}
