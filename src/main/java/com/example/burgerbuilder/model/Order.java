package com.example.burgerbuilder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	public void setPrice(Double newValue) {
		this.price = newValue;
	};
	
	public Double getPrice() {
		return this.price;
	}
	
	public void setId(Long newValue) {
		this.id = newValue;
	};
	
	public Long getId() {
		return this.id;
	};
	
	public void setUser(User newValue) {
		this.user = newValue;
	};
	
	public User getUser() {
		return this.user;
	};
	
	public void setIngredients(List<OrderIngredient> newValue) {
		this.ingredients = newValue;
	};
	
	public List<OrderIngredient> getIngredients() {
		return this.ingredients;
	};
}
