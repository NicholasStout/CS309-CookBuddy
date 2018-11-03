package com.demo2.backend;

import java.util.HashMap;
import java.util.Map;

public class Response {
	public static Map<String, String> failed () {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("Error", "309");
		ret.put("Response", "Entry Refused");
		return ret;
	}
	
	public static Map<String, String> success (int i) {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("Error", "0");
		ret.put("Response", "Success!");
		ret.put("user_id", new Integer(i).toString());
		return ret;
	}
	
	public static Map<String, String> user (User u) {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("Error", "0");
		ret.put("name", u.getName());
		ret.put("id", new Integer(u.getID()).toString());
		return ret;
	}
	
	public static Map<String, String> recipe (Recipe r) {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("Error", "0");
		ret.put("recipe_id",  new Integer(r.getId()).toString());
		ret.put("recipe_name", r.getRecipeName());
		ret.put("instructions", r.getInstructions());
		ret.putAll(user(r.getUser()));
		return ret;
	}
}
