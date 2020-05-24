package com.example.burgerbuilder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.burgerbuilder.model.Order;
import com.example.burgerbuilder.model.OrderIngredient;
import com.example.burgerbuilder.repository.OrderIngredientRepository;
import com.example.burgerbuilder.repository.OrderRepository;
import com.example.burgerbuilder.repository.UserRepository;
import com.example.burgerbuilder.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderIngredientRepository orderIngredientRepository;

	@GetMapping("users/{userId}/orders")
	public Page<Order> getAllOrdersByUser(@PathVariable(value = "userId") String userId, Pageable pageable) {
		return orderRepository.findByUserId(userId, pageable);
	};
	
	@GetMapping("users/{userId}/orders/{orderId}/ingredients")
	public Page<OrderIngredient> getOrderById(@PathVariable(value = "orderId") Long orderId, Pageable pageable) {
		return orderIngredientRepository.findAllByOrderId(orderId, pageable);
	};

	@PostMapping("users/{userId}/orders")
    public Order createOrder(@PathVariable (value = "userId") String userId, @RequestBody Order order) {
    	return userRepository.findById(userId).map(user -> {
    		order.setUser(user);
    		return orderRepository.save(order);
    	}).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    };

	@PutMapping("users/{userId}/orders/{orderId}")
    public Order updateOrder(@PathVariable (value = "userId") String userId,
    						 @PathVariable (value = "orderId") Long orderId,
    						 @RequestBody Order orderRequest) {
		if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
		
		return orderRepository.findById(orderId).map(order -> {
            order.setPrice(orderRequest.getPrice());
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + "not found"));
	};
	
	@DeleteMapping("users/{userId}/orders/{orderId}")
	public ResponseEntity<?> deletePost(@PathVariable (value = "userId") String userId,
			 							@PathVariable (value = "orderId") Long orderId) {
		return orderRepository.findById(orderId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId + " and userId " + userId));
	};
}