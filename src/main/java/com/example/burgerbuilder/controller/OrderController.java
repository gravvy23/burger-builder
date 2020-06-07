package com.example.burgerbuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.burgerbuilder.model.Order;
import com.example.burgerbuilder.model.Ingredient;
import com.example.burgerbuilder.model.OrderIngredient;
import com.example.burgerbuilder.repository.IngredientRepository;
import com.example.burgerbuilder.repository.OrderIngredientRepository;
import com.example.burgerbuilder.repository.OrderRepository;
import com.example.burgerbuilder.repository.UserRepository;
import com.example.burgerbuilder.exception.ResourceNotFoundException;

/**
 * Order Controller
 */
@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private OrderIngredientRepository orderIngredientRepository;

	/**
	 * GET /api/users/{userId}/orders
	 * get all user orders
	 * @param userId ID of the user
	 * @param pageable
	 * @return
	 */
	@GetMapping("users/{userId}/orders")
	public Page<Order> getAllOrdersByUser(@PathVariable(value = "userId") String userId, Pageable pageable) {
		return orderRepository.findByUserId(userId, pageable);
	};
	
	/**
	 * GET /api/users/{userId}/orders/{orderId}/ingredients
	 * get ingredients of the specific order
	 * @param orderId
	 * @param pageable
	 * @return
	 */
	@GetMapping("users/{userId}/orders/{orderId}/ingredients")
	public Page<OrderIngredient> getOrderById(@PathVariable(value = "orderId") Long orderId, Pageable pageable) {
		return orderIngredientRepository.findAllByOrderId(orderId, pageable);
	};

	/**
	 * POST /api/users/{userId}/orders
	 * create new order 
	 * @param userId ID of the user who's the owner of order
	 * @param order Order to be created
	 * @return
	 */
	@PostMapping("users/{userId}/orders")
    public Order createOrder(@PathVariable (value = "userId") String userId, @RequestBody Order order) {
		Order resultOrder = userRepository.findById(userId).map(user -> {
			order.setUser(user);
    		return orderRepository.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
		order.getIngredients().forEach(ingredient -> {
			Ingredient ing = ingredientRepository.findById(ingredient.getUid()).orElseThrow(() -> new ResourceNotFoundException("not such ingredient"));
			ingredient.setIngredient(ing);
			ingredient.setOrder(resultOrder);
			orderIngredientRepository.save(ingredient);
		});
		return resultOrder;
    };

	/**
	 * PUT api/users/{userId}/orders/{orderId}
	 * update the order
	 * @param userId ID of user who's the owner of the order
	 * @param orderId ID of order to be updated
	 * @param orderRequest updated order
	 * @return
	 */
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
	
	/**
	 * DELETE api/users/{userId}/orders/{orderId}
	 * delete the order
	 * @param userId ID of the user who's the owner of order
	 * @param orderId ID of the order to be deleted
	 * @return
	 */
	@DeleteMapping("users/{userId}/orders/{orderId}")
	public ResponseEntity<?> deletePost(@PathVariable (value = "userId") String userId,
			 							@PathVariable (value = "orderId") Long orderId) {
		return orderRepository.findById(orderId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId + " and userId " + userId));
	};
}