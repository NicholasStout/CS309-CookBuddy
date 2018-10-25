package com.demo2.backend;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo2.backend.User;
import com.demo2.backend.UserRepository;

@Controller
@RequestMapping(path="/users")
public class AppController {
	@Autowired
	private UserRepository uRepo;
	
	@PostMapping(path="/add", consumes = "application/json")
	public @ResponseBody int addNewUser (@RequestBody User user) {
		uRepo.save(user);
		return user.getID();
	}
	
	@PostMapping(path="/sign_in", consumes = "application/json")
	public @ResponseBody User signIn (@RequestBody User user) {
		Iterable<User>iter = uRepo.findAll();
		boolean found = false;
		User tmp;
		for (User u : iter) {
			if ((u.getName().equals(user.getName()) || u.getEmail().equals(user.getEmail())) && (u.getPassword().equals(user.getPassword()))) {
				found = true;
				user = u;
				break;
			}
		}
		if (found)
			return user;
		else
			tmp = new User();
			tmp.setName("No sign in");
			return tmp;
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return uRepo.findAll();
	}
	
	@GetMapping(path="/get_by_id")
	public @ResponseBody String get_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		Optional<User> u = uRepo.findById(i);
		return u.get().getName();
	}
	
	@GetMapping(path="/remove_by_id")
	public @ResponseBody String remove_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		uRepo.deleteById(i);
		return "deletion successful";
	}
}
