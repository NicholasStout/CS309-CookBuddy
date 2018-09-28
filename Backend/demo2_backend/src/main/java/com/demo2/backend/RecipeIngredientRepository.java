package com.demo2.backend;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo2.backend.RecipeIngredient;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Integer> {
	List<RecipeIngredient> findByRecipeId(int RecipeId);
}