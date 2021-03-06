package com.demo2.backend;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="Ingredients")
public class RecipeIngredient {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;	
	
	private String name;	
	
	private String amount;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "recipe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Recipe recipe;
	
	private int user_id = 0;
	
	public void setName (String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAmount (String i) {
		amount = i;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public int getId() {
		return id;
	}
	
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}
	
	public Recipe getRecipe () {
		return recipe;
	}
	
	public void setRecipe (Recipe r) {
		recipe = r;
	}
}
