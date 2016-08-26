package com.packt.cookbook.repository;

import java.util.List;

import com.packt.cookbook.domain.Recipe;

public interface RecipeRepository {

	Recipe getRecipe(int id_r);

	List<Recipe> getAllRecipe();

}
