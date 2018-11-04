package com.demo2.backend;

import java.util.List;
import java.util.ListIterator;
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
public class ItemController {
	@Autowired
	private ItemRepository I_Repo;
	
	@Autowired
	private UserRepository U_Repo;
	
	@PostMapping(path="/items/{user_id}/add", consumes= "application/json")
	public @ResponseBody int addNewIngredient (@PathVariable (value = "user_id") int userID, @RequestBody Item n) {
		Optional<User> u = U_Repo.findById(userID);
		n.setUser(u.get());
		I_Repo.save(n);
		return n.getId();
	}
	
	@PostMapping(path="/items/{user_id}/addall", consumes= "application/json")
	public @ResponseBody int addNewIngredients (@PathVariable (value = "user_id") int userID, @RequestBody List<Item> n) {
		Optional<User> r = U_Repo.findById(userID);
		ListIterator<Item> iter = n.listIterator();
		while (iter.hasNext()) {
			Item tmp = iter.next();
			tmp.setUser(r.get());
			I_Repo.save(tmp);
		}
		return 1;
	}
	
	@GetMapping(path="/items/{user_id}/all")
	public @ResponseBody Iterable<Item> getAllIngredients(@PathVariable (value="user_id") int user_id) {
		// This returns a JSON or XML with the users
		return I_Repo.findByUserId(user_id);
		//return I_Repo.findAll();
	}
	
	@GetMapping(path="/items/{item_id}/get_by_id")
	public @ResponseBody String get_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		Optional<Item> u = I_Repo.findById(i);
		return u.get().getName();
	}
	
	@GetMapping(path="/items/{item_id}/remove_by_id")
	public @ResponseBody String remove_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		I_Repo.deleteById(i);
		return "deletion successful";
	}
	
}
