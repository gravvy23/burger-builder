package com.example.burgerbuilder.repository;

import com.example.burgerbuilder.model.OrderIngredient;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderIngredientRepository extends JpaRepository<OrderIngredient, Long> {
	public Page<OrderIngredient> findAllByOrderId(Long orderId, Pageable pageable);
}