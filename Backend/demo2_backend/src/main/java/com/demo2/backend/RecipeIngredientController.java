package com.demo2.backend;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecipeIngredientController {
	@Autowired
	private RecipeIngredientRepository RI_Repo;
	
	@Autowired
	private RecipeRepository R_Repo;
	
	@PostMapping(path="/recipesIng/{recipe_id}/add", consumes= "application/json")
	public @ResponseBody int addNewIngredient (@PathVariable (value = "recipe_id") int recipeID, @RequestBody RecipeIngredient n) {
		Optional<Recipe> r = R_Repo.findById(recipeID);
		n.setRecipe(r.get());
		RI_Repo.save(n);
		return n.getId();
	}
	
	@GetMapping(path="/recipesIng/{recipe_id}/all")
	public @ResponseBody Iterable<RecipeIngredient> getAllIngredients(@PathVariable (value="recipe_id") int recipe_id) {
		// This returns a JSON or XML with the users
		return RI_Repo.findByRecipeId(recipe_id);
		//return RI_Repo.findAll();
	}
	
	@GetMapping(path="/recipesIng/{recipe_id}/get_by_id")
	public @ResponseBody String get_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		Optional<RecipeIngredient> u = RI_Repo.findById(i);
		return u.get().getName();
	}
	
	@GetMapping(path="/recipesIng/{recipe_id}/remove_by_id")
	public @ResponseBody String remove_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		RI_Repo.deleteById(i);
		return "deletion successful";
	}
	
}
