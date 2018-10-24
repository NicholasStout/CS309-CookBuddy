package com.demo2.backend;

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

import com.demo2.backend.User;
import com.demo2.backend.UserRepository;

@Controller
@RequestMapping(path="/recipes")
public class RecipeController {
	@Autowired
	private RecipeRepository RRepo;
	@Autowired
	private UserRepository URepo;
	
	@PostMapping(path= "/{user_id}/add", consumes = "application/json")
	public @ResponseBody int addNewRecipe (@PathVariable (value = "user_id") int userID, @RequestBody Recipe recipe) {
		Optional<User> u = URepo.findById(userID);
		recipe.setUser(u.get());
		RRepo.save(recipe);
		return recipe.getId();
	}
	
	@GetMapping(path="/{user_id}/all")
	public @ResponseBody Iterable<Recipe> getRecipesByUserId(@PathVariable (value = "user_id") int userID) {
		return RRepo.findByUserId(userID);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Recipe> getAllRecipes() {
		// This returns a JSON or XML with the users
		return RRepo.findAll();
	}
	
	@GetMapping(path="/get_by_id")
	public @ResponseBody String get_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		Optional<Recipe> u = RRepo.findById(i);
		return u.get().getName();
	}
	
	@GetMapping(path="/remove_by_id{id}")
	public @ResponseBody String deleteRecipe(@RequestParam String id) {
		int n = Integer.parseInt(id);
		RRepo.deleteById(n);
		return "removal successful";
	}
	
}
