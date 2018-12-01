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

@Entity
@Table(name="Recipes")
public class Recipe {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	private String recipeName;
	
	private String instructions;
	
	
	public void setUser (User u) {
		user = u;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setRecipeName (String n) {
		recipeName = n;
	}
	
	public String getRecipeName() {
		return recipeName;
	}
	
	public void setInstructions (String n) {
		instructions = n;
	}
	
	public String getInstructions() {
		return instructions;
	}
	
	public int getId() {
		return id;
	}
	
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}
	
}
