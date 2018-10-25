package com.demo2.backend;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@NonNull
    private int id;
	
	private String name;
	private String email;
	private String password;
	
	public void setName (String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setEmail(String e) {
		email = e;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String p) {
		password = p;
	}
	
	public String getPassword() {
		return password;
	}
}
