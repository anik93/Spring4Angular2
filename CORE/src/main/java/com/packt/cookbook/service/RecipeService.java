package com.packt.cookbook.service;

import java.util.List;

import com.packt.cookbook.domain.Filter;
import com.packt.cookbook.domain.Product;
import com.packt.cookbook.domain.Recipe;

public interface RecipeService {

	Recipe getRecipe(int id_r);

	List<Recipe> getAllRecipe(Filter filter);

	List<String> getNameLike(String name);

	List<Product> getAllProducts();

}
