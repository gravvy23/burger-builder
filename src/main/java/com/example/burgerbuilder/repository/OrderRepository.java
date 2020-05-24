package com.example.burgerbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.burgerbuilder.model.Order;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Page<Order> findByUserId(String userId, Pageable pageable);
	Optional<Order> findByIdAndUserId (Long id, String userId);
}