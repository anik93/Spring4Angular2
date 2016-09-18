package com.packt.cookbook.repository.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packt.cookbook.domain.Filter;
import com.packt.cookbook.domain.Product;
import com.packt.cookbook.domain.Recipe;
import com.packt.cookbook.domain.SortType;
import com.packt.cookbook.repository.RecipeRepository;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository{
	
	@SuppressWarnings("unused")
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
		List<Recipe> resault = new ArrayList<>(0);
		cr.add(Restrictions.eq("id_re", id_r));
		cr.add(Restrictions.eq("approve", true));
		resault = cr.list();
		if(!resault.isEmpty())
			recipe = resault.get(0);
		session.close();
		return recipe;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recipe> getAllRecipe(Filter filter) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Recipe.class);
		cr.add(Restrictions.eq("approve", true));

		if(!filter.getListOfFilters().isEmpty())
			for(Entry<String, Object> filters: filter.getListOfFilters().entrySet()){
				if(filters.getKey().equals("products")){
					List<String> listOfProduct = (List<String>) filters.getValue();
					//cr.createCriteria("listOfRecipe_Product").createCriteria("id").createCriteria("product").add(Restrictions.eq("product", 1.0f));
					//cr.createAlias("listOfRecipe_Product", "listPR").createAlias("listPR.id", "idP").createAlias("idP.product", "producrN").add(Restrictions.eqProperty("producrN.name", "produkt1"));
					//cr.createCriteria("listOfRecipe_Product").createCriteria("id").createCriteria("product").add(Restrictions.eq("name", "produkt1"));
				}else if(filters.getKey().equals("rating") || filters.getKey().equals("level") || filters.getKey().equals("time")){
					Map<String, String> mapa = (Map<String, String>) filters.getValue();
					if(mapa.get("from") != null && mapa.get("to") != null)
						cr.add(Restrictions.between(filters.getKey(), Float.parseFloat(mapa.get("from")), Float.parseFloat(mapa.get("to"))));
					else if(mapa.get("to") != null && mapa.get("from") == null)
						cr.add(Restrictions.lt(filters.getKey(), Float.parseFloat(mapa.get("to"))));
					else if(mapa.get("from") != null && mapa.get("to") == null)
						cr.add(Restrictions.gt(filters.getKey(), Float.parseFloat(mapa.get("from"))));
				} 
			}
		if(filter.getName() != null)
			if(filter.getFuzzy())
				cr.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));						
			else
				cr.add(Restrictions.eq("name", filter.getName()));
		
		if(filter.getSort() != null && filter.getSort().equals(SortType.ASC))
			cr.addOrder(Order.asc("name"));
		else if(filter.getSort() != null && filter.getSort().equals(SortType.DESC))
			cr.addOrder(Order.desc("name"));
		
		Recipe recipe = new Recipe();
		recipe.setId_re(cr.list().size());
		
		if(filter.getPage()>0 && filter.getSize()>9){
			cr.setFirstResult((filter.getPage() - 1) * filter.getSize());
			cr.setMaxResults(filter.getSize());
		}
		List<Recipe> listOfRecipe = cr.list();
		session.close();
		listOfRecipe.add(recipe);
		return listOfRecipe;
	}

	@Override
	public List<String> getNameLike(String name) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Recipe.class);
		cr.add(Restrictions.eq("approve", true));
		cr.add(Restrictions.ilike("name", name+"%"));
		cr.setMaxResults(10);
		cr.addOrder(Order.asc("name"));
		@SuppressWarnings("unchecked")
		List<Recipe> resault = cr.list();
		session.close();
		List<String> listOfName = new LinkedList<>();
		resault.forEach(x -> listOfName.add(x.getName()));
		return listOfName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Product.class);
		return ((List<Product>)cr.list());
	}

}
