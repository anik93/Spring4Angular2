package com.packt.cookbook.repository.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packt.cookbook.domain.User;
import com.packt.cookbook.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	@Override
	public User login(User login) {
		User user = getUser(login);
		if(user != null){
			user.setToken(UUID.randomUUID().toString());
			user.setTimeToken(LocalDateTime.now());
			user.setLogin(true);
			updateUser(user);
		}
		return user;
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
		} catch (Exception e){
			session.getTransaction().rollback();
			log.error("Error when save user ",e);
		} finally {
			session.close();
		}
		return false;
	}
	
	@Override
	public Boolean logout(User logout) {
		User user = getUser(logout);
		if(user != null && user.getToken() != null){
			user.setTimeToken(null);
			user.setToken(null);
			user.setLogin(false);
			updateUser(user);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private User getUser(User userToFind){
		User user = null;
		Session session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(User.class);
		List<User> listOfUser = new ArrayList<>(0);
		if(userToFind.getName() != null && userToFind.getPassword() != null){
			cr.add(Restrictions.eq("name", userToFind.getName()));
			cr.add(Restrictions.eq("password", userToFind.getPassword()));		
		} else if(userToFind.getToken() != null){
			cr.add(Restrictions.eq("token", userToFind.getToken()));
		}
		listOfUser = cr.list();
		if(!listOfUser.isEmpty())
			user = listOfUser.get(0);
		return user;
	}
	
	private void updateUser(User test){
		User user = new User();
		user.setId_u(test.getId_u());
		user.setName(test.getName());
		user.setPassword(test.getPassword());
		user.setEmail(test.getEmail());			
		user.setToken(test.getToken());
		user.setLogin(test.getLogin());
		user.setTimeToken(test.getTimeToken());		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user); 
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unused")
	private Boolean validToken(User userToken) {
		User user = getUser(userToken);
		if(user != null && userToken.getToken().equals(user.getToken()) && ChronoUnit.HOURS.between(user.getTimeToken(), LocalDateTime.now())<=2){
			user.setTimeToken(LocalDateTime.now());
			updateUser(user);
			return true;
		}
		return false;
	}
}
