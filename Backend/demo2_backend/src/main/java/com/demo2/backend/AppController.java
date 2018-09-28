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
@RequestMapping(path="/users")
public class AppController {
	@Autowired
	private UserRepository uRepo;
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestParam String name) {
		User n = new User();
		n.setName(name);
		uRepo.save(n);
		return n.getID().toString();
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
}