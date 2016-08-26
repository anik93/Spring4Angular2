package com.packt.cookbook.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packt.cookbook.domain.Recipe;
import com.packt.cookbook.repository.RecipeRepository;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository{
	
	private static final Logger log = LoggerFactory.getLogger(RecipeRepositoryImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public Recipe getRecipe(int id_r) {
		Recipe recipe = null;
		Session session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Recipe.class);
		List<Recipe> listOfRecipe = new ArrayList<>(0);
		cr.add(Restrictions.eq("id_re", id_r));
		listOfRecipe = cr.list();
		if(!listOfRecipe.isEmpty())
			recipe = listOfRecipe.get(0);
		session.close();
		return recipe;
	}

	@Override
	public List<Recipe> getAllRecipe() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Recipe.class);
		@SuppressWarnings("unchecked")
		List<Recipe> listOfRecipe = cr.list();
		session.close();
		return listOfRecipe;
	}
	
	

}
