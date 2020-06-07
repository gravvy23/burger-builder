package com.example.burgerbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.burgerbuilder.model.Order;
import java.util.Optional;

/**
 * Order repository
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	/**
	 * Find all entities for provided User ID
	 * @param userId
	 * @param pageable
	 * @return
	 */
	Page<Order> findByUserId(String userId, Pageable pageable);

	/**
	 * Find one Order for provided User ID and Order ID
	 * @param id
	 * @param userId
	 * @return
	 */
	Optional<Order> findByIdAndUserId (Long id, String userId);
}