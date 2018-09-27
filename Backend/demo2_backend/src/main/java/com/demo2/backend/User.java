package com.demo2.backend;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private String name;
	
	public void setName (String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getID() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
