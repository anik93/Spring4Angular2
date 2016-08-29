package com.packt.cookbook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.cookbook.domain.Filter;
import com.packt.cookbook.domain.Recipe;
import com.packt.cookbook.repository.RecipeRepository;
import com.packt.cookbook.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{

	@Autowired
	private RecipeRepository recipeRepository;
	
	@Override
	public Recipe getRecipe(int id_r) {
		return recipeRepository.getRecipe(id_r);
	}

	@Override
	public List<Recipe> getAllRecipe(Filter filter) {
		return recipeRepository.getAllRecipe(filter);
	}

}
