package com.packt.cookbook.repository;

import java.util.List;

import com.packt.cookbook.domain.Filter;
import com.packt.cookbook.domain.Product;
import com.packt.cookbook.domain.Recipe;

public interface RecipeRepository {

	Recipe getRecipe(int id_r);

	List<Recipe> getAllRecipe(Filter filter);

	List<String> getNameLike(String name);

	List<Product> getAllProducts();

}
