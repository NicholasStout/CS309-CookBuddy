package com.sharedObjects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

public class PostUser {
	private String name;
	private String email;
	private String password;
	
	public void setName (String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setemail (String n) {
		email = n;
	}
	
	public String getemail() {
		return email;
	}
	
	public void setPassword(String p) {
		password = p;
	}
}
