package com.example.burgerbuilder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model representing one order
 */
@Entity
@Table(name = "orders")
public class Order {

	@Column(columnDefinition = "price")
	private Double price;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "user_id", nullable = false)
	@JsonIgnore
	private User user;
	
//	@ManyToMany(cascade = { CascadeType.ALL })
//	@JoinTable(
//			name = "orders_ingredients",
//			joinColumns =  { @JoinColumn(name = "order_id")},
//			inverseJoinColumns = { @JoinColumn(name = "ingredient_id")}
//	)
//	List<Ingredient> ingredients = new ArrayList<>();
	@OneToMany(mappedBy = "order")
	List<OrderIngredient> ingredients = new ArrayList<>();
	
	/**
	 * order price setter
	 */
	public void setPrice(Double newValue) {
		this.price = newValue;
	};
	
	/**
	 * order price getter
	 * @return order price
	 */
	public Double getPrice() {
		return this.price;
	}
	
	/**
	 * order id setter
	 * @param newValue
	 */
	public void setId(Long newValue) {
		this.id = newValue;
	};
	
	/**
	 * order id getter
	 * @return unique id
	 */
	public Long getId() {
		return this.id;
	};
	
	/**
	 * set user to which order is entitled
	 * @param newValue
	 */
	public void setUser(User newValue) {
		this.user = newValue;
	};
	
	/**
	 * get user to which order is entitled
	 * @return user who owns the order
	 */
	public User getUser() {
		return this.user;
	};
	
	/**
	 * set list of ingredients in order 
	 * @param newValue
	 */
	public void setIngredients(List<OrderIngredient> newValue) {
		this.ingredients = newValue;
	};
	
	/**
	 * get list of ingredients in order
	 * @return list of ingredients in order
	 */
	public List<OrderIngredient> getIngredients() {
		return this.ingredients;
	};
}
