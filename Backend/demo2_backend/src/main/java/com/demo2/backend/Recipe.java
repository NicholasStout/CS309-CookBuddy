package com.demo2.backend;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Recipes")
public class Recipe {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;	
	private String recipeName;
	
	
	public void setName (String n) {
		recipeName = n;
	}
	
	public String getName() {
		return recipeName;
	}
	
	public int getId() {
		return id;
	}
	
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}
	
}
