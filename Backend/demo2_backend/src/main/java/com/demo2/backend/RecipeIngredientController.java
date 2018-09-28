package com.demo2.backend;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo2.backend.User;
import com.demo2.backend.UserRepository;

@Controller
@RequestMapping(path="/{recipe_id}/recipes")
public class RecipeIngredientController {
	@Autowired
	private RecipeIngredientRepository RI_Repo;
	
	@Autowired
	private RecipeRepository R_Repo;
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewIngredient (@RequestParam String name) {
		RecipeIngreient n = new User();
		n.setName(name);
		RRepo.save(n);
		return n.getID().toString();
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllRecipes() {
		// This returns a JSON or XML with the users
		return RRepo.findAll();
	}
	
	@GetMapping(path="/get_by_id")
	public @ResponseBody String get_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		Optional<User> u = RRepo.findById(i);
		return u.get().getName();
	}
	
	
}
