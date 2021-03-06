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
public class Item {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;	
	
	private String name;	
	
	private String notes;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	public void setName (String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setNotes (String i) {
		notes = i;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public int getId() {
		return id;
	}
	
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser () {
		return user;
	}
	
	public void setUser (User u) {
		user = u;
	}
}
