package com.example.burgerbuilder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model for Order - Ingredient relation
 */
@Entity
@Table(name = "orders_ingredients")
public class OrderIngredient {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
    
    /**
     * set unique id
     * @param id
     */
	public void setId(Long id) {
		this.id = id;
	}
    
    /**
     * get unique id
     * @return id
     */
	public Long getId() {
		return this.id;
	}
	
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    
    /**
     * set order
     * @param order
     */
    public void setOrder(Order order) {
    	this.order = order;
    }
 
    /**
     * get order
     * @return order
     */
    public Order getOrder() {
    	return this.order;
    }
    
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    
    /**
     * set ingredient
     * @param ingredient
     */
    public void setIngredient(Ingredient ingredient) {
    	this.ingredient = ingredient;
    }
    
    /**
     * get ingredient
     * @return ingredient
     */
    public Ingredient getIngredient() {
    	return this.ingredient;
    }
 
    @Column(columnDefinition = "position")
    private Integer position;
    
    /**
     * set position of ingredient in burger
     * @param position
     */
    public void setPosition(Integer position) {
    	this.position = position;
    }

    /**
     * get position of ingredient in burger
     * @return
     */
    public Integer getPosition() {
    	return this.position;
    }

    Long uid;
    
    /**
     * set ingredient uid
     * @param uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * get ingredient uid
     * @return ingredient uid
     */
    public Long getUid() {
        return this.uid;
    }
}
