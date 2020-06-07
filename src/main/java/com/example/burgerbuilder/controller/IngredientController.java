package com.example.burgerbuilder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.burgerbuilder.model.Ingredient;
import com.example.burgerbuilder.repository.IngredientRepository;
import com.example.burgerbuilder.exception.ResourceNotFoundException;

/**
 * Ingredient Controller
 */
@RestController
@RequestMapping("/api")
public class IngredientController {

	@Autowired
	private IngredientRepository ingredientRepository;

	/**
	 * GET /api/ingredients
	 * get all ingredients
	 * @param pageable
	 * @return
	 */
	@GetMapping("/ingredients")
	public Page<Ingredient> getIngredients(Pageable pageable) {
		return ingredientRepository.findAll(pageable);
	}

	/**
	 * POST api/ingredients
	 * save new ingredient
	 * @param ingredient new ingredient
	 * @return
	 */
	@PostMapping("/ingredients")
	public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

	/**
	 * PUT api/ingredients/{id}
	 * update ingredient
	 * @param id ID of the ingredient to be updated
	 * @param ingredientRequest updated ingredient 
	 * @return
	 */
	@PutMapping("/ingredients/{id}")
	public Ingredient updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredientRequest) {
		return ingredientRepository.findById(id).map(ingredient -> {
			ingredient.setDisplayname(ingredientRequest.getDisplayname());
			ingredient.setName(ingredientRequest.getName());
			ingredient.setPrice(ingredientRequest.getPrice());
			return ingredientRepository.save(ingredient);
		}).orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with id " + id));
	}
	
	/**
	 * DELETE api/ingredients/{id}
	 * delete ingredient
	 * @param id ID of the ingredient to be deleted
	 * @return
	 */
	@DeleteMapping("ingredients/{id}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
        return ingredientRepository.findById(id)
                .map(ingredient -> {
                	ingredientRepository.delete(ingredient);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with id " + id));
    }

	/**
	 * GET api/ingredients/{id}
	 * get one ingredient of the provided ID
	 * @param id ID of the ingredient
	 * @return
	 */
	@GetMapping("/ingredients/{id}")
	public Ingredient updateQuestion(@PathVariable Long id) {
		Ingredient entity = ingredientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with id " + id));
		;
		return entity;
	}
}