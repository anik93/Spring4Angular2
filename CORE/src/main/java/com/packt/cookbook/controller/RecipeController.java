package com.packt.cookbook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packt.cookbook.domain.Recipe;
import com.packt.cookbook.service.RecipeService;

@Controller
@RequestMapping(value = "recipe")
public class RecipeController {
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(RecipeController.class);
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getRecipe(@RequestBody Recipe recipe){
		Map<String, Object> mapForReponse = new HashMap<>();
		if(recipe != null && recipe.getId_re()>0){
			Recipe newRecipe = recipeService.getRecipe(recipe.getId_re());
			if(newRecipe != null){
				mapForReponse.put("success", true);
				mapForReponse.put("recipe", recipe);
			} else {
				mapForReponse.put("success", false);
				mapForReponse.put("error", "recipe not exist");
			}
		} else {
			mapForReponse.put("success", false);
			mapForReponse.put("error", "wrong data");
		}
		return new ResponseEntity<Map<String, Object>>(mapForReponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "filtr", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getRecipes(@RequestBody Map<String, List<String>> mapOfFilters){
		Map<String, Object> mapForReponse = new HashMap<>();
		if(mapOfFilters.isEmpty()){
			mapForReponse.put("recipes", recipeService.getAllRecipe());
			mapForReponse.put("success", true);
		} else 
			mapForReponse.put("success", false);
		return new ResponseEntity<Map<String, Object>>(mapForReponse, HttpStatus.OK);
	}
	
}
