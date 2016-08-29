package com.packt.cookbook.service;

import java.util.List;

import com.packt.cookbook.domain.Recipe;

public interface RecipeService {

	Recipe getRecipe(int id_r);

	List<Recipe> getAllRecipe(int page, int size);

}
