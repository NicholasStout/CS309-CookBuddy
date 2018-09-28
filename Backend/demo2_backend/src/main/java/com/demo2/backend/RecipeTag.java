package com.demo2.backend;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RecipeTag {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;	
	private String recipeName;	
	private List<String> recipeIngredients;	
	private List<String> recipeTags;
	
	public void setName (String n) {
		recipeName = n;
	}
	
	public String getName() {
		return recipeName;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<String> getRecipeIngredients () {
		return recipeIngredients;
	}
	
	public void addRecipeIngredient (String i) {
		recipeIngredients.add(i);
	}
}
