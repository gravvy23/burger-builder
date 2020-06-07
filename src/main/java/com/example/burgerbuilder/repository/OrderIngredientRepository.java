package com.example.burgerbuilder.repository;

import com.example.burgerbuilder.model.OrderIngredient;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Order-Ingredient repository
 */
@Repository
public interface OrderIngredientRepository extends JpaRepository<OrderIngredient, Long> {
	/**
	 * Find all entities for provided Order ID
	 * @param orderId
	 * @param pageable
	 * @return
	 */
	public Page<OrderIngredient> findAllByOrderId(Long orderId, Pageable pageable);
}