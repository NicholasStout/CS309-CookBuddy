package com.demo2.backend;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecipeIngredientController {
	@Autowired
	private RecipeIngredientRepository RI_Repo;
	
	@Autowired
	private RecipeRepository R_Repo;
	
	@GetMapping(path="/recipes/{recipe_id}/add")
	public @ResponseBody String addNewIngredient (@PathVariable (value = "recipe_id") int recipeID, @RequestParam String name) {
		Optional<Recipe> r = R_Repo.findById(recipeID);
		RecipeIngredient n = new RecipeIngredient();
		n.setName(name);
		n.setRecipe(r.get());
		RI_Repo.save(n);
		return n.getId().toString();
	}
	
	@GetMapping(path="/recipes/{recipe_id}/all")
	public @ResponseBody Iterable<RecipeIngredient> getAllIngredients(@PathVariable (value="recipe_id") int recipe_id) {
		// This returns a JSON or XML with the users
		return RI_Repo.findByRecipeId(recipe_id);
		//return RI_Repo.findAll();
	}
	
	@GetMapping(path="/recipes/{recipe_id}/get_by_id")
	public @ResponseBody String get_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		Optional<RecipeIngredient> u = RI_Repo.findById(i);
		return u.get().getName();
	}
	
}
