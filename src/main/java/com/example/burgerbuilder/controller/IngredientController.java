package com.example.burgerbuilder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.burgerbuilder.model.Ingredient;
import com.example.burgerbuilder.repository.IngredientRepository;
import com.example.burgerbuilder.exception.ResourceNotFoundException;
 
@RestController
@RequestMapping("/api")
public class IngredientController {
     
    @Autowired
    private IngredientRepository ingredientRepository;
     
    @GetMapping("/ingredients")
    public Page<Ingredient> getIngredients(Pageable pageable) {
        return ingredientRepository.findAll(pageable);
    }
     
    @GetMapping("/ingredients/{id}")
    public Ingredient updateQuestion(@PathVariable Long id) {
        Ingredient entity = ingredientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));;
        return entity;
    }
}