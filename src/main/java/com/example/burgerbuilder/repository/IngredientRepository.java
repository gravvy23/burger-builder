package com.example.burgerbuilder.repository;

import com.example.burgerbuilder.model.Ingredient;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Ingredient repository
 */
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}