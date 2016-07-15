package com.packt.cookbook.repository.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.HibernateError;
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
	
	@SuppressWarnings("deprecation")
	@Override
	public User login(User login) {
		User user = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("name", login.getName()));
		cr.add(Restrictions.eq("password", login.getPassword()));
		List<User> listOfUser = cr.list();
		if(!listOfUser.isEmpty()){
			user = listOfUser.get(0);
			user.setToken(UUID.randomUUID().toString());
			LocalDateTime now = LocalDateTime.now();
			user.setTimeToken(now);
			user.setLogin(true);
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
		user.setTimeToken(test.getTimeToken());
		user.setLogin(test.getLogin());
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
		} catch (Exception e){
			session.getTransaction().rollback();
			log.error("Error when save user ",e);
		} finally {
			session.close();
		}
		return false;
	}
	
	private Boolean validToken(User userToken) {
		User user = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("name", userToken.getName()));
		cr.add(Restrictions.eq("password", userToken.getPassword()));
		List<User> listOfUser = cr.list();
		if(!listOfUser.isEmpty()){
			user = listOfUser.get(0);
			LocalDateTime now = LocalDateTime.now();
			if(userToken.getToken().equals(user.getToken()) && ChronoUnit.HOURS.between(user.getTimeToken(), now)<=2){
				user.setToken(UUID.randomUUID().toString());
				saveToken(user);
				return true;
			}	
		} 
		return false;
	}
	
	@Override
	public Boolean logout(User logout) {
		logout.setTimeToken(null);
		logout.setLogin(false);
		saveToken(logout);
		return true;
	}
}
