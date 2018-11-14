package com.demo2.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
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
	public @ResponseBody Map<String, String> addNewItem (@PathVariable (value = "user_id") int userID, @RequestBody Item n) {
		Optional<User> u = U_Repo.findById(userID);
		if(!u.isPresent()) {
			return Response.failed();
		}
		n.setUser(u.get());
		I_Repo.save(n);
		return Response.item(n);
	}
	
	@PostMapping(path="/items/{user_id}/addall", consumes= "application/json")
	public @ResponseBody Map<String, String> addNewItems (@PathVariable (value = "user_id") int userID, @RequestBody List<Item> n) {
		Optional<User> r = U_Repo.findById(userID);
		ListIterator<Item> iter = n.listIterator();
		if (!iter.hasNext() || !r.isPresent()) {
			return Response.failed();
		}
		while (iter.hasNext()) {
			Item tmp = iter.next();
			tmp.setUser(r.get());
			I_Repo.save(tmp);
		}
		return Response.success(1);
	}
	
	@GetMapping(path="/items/{user_id}/all")
	public @ResponseBody ArrayList<Map<String,String>> getAllItems(@PathVariable (value="user_id") int user_id) {
		// This returns a JSON or XML with the users
		Iterable<Item> items = I_Repo.findByUserId(user_id);
		ArrayList<Map<String,String>> ret = new ArrayList<Map<String,String>>();
		Iterator<Item> i =items.iterator();
		if (!i.hasNext()) {
			ret.add(Response.failed());
			return ret;
		}
		while (i.hasNext()) {
			ret.add(Response.item(i.next()));
		}
		return ret;
		//return I_Repo.findAll();
	}
	
	@GetMapping(path="/items/{item_id}/get_by_id")
	public @ResponseBody Map<String, String> get_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		Optional<Item> u = I_Repo.findById(i);
		if (u.isPresent())
			return Response.item(u.get());
		return Response.failed();
	}
	
	@GetMapping(path="/items/{item_id}/remove_by_id")
	public @ResponseBody Map<String, String> remove_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		I_Repo.deleteById(i);
		return Response.success(1);
	}
	
}
